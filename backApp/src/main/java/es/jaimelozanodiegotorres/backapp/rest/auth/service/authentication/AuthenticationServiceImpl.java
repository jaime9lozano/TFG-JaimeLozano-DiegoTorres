package es.jaimelozanodiegotorres.backapp.rest.auth.service.authentication;

import es.jaimelozanodiegotorres.backapp.rest.auth.dto.JwtAuthResponse;
import es.jaimelozanodiegotorres.backapp.rest.auth.dto.UserSignInRequest;
import es.jaimelozanodiegotorres.backapp.rest.auth.dto.UserSignUpRequest;
import es.jaimelozanodiegotorres.backapp.rest.auth.exceptions.AuthSingInInvalid;
import es.jaimelozanodiegotorres.backapp.rest.auth.exceptions.UserAuthNameOrEmailExisten;
import es.jaimelozanodiegotorres.backapp.rest.auth.exceptions.UserDiferentePasswords;
import es.jaimelozanodiegotorres.backapp.rest.auth.repository.AuthUsersRepository;
import es.jaimelozanodiegotorres.backapp.rest.auth.service.jwt.JwtService;
import es.jaimelozanodiegotorres.backapp.rest.user.models.Role;
import es.jaimelozanodiegotorres.backapp.rest.user.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementación del servicio de autenticación
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthUsersRepository authUsersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(AuthUsersRepository authUsersRepository, PasswordEncoder passwordEncoder,
                                     JwtService jwtService, AuthenticationManager authenticationManager) {
        this.authUsersRepository = authUsersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un usuario
     *
     * @param request datos del usuario
     * @return Token de autenticación
     */
    @Override
    public JwtAuthResponse signUp(UserSignUpRequest request) {
        log.info("Creando usuario: {}", request);
        if (request.getPassword().contentEquals(request.getPasswordRepeat())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .name(request.getName())
                    .surname(request.getSurname())
                    .roles(Stream.of(Role.USER).collect(Collectors.toSet()))
                    .build();
            try {
                // Salvamos y devolvemos el token
                var userStored = authUsersRepository.save(user);
                return JwtAuthResponse.builder().token(jwtService.generateToken(userStored)).build();
            } catch (DataIntegrityViolationException ex) {
                throw new UserAuthNameOrEmailExisten("El usuario con username " + request.getUsername() + " o email " + request.getEmail() + " ya existe");
            }
        } else {
            throw new UserDiferentePasswords("Las contraseñas no coinciden");

        }
    }

    /**
     * Autentica un usuario
     *
     * @param request datos del usuario
     * @return Token de autenticación
     */
    @Override
    public JwtAuthResponse signIn(UserSignInRequest request) {
        log.info("Autenticando usuario: {}", request);
        // Autenticamos y devolvemos el token
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = authUsersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthSingInInvalid("Usuario o contraseña incorrectos"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }
}
