package com.example.bookApi.persistance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Book exist already")
public class CreateBookException extends RuntimeException{
}
