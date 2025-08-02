package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
  
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path=".\\testdata\\Opencart_LoginData.xlsx";// taking xl file from testdata
		
		
		ExcelUtility xlutil=new ExcelUtility(path);// creating an object for xlUtility
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		String logindata[][]=new String [totalrows][totalcols];//created for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[i-1][j]=xlutil.getCellData("Sheet",i, j);// 0 ,1
			}
		}
		
		return logindata;// returning two dimension array
		
		
	}
	//dataprovider
	
}
