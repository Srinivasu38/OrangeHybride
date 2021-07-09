package DriverFactory;

import org.openqa.selenium.WebDriver;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {


	public static WebDriver driver;
	
	//golbal decraration
	String inputpath="D:\\software\\seleniumproject\\OrangeHybrideFramework\\TestInput\\OrangeHybrid.xlsx";
String outputpath="D:\\software\\seleniumproject\\OrangeHybrideFramework\\TestOutput\\OrangeHybrid.xlsx";
	
public void startTest() throws Throwable
{
	//creating object for accessing excel util
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	
	//iterate all rows in master testcase
	for(int i=1;i<=xl.rowcount("MasterTestCase");i++)
	{
		String moduleStatus="";
		if(xl.getCellData("MasterTestCase", i, 2).equalsIgnoreCase("Y"))
		{
			//define module name
			String TCModule=xl.getCellData("MasterTestCase", i, 1);
			
			for(int j=1;j<=xl.rowcount(TCModule);j++)
			{
				String Description=xl.getCellData(TCModule, j, 0);
				String FunctionName=xl.getCellData(TCModule, j, 1);
				String LocatorType=xl.getCellData(TCModule, j, 2);
				String LocatorValue=xl.getCellData(TCModule, j, 3);
				String TestData=xl.getCellData(TCModule, j, 4);
				
				try
				{
					if(FunctionName.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionLibrary.startBrowser();
					}
					else if(FunctionName.equalsIgnoreCase("openApplication"))
					{
					FunctionLibrary.openApplication();
					}
					else if(FunctionName.equalsIgnoreCase("waitForElement"))
					{
					FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
					}
					else if(FunctionName.equalsIgnoreCase("typeAction"))
					{
					FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
					}
					else if(FunctionName.equalsIgnoreCase("clickAction"))
					{
					FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
					}
					else if(FunctionName.equalsIgnoreCase("validateTitle"))
					{
						Thread.sleep(5000);
					FunctionLibrary.validateTitle(driver, TestData);
					}
					else if(FunctionName.equalsIgnoreCase("Users"))
					{
						Thread.sleep(5000);
						FunctionLibrary.Users(driver);
					}
					else if(FunctionName.equalsIgnoreCase("closebrowser"))
					{
						Thread.sleep(5000);
					FunctionLibrary.closebrowser(driver);
					}
					//write as pass into status
					xl.setCellData(TCModule, j, 5, "pass", outputpath);
					moduleStatus="true";
				
			}catch (Exception e) {
			System.out.println(e.getMessage());
			xl.setCellData(TCModule, j, 5, "Fail", outputpath);
			moduleStatus="false";
			}
				if(moduleStatus.equalsIgnoreCase("True")) {
					xl.setCellData("MasterTestCase", i, 3, "pass", outputpath);
				}
				else
				{
					xl.setCellData("MasterTestCase", i, 3, "Fail", outputpath);
				}
		}
	}
		else
		{
		xl.setCellData("MasterTestCase", i, 3, "Not Executed", outputpath);
		}
}
}
}

