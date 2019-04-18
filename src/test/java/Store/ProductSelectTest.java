package Store;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import StoreJava.productSelectPO;
import util.DataProviderClass;

public class ProductSelectTest {
	productSelectPO productSelect;
	WebDriver driver;
	
	
	// @Test(dataProvider ="SignIn" , dataProviderClass=DataProviderClass.class)
	  public void Login(String email,String password,String name) {
			
		 productSelect.SIGNIN(email, password,name);
	  }
	 
	 @Test(dataProvider ="Product" , dataProviderClass=DataProviderClass.class)
	 public void ProductSelectTest12(String locator,String Detail,String path,String prodList) throws IOException {
	  productSelect.productSelectPath(locator,Detail, path,prodList);
      }
	 @Test
	 public void selectedAddToCart() throws IOException
	 {
		 productSelect.ProductSelect();
	 }
	 
	 @Test(dataProvider ="UserDetails" , dataProviderClass=DataProviderClass.class)
	  public void UserDetails(String fname,String lname,String email,String country,String postcode,String state,String city,String add1,String add2,String phone,String name)  {	
		productSelect.userDetails(fname, lname, email, country, postcode, state,city,add1, add2, phone,name);
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
  public void before()
  {
	  productSelect=new productSelectPO(driver);
	  productSelect.load();
  }
  @AfterClass
  public void afterMethod() {
	 driver.quit();
  }


}
