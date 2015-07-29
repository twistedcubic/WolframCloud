package WolframCloudPackage;

//Notebook page object

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.NoSuchElementException;

public class Notebook{
	private WebDriver driver;
	
	public Notebook(WebDriver driver){
		this.driver = driver;
		if(driver.findElements(By.id("toolbarRenameInputField")).size() == 0)
			throw new NoSuchElementException("Name input field missing");
		
		//checks the name field initially says "(unnamed)"
		if((driver.findElements(By.xpath("//*[contains(text(), (unnamed) )]" ))).size() == 0) 
			throw new NoSuchElementException("Default input name should contain unnamed");
		
	}
	
	//returns the name field  (should say "(unnamed)")
		public String getNameInputVal(){
			String defaultVal = driver.findElement(By.className("rename-title")).getAttribute("innerHTML");
			return defaultVal;
		}
		
	//returns extension of page element for the notebook name
	public String getNameInputExt(){
		
		driver.findElement(By.id("renameButton")).click();

		String defaultExt = driver.findElement(By.id("toolbarRenameInputField")).getAttribute("value");
		return defaultExt;		
	}
}