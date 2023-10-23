package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

	Optional<Document> findByDocumentName(String name);
	void deleteByDocumentName(String name);
	boolean existsByDocumentName(String name);
	
	Document findById(int id);
	
	//Optinal<Document> findByDocumentName(String name);
	
	
}
