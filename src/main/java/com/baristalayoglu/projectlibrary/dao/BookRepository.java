package com.baristalayoglu.projectlibrary.dao;

import com.baristalayoglu.projectlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
