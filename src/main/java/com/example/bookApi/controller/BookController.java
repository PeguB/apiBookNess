package com.example.bookApi.controller;

import com.example.bookApi.business.BookService;
import com.example.bookApi.persistance.Book;
import com.example.bookApi.persistance.FindingBookException;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private final BookService bookService;
    public BookController(BookService bookService){
            this.bookService = bookService;
    }
    @DeleteMapping(path = "books/{id}")
    public void deleteBook(@PathVariable("id") int id){

        if(bookService.findBook(id) == null){
            throw new FindingBookException();
        }else{
            bookService.deleteBook(id);
        }

    }

    @GetMapping(path = "books/{title}")
    public Book getBookByTitle(@PathVariable("title") String title) {
        title.replaceAll("%20"," ");
        if(bookService.findBookByTitle(title) == null){
            throw new FindingBookException();
        }
        return bookService.findBookByTitle(title);
    }
    @PutMapping(path = "books/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBookById(@PathVariable("id") int id, @RequestBody String author){
        if(bookService.findBook(id) == null){
            throw new FindingBookException();
        }

        return bookService.updateAuthorBook(author,id);
    }

   @GetMapping(path = "books")
   public ArrayList<Book> getAll(){
        return bookService.getAll();
   }

    @PostMapping(path = "books",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Book addBook(@RequestBody  Book newBook){
        if(bookService.addBook(newBook) == true) {
            return newBook;
        }else{
            throw new FindingBookException();
        }
    }

}
