package StoreJava;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.sun.istack.internal.logging.Logger;

import util.ConfigProperties;
import util.WebTable;

public class productSelectPO {
	
	
	String Sign_name="";
	WebDriver driver;
	private Logger log;
	WebDriverWait wait;
	Actions builder;
	TakesScreenshot ts;
	String expectedresult="Welcome,";
	String firstname="",lastname="",emailid="",state="",pincod="",CCity="",address1="",address2="",contact="",country="";
	int count=0;
	private static String FINALORDERId,OrderAmt;
	
	List<String> expectedProdOptions=new ArrayList<String>();
	List<String> ActualProdOptions=new ArrayList<String>();
	List<String> ActualQtyOptions=new ArrayList<String>();
	List<String> ActualRateOptions=new ArrayList<String>();
	
	List<String> BillProdOptions=new ArrayList<String>();
	List<String> BillQtyOptions=new ArrayList<String>();
	List<String> BillRateOptions=new ArrayList<String>();

	 //Sign In
	private static final String EmailId = "//input[@id='account_sign_in_form_email_id']";
	private static final String Password = "//input[@id='account_sign_in_form_passwd_id']";
	 
	@FindBy (xpath="//input[@value='Sign In']")
	WebElement SignButton;
		
	@FindBy (xpath="//a[text()='Sign In']")
	WebElement SignInLink;
	
	@FindBy(xpath="//li/span/span[text()='Welcome,']")
	WebElement welcome;
	 
	@FindBy(xpath="//i[@class='fa fa-shopping-cart']")
	WebElement cartLink;
	
	private static final String cartCount="//a[@class='top-cart-info-count']";
		
	private static final String viewCart="//div/a[text()=\"View Cart\"]";

	private static final String CheckOut="//a[text()='Checkout ']";
	
	private static final String actualBillAddr="//form[@id=\"checkout_1\"]//span[text()='1. Billing and Shipping Addresses']";
	
	private static final String continuecheckout1="//form[@id=\"checkout_1\"]//input[@value='Continue Checkout']";
	
	private static final String actualBillmethod="//form[@id='checkout_2']//span[text()='2. Billing and Shipping Methods']";
	
	private static final String continuecheckout2="//form[@id='checkout_2']//input[@value='Continue Checkout']";
	
	private static final String shoppingRadio="//input[@value='BCE5D24D-666C-43CA-94A0-D6F775903BE2_3']";
	
	private static final String autualplaceorder="//h3[text()='Billing and Shipping Options']";
	
	private static final String placeOrder="//input[@value='Place Order']";
	
	private static final String Viewtable="//form[@id=\"Product_Quan\"]/table";
	
	private static final String prodtable="//table[@class='order_items without_images']";

	private static final String Autualfinal="//h1[text()='Thank you for your order!']";
	
	@FindBy(xpath="//div[@class='checkout_addresses']//div[contains(text(),'#')]")
	static WebElement OrderId;

	@FindBy(xpath="//ul/li[3]/strong[@class='price']")
	static	WebElement OrderAmount;
 	
	@FindBy(xpath="//input[@name='billingInfo[Firstname]']")
	WebElement Fname;
	
	@FindBy(name="billingInfo[Lastname]")
	WebElement Lname;
	
	@FindBy(name="billingInfo[Email]")
	WebElement emailID;

	@FindBy(name="billingInfo[Country]")
	WebElement Countryname;
	
	@FindBy(name="billingInfo[Postcode]")
	WebElement PostCode;
	
	@FindBy(name="billingInfo[Statemenu]")
	WebElement Statemenu;
	
	@FindBy(name="billingInfo[City]")
	WebElement city;
	
	@FindBy(name="billingInfo[Streetline1]")
	WebElement Addr1;
	
	@FindBy(name="billingInfo[Streetline2]")
	WebElement Addr2;
	
	@FindBy(name="billingInfo[Phone]")
	WebElement Phone;
	
	@FindBy(name="checkbox_shipping_same_as_billing")
	WebElement checkAddr;

	
	public productSelectPO(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;	
		ConfigProperties.loadProperties();
		PageFactory.initElements(driver, this);
		DOMConfigurator.configure("log4j-alternate.xml");
		log = Logger.getLogger(getClass());
		builder=new Actions(driver);
		
	}
	
	
	
	 public void SIGNIN(String email,String pass,String name)
	 {
		 Sign_name=name;
		 
		 SignInLink.click(); 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EmailId))).sendKeys(email);
			
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Password))).sendKeys(pass);
		
		 SignButton.click();
		 log.info("Sign In Successfully");
		 try {
			 	assertEquals(welcome.getText(), expectedresult, "Sign In failed");
				log.info("Sign In successful");
			} catch (NoSuchElementException e) {
				log.warning("Sign In failed");
			}

	 }
	 
	 
	 public void userDetails(String fname,String lname,String email,String Country,String postcode,String State,String City,String add1,String add2,String phone,String name)
		{
	
			firstname=fname;
			lastname=lname;
			emailid=email;
			country=Country;
			pincod=postcode;
			state=State;
			CCity=City;
			address1=add1;
			address2=add2;
			contact=phone;
		    Sign_name=name;
				
		}

	 public void productSelectPath(String locat,String Detail,String path,String prodList)
	 {
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locat))).click(); 
		   if(!Detail.equals("null"))
		   {
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Detail))).click();
	           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path))).click();     
		   }
		   else
		   if(Detail.equals("null"))
		   {
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path))).click();   
		   }
			  count++;
			  expectedProdOptions.add(prodList); 
			  
	 }
	 
	 
	public void ProductSelect() throws IOException
	{
		// COUNT NO. OF PRODUCT IN THE CART	
		WebElement cartc=driver.findElement(By.xpath(cartCount));
		String countarray[]=cartc.getText().split(" ");
		int cartCount=Integer.parseInt(countarray[0]);
		if(cartCount>0 && cartCount==expectedProdOptions.size())
		{
			log.info("Product Count match");
		}
		else if(cartCount!=expectedProdOptions.size())
		{
			log.warning("product removed from cart");
			count=cartCount;
		}
		
		// CHECK PRODUCT INSERT INTO CART IS CORRECT OR NOT
		
		builder.moveToElement(cartLink).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(viewCart))).click();
		WebTable table = new WebTable(driver.findElement(By.xpath(Viewtable)));
		//System.out.println("row     "+table.getRowCount()+"column      "+table.getColumnCount()+"cell data: "+table.getCellData(2,2));
		
		for(int i=2;i<=table.getRowCount();i++)
		{
			//System.out.println("row     "+table.getRowCount()+"column      "+table.getColumnCount()+"    cell data "+table.getCellData(i,1));
			ActualProdOptions.add(table.getCellData(i,1));
			ActualQtyOptions.add(table.getCellData(i,2));
			ActualRateOptions.add(table.getCellData(i,3));
			
		}
		
		 assertEquals(ActualProdOptions, expectedProdOptions,"Product Selected are not Expected");

		
		//CHECKOUT
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CheckOut))).click();
	
		
		//PAGE REDIRECTION VERIFICATION
		try
		{
			String ExpectedBillAddress="1. Billing and Shipping Addresses";
			WebElement actualBillAddress=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualBillAddr)));
		assertTrue(actualBillAddress.getText().toLowerCase().contains(ExpectedBillAddress.toLowerCase()), "Not Redirected");
		log.info("Redirected on Next Page");
		}
		catch(NoSuchElementException ex)
		{
			log.warning("Not Redirected on Next Page");
		}
		
		if(Sign_name.equals("SIGNIN"))
		{
		String welcomeText=welcome.getText();
		if(welcomeText.equals("Welcome,"))
		{
			PurchaseProduct();
		}
		}
		else
		{
			Fname.sendKeys(firstname);
			Lname.sendKeys(lastname);
			Countryname.sendKeys(country);
			Statemenu.sendKeys(state);
			emailID.sendKeys(emailid);
			Addr1.sendKeys(address1);
			Addr2.sendKeys(address2);
			Phone.sendKeys(contact);
			PostCode.sendKeys(pincod);
			city.sendKeys(CCity);
			checkAddr.click();
			PurchaseProduct();
		}
			
	}
	//TAKING VALUE OF ORDER NO AND AMOUNT
	public static String Senddata1()
	{
     	final	String OrderID[]=OrderId.getText().split("#");
		FINALORDERId=OrderID[1];
		return FINALORDERId;
	}
	
	public static String Senddata2()
	{
		OrderAmt=OrderAmount.getText();		
		return OrderAmt;		
	}

	public void PurchaseProduct() throws IOException
	{
		System.out.println("In purchase product");
		//CLICK CONTINUE CHECKOUT BUTTON 1 
		WebElement continueCheckout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(continuecheckout1)));
		continueCheckout.click();
		try
		{
			String ExpectedBillMethods="2. Billing and Shipping Methods";
			WebElement actualBillMethods=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualBillmethod)));
					
		assertTrue(actualBillMethods.getText().toLowerCase().contains(ExpectedBillMethods.toLowerCase()), "Not Redirected");
		log.info("Redirected on Next Page");
		}
		catch(NoSuchElementException ex)
		{
			log.warning("Not Redirected on Next Page");
		}
	
		//SELECT SHIPPING METHOD
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shoppingRadio))).click();
		
		//CLICK ON CONTINUE CHECKOUT BUTTON 2
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(continuecheckout2))).click();
		
		try
		{
		
		String expectedplaceorder="Billing and Shipping Options";
		WebElement autualPlaceorder=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(autualplaceorder)));
				
		assertTrue(autualPlaceorder.getText().toLowerCase().contains(expectedplaceorder.toLowerCase()), "Not Redirected to place order page");
		log.info("Redirected to place order page");
		}
		catch(NoSuchElementException ex)
		{
			log.warning("Not Redirected to place order page");
		}
		
		// VERIFY PRODUCT SELECTED QUANTITY AND RATE ARE MATCHED
		
		WebTable table1 = new WebTable(driver.findElement(By.xpath(prodtable)));	
		for(int i=2;i<=table1.getRowCount();i++)
		{
			//System.out.println("row     "+table1.getRowCount()+"column      "+table1.getColumnCount()+"    cell data "+table1.getCellData(i,1));
			BillProdOptions.add(table1.getCellData(i,0));
			BillQtyOptions.add(table1.getCellData(i,1));
			BillRateOptions.add(table1.getCellData(i,2));
			
		}

		 assertEquals(BillProdOptions, ActualProdOptions,"Product Selected are not Expected");
		// assertTrue(BillQtyOptions.contains(ActualQtyOptions),"Qty Selected are not Expected");
		 assertEquals(BillRateOptions, ActualRateOptions,"Rate Selected are not Expected");
		
		//CLICK ON PLACE OREDR BUTTON
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(placeOrder))).click();
		
		//CHECK ORDER PLACED OR NOT
		try
		{
		
		String expectedfinal="Thank you for your order!";
		WebElement autualfinal=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Autualfinal)));
				
		assertTrue(autualfinal.getText().toLowerCase().contains(expectedfinal.toLowerCase()), "ERROR IN PLACING ORDER....!");
		log.info("ORDER PLACED SUCCESSFULLY...!");
		}
		catch(NoSuchElementException ex)
		{
			log.warning("ERROR IN PLACING ORDER....!");
		}
		
		//SCREENSHOT OF OREDER PLACED
		try
		{
			ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);			
			Files.copy(srcFile, new File ("c:\\main_page_success.png"));
		}
		catch(Exception e){
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File ("c:\\main_page_failure.png"));
		}		
	}
	
	
	public void load()
	 {
		 driver.get(ConfigProperties.getProperty("STORE_URL"));
		 driver.manage().window().maximize();
		 wait=new WebDriverWait(driver, 30,100);
		
	 }

}
