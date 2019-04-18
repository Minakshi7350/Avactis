package Admin;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import StoreJava.productSelectPO;
import util.ConfigProperties;
import util.WebTable;

public class Login {
	
	WebDriver driver;
	WebDriverWait wait;
	@FindBy (xpath= "//div/input[@name='AdminEmail']")
	private WebElement AdminMail;
	
	@FindBy (xpath="//div/input[@name='Password']")
	private WebElement AdminPass;
	
	@FindBy (xpath="//button[text()='Sign In ']")
	private WebElement SignIn;
	
	private static final String OrderLink="//li[@id=\"menu-orders\"]/a";
	private static final String ordertable="//table[@id='datatable_orders']";
	
	
	
	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 
			this.driver=driver;
			ConfigProperties.loadProperties();
			driver.manage().window().maximize();
			PageFactory.initElements(driver, this);		
	}
  
	
	
  public void AdminLogin(String user,String pass) {
	  
	  AdminMail.sendKeys(user);
	  AdminPass.sendKeys(pass);
	  SignIn.click();
	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderLink))).click();
		WebTable table1 = new WebTable(driver.findElement(By.xpath(ordertable)));	
		String OrderId=table1.getCellData(2,1);
		String Amount=table1.getCellData(2,5);
		System.out.println("row     "+table1.getRowCount()+"column      "+table1.getColumnCount()+"    cell data "+table1.getCellData(2,1)+"cell data 2 "+table1.getCellData(2,5));
		String data1=productSelectPO.Senddata1();
		String data2=productSelectPO.Senddata2();
		assertEquals(OrderId, data1,"Id not Match");
		assertEquals(Amount, data2,"amount not match");

	  
  }
  
  protected void load() {
		// TODO Auto-generated method stub
	  driver.get(ConfigProperties.getProperty("ADMIN_URL"));
	  wait=new WebDriverWait(driver, 30,100);
	}

  public String getNextPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

}
