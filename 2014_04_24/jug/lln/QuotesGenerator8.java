package com.tritschler.jcep;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class QuotesGenerator8 {
		
		private static Stream<String> generateSymbols(int n) {
			if (n < 1 || n > 50) {
				throw new IllegalArgumentException("invalid argument");
			}
			
			Stream<Character> symbols = Random.ints(65,91).limit(4).map(i->Character.toChars(i));
			
			
			List<String> symbols = new ArrayList<>();
			Random r = new Random(); 
			for (int i=0; i<n; i++) {
				symbols.add(""+((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26))) +((char) (65 + r.nextInt(26)))); 
			}
			return symbols;
		}
		
		private static LocalDate nextBusinessDay(LocalDate localDate) {	// Date -> Instant or LocalDate			
			if (LocalDate.plusDays(1).getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				return LocalDate.plusDays(3);
			} else if (LocalDate.plusDays(1).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				return LocalDate.plusDays(2);
			}
			return LocalDate.plusDays(1);
		}
		
		private static List<Quote> generateQuotes(LocalDate date, int n, int startPrice) {
			//Stream<Double> prices = Streams.generate(Math::Random);
			DoubleStream prices = Random.doubles(startPrice, startPrice*1.05).limit(n);
			Stream<Quote> quotes 
			
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
				List<Quote> quotes =  generateQuotes(LocalDate.now(), 10, 250);	//new Date()-> Instant.now() or LocalDate.now
				History h = new History(s, quotes);
				h.print();
				h.save(args[0]);
			}
		}
	}
}
