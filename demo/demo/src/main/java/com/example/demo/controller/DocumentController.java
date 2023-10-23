package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.SizeLimitExceededException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.response.DocumentReponse;
import com.example.demo.response.ResponseMessage;
import com.example.demo.services.DocumentService;



@RestController
@ControllerAdvice
@RequestMapping("v2/")
public class DocumentController {
	
	DocumentService documentService;
	DocumentRepository documentRepository;
	

	public DocumentController(DocumentService documentService, DocumentRepository documentRepository) {
		super();
		this.documentService = documentService;
		this.documentRepository = documentRepository;
	}

	
	 
	
	 
	 @PostMapping("/uploadDocument")
	  public ResponseEntity<ResponseMessage> uploadDocument(@RequestParam("file") MultipartFile file) {
				 
	    String message = "";
	    
	    String[] allowedFileExtension = {"png", "jpeg","jpg", "xls", "doc", "pdf"};
		  
		  String fileExtension = file.getOriginalFilename().split("\\.")[1];
		  
		  if (file.getOriginalFilename() == null || file.getOriginalFilename().equals("")) {
			  message = "File Name  must not be null or empty ";
			  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	        }
		  
		  else if( !Arrays.asList(allowedFileExtension).contains(fileExtension) ){
			  message =  "File type is not supported: " + fileExtension;
			  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		  }
		  
			    try {
			    	
			      message = documentService.uploadDocument(file);
		
			      //message = "Uploaded the document successfully: " + file.getOriginalFilename();
			    
			      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			    } catch (Exception e) {
			      message = "error" + e.getMessage();
			    	
			      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			    }
		  
		  
	    
	  }



	 
	
	  //download document byte[]
	  @GetMapping("/downloadDocument/{name}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String name) {
	    Optional<Document> document = documentService.getDocument(name);

	  
	    
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.get().getDocumentName() + "\"")
	        .body(document.get().getDocumentData());
	  }
	  
	  
	  @GetMapping("/getAllDocument")
	  public ResponseEntity<List<DocumentReponse>> getListFiles() {
	    List<DocumentReponse> documents = documentService.getAllDocument().map(document -> {

	      return new DocumentReponse(
	    		  document.getDocumentName(),
	    		  document.getDocumentPath(),
	    		  document.getDocumentType(),
	    		  document.getDocumentData().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(documents);
	  }

	  
	  
	  @PutMapping("/updateDocument/documentId")
	  public ResponseEntity<ResponseMessage> updateDocument(@PathVariable int documentId, @RequestParam("file") MultipartFile file  ) {
		  
		  String message = "";
		  
		  
		  try {
			message = documentService.updateDocument(documentId, file);
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (IOException e) {
			message = "error" + e.getMessage();
			 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		  
		  
		  
		  
	  }
	 

	  
	  
	  @DeleteMapping("/deleteDocumentById/{id}")
		public ResponseEntity<String> deleteDocumentById(@PathVariable int id) throws Exception {
		 
		  return ResponseEntity.status(HttpStatus.OK).body(documentService.deleteDocumentById(id));  
	  }
		
	 /* @DeleteMapping("/deleteDocumentByName/{name}")
			public ResponseEntity<String> deleteDocumentByName(@PathVariable String name) throws Exception {
			 
			  return ResponseEntity.status(HttpStatus.OK).body(documentService.deleteByDocumentName(name));  
		  }*/
	  
	  
	  
	  
	  @ExceptionHandler(MaxUploadSizeExceededException.class)
		 public ResponseEntity<ResponseMessage> handlefileUploadError(RedirectAttributes ra) {
			
			 String message = "Document Size is long max size = 5 MB ";
			
			 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			
		 }
	  
	  
	  @GetMapping("/all")
	  
	  public List<Document> getAllDocument(){
		  
		  List<Document> allBook = documentRepository.findAll();
		  
		  return allBook;
	  }
	  
	  

	 /* @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String name) {
	    Document document = documentService.getDocument(name);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getDocumentName()) + "\"")
	        .body(document.getDocumentData());
	  }*/
	
	  
	  
	  /*@GetMapping("/download/{fileName:.+}")
	  public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
	  	Path path = Paths.get("path"+ fileName);
	  	Resource resource = null;
	  	try {
	  		resource = new UrlResource(path.toUri());
	  	} catch (MalformedURLException e) {
	  		e.printStackTrace();
	  	}
	  	return ResponseEntity.ok()
	  			.contentType(MediaType.parseMediaType(contentType))
	  			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	  			.body(resource);
	  }*/
	  
	
	/*
	@PostMapping("/uploadFilesIntoDB")
	public ResponseEntity<String> storeFilesIntoDB(@RequestParam("file") MultipartFile file) throws IOException {
		String response = documentService.storeFile(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/getFileByName/{fileName}")
	public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
		byte[] imageData = documentService.getFiles(fileName);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).body(imageData);
	}*/
	
	
	/*
	@PostMapping("/upload")
	public DocumentReponse uploadDocument(@RequestParam("file")MultipartFile file) throws Exception {
		
		Document doc = null;
		doc = documentService.saveDocument(file);
		
		return new DocumentReponse(doc.getFileName(),
				file.getContentType(),
				file.getSize());
		
	}
	*/
	

	
	

}
