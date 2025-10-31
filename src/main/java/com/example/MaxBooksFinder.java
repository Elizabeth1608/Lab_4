package com.example;

import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MaxBooksFinder {
    public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("Максимальное число книг");
        
        int maxBooks = visitors.stream()
            .mapToInt(v -> v.favoriteBooks.size())
            .max()
            .orElse(0);
        
        System.out.println("Максимальное количество книг у одного посетителя: " + maxBooks);
        
        System.out.println("\nПосетители с максимальным количеством книг:");
        visitors.stream()
            .filter(v -> v.favoriteBooks.size() == maxBooks)
            .forEach(v -> {
                System.out.println(v.name + " " + v.surname + " - " + v.favoriteBooks.size() + " книг");
                System.out.println(v.phone);
                System.out.println("Книги:");
                v.favoriteBooks.forEach(book -> 
                    System.out.println(" - " + book.name + " (" + book.author + ")")
                );
                System.out.println();
            });
    }
}
