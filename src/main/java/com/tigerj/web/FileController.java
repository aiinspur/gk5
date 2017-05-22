package com.tigerj.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("api/file")
public class FileController {
	
	@GetMapping("demo")
	public String uploadDemo(){
		return "upload";
	}
	
	@ResponseBody
	@RequestMapping("upload")
	public void upload(@RequestParam("file") MultipartFile file){
		if (file.isEmpty()) {
			System.out.println("file is empty.");
			return;
		}
		
		System.out.println("file.getOriginalFilename():"+file.getOriginalFilename());
		System.out.println("file.getContentType():"+file.getContentType());
		
		try {
			byte[] fileB = file.getBytes();
		    OutputStream output = new FileOutputStream(new File("/tmp/777"));  
		    BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
		    bufferedOutput.write(fileB);  
		    bufferedOutput.close();
		    output.close();
			
		    // ok
			//file.transferTo(new File("/tmp/t.png"));
		    
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	

}
