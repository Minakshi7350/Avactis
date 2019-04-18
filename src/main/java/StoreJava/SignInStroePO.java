package StoreJava;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.istack.internal.logging.Logger;

import util.ConfigProperties;

public class SignInStroePO {
	
	private Logger log;
	WebDriver driver;
	WebDriverWait wait;
	String expectedresult="Welcome,";
	
	@FindBy(xpath="//li/span/span[text()='Welcome,']")
	WebElement welcome;
	
	
	@FindBy (xpath="//a[text()='Sign In']")
	WebElement SignInLink;
	
	@FindBy (id="//input[@id='account_sign_in_form_email_id']")
	WebElement EmailId;

	@FindBy (id="//input[@id='account_sign_in_form_passwd_id']")
	WebElement Password;

	
	@FindBy (xpath="//input[@value='Sign In']")
	WebElement SignButton;
	
	 public SignInStroePO(WebDriver driver) {
			// TODO Auto-generated constructor stub
				this.driver=driver;
				ConfigProperties.loadProperties();
				PageFactory.initElements(driver, this);
				DOMConfigurator.configure("log4j-alternate.xml");
				 log = Logger.getLogger(getClass());
				 
		}
	 
	 public void SIGNIN(String email,String pass,String name)
	 {
		
		SignInLink.click(); 
		WebElement Search=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account_sign_in_form_email_id")));
		Search.sendKeys(email);
			
		WebElement Passwords=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account_sign_in_form_passwd_id")));
		Passwords.sendKeys(pass);

		SignButton.click();
		log.info("Sign In Successfully");
		try {
				assertEquals(welcome.getText(), expectedresult, "Sign In failed");
				log.info("Sign In successful");
			} catch (NoSuchElementException e) {
				
				log.warning("Sign In failed");
			}

	 }

	 public void load()
	 {
		 driver.get(ConfigProperties.getProperty("STORE_URL")); 
		 driver.manage().window().maximize();
		 wait=new WebDriverWait(driver, 30,500);
			
	 }

}
