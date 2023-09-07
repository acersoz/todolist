/**
 * © 2023 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.example.todolist.controller;

import com.example.todolist.model.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo create(@RequestBody ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @GetMapping
    public Iterable findAll(){
        return toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ToDo findOne(@PathVariable Long id) throws Exception {
        return toDoRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        toDoRepository.findById(id)
                .orElseThrow(Exception::new);
        toDoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@RequestBody ToDo toDo, @PathVariable Long id) throws Exception {
        if (toDo.getId() != id) {
            throw new Exception();
        }
        toDoRepository.findById(id)
                .orElseThrow(Exception::new);
        return toDoRepository.save(toDo);
    }

}