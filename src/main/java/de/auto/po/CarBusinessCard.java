package de.auto.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.auto.CurrencyHelper;

public class CarBusinessCard {
	
	
	private WebElement carPrice ;
	private WebElement carFirstRegistrationDate ;

	public CarBusinessCard(WebElement carElement) {
		carPrice = carElement.findElement(By.cssSelector("div[data-qa-selector='price']"));
		carFirstRegistrationDate = carElement.findElement(By.cssSelector("ul[data-qa-selector='spec-list'] > li[data-qa-selector='spec']"));
	}
	
	public int getCarPrice() {
		return CurrencyHelper.reformatValueToCalulatorFormat(carPrice.getText());
	}
	
	public Long getCarRegistrationDate() {
		String numbersOnly = carFirstRegistrationDate.getText().replaceAll("[^0-9]+", "");
		return Long.valueOf(numbersOnly.trim());
	}
}
