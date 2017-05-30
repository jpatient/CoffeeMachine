package com.coffee.machine.translater;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;

public class OrderTranslater {
	
	public static final String CHOCOLATE_CHAR = "H";
	public static final String COFFEE_CHAR = "C";
	public static final String TEA_CHAR = "T";
	public static final String ORANGE_JUICE_CHAR = "O";
	public static final String MESSAGE_CHAR = "M";
	
	public String translateOrder(Order order) {
		int sugars = order.getSugars();
		double amount = order.getAmount();
		Drink drink = order.getDrink();
		
		if (amount < drink.getCost()) {
			String liaisonChar = drinkStartsByVowel(drink) ? "n" : "";
			return String.format("M:It misses %.2f for a%s %s", drink.getCost() - amount, liaisonChar, drink.getLabel());
		}
		
		String drinkChar = determineDrinkChar(drink);
		String sugarsChar = sugars <= 0 ? "" : Integer.toString(sugars);
		String stickChar = sugars > 0 ? "0" : "";
		String extraHotChar = order.isExtraHot() ? "h" : "";
		return String.format("%s%s:%s:%s", drinkChar, extraHotChar, sugarsChar, stickChar);
	}
	
	private static boolean drinkStartsByVowel(Drink drink) {
		return drink.getLabel().matches("^[aeiuoy].+$");
	}
	
	private String determineDrinkChar(Drink drink) {
		switch(drink) {
		case CHOCOLATE:
			return CHOCOLATE_CHAR;
		case COFFEE:
			return COFFEE_CHAR;
		case TEA:
			return TEA_CHAR;
		case ORANGE_JUICE:
			return ORANGE_JUICE_CHAR;
		default: return null;
		}
	}
	
}
