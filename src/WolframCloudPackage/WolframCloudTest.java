package WolframCloudPackage;

/*Tests Wolfram Cloud. Automates and tests:
 * 1. Launch Wolfram Cloud
 * 2. Create new Wolfram account and checks error responses
 * 3. Access Homescreeen with new account
 * 3. Create new Notebook in Homescreen
 * 4. Checks the new Notebook interface
*/
import org.testng.annotations.*;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;
import java.util.Random;

public class WolframCloudTest{
	protected WebDriver driver;
	protected String baseUrl;
	private CloudPage myCloudPage;
	private LoginPage myLoginPage;
	private RegistrationPage myRegistrationPage;
	private Homescreen myHomescreen;
	private Notebook myNotebook;
	
	@BeforeSuite
	public void setUp() throws Exception{
		driver = new FirefoxDriver();
		baseUrl = "http://www.wolframcloud.com/";
		System.out.println("Before Suite.");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void cleanUp() throws Exception{
		driver.quit();
		System.out.println("After Suite.");
	}
	
	//Launch Wolfram Cloud site
	@Test(groups={"functest", "usrexptest"}, priority=1)
	public void testLaunch(){
		driver.get(baseUrl); //waits for page to load
		try{
			this.myCloudPage = new CloudPage(driver);
		}catch(NoSuchElementException e){
			System.out.println("CloudPage did not load properly");
		}

		Assert.assertTrue(driver.getTitle().contains("Wolfram Cloud"));
		
	}
	
	//tests login page 
	@Test(groups={"functest", "usrexptest"}, priority=2)
	public void testLogin(){
		try{
		this.myLoginPage = myCloudPage.getLoginPage();
		}catch(NoSuchElementException e){
			System.out.println("Login page could not be created");
		}
		Assert.assertTrue(driver.getTitle().contains("Sign In - Wolfram Programming Cloud"));			
	}
	
	//tests registration page
	@Test(groups={"functest", "usrexptest"}, priority=3)
	public void testRegistration(){
		try{
			this.myRegistrationPage = myLoginPage.getRegistrationPage();
		}catch(NoSuchElementException e){
			System.out.println("Registration page could not be created");
		}
		
		Assert.assertTrue(driver.getTitle().contains("Sign Up - Wolfram Programming Cloud"));			

		//use random number generator to generate login name and password 
		Random rand = new Random();
		//six char password required
		int randInt = rand.nextInt(899999) + 100000; 
		String email = Integer.toString(randInt) + "@test.com"; 
		String firstname = "test";
		String lastname = "er";
		String password = Integer.toString(randInt); 
		
		String[] info = {email, firstname, lastname, password};
		
		//checks that various invalid input produces errors		
		String[] info2 = new String[4];
		System.arraycopy(info, 0, info2, 0, info.length);
		
		//missing entries should generate errors
		for(int i = 0; i < 4; i++){
			//replace value with empty string
			info2[i] = info2[i].replace(info[i], ""); 
			myRegistrationPage.register(info2);
			Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed()); 		
			System.arraycopy(info, 0, info2, 0, info.length);
		}
		
		//fewer than 6 chars for password should generate an error
		info2[info.length-1] = info2[info.length-1].replace(info[info.length-1], Integer.toString(randInt/10)); 
		myRegistrationPage.register(info2);
		Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
		
		//email without @ should generate error
		System.arraycopy(info, 0, info2, 0, info.length);
		info2[0] = info2[0].replace(info2[0], "test.com"); 
		myRegistrationPage.register(info2);
		Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
		
		//valid entries
		try{
			this.myHomescreen = myRegistrationPage.register(info);	
		}catch(NoSuchElementException e){
			System.out.println("Could not register properly");
		}
		//check that the form entries created account successfully 
		Assert.assertEquals(driver.getTitle(),"Home - Wolfram Programming Cloud");
	}
	
	//tests Homescreen
	@Test(groups={"usrexptest"}, priority=4)
	public void testHomescreen(){	
		//make sure again that we are on Homescreen
		Assert.assertEquals(driver.getTitle(),"Home - Wolfram Programming Cloud");

		//checks that the upload button is present. 
		//Check presence of other elements similarly 
		Assert.assertTrue(driver.findElements(By.id("uploadBtn")).size() > 0);
	}
	
	//tests new document interface 
	@Test(groups={"functest", "usrexptest"}, priority=5)
	public void testNotebook(){	
		//specify what type of Document to create
		//can change to other DocTypes for other tests
		String newDocType = "Notebook";
		
		try{
			this.myNotebook = myHomescreen.createNb(newDocType);
		}catch(NoSuchElementException e){
			System.out.println(newDocType + " cannot be created");
		}

		Assert.assertTrue(driver.getTitle().contains("(unnamed) - Wolfram Programming Cloud"));			
		
		//checks that the name field says "(unnamed)"
		Assert.assertEquals(myNotebook.getNameInputVal(), "(unnamed)");
		
		//checks that the value after click has the right extension
		Assert.assertEquals(myNotebook.getNameInputExt(), "."+DocType.valueOf(newDocType).getId());
	}
	
}
