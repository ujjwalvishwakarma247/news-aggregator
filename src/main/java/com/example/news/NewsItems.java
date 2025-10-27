package com.example.news;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewsItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public String title;
    public String link;
    public String author;
    public String categories;
    
    @jakarta.persistence.Lob
    @jakarta.persistence.Column(columnDefinition = "TEXT")
    public String contents;

     public String guid ;

    

    public void setTitle(String title) {
        this.title = title;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public NewsItems() {}

    public NewsItems(String title, String link, String author, String categories, String contents) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.categories = categories;
        this.contents = contents;
    }
    // getters and setters
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getAuthor() {
        return author;
    }
    public String getCategories() {
        return categories;
    }
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }
}
