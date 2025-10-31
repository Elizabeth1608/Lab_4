package com.example;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LibraryAnalyzer {
    public static void main(String[] args) throws Exception {
     
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("\nКниги по году издания");
        
        List<Book> allBooks = new ArrayList<>();
        for (Visitor visitor : visitors) { allBooks.addAll(visitor.favoriteBooks);
        }
        Collections.sort(allBooks, new Comparator<Book>() {
            public int compare(Book book1, Book book2) {
                return Integer.compare(book1.publishingYear, book2.publishingYear);
            }
        });
        
        for (Book book : allBooks) {
            System.out.println(book.publishingYear + " - " + book.name + " (" + book.author + ")");
        }
    }
}