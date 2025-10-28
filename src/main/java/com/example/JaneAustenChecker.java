package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;

public class JaneAustenChecker {
    public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("=== ПРОВЕРКА КНИГ JANE AUSTEN ===");
        
        boolean hasJaneAustenBooks = visitors.stream()
            .flatMap(v -> v.favoriteBooks.stream())
            .anyMatch(book -> book.author.equals("Jane Austen"));
        
        if (hasJaneAustenBooks) {
            System.out.println("✅ Найдены книги Jane Austen в избранном!");
            
            System.out.println("\nПосетители с книгами Jane Austen:");
            visitors.stream()
                .filter(v -> v.favoriteBooks.stream()
                    .anyMatch(book -> book.author.equals("Jane Austen")))
                .forEach(v -> {
                    System.out.println("👤 " + v.name + " " + v.surname);
                    v.favoriteBooks.stream()
                        .filter(book -> book.author.equals("Jane Austen"))
                        .forEach(book -> System.out.println("   📖 " + book.name));
                });
        } else {
            System.out.println("❌ Книги Jane Austen не найдены в избранном посетителей");
        }
    }
}
