package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int available;

    public Book(int id, String title, String author, int available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getAvailable() { return available; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAvailable(int available) { this.available = available; }
}
