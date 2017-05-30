package com.coffee.machine.translater;

import java.util.Random;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;

public class OrderTranslaterTest {
	
	@Test
	public void testTranslateOrder_OrangeJuice_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.ORANGE_JUICE, 0.6d))).isEqualTo("O::");
	}
	
	@Test
	public void testTranslateOrder_OrangeJuice_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.ORANGE_JUICE, 0.1d))).isEqualTo("M:It misses 0,50 for an orange juice");
	}
	
	@Test()
	public void testTranslateOrder_CoffeeWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, 0, 0.6d))).isEqualTo("C::");
	}
	
	@Test
	public void testTranslateOrder_ChocolateWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, 0, 0.5d))).isEqualTo("H::");
	}
	
	@Test
	public void testTranslateOrder_TeaWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, 0, 0.4d))).isEqualTo("T::");
	}
	
	@Test
	public void testTranslateOrder_CoffeeWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, sugars, 0.6d))).isEqualTo("C:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_ChocolateWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, sugars, 0.5d))).isEqualTo("H:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_TeaWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, sugars, 0.4d))).isEqualTo("T:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_CoffeeWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, 0, 0.1d))).isEqualTo("M:It misses 0,50 for a coffee");
	}
	
	@Test
	public void testTranslateOrder_ChocolateWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, 0, 0.1d))).isEqualTo("M:It misses 0,40 for a chocolate");
	}
	
	@Test
	public void testTranslateOrder_TeaWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, 0, 0.1d))).isEqualTo("M:It misses 0,30 for a tea");
	}
	
	@Test
	public void testTranslateOrder_CoffeeWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, sugars, 0.1d))).isEqualTo("M:It misses 0,50 for a coffee");
	}
	
	@Test
	public void testTranslateOrder_ChocolateWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, sugars, 0.1d))).isEqualTo("M:It misses 0,40 for a chocolate");
	}
	
	@Test
	public void testTranslateOrder_TeaWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, sugars, 0.1d))).isEqualTo("M:It misses 0,30 for a tea");
	}
	
	// XXX test with extraHot option
	@Test()
	public void testTranslateOrder_ExtraHotCoffeeWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, 0, 0.6d, true))).isEqualTo("Ch::");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotChocolateWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, 0, 0.5d, true))).isEqualTo("Hh::");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotTeaWithoutSugar_WithAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, 0, 0.4d, true))).isEqualTo("Th::");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotCoffeeWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, sugars, 0.6d, true))).isEqualTo("Ch:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotChocolateWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, sugars, 0.5d, true))).isEqualTo("Hh:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotTeaWithSugar_WithAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, sugars, 0.4d, true))).isEqualTo("Th:" + sugars + ":0");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotCoffeeWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, 0, 0.1d, true))).isEqualTo("M:It misses 0,50 for a coffee");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotChocolateWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, 0, 0.1d, true))).isEqualTo("M:It misses 0,40 for a chocolate");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotTeaWithoutSugar_WithoutAmountRequired() {
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, 0, 0.1d, true))).isEqualTo("M:It misses 0,30 for a tea");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotCoffeeWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.COFFEE, sugars, 0.1d, true))).isEqualTo("M:It misses 0,50 for a coffee");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotChocolateWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.CHOCOLATE, sugars, 0.1d, true))).isEqualTo("M:It misses 0,40 for a chocolate");
	}
	
	@Test
	public void testTranslateOrder_ExtraHotTeaWithSugar_WithoutAmountRequired() {
		int sugars = getRandomNumberOfSugars();
		Assertions.assertThat(testTranslateOrder(new Order(Drink.TEA, sugars, 0.1d, true))).isEqualTo("M:It misses 0,30 for a tea");
	}
	
	private String testTranslateOrder(Order order) {
		return new OrderTranslater().translateOrder(order);
	}
	
	private int getRandomNumberOfSugars() {
		return new Random().nextInt(2) + 1;
	}
	
}
