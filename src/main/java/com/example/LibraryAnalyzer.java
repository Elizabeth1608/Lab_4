package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;


public class LibraryAnalyzer {
     public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("\n=== КНИГИ ОТСОРТИРОВАННЫЕ ПО ГОДУ ИЗДАНИЯ ===");
        
        visitors.stream()
            .flatMap(v -> v.favoriteBooks.stream())
            .sorted((b1, b2) -> Integer.compare(b1.publishingYear, b2.publishingYear))
            .forEach(book -> System.out.println(
                "📅 " + book.publishingYear + " - " + book.name + " (" + book.author + ")"
            ));
    }
}
