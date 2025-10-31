package com.example;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class SMS {
    public String phoneNumber;
    public String message;
    
    public SMS(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
    
    @Override
    public String toString() {
        return phoneNumber + ": " + message;
    }
}

public class SMSNotifier {
     public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );

        System.out.println("SMS Рассылка");
        
        List<Visitor> subscribedVisitors = visitors.stream()
            .filter(v -> v.subscribed)
            .collect(Collectors.toList());
        
        if (subscribedVisitors.isEmpty()) {
            System.out.println("Нет посетителей для рассылки");
            return;
        }
        
        double averageBooks = subscribedVisitors.stream()
            .mapToInt(v -> v.favoriteBooks.size())
            .average()
            .orElse(0.0);
        
        System.out.printf("Среднее количество книг: %.1f%n%n", averageBooks);
        
        List<SMS> smsList = subscribedVisitors.stream()
            .map(v -> {
                int bookCount = v.favoriteBooks.size();
                String message;
                
                if (bookCount > averageBooks) {
                    message = "you are a bookworm";
                } else if (bookCount < averageBooks) {
                    message = "read more";
                } else {
                    message = "fine";
                }
                
                return new SMS(v.phone, message);
            })
            .collect(Collectors.toList());
        
        smsList.forEach(System.out::println);
        
        System.out.println("\nРассылка");
        long bookworms = smsList.stream().filter(s -> s.message.equals("you are a bookworm")).count();
        long readMore = smsList.stream().filter(s -> s.message.equals("read more")).count();
        long fine = smsList.stream().filter(s -> s.message.equals("fine")).count();
        
        System.out.println("Bookworms: " + bookworms);
        System.out.println("Read more: " + readMore);
        System.out.println("Fine: " + fine);
        System.out.println("Всего SMS: " + smsList.size());
    }
}
