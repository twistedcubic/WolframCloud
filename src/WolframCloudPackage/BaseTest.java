package WolframCloudPackage;

import org.testng.*;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

//base tests that wrap around WolframCloudTest

public class BaseTest{
	protected WebDriver driver;
	protected String baseUrl;
	
	@BeforeSuite
	public void setUp() throws Exception{ //setup vs contructor?
		driver = new FirefoxDriver();
		baseUrl = "http://www.wolframcloud.com/";
		System.out.println("Before Suite.");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void cleanUp() throws Exception{
		System.out.println("After Suite.");
	}
	
}