package CommonFunLibrary;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {

	public static WebDriver driver;
	
	//method start browser
	public static WebDriver startBrowser() throws Throwable 
	{
		if(PropertyFileUtil.getValueForkey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\software\\seleniumproject\\OrangeHybrideFramework\\CommonDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else	if(PropertyFileUtil.getValueForkey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "D:\\software\\seleniumproject\\OrangeHybrideFramework\\CommonDrivers\\IEDriverServer.exe");
			driver=new FirefoxDriver();
			
		}
		else {
			Reporter.log("Browser value is not matching",true);
		}
		
		return driver;
	}
	
	//method for login
	public static void openApplication() throws Throwable
	{
	driver.get(PropertyFileUtil.getValueForkey("Url"));	
	}
	//method for waitforelement
	
	public static void waitForElement(WebDriver driver, String LocatorType,String LocatorValue, String waititme)
	{
	WebDriverWait mywait= new WebDriverWait(driver, Integer.parseInt(waititme));
	if(LocatorType.equalsIgnoreCase("name"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
	}
	else if(LocatorType.equalsIgnoreCase("xpath"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
	}
	else if(LocatorType.equalsIgnoreCase("id"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
	}
	else if(LocatorType.equalsIgnoreCase("cssSelector"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(LocatorValue)));
	}
	}
	
	//method for typeAction
	public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue, String TestData )
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		else 	if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		else 	if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("cssSelector"))
		{
			driver.findElement(By.cssSelector(LocatorValue)).clear();
			driver.findElement(By.cssSelector(LocatorValue)).sendKeys(TestData);
		}
	}
	
	//method for click action
	public static void clickAction(WebDriver driver, String LocatorType ,String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else	if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();;
		}
		else	if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();;
		}
		else	if(LocatorType.equalsIgnoreCase("cssSelector"))
		{
			driver.findElement(By.cssSelector(LocatorValue)).click();;
		}
	}
	//validate title validateTitle
public static void validateTitle(WebDriver driver, String Exp_title)
	{
		
	}
	//method for close browser
	public static void closebrowser(WebDriver driver) throws Throwable
	{
		Thread.sleep(5000);
		driver.close();
	}
	
	//method for mouse click
	public static void Users(WebDriver driver) throws Throwable
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//b[normalize-space()='Admin']"))).perform();
		Thread.sleep(5000);
		ac.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='User Management']"))).click().perform();
		Thread.sleep(5000);
		ac.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='Users']"))).click().perform();
	}
}
