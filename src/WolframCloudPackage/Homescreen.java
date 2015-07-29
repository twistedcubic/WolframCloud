package WolframCloudPackage;

//checks Homescreen for newly registered user, make sure elements are present
//verify drop down buttons
//a function that creates new document of chosen type
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Homescreen{
	private WebDriver driver;
	
	public Homescreen(WebDriver driver){
		this.driver = driver;
	}
	
	//creates new notebook of docType type
	//types are as specified by DocType enum
	public Notebook createNb(String type){
		DocType newType = DocType.valueOf(type); 
		//not necessary to convert to DocType type, but this allows 
		//for more general tests and more clarity
		
		//"folder" creation is unavailable for the free plan		
		driver.findElement(By.className("newNotebookBtn-dropdown")).click();
		driver.findElement(By.id(newType.getId())).click();
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//switch to new window	
		for(String handle : driver.getWindowHandles()){
			driver.switchTo().window(handle);
		}
		return new Notebook(driver);		
	}
	
}