package com.example.bookApi.business;

import com.example.bookApi.persistance.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class BookService {
    public boolean addBook(Book book) {
        if (Book.getAllBooks().contains(book) == false) {

            Book.getAllBooks().add(book);
            Book.setCount(Book.getCount() + 1);
            book.setId(Book.getCount());
            return true;
        }else{
            return false;
        }

    }
    public void deleteBook(int id){
        for(Book i : Book.getAllBooks()) {
            if (i.getId() == id) {
                Book.getAllBooks().remove(i);
                break;
            }
        }
    }
    public ArrayList<Book> getAll(){
        Collections.sort(Book.getAllBooks());
        return Book.getAllBooks();
    }

    public Book findBook(int id){
        for(Book i : Book.getAllBooks()){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }
    public Book findBookByTitle(String title){
        for(Book i : Book.getAllBooks()){
            if(i.getTitle().equals(title)){
                return i;
            }
        }
        return null;
    }
    public Book updateAuthorBook(String author, int bookId){
        Book updateBook = findBook(bookId);
        updateBook.setAuthor(author);
        return updateBook;
    }
}
