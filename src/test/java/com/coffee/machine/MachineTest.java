package com.coffee.machine;

import java.util.Random;

import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.coffee.machine.beans.Drink;
import com.coffee.machine.beans.Order;
import com.coffee.machine.services.BeverageQuantityChecker;
import com.coffee.machine.services.EmailNotifier;

public class MachineTest {
	
	private BeverageQuantityChecker mockedBeverageQuantityChecker;
	private EmailNotifier mockedEmailNotifier;
	
	private Machine machine;
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		machine = new Machine();
		mockedBeverageQuantityChecker = Mockito.mock(BeverageQuantityChecker.class);
		Mockito.when(mockedBeverageQuantityChecker.isEmpty(Mockito.anyString())).thenReturn(false);
		
		mockedEmailNotifier = Mockito.mock(EmailNotifier.class);
		
		machine.setBeverageQuantityChecker(mockedBeverageQuantityChecker);
		machine.setEmailNotifier(mockedEmailNotifier);
	}
	
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
	
	@Test
	public void testReport() {
		// coffees
		machine.manageOrder(new Order(Drink.COFFEE, 0, 0.1d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 1, 0.2d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 2, 0.3d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 0, 0.6d, false)); // OK
		machine.manageOrder(new Order(Drink.COFFEE, 1, 0.7d, false)); // OK
		machine.manageOrder(new Order(Drink.COFFEE, 2, 1.0d, false)); // OK
		machine.manageOrder(new Order(Drink.COFFEE, 0, 0.1d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 1, 0.2d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 2, 0.3d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.COFFEE, 0, 0.6d, true)); // OK
		machine.manageOrder(new Order(Drink.COFFEE, 1, 0.7d, true)); // OK
		machine.manageOrder(new Order(Drink.COFFEE, 2, 0.8d, true)); // OK
		// sold for 3.6 €
		
		// chocolates
		machine.manageOrder(new Order(Drink.CHOCOLATE, 0, 0.1d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 2, 0.3d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 0, 0.6d, false)); // OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 1, 0.7d, false)); // OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 2, 1.0d, false)); // OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 0, 0.1d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 1, 0.2d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 0, 0.5d, true)); // OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 2, 0.6d, true)); // OK
		machine.manageOrder(new Order(Drink.CHOCOLATE, 1, 0.7d, true)); // OK
		// sold for 3 €
		
		// teas
		machine.manageOrder(new Order(Drink.TEA, 1, 0.2d, false)); // NOT OK
		machine.manageOrder(new Order(Drink.TEA, 0, 0.6d, false)); // OK
		machine.manageOrder(new Order(Drink.TEA, 1, 0.7d, false)); // OK
		machine.manageOrder(new Order(Drink.TEA, 2, 1.0d, false)); // OK
		machine.manageOrder(new Order(Drink.TEA, 0, 0.1d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.TEA, 2, 0.3d, true)); // NOT OK
		machine.manageOrder(new Order(Drink.TEA, 0, 0.6d, true)); // OK
		machine.manageOrder(new Order(Drink.TEA, 1, 0.7d, true)); // OK
		// sold for 2 €
		
		// orange juices
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 0.2d)); // NOT OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 0.8d)); // OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 0.6d)); // OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 0.5d)); // NOT OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 0.3d)); // NOT OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 1.0d)); // OK
		machine.manageOrder(new Order(Drink.ORANGE_JUICE, 1.0d)); // OK
		// sold for 2.4 €
		
		// total sold : 11€
		
		String report = machine.report();
		String[] splitReport = report.split("\n");
		Assertions.assertThat(splitReport[0]).isEqualTo("Sold coffees:6");
		Assertions.assertThat(splitReport[1]).isEqualTo("Sold chocolates:6");
		Assertions.assertThat(splitReport[2]).isEqualTo("Sold teas:5");
		Assertions.assertThat(splitReport[3]).isEqualTo("Sold orange juices:4");
		Assertions.assertThat(splitReport[4]).isEqualTo("Total maked money:11,00€");
		System.out.println("test fini");
	}
	
	@Test
	public void testOrderAvailableCoffee() {
		testTranslateOrder(new Order(Drink.COFFEE, 1, 0.6d, false));
		
		Mockito.verify(mockedBeverageQuantityChecker, Mockito.only()).isEmpty(Drink.COFFEE.getLabel());
	}
	
	@Test
	public void testOrderUnavailableOrangeJuice() {
		Mockito.when(mockedBeverageQuantityChecker.isEmpty(Drink.ORANGE_JUICE.getLabel())).thenReturn(true);
		Mockito.doNothing().when(mockedEmailNotifier).notifyMissingDrink(Drink.ORANGE_JUICE.getLabel());
		
		testTranslateOrder(new Order(Drink.ORANGE_JUICE, 1.0d));
		
		Mockito.verify(mockedBeverageQuantityChecker, Mockito.times(1)).isEmpty(Drink.ORANGE_JUICE.getLabel());
		Mockito.verify(mockedEmailNotifier, Mockito.times(1)).notifyMissingDrink(Drink.ORANGE_JUICE.getLabel());
	}
	
	private String testTranslateOrder(Order order) {
		return machine.manageOrder(order);
	}
	
	private int getRandomNumberOfSugars() {
		return new Random().nextInt(2) + 1;
	}
	
}
