package es.jaimelozanodiegotorres.backapp.rest.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción de autenticación  400
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAuthNameOrEmailExisten extends AuthException {
    public UserAuthNameOrEmailExisten(String message) {
        super(message);
    }
}
