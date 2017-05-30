package com.coffee.machine;

import java.util.HashMap;
import java.util.Map;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;
import com.coffee.machine.translater.OrderTranslater;

public class Machine {
	
	private OrderTranslater orderTranslater = new OrderTranslater();
	private Map<Drink, Integer> drinkCounter = new HashMap<>();

	public String manageOrder(Order order) {
		String command = orderTranslater.translateOrder(order);
		
		if (!commandIsAMessage(command)) {
			Integer nbOfDrinkAlreadyOrdered = null != drinkCounter.get(order.getDrink()) ? drinkCounter.get(order.getDrink()) : 0;
			drinkCounter.put(order.getDrink(), nbOfDrinkAlreadyOrdered + 1);
		}
		
		return command;
	}
	
	private boolean commandIsAMessage(String command) {
		return null != command && command.startsWith(OrderTranslater.MESSAGE_CHAR);
	}
	
	public String report() {
		double totalMoney = 0.0d;
		for (Drink drink : Drink.values()) {
			totalMoney += drink.getCost() * drinkCounter.get(drink);
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("Sold coffees:").append(drinkCounter.get(Drink.COFFEE)).append("\n")
				.append("Sold chocolates:").append(drinkCounter.get(Drink.CHOCOLATE)).append("\n")
				.append("Sold teas:").append(drinkCounter.get(Drink.TEA)).append("\n")
				.append("Sold orange juices:").append(drinkCounter.get(Drink.ORANGE_JUICE)).append("\n")
				.append("Total maked money:").append(String.format("%.2f", totalMoney)).append("€");
		String report = builder.toString();
		System.out.println(report);
		return report;
	}

}
