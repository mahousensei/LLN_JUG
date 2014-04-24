package jug.lln;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class History {

	private String symbol;
	private List<Quote> quotes;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public History(String s, List<Quote> quotes) {
		this.symbol = s;
		this.quotes = quotes;
	}
	
	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
	
	public List<Quote> getQuotes() {
		return quotes;
	}
	
	public static History fromFile(String symbol, String path) throws IOException, NumberFormatException, ParseException {
		List<String> lines = Files.readAllLines(FileSystems.getDefault().getPath(path), StandardCharsets.UTF_8);   // xlf is xml ... hence UTF-8
		List<Quote> quotes = new ArrayList<Quote>(); 
		for (String line : lines) {
			String[] x = line.split(",");
			if (x.length <2) {
				System.out.println("invalid line ... skip");
				continue;
			}
			quotes.add(new Quote(sdf.parse(x[0]), Double.parseDouble(x[1])));					
		}		
		return new History(symbol, quotes);		
	}
	
	public void save(String path) {
		byte data[] = toCSV().getBytes(StandardCharsets.UTF_8);
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(FileSystems.getDefault().getPath(path+File.separator+this.symbol+".txt"), StandardOpenOption.CREATE))) {		    
		    out.write(data, 0, data.length);		    
		} catch (IOException x) {
		    System.out.println("error: " + x.getMessage());
		}	
		System.out.println(toCSV());
	}
	
	public String toCSV() {		
		StringBuilder sb = new StringBuilder();
		for (Quote q: quotes) {			
			sb.append(sdf.format(q.getDate())).append(",").append(q.getClosePrice()).append("\n");
		}
		return sb.toString();
	}
	
	public void print() {
		for (Quote q: quotes) {
			System.out.println(this.symbol + "\t" + q.getDate() + "\t" + q.getClosePrice());
		}
	}
}
