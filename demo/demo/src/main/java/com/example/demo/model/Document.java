package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="document")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(generator = "uuid")
	//  @GenericGenerator(name = "uuid", strategy = "uuid2")
	int Id;
	
	


	@Column(name = "documentName")
	String documentName;
	
	@Column(name = "documentType")
	String documentType;
	
	@Column(name = "documentPath")
	String documentPath;
	
	
	@Column(name = "documentsize")
	long documentsize;
	
	@Lob
	@Column(name = "documentData", length  = 5000000 )
	byte[] documentData;
	

	

}
