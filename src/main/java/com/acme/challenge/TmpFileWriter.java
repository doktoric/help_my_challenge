package com.acme.challenge;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TmpFileWriter {
	
private TmpFileWriter(){}
	
	private PrintWriter writer;
	
	private static class TmpFileWriterHolder { 
        public static final TmpFileWriter INSTANCE = new TmpFileWriter();
	}
	
	public static TmpFileWriter getInstance() {
        return TmpFileWriterHolder.INSTANCE;
	}
	
	public void initFile() {  
		try {
			writer = new PrintWriter("tmpForecast.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeline(String line){
		writer.println(line);
	}

}
