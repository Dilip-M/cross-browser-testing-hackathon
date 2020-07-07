package com.applitools.CommonMethods;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonLibrary {
	public static WebDriver driver;
	public static Eyes eyes;
	public static VisualGridRunner visualGridRunner;
	public static EyesRunner runner;
	public static String viewport = "1200X700";
	public static String device = "Laptop";
	public static String task1 ="Task 1";
	public static String task2 ="Task 2";
	public static String task3 ="Task 3";
	public static String appName = "AppliFashion";
	public static String testname1 ="Cross-Device Elements Test";
	public static String testname2 ="Check the checkbox and Click Filter button";
	public static String testname3 ="Product Details test";
	public static String logoId = "logo";
	public static String blackId = "LABEL__containerc__104"; 
	public static String filterId = "ti-filter";
	public static String filterBtnId = "filterBtn"; 
	public static String productId = "product_grid";
	public static String product1Id = "product_1";
	public static String productPageId = "MAIN__bggray__64"; 
	public static String batchName = "UFG Hackathon";
	public static String apiKey = "TD7obUwRNYYzWBI8NzitWnaEbYW6dTqwuEv1PixPVzI110";
	public static String filename;
	Boolean traditional = true;
	
	
	public void invokeBrowser(String sBrowserType,String url){

		if(sBrowserType.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		} else if (sBrowserType.equalsIgnoreCase("firefox")){
			//System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver");
			driver = new FirefoxDriver();
		} else {
			driver = new SafariDriver();
		}
		driver.get(url);

		if (driver.getCurrentUrl().contains("gridHackathonV1")) {
			filename = "./Traditional-V1-TestResults.txt";
		}
		else {
			filename = "./Traditional-V2-TestResults.txt";
		}
	}
	
	public void setupTraditional(){
		runner = new ClassicRunner();
		eyes = new Eyes(runner);
		// Initialize eyes Configuration
		Configuration config = new Configuration();
		// create a new batch info instance and set it to the configuration
		// You can get your api key from the Applitools dashboard
		config.setApiKey(apiKey);
		
		config.setBatch(new BatchInfo(batchName));
		// Set the configuration object to eyes
		eyes.setConfiguration(config);
	}
	
	public void setupModern(){
		traditional = false;
		visualGridRunner = new VisualGridRunner(10);
		eyes = new Eyes(visualGridRunner);
		// Initialize eyes Configuration
		Configuration config = new Configuration();
		
		// You can get your api key from the Applitools dashboard
		config.setApiKey(apiKey);
		
		// create a new batch info instance and set it to the configuration
		config.setBatch(new BatchInfo(batchName));
		
		// Add browsers with different viewports
		config.addBrowser(1200, 700, BrowserType.CHROME);
		config.addBrowser(1200, 700, BrowserType.FIREFOX);
		config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
		config.addBrowser(768, 700, BrowserType.CHROME);
		config.addBrowser(768, 700, BrowserType.FIREFOX);
		config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);
		
		// Add mobile emulation devices in Portrait mode
		config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
		config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
		
		// Set the configuration object to eyes
		eyes.setConfiguration(config);
	}

 public void performTask1(String browser) {

		try {
			// Call Open on eyes to initialize a test session
			eyes.open(driver,appName,task1, new RectangleSize(800, 600));
			
			eyes.check(Target.window().fully().withName(testname1));
			WebElement appLogo =  findElement(logoId); 
			if(traditional)
			hackathonReporter(1,testname1,logoId,browser,appLogo.isDisplayed());
			
			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();
		}  catch (Exception e) {
			System.out.println("performTask1:"+e.getMessage());
		}
		finally  {
			eyes.abortAsync();
		}

	}
	public void performTask2(String browser) {

		try {
			// Call Open on eyes to initialize a test session
			eyes.open(driver,appName,task2, new RectangleSize(800, 600));
			WebElement blackLabel = findElement(blackId);
			WebElement filterIcon = findElement(filterId);
			if (filterIcon.isDisplayed()){
				clickElement(filterIcon,"filterIcon");
				WebElement blackfilter = findElement(blackId);
				clickElement(blackfilter,"blackfilter");
			}
			else {
				clickElement(blackLabel,"blackLabel");
			}
			
			WebElement filterBtn = findElement(filterBtnId);
			clickElement(filterBtn,"filterBtn");
			
			WebElement product = findElement(productId);
					
			eyes.checkRegion(By.id(productId),testname2);
			if(traditional)
			hackathonReporter(2,testname2,productId,browser,product.isDisplayed());
			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();

		}  catch (Exception e) {
			System.out.println("performTask2:"+e.getMessage());
		}
		finally  {
			eyes.abortAsync();
		}

	}

	public void performTask3(String browser) {

		try {

			// Call Open on eyes to initialize a test session
			eyes.open(driver,appName,task3, new RectangleSize(800, 600));
			
			WebElement firstProduct = findElement(product1Id);
			clickElement(firstProduct,"firstProduct");
			
			eyes.check(Target.window().fully().withName(testname3));
			WebElement productPage = findElement(productPageId);
			if(traditional)
			hackathonReporter(3,testname3,productPageId,browser,productPage.isDisplayed());
			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();

		} catch (Exception e) {
			System.out.println("performTask3:"+e.getMessage());
		}
		finally  {
			
			eyes.abortAsync();
		}

	}
	public void tearDownTraditional() {
		// Close the browser
		driver.quit();
		
		
		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);
		System.out.println(allTestResults);
	}
	public void tearDownModern() {
		// Close the browser
		driver.quit();
		

		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = visualGridRunner.getAllTestResults(false);
		System.out.println(allTestResults);
	}
	
	public WebElement findElement(String domId) {
		WebElement element = null;
		try {
		element = driver.findElement(By.id(domId));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return element;
	}
	
	public void clickElement(WebElement element,String description) {
		try {
			if (element != null) {
				element.click();
			}
			else {
				System.out.println("Not able to click element"+ description);
			}
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean hackathonReporter(int task, String testName, String domId,String browser, boolean comparisonResult) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
	        writer.write("Task: " + task + ", Test Name: " + testName +", DOM Id: " + domId + ", Browser: " + browser 
	        		+ ", Viewport: " + viewport + ", Device: " + device + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
	        writer.newLine();
	        writer.close();
	    }catch(Exception e){
	        System.out.println("Error writing to report file");
	        e.printStackTrace();
	    }
		//returns the result so that it can be used for further Assertions in the test code.
		return comparisonResult;
	}

}