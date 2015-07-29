//registers the new user

package WolframCloudPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

public class RegistrationPage{
	private WebDriver driver;
	
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
		if(!"Sign Up - Wolfram Programming Cloud".equals(driver.getTitle())){ //check title of page
			throw new NoSuchElementException("This is not the login page");
		}
		
		//verify that all relevant fields are present
		String[] elements = {"email", "firstname", "lastname", "password", "password2", "signIn"};
		for(int i = 0; i < elements.length; i++){
			if(driver.findElements(By.id(elements[i])).size() == 0)
				throw new NoSuchElementException(elements[i] + "field not present");
		}
	}
	
	//enters the account info
	//returns homescreen for the newly registered user
	public Homescreen register(String[] info){
		String[] elements = {"email", "firstname", "lastname", "password"};
		
		for(int i = 0; i < elements.length; i++){
			driver.findElement(By.id(elements[i])).clear();
			driver.findElement(By.id(elements[i])).sendKeys(info[i]);
		}
		driver.findElement(By.id("password2")).clear();
		driver.findElement(By.id("password2")).sendKeys(info[info.length-1]);
		driver.findElement(By.id("signIn")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//update if next page is no longer Homescreen
		return new Homescreen(driver);
	}
	
}