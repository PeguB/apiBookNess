package com.example.bookApi.persistance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book doesn't exist")
public class FindingBookException extends RuntimeException{

}
