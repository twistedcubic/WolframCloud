//Login page page object

package WolframCloudPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

public class LoginPage{
	private WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		//verify "create id" link is present
		if(driver.findElements(By.id("createAccount")).size() == 0){
			throw new NoSuchElementException();
		}		
	}
	
	//returns registration page to create new account 
	public RegistrationPage getRegistrationPage(){
		driver.findElement(By.id("createAccount")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return new RegistrationPage(driver);
	}

	
}