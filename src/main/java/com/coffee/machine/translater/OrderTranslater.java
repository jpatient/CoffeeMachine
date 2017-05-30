package com.coffee.machine.translater;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;

public class OrderTranslater {
	
	String translateOrder(Order order) {
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
		String drinkChar = null;
		switch(drink) {
		case CHOCOLATE:
			drinkChar = "H";
			break;
		case COFFEE:
			drinkChar = "C";
			break;
		case TEA:
			drinkChar = "T";
			break;
		case ORANGE_JUICE:
			drinkChar = "O";
			break;
		}
		return drinkChar;
	}
	
}
