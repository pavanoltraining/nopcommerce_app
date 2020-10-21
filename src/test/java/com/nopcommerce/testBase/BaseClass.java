package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;   //Log4j2
import org.apache.logging.log4j.Logger;  //Log4j2

public class BaseClass {

public WebDriver driver;
public Properties configPropObj;
public Logger logger=LogManager.getLogger(this.getClass());
	
	@BeforeClass(alwaysRun=true)// Add alwaysRun=true
	@Parameters("browser")
	public void setup(String br) throws IOException
	{
		//Load config.properties file
		configPropObj=new Properties();
		FileInputStream configfile=new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		configPropObj.load(configfile);
		//end of loading config.properties file
	
		if(br.equals("chrome"))
		{
			//headless mode setup chrome
			ChromeOptions options=new ChromeOptions();
			options.setHeadless(true);
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(options);
		}
		else if(br.equals("edge"))
		{
			//headless mode setup edge
			EdgeOptions options=new EdgeOptions();
			options.setHeadless(true);
			
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver(options);
		}
		else if(br.equals("firefox"))
		{
			//headless mode setup firefox
			FirefoxOptions options=new FirefoxOptions();
			options.setHeadless(true);
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver(options);
		}
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	

	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}
	
	
	
}
