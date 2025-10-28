package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            new TypeToken<List<Visitor>>(){}.getType()
        );
        visitors.forEach(v -> System.out.println(
            v.name + " " + v.surname + " - " + v.phone)
        );
        
        System.out.println("\nВсего посетителей: " + visitors.size());
    }
}