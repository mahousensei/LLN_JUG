package jug.lln;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class QuotesAnalyzer {


	public static void main(String[] args) {		
		if (args.length < 1) {
			System.out.println("usage: ");
			System.exit(-1);
		}

		String[] symbols = args[0].split(",");
		for (String s : symbols) {		
			try {
				History h = History.fromFile(s, args[0] + File.separator + s + ".txt");
				h.print();

			} catch (NumberFormatException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
