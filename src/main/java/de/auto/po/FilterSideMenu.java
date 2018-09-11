package de.auto.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FilterSideMenu {

	@FindBy(css = "div[data-qa-selector='filter-year']")
	private WebElement filterByYear;

	@FindBy(css = "div[data-qa-selector='filter-year'] select[data-qa-selector='select']")
	private WebElement filterByYearSelector;

	public WebDriver driver;

	public FilterSideMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void applyFilter(String filter) {
		filterByYear.click();
		String[] key_value = filter.split("\\|");
		switch (key_value[0]) {
		case "registrationDate":
			selectRegisationDate(key_value[1]);
			break;
		default:
			new RuntimeException("Unknown Filter");
			break;
		}
	}

	public void selectRegisationDate(String date) {
		Select countrySelect = new Select(filterByYearSelector);
		countrySelect.selectByVisibleText(date);
	}

}