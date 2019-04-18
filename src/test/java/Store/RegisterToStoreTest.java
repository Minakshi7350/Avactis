package Store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sun.istack.internal.logging.Logger;

import StoreJava.RegisterNewAccount;
import util.ConfigProperties;

public class RegisterToStoreTest {
	
	WebDriver driver;
	RegisterNewAccount MyRegisterPage;
	private Logger log;
  @Test
  public void Register() {
	  MyRegisterPage.registerNewUser(ConfigProperties.getProperty("USER_EMAIL"),
			  						ConfigProperties.getProperty("USER_PASS"), 
			  						ConfigProperties.getProperty("USER_REPASS"),
			  						ConfigProperties.getProperty("USER_FIRSTNAME"), 
			  						ConfigProperties.getProperty("USER_LASTNAME"),
			  						ConfigProperties.getProperty("USER_COUNTRY"),
			  						ConfigProperties.getProperty("USER_STATE"),
			  						ConfigProperties.getProperty("USER_ZIP"),
			  						ConfigProperties.getProperty("USER_CITY"),
			  						ConfigProperties.getProperty("USER_ADD1"),
			  						ConfigProperties.getProperty("USER_ADD2"),
			  						ConfigProperties.getProperty("USER_CONTACT"));
  }
 
  	@BeforeTest
	@Parameters("browser")
	public void  setup(String browser) throws Exception{
		System.out.println("in setup method");
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox"))
		{
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver-64bit.exe");
			driver = new FirefoxDriver();		
			}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			
			//set path to chromedriver.exe
			 System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	}
  	
  	 @BeforeClass
  	  public void before()
  	  {
  		  MyRegisterPage=new RegisterNewAccount(driver);
  		  MyRegisterPage.load();
  	  }
  	  @AfterClass
  	  public void afterMethod() {
  		 
  	  }

	
}
