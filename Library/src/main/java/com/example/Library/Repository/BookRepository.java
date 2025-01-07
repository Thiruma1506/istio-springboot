package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
