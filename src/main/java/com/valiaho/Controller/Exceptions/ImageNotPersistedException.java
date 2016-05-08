package com.valiaho.Controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by akivv on 1.5.2016.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Image was not properly persisted")
public class ImageNotPersistedException extends RuntimeException {
}
