package com.example.bookApi.persistance;

import java.util.ArrayList;
import java.util.Objects;

public class Book implements Comparable<Book>{
    private String title;
    private String author;
    private  int  Id;
    private  static int count;
    private static ArrayList<Book>  library = new ArrayList<Book>();
    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.Id = Id;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ArrayList<Book> getAllBooks() {
        return library;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public static void setCount(int count) {
        Book.count = count;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return  Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, Id);
    }

    @Override
    public int compareTo(Book o) {
        int compareCase = this.author.compareTo(o.getAuthor());
        if(compareCase != 0) return compareCase;
        return  this.title.compareTo(o.getTitle());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", Id=" + Id +
                '}';
    }
}
