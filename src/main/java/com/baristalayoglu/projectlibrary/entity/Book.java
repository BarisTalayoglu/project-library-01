package com.baristalayoglu.projectlibrary.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="book_name")
    private String bookName;
    @Column(name="book_status")
    private boolean bookStatus=true;
    @Column(name="book_type")
    private String bookType;

    @Column(name="member_name")
    private String memberName;

    public Book(String bookName, boolean bookStatus, String bookType, String memberName) {
        this.bookName = bookName;
        this.bookStatus = bookStatus;
        this.bookType = bookType;
        this.memberName = memberName;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
