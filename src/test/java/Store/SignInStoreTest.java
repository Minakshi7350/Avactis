package Store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import StoreJava.SignInStroePO;
import util.DataProviderClass;

public class SignInStoreTest {
	SignInStroePO signStore;
	WebDriver driver;
	
	@Test(dataProvider ="SignIn" , dataProviderClass=DataProviderClass.class)
	  public void Login(String email,String password,String name) {
			
		signStore.SIGNIN(email, password,name);
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
  
  @BeforeMethod
  public void beforeMethod() {
	  signStore=new SignInStroePO(driver);
	  signStore.load();
  }

  @AfterMethod
  public void afterMethod() {
	  
  }

}
