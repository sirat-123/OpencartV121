package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
//import java.util.logging.LogManager;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

//import com.beust.jcommander.Parameters;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.logging.log4j.core.Logger;
// this Base class keep commen method which are commens for multiple testcases
public class BaseClass {
	
	public static WebDriver driver;
	
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	void setup(String os,String br) throws IOException {
		
		//loading config.properties
		
		 FileReader file=new FileReader("./src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
//		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
//			DesiredCapabilities capabilities=new DesiredCapabilities();
//			//os
//			
//			if(os.equalsIgnoreCase("windows")) {
//				capabilities.setPlatform(Platform.WIN10);
//			}
//			else if(os.equalsIgnoreCase("mac")) {
//				capabilities.setPlatform(Platform.MAC);
//			}
//			else {
//				System.out.println("No matching os");
//				return;
//			}
//			//browser
//			switch(br.toLowerCase()) {
//			case "chrome":capabilities.setBrowserName("chrome");break;
//			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
//			default:System.out.println("No matching browser");return;
//			}
//			
//			driver=new RemoteWebDriver(new URL("http://localhost:4444/WD/hub"),capabilities);
//		}
		
		
		
//		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
//			switch(br.toLowerCase()){
//			
//			case "chrome": driver=new ChromeDriver();break;
//			case "edge": driver=new EdgeDriver();break;
//			case"firefox": driver=new FirefoxDriver();break;
//			default:System.out.println("Invalid browser name");return;
//				
//			}
//		}
		//
		switch(br.toLowerCase()){
		
		case "chrome": driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case"firefox": driver=new FirefoxDriver();break;
		default:System.out.println("Invalid browser name");return;
			
		}
		
		
		
//		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL2"));//reading url from properties file
		driver.manage().window().maximize();
		
		
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	void tearDown() {
		
	}
	
	 public String randomeString() {
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		 } 
		 
		 public String randomeNumber() {
				String generatedNumber=RandomStringUtils.randomNumeric(10);
				return generatedNumber;
			 } 
		 public String randomeAlphaNumberic() {
				String generatedString=RandomStringUtils.randomAlphabetic(3);
				String generatedNumber=RandomStringUtils.randomNumeric(3);
				
				return (generatedString+generatedNumber);
			 } 
		 //capturing the screenshots
		 public String captureScreen(String tname)throws IOException{
			String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
			File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
			
		    String targetFilePath=System.getProperty("user.dir")+"screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
		    
		    sourceFile.renameTo(targetFile);
		    
		    return targetFilePath;
		 }
}
