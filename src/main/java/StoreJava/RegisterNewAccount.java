package StoreJava;


import static org.testng.Assert.assertEquals;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sun.istack.internal.logging.Logger;

import util.ConfigProperties;

public class RegisterNewAccount {
	
	WebDriver driver;
	private Logger log;
	String expectedresult = "Account created successfully. You are now registered.";
	
	@FindBy(xpath="//div[contains(text(),'Account created successfully. You are now registered.')]")
	public WebElement usercreatescuss;
	
	@FindBy (xpath="//a[text()='Register']")
	WebElement Register;
		
	@FindBy (name="customer_info[Customer][Email]")
	WebElement UserEmail;
	
	@FindBy (name="customer_info[Customer][Password]")
	WebElement UserPass;
	
	@FindBy (name="customer_info[Customer][RePassword]")
	WebElement UserRePass;
	
	@FindBy (name="customer_info[Customer][FirstName]")
	WebElement UserFirstName;
	
	@FindBy (name="customer_info[Customer][LastName]")
	WebElement UserLastName;
	
	@FindBy (id="customer_info_Customer_Country")
	WebElement UserCountry;
	
	@FindBy (id="customer_info_Customer_State")
	WebElement UserState;
	
	@FindBy (name="customer_info[Customer][ZipCode]")
	WebElement UserZipCode;
	
	@FindBy (name="customer_info[Customer][City]")
	WebElement UserCity;
	
	@FindBy (name="customer_info[Customer][Streetline1]")
	WebElement UserAdd1;
	
	@FindBy (name="customer_info[Customer][Streetline2]")
	WebElement UserAdd2;
	
	@FindBy (name="customer_info[Customer][Phone]")
	WebElement UserContact;
	
	@FindBy (xpath="//input[@type='submit']")
	WebElement RegisterUser;
	
	 public RegisterNewAccount(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 
		 this.driver=driver;
			ConfigProperties.loadProperties();
			PageFactory.initElements(driver, this);
			DOMConfigurator.configure("log4j-alternate.xml");
			 log = Logger.getLogger(getClass());
			 
	}
	  
	 public void registerNewUser(String mail,String pass,String Repass,String fname,String lname,String country
			 ,String state,String zip,String city,String Add1,String Add2,String contact)
	 {
		
		 Register.click();
		 UserEmail.sendKeys(mail);
		 UserPass.sendKeys(pass);
		 UserRePass.sendKeys(Repass);
		 UserFirstName.sendKeys(fname);
		 UserLastName.sendKeys(lname);
		 UserCountry.sendKeys(country);
		 UserState.sendKeys(state);
		 UserZipCode.sendKeys(zip);
		 UserCity.sendKeys(city);
		 UserAdd1.sendKeys(Add1);
		 UserAdd2.sendKeys(Add2);
		 UserContact.sendKeys(contact);
		 RegisterUser.click();

		 log.info("Registration Page Loaded Scussefully and User Data Entered");
			try {
				assertEquals(usercreatescuss.getText(), expectedresult, "User Allready Registered");
				log.info("User Registered Scussfully");
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.warning("User Allready Registered");
			}

	 }
	 
	 public void load()
	 {
		 driver.get(ConfigProperties.getProperty("STORE_URL"));
		 driver.manage().window().maximize();
	 } 
}
