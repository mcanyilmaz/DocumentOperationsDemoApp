package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;


@Service
public class DocumentService {
	
	private DocumentRepository documentRepository;
	
	private final String documentFolderPath = "C:\\Users\\lenovo\\Desktop\\UploadedDocuments\\";
	
	public DocumentService(DocumentRepository documentRepository) {
	
		this.documentRepository = documentRepository;
	}


	
	
	  public String uploadDocument(MultipartFile file) throws Exception {
		  
			 String filePath = documentFolderPath+file.getOriginalFilename();
	
			 Optional<Document> document = documentRepository.findByDocumentName(file.getOriginalFilename());
			 
			 
			 if(document.isPresent()){
				 
				 return "Document is exists";
			 }
			 
			Document doc = new Document();
			doc.setDocumentName(file.getOriginalFilename());
			doc.setDocumentType(file.getContentType());
			doc.setDocumentData(file.getBytes());
			file.transferTo(new File(filePath));
			doc.setDocumentPath(filePath);
			doc.setDocumentsize(file.getSize());
			
			documentRepository.save(doc);
		
		    return "Document Upload Successfull " + file.getOriginalFilename() ;
		  }
	  
	  
	  
	  
	
	  
	
	  public String deleteDocumentById(int id) {
		  String message = "";
		  Document document = documentRepository.findById(id);
		  
		  document.getDocumentPath();
		 
		  if(!documentRepository.existsById(id)) {
			  message = "Not found document ";
			
		  }else {
		
				  documentRepository.deleteById(id);
				  try {
					Files.deleteIfExists(Paths.get(document.getDocumentPath()));
					message = "Document is deleted Database and Local Storage";
				  } catch (IOException e) {
					
					message = "Document could not be delete local storage";
				  }
			  
		  	}
		  return message;

	  }
	  
	  
	  
	  
	  

		  public Optional<Document> getDocument(String name) {
		    return documentRepository.findByDocumentName(name);
		  }
		  
		  public Stream<Document> getAllDocument() {
			    return documentRepository.findAll().stream();
			  }
	
		  
		  public String updateDocument(int id, MultipartFile file) throws IOException {
			  
			  
			  String message = "";
			  
			  if(documentRepository.existsById(id)) {
				  
				  Document doc = documentRepository.findById(id);
				  
				  //deleteOldDocumentInPath
				  Files.deleteIfExists(Paths.get(doc.getDocumentPath()));
				  
				  String filePath = documentFolderPath+file.getOriginalFilename();
				  
				  doc.setDocumentName(file.getName());
				  doc.setDocumentType(file.getContentType());
				  doc.setDocumentData(file.getBytes());
				  doc.setDocumentsize(file.getSize());
				  doc.setDocumentPath(filePath);
				  file.transferTo(new File(filePath));
				  
				  documentRepository.save(doc);
				  return "Document update successfull ";
				 
			  }else {
				  return " Document could not  be updated "; 
			  }
			  
			
			  
			  
		  }
	

	
	
	
	
	
	
	
	

}
