package com.valiaho.Controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by akivv on 28.4.2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such image")
public class ImageNotFoundException extends RuntimeException {
}
