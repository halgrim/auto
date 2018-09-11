package de.auto.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterTopBar {

	@FindBy(css = "li[data-qa-selector='active-filter'] button")
	private WebElement activeFilter;

	public WebDriver driver;

	public FilterTopBar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyFilterHasBeenApplied(String filter) {
		(new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.textToBePresentInElement(activeFilter, filter));
	}

	
}
