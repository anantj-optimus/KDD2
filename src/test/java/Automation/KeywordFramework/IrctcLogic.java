package Automation.KeywordFramework;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
 
//import Keywords.Defined.Assertions;
import Keywords.Defined.Keywords;
 
public class IrctcLogic {
 	WebDriver driver;
 	String path = System.getProperty("user.dir");
 
 	//create object of reusable class Keywords and Assertions
 	Keywords keyword = new Keywords();
 	//Assertions assertion = new Assertions();
 
 	@Test
 	public void readExcelandexecute() throws IOException, InterruptedException{
 
 		//From excelfile
 		String excelFilePath = path+"\\Externals\\Test Cases.xlsx"; //path to excel file saved in excelFilePath variable
 		FileInputStream fileInputStream = new FileInputStream(excelFilePath); //reads all sheets from excelFilePath
 		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); //reads data from xls sheets from fileInputStream
 		//Above code is file handling
 		
 		int testcasescount = workbook.getNumberOfSheets()-1; //reads sheets upto 1 less from last
  		System.out.println("Total test cases :"+testcasescount);
  		for (int testcase=0;testcase<testcasescount;testcase++){
  			System.setProperty("webdriver.chrome.driver", path+"\\Drivers\\chromedriver.exe");
  			driver = new ChromeDriver();
  			XSSFSheet worksheet = workbook.getSheetAt(testcase); //gets  
  			System.out.println("worksheet Number "+testcase+":"+worksheet.getSheetName());
  			int row = worksheet.getLastRowNum(); //gets number of row coumts
  			int column = worksheet.getRow(1).getLastCellNum(); //counts the col num. We pick 
  			// The first row and move to last row number  so we get the number of column 
  			for(int i=1;i<=row;i++){ //runs until last row is reached
 
  				LinkedList<String> Testexecution = new LinkedList<>();  // all excel data will be imported here in
  				// Testexecution object from where we will use which data to particularly pick
  				System.out.println("Row value :"+i+"It has first cell value as : "+worksheet.getRow(i).getCell(0));
 
  				for(int j=0;j<column-1;j++){ 
  					System.out.println("Column index :"+j);
  					Cell Criteria = worksheet.getRow(i).getCell(j);
  					String CriteriaText;
  					if(Criteria==null){
  						CriteriaText = null;
  					}else{
  						CriteriaText = Criteria.getStringCellValue();
  					}
  					Testexecution.add(CriteriaText);
  				}
  				System.out.println("List :"+Testexecution);
 
  				String TestStep = Testexecution.get(0);
  				String ObjectName = Testexecution.get(1);
  				String LocatorType = Testexecution.get(2);
  				String Testdata = Testexecution.get(3);
  				String AssertionType = Testexecution.get(4);
  				String ExpectedValue = Testexecution.get(5);
  				String ActualValue = Testexecution.get(6);
  				perform(TestStep,ObjectName,LocatorType,Testdata,AssertionType,ExpectedValue,ActualValue);
 
  				System.out.println("Row "+i+" is read and action performed");
  			}
 
  			driver.close();
  			System.out.println("************************TEST CASE "+worksheet.getSheetName()+" is executed*******************");
  		}
 	}
 
 public void perform(String operation, String objectName, String locatorType, String testdata,
String assertionType, String expectedValue, String actualValue) throws IOException, InterruptedException {
 
switch (operation) {
case "enter_URL":
//Perform click
keyword.enter_URL(driver,testdata);
break;
 
case "get_currentURL":
//Set text on control
keyword.get_currentURL(driver);
break;
 
case "type":
keyword.type(driver, objectName, locatorType, testdata);//values of the 

 
case "click":
keyword.click(driver, objectName, locatorType);
 
case "wait":
keyword.wait(driver, objectName, locatorType);
 
case "implicitWait":
Thread.sleep(100); 
 
default:
break;
}
/* 
if(operation.contains("AssertElement")){
	 
//assertion.AssertElement(locatorType, assertionType, objectName, driver);*/
 
}
 
}
 
