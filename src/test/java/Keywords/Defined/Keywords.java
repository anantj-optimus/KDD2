package Keywords.Defined;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class Keywords {
 
String path = System.getProperty("user.dir");
 
WebDriver driver;

 
	public void enter_URL(WebDriver driver,String TestData) throws IOException{
		driver.get(this.getData(TestData));
	}

	public void type(WebDriver driver, String ObjectName, String locatorType, String TestData) throws IOException{
		driver.findElement(this.getObject(ObjectName,locatorType)).sendKeys(this.getData(TestData));
		//driver.findElement(By.xpath("//")).sendKeys(testdata);
	}
	
	
	
	public void wait(WebDriver driver,String ObjectName, String locatorType) throws IOException{
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(ObjectName,locatorType))));
		}
		public void click(WebDriver driver,String ObjectName, String locatorType) throws IOException{
			driver.findElement(this.getObject(ObjectName,locatorType)).click();
		}
		public String get_currentURL(WebDriver driver){
			String URL = driver.getCurrentUrl();
			System.out.println("print URL "+URL);
			return URL;
		}
		
		
		//code to make test data fetch from properties file
		public String getData(String TestData) throws IOException{
				File readfile = new File(path+"\\Externals\\or.properties");
			FileInputStream fileInputValue = new FileInputStream(readfile);
				Properties prop = new Properties();
			prop.load(fileInputValue);//load all testdata from the properties file and this code keeps the data in keyword=value format
		
			String data = prop.getProperty(TestData); //testdata variable input
			return data;
		}
 
		//code to make objectname & locatortype fetch from properties file
		By getObject(String ObjectName, String locatorType) throws IOException{
 			File file = new File(path+"\\Externals\\or.properties");
			FileInputStream fileInput = new FileInputStream(file);
 			Properties prop = new Properties();
			prop.load(fileInput);//load all data from the properties file and this load keeps the data in keyword=value format
			//return (prop.getProperty(locatorType));

			//find by xpath
			if(locatorType.equalsIgnoreCase("xpath")){
				return By.xpath(prop.getProperty(ObjectName));//this line picks objectname variable value from properties file
			}
			//find by class
			else if(locatorType.equalsIgnoreCase("CLASSNAME")){
				return By.className(prop.getProperty(ObjectName));
 			}
			//find by name
			else if(locatorType.equalsIgnoreCase("NAME")){
				return By.name(prop.getProperty(ObjectName));
 			}
			//Find by css
			else if(locatorType.equalsIgnoreCase("CSS")){
				return By.cssSelector(prop.getProperty(ObjectName));
			}
			//find by link
			else if(locatorType.equalsIgnoreCase("LINK")){
				return By.linkText(prop.getProperty(ObjectName));
			}
			//find by partial link
			else if(locatorType.equalsIgnoreCase("PARTIALLINK")){
				return By.partialLinkText(prop.getProperty(ObjectName));
 			}
			return null;
		}
 	}