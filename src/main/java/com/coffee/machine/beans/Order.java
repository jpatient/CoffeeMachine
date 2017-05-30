package com.coffee.machine.beans;

public class Order {
	
	private Drink drink;
	private int sugars;
	private double amount;
	private boolean extraHot;
	
	public Order() {
		super();
	}
	
	public Order(Drink drink, double amount) {
		super();
		this.drink = drink;
		this.amount = amount;
	}
	
	public Order(Drink drink, int sugars, double amount) {
		super();
		this.drink = drink;
		this.sugars = sugars;
		this.amount = amount;
	}

	public Order(Drink drink, int sugars, double amount, boolean extraHot) {
		super();
		this.drink = drink;
		this.sugars = sugars;
		this.amount = amount;
		this.extraHot = extraHot;
	}

	public Drink getDrink() {
		return drink;
	}
	
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	
	public int getSugars() {
		return sugars;
	}
	
	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isExtraHot() {
		return extraHot;
	}

	public void setExtraHot(boolean extraHot) {
		this.extraHot = extraHot;
	}

}
