package com.coffee.machine.beans;

public enum Drink {
	
	COFFEE(0.6d),
	CHOCOLATE(0.5d),
	TEA(0.4d),
	ORANGE_JUICE(0.6d);
	
	private final double cost;
	
	private Drink(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public String getLabel() {
		return name().toLowerCase().replaceAll("_", " ");
	}
	

}
