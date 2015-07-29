package WolframCloudPackage;

//Wolfram Cloud page object
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

public class CloudPage{
	private WebDriver driver;
	
	public CloudPage(WebDriver driver){
		this.driver = driver;
		//verify the "Programming Cloud" icon is present
		//could also use findElement() and try-catch 
		if(driver.findElements(By.id("programming-tile")).size() == 0 )
			throw new NoSuchElementException("CloudPage: Programming Cloud icon not present");
	}
	
	/*clicks the Programming Cloud icon 
	returns a LoginPage page object
	*/
	public LoginPage getLoginPage(){
		driver.findElement(By.id("programming-tile")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //what if timeout
		return new LoginPage(driver);
	}
	
}