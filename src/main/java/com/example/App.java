package com.example;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
    public static void main(String[] args) throws Exception {
        Type listType = new TypeToken<List<Visitor>>(){}.getType();
        
        List<Visitor> visitors = new Gson().fromJson(
            new FileReader("book.json"),
            listType
        );
        
        for (Visitor visitor : visitors) {
            System.out.println(visitor.name + " " + 
                             visitor.surname + " - " + 
                             visitor.phone);
        }
        
        System.out.println("\nВсего посетителей: " + visitors.size());
    }
}