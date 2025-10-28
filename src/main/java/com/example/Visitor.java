package com.example;

import lombok.Data;
import java.util.List;

@Data

public class Visitor {
     public String name;        
    public String surname;     
    public String phone;       
    public boolean subscribed;
    public List<Book> favoriteBooks;
}
