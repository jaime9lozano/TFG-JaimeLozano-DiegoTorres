package es.jaimelozanodiegotorres.backapp.config.auth;

import es.jaimelozanodiegotorres.backapp.interceptors.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
/**
 * Configuración de seguridad
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    public SecurityConfig(UserDetailsService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    /**
     * Configuración de seguridad
     * @param http Petición HTTP
     * @return SecurityFilterChain
     * @throws Exception Excepción
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Podemos decir que forzamos el uso de HTTPS, para algunas rutas de la API o todas
                // Requerimos HTTPS para todas las peticiones, pero ojo que devuelve 302 para los test
                // .requiresChannel(channel -> channel.anyRequest().requiresSecure())

                // Deshabilitamos CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // Sesiones
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                // Lo primero es decir a qué URLs queremos dar acceso libre
                // Lista blanca de comprobación

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/error/**").permitAll()
                        // Abrimos a Swagger -- Quitar en produccion
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Websockets para notificaciones
                        .requestMatchers("/ws/**").hasAnyRole("ADMIN", "WORKER")
                        // Endpoints
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/evaluations/**").permitAll()
                        .requestMatchers("/products/**").permitAll()
                        .requestMatchers("/restaurants/**").permitAll()
                        .requestMatchers("/orders/**").permitAll()
                        .requestMatchers("/offers/**").permitAll()
                        .requestMatchers("/categories/**").permitAll()
                        .requestMatchers("/addresses/**").permitAll()
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/users/me/**").permitAll()
                        .requestMatchers("/app-status/**").permitAll()
                        .anyRequest().authenticated())

                // Añadimos el filtro de autenticación
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Devolvemos la configuración
        return http.build();
    }

    /**
     * Codificador de contraseñas
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Configuración de autenticación
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configuración de autenticación
     * @param config Configuración de autenticación
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
