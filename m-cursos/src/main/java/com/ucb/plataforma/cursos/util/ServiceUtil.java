package com.ucb.plataforma.cursos.util;

import com.ucb.plataforma.cursos.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component // Lo marcamos como un Bean de Spring para poder inyectarlo
public class ServiceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);

    /**
     * Un método ayudante que centraliza cómo manejamos los errores
     * de "llave duplicada" de la base de datos.
     */
    public void handleDataIntegrityViolation(DataIntegrityViolationException dive, String messagePrefix) throws InvalidInputException {
        LOGGER.warn("{}: {}", messagePrefix, dive.getMessage());
        // Lanzamos nuestra propia excepción, como en la diapositiva 11
        throw new InvalidInputException(messagePrefix + ": " + dive.getMessage());
    }

    // Aquí podrías agregar más métodos útiles...
    // public String formatApiResponse(Object data) { ... }
} // <--- El error estaba aquí. Había un "." después de esta llave.