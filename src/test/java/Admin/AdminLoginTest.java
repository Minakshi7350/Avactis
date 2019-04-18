package Admin;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sun.istack.internal.logging.Logger;

import util.ConfigProperties;

public class AdminLoginTest {
	
	Login MyLoginPage;
	private Logger log;
	WebDriver driver;
    @Test(description="Invalid Login Scenario with wrong username and password.")
 
  public void AvactisAdminLogin() {
	  MyLoginPage.AdminLogin(ConfigProperties.getProperty("ADMIN_MAIL"), ConfigProperties.getProperty("ADMIN_PASSWORD"));
	  log.info("Login Successfully");
	  assertEquals(MyLoginPage.getNextPageTitle(), ConfigProperties.getProperty("PAGE_TITLE"),"Page is not loaded");
	  
  }
  
  @BeforeTest
	@Parameters("browser")
	public void  setup(String browser) throws Exception{
		
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
  public void beforeMethod() {
	  MyLoginPage=new Login(driver);
	  MyLoginPage.load();
	
  }

  @AfterClass
  public void afterMethod() {
	  
  }

}
