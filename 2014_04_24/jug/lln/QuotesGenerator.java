package jug.lln;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class QuotesGenerator {	
	
	private static List<String> generateSymbols(int n) {
		if (n < 1 || n > 50) {
			throw new IllegalArgumentException("invalid argument");
		}
		List<String> symbols = new ArrayList<>();
		Random r = new Random(); 
		for (int i=0; i<n; i++) {
			symbols.add(""+((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26)))); 
		}
		return symbols;
	}
	
	private static Date nextBusinessDay(Date date) {
		Calendar c = new GregorianCalendar();		
	    c.setTime(new Date(date.getTime()+86400000));
	    if((Calendar.SATURDAY == c.get(c.DAY_OF_WEEK))) {
	    	return new Date(date.getTime()+86400000*3);
	    } else if (Calendar.SUNDAY == c.get(c.DAY_OF_WEEK)) {
	    	return new Date(date.getTime()+86400000*2);
	    }	    	
		return c.getTime();
	}
	
	private static List<Quote> generateQuotes(Date date, int n, int startPrice) {
		List<Quote> quotes = new ArrayList<Quote>();
		Random r = new Random();
		double previousPrice = r.nextDouble()*startPrice;
		double newPrice = previousPrice*(1+(double)r.nextInt(5)/100);
		for (int i=0; i<n; i++) {			
			Quote q = new Quote(date, newPrice);
			date = nextBusinessDay(date);
			newPrice = previousPrice*(1+(double)r.nextInt(5)/100);
			previousPrice = newPrice;
			quotes.add(q);
		}				
		return quotes;
	}
	
	public static void main(String[] args) {
		if (args.length<1) {
			System.out.println("");
			System.exit(-1);
		}
		
		List<String> symbols = generateSymbols(25);
		for (String s: symbols) {
			List<Quote> quotes =  generateQuotes(new Date(), 10, 250);
			History h = new History(s, quotes);
			h.print();
			h.save(args[0]);
		}
	}
}
