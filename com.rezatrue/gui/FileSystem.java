package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileSystem {
	public ArrayList<String> confidentialList;
	
	public FileSystem() {
		confidentialList = new ArrayList<String>();
	}

	public boolean CreateDir(String dirName){
		try {
			File file = new File(dirName);
			file.exists();
			file.mkdir();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleateFile(String fileName){
		try {
			File file = new File(fileName);
			if (file.exists()) {
				return file.delete();
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	public ArrayList<String> CopyfromZip(String from, String to){
		File newFile;
		try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream(from));
			try {
				ZipEntry ze = zis.getNextEntry();
				int counter = 0;
	    		String filename = null;
		    	while(ze!=null){
		    		String folderName = null;
		    		if(ze.isDirectory()){
		    			folderName = ze.getName();
		    			newFile = new File(to + File.separator + folderName);
		    			System.out.println("folder unzip : "+ newFile.getAbsoluteFile());
		    			newFile.mkdirs();
		    			String name = newFile.getAbsoluteFile().toString();
		    			filename = name.substring(name.lastIndexOf("\\"), name.length());
		    			counter = 1;
		    		}else {
		    			String fileName = ze.getName();
		  	           	newFile = new File(to + File.separator + fileName);
		  	           	System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		  	            //create all non exists folders
		  	            //else you will hit FileNotFoundException for compressed folder
		  	           	new File(newFile.getParent()).mkdirs();
		  	           	PrintWriter printer = new PrintWriter(newFile.getAbsoluteFile().toString(), "UTF-8");
		  	           	
		  	          Scanner sc = new Scanner(zis);
		  	          boolean confidential = false;
		  	          int lineno = 0;
		  	          while (sc.hasNext()) {
		  	        	String linedata= sc.nextLine();
		  	        	String garbage = "﻿";
		  	        	String data = null;
		  	        	
		  	        	if (linedata.contains(garbage)) {
		  	        		try {
								data = linedata.substring(garbage.length(), linedata.length()).trim();
							} catch (StringIndexOutOfBoundsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								data = null;
							}
						}else {
							data = linedata.trim();
						}
		  	        	
		  	        	if (data.length()>1 && !(data==null)) {
		  	        		printer.write(data + "\r\n");
		  	        		lineno++;
		  	        		if(!confidential && lineno<26){
		  	        			if (data.toLowerCase().contains("confidential")) {
		  	        				System.out.println("Line no :" + lineno);
		  	        				System.out.println(data);
		  	        				confidential = true;
								}
		  	        		}
		  	        		
//		  	        		if (lineno==0) {
//		  	        			if (data.toLowerCase().contains("confidential")) {
//		  	        				confidential = true;
//								}
//							} else if (lineno>0 && data.contains("@")) {
//								if (data.toLowerCase().contains("confidential@")) {
//									confidential = true;
//								}
//							}
		  	        		
		  	        		
						}
		  	        	
		  	          }
		  	          printer.flush(); 	
		  	          printer.close();
		  	        
		  	          	String name = newFile.getAbsoluteFile().toString();
		  	          	String newfileName = name.substring(0, name.lastIndexOf("\\"))+ filename +"("+counter+")" +".txt";
		  	          	File renamedfile = new File(newfileName);

			    		if(newFile.renameTo(renamedfile)){
			    			System.out.println("File renamed");
			  	           	System.out.println("file : "+ renamedfile.getAbsoluteFile());
			    		}else {
			    			System.out.println("the file can't renamed");
			    		}
			    		
			    		if (confidential) {
			    			confidentialList.add(renamedfile.getAbsoluteFile()+"");
						}
			    		counter++;
		    		}
		    		
		            ze = zis.getNextEntry();
		    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	    	System.out.println("Done");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("---------------");

			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("file not found");
		}
		
		return confidentialList;
	}
	
	public void deleteFile(String path){
		File file = new File(path);
		file.deleteOnExit();
	}
	
}
