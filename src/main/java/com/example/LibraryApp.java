package com.example;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LibraryApp {
    public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("\nКниги в избранном");
        
        var uniqueBooks = visitors.stream()
            .flatMap(v -> v.favoriteBooks.stream())
            .collect(Collectors.groupingBy(
                book -> book.name + "|" + book.author,
                Collectors.toList()
            ))
            .values()
            .stream()
            .map(list -> list.get(0))
            .collect(Collectors.toList());
        
        uniqueBooks.forEach(book -> 
            System.out.println(book.name + " - " + book.author)
        );
        
        System.out.println("\nВсего избранных книг книг: " + uniqueBooks.size());
    }
}
