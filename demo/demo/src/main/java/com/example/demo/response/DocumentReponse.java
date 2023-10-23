package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DocumentReponse {
	
	private String documentName;
	  private String documentURL;
	  private String documentType;
	  private long documentSize;



}
