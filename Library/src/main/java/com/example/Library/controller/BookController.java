package com.example.Library.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.Entity.Book;
import com.example.Library.Repository.BookRepository;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    BookRepository repo;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Book book=repo.findById(id).orElse(null);
        if(book==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(book));
    }

    @PutMapping()
        public ResponseEntity<Book> updateBook(@RequestBody Book book){
            int id=book.getId();
            if(repo.existsById(id)){
                Book existingBook=repo.findById(id).get();
                existingBook.setBookname(book.getBookname());  
                return ResponseEntity.ok(repo.save(existingBook));                  
            }
            return ResponseEntity.notFound().build();
        }

   @DeleteMapping("/{id}")
   public ResponseEntity<HttpStatus>deleteById(@PathVariable int id){
    if(repo.existsById(id)){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
   }


}
