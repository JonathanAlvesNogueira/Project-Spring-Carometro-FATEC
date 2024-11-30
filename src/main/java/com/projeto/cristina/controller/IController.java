package com.projeto.cristina.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<T> {
	ResponseEntity<T> create(T t);
	ResponseEntity<T> update(Long id, T t);
	ResponseEntity<Void> delete(Long id);
	ResponseEntity<T> getById(Long id);
	ResponseEntity<List<T>> list();

}
