package com.meal.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileHandler {

	public static String USER_IMAGE_PATH="/user_image";
	@Autowired
	private ServletContext context;
	
	public void saveOrUpdateFile(InputStream data, String path, String fileName) {
		
		try {
			
			path=context.getRealPath(File.separator)+path;
			
			File folder=new File(path);
			
			if(!folder.exists()) 
				folder.mkdirs();
			
			File file=new File(path,fileName);
			
			FileOutputStream fos=new FileOutputStream(file);
			fos.write(data.readAllBytes());
			fos.flush();
	
			
		} catch (FileNotFoundException e) {
			System.out.println("something wrong with file");
			e.printStackTrace();
		}catch(IOException e) {
			System.out.println("something wrong with file writting");
			e.printStackTrace();
		}
		
	}
}
