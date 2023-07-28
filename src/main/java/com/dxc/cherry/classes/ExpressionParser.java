package com.dxc.cherry.classes;

import java.util.ArrayList;
import java.util.Scanner;


public class ExpressionParser {
	
	static ArrayList<String> tokens = new ArrayList<String>();
	
	public static void main(String[] args) {
		String inputString = "5+1";
		
		Scanner scanner = new Scanner(inputString);
		
		while (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				int number = scanner.nextInt();
				tokens.add("NUMBER(" + number + ")");
			} else if (scanner.hasNext("\\+\\-\\*/()")) {
				String symbol = scanner.next("\\+\\-\\*/()");
				tokens.add(symbol.toUpperCase() + "(" + symbol + ")");
			} else {
				tokens.add("Invalid token: " + scanner.next());
			}
			
		}
		
		scanner.close();
	}
}
