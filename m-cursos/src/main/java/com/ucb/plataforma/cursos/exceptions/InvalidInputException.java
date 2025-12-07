package com.ucb.plataforma.cursos.exceptions;

// Excepción personalizada para entradas inválidas
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
