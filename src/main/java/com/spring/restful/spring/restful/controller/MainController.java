package com.spring.restful.spring.restful.controller;

import com.spring.restful.spring.restful.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.spring.restful.spring.restful.repository.BookRepository;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    BookRepository repository;

    @GetMapping("/books")
    public List<Book> getAll(){
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(
    @PathVariable Long id){
    return repository.findById((Long)(id)).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting blog
        Book bookToUpdate = repository.findById((long) id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);


    }
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }









}
