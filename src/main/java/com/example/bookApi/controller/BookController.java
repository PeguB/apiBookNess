package com.example.bookApi.controller;

import com.example.bookApi.business.BookService;
import com.example.bookApi.persistance.Book;
import com.example.bookApi.persistance.FindingBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService bookService;
    public BookController(BookService bookService){
            this.bookService = bookService;
    }
    @DeleteMapping(path = "delete/{id}")
    public void deleteBook(@PathVariable("id") int id){

        if(bookService.findBook(id) == null){
            throw new FindingBookException();
        }else{
            bookService.deleteBook(id);
        }

    }

    @GetMapping(path = "searchBook/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        if(bookService.findBook(id) == null){
            throw new FindingBookException();
        }
        return bookService.findBook(id);
    }
    @PostMapping(path = "updateBook")
    public String updateBookById(@RequestParam Map<String,String> mapPath){
        if(bookService.findBook(Integer.valueOf(mapPath.get("id"))) == null){
            throw new FindingBookException();
        }
        Book foundBook = bookService.findBook(Integer.valueOf(mapPath.get("id")));


        if(foundBook == null){
            return "Book doesn't exist";
        }else{
            foundBook.setAuthor(mapPath.get("author"));
            System.out.println(mapPath.get("author"));
            return  foundBook.toString();
        }
    }

   @GetMapping(path = "getBooks")
   public ArrayList<Book> getAll(){
        return bookService.getAll();
   }
   @PostMapping(path = "addBook")
   public boolean addBook(@RequestParam Map<String,String> map){
        Book newBook = new Book(map.get("title"), map.get("author"));
       if(bookService.addBook(newBook) == true) {
           return true;
       }else{
           return false;
       }

   }
    @PostMapping(path = "addBookV2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public boolean addBook(@RequestBody  Book newBook){
        if(bookService.addBook(newBook) == true) {
            return true;
        }else{
            return false;
        }
    }

}
