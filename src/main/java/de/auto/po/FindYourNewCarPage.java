package de.auto.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static de.auto.Constants.URL_SEARCH;


public class FindYourNewCarPage {

    public WebDriver driver;

    public FindYourNewCarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void visitPage() {
    	this.driver.get(URL_SEARCH);
    }


	
	
}
