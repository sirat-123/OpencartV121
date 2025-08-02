package utilities;

import java.util.Date;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.mail.ImageHtmlEmail;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;//UI of the report
	
	public ExtentReports extent;//populate commen info on the report
	
	public ExtentTest test;// creating test case entries in the reports and update  status of the test methods
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		 /* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt=new Date();
		  String currentdatetimestamp=df.format(dt);
		*/
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time date format
		repName="Test-Report-" + timeStamp + ".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\" +repName);//specify the location of the reports
//		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReports.html");
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//title of the report
		sparkReporter.config().setReportName("opencart Functional Testing ");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Envionment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);//capturing the os name from xml file 
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser  );//capturing the browser name from xml file
		
		List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
		
	
	}
	public void onTestSuccess(ITestResult result) {// this result cantains which methods got pass 
		test=extent.createTest(result.getTestClass().getName());//create a new entry in te report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"got succsessfully executed");//update the result p/f/s 
	}
	public void onTestFailure(ITestResult result) {//this result cantains which methods got  failed
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL,result.getName()+"got failed");
	    test.log(Status.INFO,result.getThrowable().getMessage());
	    try {
	    	String imgPath=new BaseClass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgPath);
	    	
	    }catch(IOException e1) {
	    	e1.printStackTrace();
	    }
		
		
	}
	public void onTestSkiped(ITestResult result) {//this result cantains which methods got skipped
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP,result.getName()+"got skipped");
	    test.log(Status.INFO,result.getThrowable().getMessage());

		
	}
	public void onFinish(ITestContext context) {
		extent.flush();
		
		//this code open the report autometically
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		/*try 
		{ URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			//creat the email message
	      ImageHtlmEmail email=new ImageHtmlEmail();
           email.setDataSourceResolver(new DataSourceUrlResolver(url));
           email.setHostName("smtp.googlemail.com");
           email.setSmtpPort(465);
           email.setAuthenticator(new DefaultAuthenticator("ssweetychaudhary@gamil.com","password"));
           email.setSSLOnConnect(true);
           email.setFrom("ssweetychaudary@gmail.com");
           email.setSubject("Test Result");
           email.setMsg("Please find Attched Report");
           email.addTo("ssweetychaudhary@gmail.com");
           email.attach(url,"extent report","please check report..");
           email.send();
		}catch(Exception e) {
			e.printStackTrace();
		}*/
           
		}
	}



