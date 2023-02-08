package com.jsp.jspwfm.Models.Entities;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(value= {"hibernateLazyInitializer"})
public class Image 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int imid;
	@Lob
	@Column(nullable=false,columnDefinition="blob")
	private byte[] imagedata;
	private String type;
	private String name;
	
	public Image(byte[] imagedata, String name, String type) {
		super();
		this.imagedata = imagedata;
		this.name = name;
		this.type = type;
	}
	
	public Image() {}
}
