package com.daw.parcelas.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RecursoNoEncontradoException extends RuntimeException{

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }


}

