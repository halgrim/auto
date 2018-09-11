package de.auto.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultList {

	@FindBy(css = "div[data-qa-selector='ad-items'] > div")
	List<WebElement> resultList = new ArrayList<WebElement>();

	List<CarBusinessCard> carResultList = new ArrayList<CarBusinessCard>();

	public WebDriver driver;

	public ResultList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		for (WebElement result : resultList) {
			carResultList.add(new CarBusinessCard(result));
		}
	}

	public int getFirstResultPrice() {
		return carResultList.get(0).getCarPrice();
	}

	public boolean checkIfResultsAreInPriceDescendingOrder() {
		// Possible improvement: implement deep cloning list before sorting
		Comparator<CarBusinessCard> employeeNameComparator = Comparator.comparing(CarBusinessCard::getCarPrice);

		Collections.sort(carResultList, employeeNameComparator.reversed());
		int counter = 0;
		for (WebElement singleResutlParent : resultList) {
			int priceFromUnsortedList = new CarBusinessCard(singleResutlParent).getCarPrice();
			int priceFromSortedList = carResultList.get(counter).getCarPrice();
			if (priceFromUnsortedList != priceFromSortedList) {
				return false;
			}
			counter++;
		}
		return true;
	}
	
	public List<Long> getResultsRegistrationDate() {
		List<Long> registrationDates = new ArrayList<>();
		for (CarBusinessCard result : carResultList) {
			registrationDates.add(result.getCarRegistrationDate());
		}
		return registrationDates;
	}

}
