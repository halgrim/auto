package de.auto;
import static de.auto.Constants.QEERY_AND_FILTER_COMBINATIONS;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.auto.po.CarBusinessCard;
import de.auto.po.FilterSideMenu;
import de.auto.po.FilterTopBar;
import de.auto.po.FindYourNewCarPage;
import de.auto.po.ResultList;
import de.auto.po.SortTopBar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutoTest extends BaseTest{

	@DataProvider(name = "filteresAndSortData", parallel = true)
    public Iterator<Object[]> countriesDataProvider(Method testMethod) throws IOException {
        return createDataProviderIterator(QEERY_AND_FILTER_COMBINATIONS);
    }
	
	@Test(dataProvider = "filteresAndSortData")
	public void verifySearchFilterAndSortingLogic(String filter, String sorting) throws MalformedURLException, InterruptedException {
		this.createDefaultDriver(this.getClass().getSimpleName());
		WebDriver driver = this.getWebDriver();
		
		new FindYourNewCarPage(driver).visitPage();
		new FilterSideMenu(driver).applyFilter(filter);
        new FilterTopBar(driver).verifyFilterHasBeenApplied("Erstzulassung von: 2015");
        new SortTopBar(driver).applySorting(sorting);
        
        ResultList sortedAndFilteredResults = new ResultList(driver);
        
        sortedAndFilteredResults.checkIfResultsAreInPriceDescendingOrder();
        
        List<Long> dates = sortedAndFilteredResults.getResultsRegistrationDate();
        for (Long carRegistrations : dates) {    	
        		assertThat("Registration date must be 2015+",carRegistrations,greaterThanOrEqualTo(2015L));
		}		
	}
}
