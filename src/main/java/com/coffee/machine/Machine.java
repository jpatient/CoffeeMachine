package com.coffee.machine;

import java.util.HashMap;
import java.util.Map;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;
import com.coffee.machine.services.BeverageQuantityChecker;
import com.coffee.machine.services.EmailNotifier;
import com.coffee.machine.translater.OrderTranslater;

public class Machine {
	
	private BeverageQuantityChecker beverageQuantityChecker;
	private EmailNotifier emailNotifier;
	
	private OrderTranslater orderTranslater = new OrderTranslater();
	private Map<Drink, Integer> drinkCounter = new HashMap<>();

	public String manageOrder(Order order) {
		Drink drink = order.getDrink();
		
		// check for the availability of the beverage
		if (beverageQuantityChecker.isEmpty(drink.getLabel())) {
			emailNotifier.notifyMissingDrink(drink.getLabel());
			return orderTranslater.getMessageForUnavailableDrink(drink);
		}
		
		// translate the order
		String command = orderTranslater.translateOrder(order);
		
		// count the delivery beverage for stats
		if (!commandIsAMessage(command)) {
			Integer nbOfDrinkAlreadyOrdered = null != drinkCounter.get(drink) ? drinkCounter.get(drink) : 0;
			drinkCounter.put(drink, nbOfDrinkAlreadyOrdered + 1);
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

	void setBeverageQuantityChecker(BeverageQuantityChecker beverageQuantityChecker) {
		this.beverageQuantityChecker = beverageQuantityChecker;
	}

	void setEmailNotifier(EmailNotifier emailNotifier) {
		this.emailNotifier = emailNotifier;
	}

}
