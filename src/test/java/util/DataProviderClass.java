package util;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	ReadExcelDemo exceldata;
	PropertyFileRead propertiesfile;
	ConfigProperties property = new ConfigProperties();
	ReadExcelDemo read = new ReadExcelDemo();
	 public DataProviderClass() {
		// TODO Auto-generated constructor stub
		ConfigProperties.loadProperties();
	}

	 @DataProvider(name = "SignIn")
	  public Object[][] dataProviderMethod()
	  {
		
		 //Object retObjArr[][]=getDataFromXLSUsingJXL("E:\\Eclipse\\Avactis\\src\\test\\resources\\Data\\LoginSignIn.xls","Sheet1","StartAndEnd"); 
		  Object retObjArr[][]=read.getDataFromXLSUsingJXL(ConfigProperties.getProperty("FILE_PATH"),ConfigProperties.getProperty("SHEET_NAME"),ConfigProperties.getProperty("STARTEND")); 
		  return(retObjArr);
	  }
	 
	  @DataProvider (name="Product")
	  public Object[][] dataProviderMethod1() throws IOException
	  {
		  
			  Object retObjArr[][]=read.getDataFromXLSUsingJXL(ConfigProperties.getProperty("FILE_PATH_PROD"),ConfigProperties.getProperty("SHEET_NAME_PROD"),ConfigProperties.getProperty("STARTEND_PROD"));
	         return(retObjArr);
		 
	  }

	  @DataProvider (name="UserDetails")
	  public Object[][] dataProviderMethodUserDetails() throws IOException
	  {
		  System.out.println("in data provider");
		  Object retObjArr[][]=read.getDataFromXLSUsingJXL("E:\\Eclipse\\Avactis\\src\\test\\resources\\Data\\UserDetails.xls","userDtls","StartAndEnd");
		  System.out.println("last   ");
		  // Object retObjArr[][]=read.getDataFromXLSUsingJXL(ConfigProperties.getProperty("FILE_PATH_GU"),ConfigProperties.getProperty("SHEET_NAME_GU"),ConfigProperties.getProperty("STARTEND_GU"));
	         return(retObjArr);
		
	  }

}
