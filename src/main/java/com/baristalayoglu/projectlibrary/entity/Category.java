package com.baristalayoglu.projectlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long id;
    private String categoryName;
    private List<Book> books;

    public Category() {
        books= new ArrayList<>();
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
        books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
