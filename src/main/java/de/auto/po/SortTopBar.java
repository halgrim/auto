package de.auto.po;

import static de.auto.Constants.SORT_DESCENDING;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;;

public class SortTopBar {
	
	@FindBy(css = "div[data-qa-selector='top-options'] div[data-qa-selector='sort-select'] select[data-qa-selector='select']")
	private WebElement sortSelect;
	
	@FindBy(css = "div[data-qa-selector='results-amount']")
	private WebElement resultAmount;
	
	public WebDriver driver;

	public SortTopBar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void applySorting(String sortBy) throws InterruptedException {
		switch (sortBy) {
		case SORT_DESCENDING:
			sortByPriceDescending();
			break;
		default:
			new RuntimeException("Unknown sorting");
			break;
		}
	}

	public void sortByPriceDescending() throws InterruptedException {
		int initialFirstResultPrice = new ResultList(driver).getFirstResultPrice();
       
		Select countrySelect = new Select(sortSelect);
		countrySelect.selectByVisibleText(SORT_DESCENDING);
		int counter = -1;
		while (counter < 5000) {
		    if (initialFirstResultPrice < new ResultList(driver).getFirstResultPrice()) {
		        break;
		    }
			Thread.sleep(500);
		}
		
	}

}
