package de.auto;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


public class BaseTest {

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public WebDriver getWebDriver() {
        return webDriver.get();
    }
    
    protected void createDefaultDriver(String testName) throws MalformedURLException {
    	ChromeOptions desiredCapabilities = new ChromeOptions();
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        desiredCapabilities.setCapability("name", testName);
        desiredCapabilities.addArguments("--window-size=1920,1080");
        
        webDriver.set(new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                desiredCapabilities));
    }

    protected Iterator<Object[]> createDataProviderIterator(String parameterFile) throws IOException {
        List<Object[]> testData = new ArrayList<>();

        ClassLoader loader = AutoTest.class.getClassLoader();
        Path labelsAndSymbolsFilePath = Paths.get(loader.getResource(parameterFile).getPath());

        Files.lines(labelsAndSymbolsFilePath).forEach(line ->
                testData.add((Object[]) line.split(";"))
        );

        return testData.iterator();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
    	webDriver.get().quit();
    }


}