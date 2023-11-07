package com.company.test.java.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes;
}



