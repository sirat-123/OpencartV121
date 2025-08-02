package utilities;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtility {
	public static FileInputStream fi;
     public static FileOutputStream fo;
     public static XSSFWorkbook workbook;
     public static XSSFSheet sheet;
     public static XSSFRow row;
     public static XSSFCell cell;
     public static CellStyle style;
     public static String path;
     
     public ExcelUtility(String path) {
    	 this.path=path;
     }
     
     public static int getRowCount(String sheetName)throws IOException{
    	 fi=new FileInputStream(path);
    	 workbook=new XSSFWorkbook(fi);
    	 
    	 sheet=workbook.getSheet(sheetName);
    	int rowCount=sheet.getLastRowNum();
    	workbook.close();
    	fi.close();
    	return rowCount;
     }
     public static int getCellCount(String sheetName,int rownum)throws IOException{
    	 fi=new FileInputStream(path);
    	 workbook=new XSSFWorkbook(fi);
    	 sheet=workbook.getSheet(sheetName);
    	row=sheet.getRow(rownum);
    	int cellCount=row.getLastCellNum();
    	workbook.close();
    	fi.close();
    	return cellCount;
    	
     }
     public static String getCellData(String sheetName,int rownum,int column )throws IOException{
    	 fi=new FileInputStream(path);
    	 workbook=new XSSFWorkbook(fi);
    	 sheet=workbook.getSheet(sheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(column);
    	String data;
    	try {
//    		data=cell.toString();
    		
    		//to read data from cell directly
    		DataFormatter formatter=new DataFormatter();
    		data=formatter.formatCellValue(cell);//returns the formated value of a cell as a string
    		
    	}catch(Exception e) {
    		data="";
    	}
    	workbook.close();
    	fi.close();
    	return data;
    	
     }
     public static void setCellData(String sheetName,int rownum,int colnum,String data)throws Exception{
    	  
    	 File xfile=new File(path);
    	 if(!xfile.exists()) {
    		 workbook=new XSSFWorkbook();
    		 fo=new FileOutputStream(path);
    		 workbook.write(fo);
    	 }
    	 fi=new FileInputStream(path);
    	 workbook=new XSSFWorkbook(fi);
    	 
    	 if(workbook.getSheetIndex(sheetName)==-1)
    		 workbook.createSheet(sheetName);
    	    sheet=workbook.getSheet(sheetName);
    	    
    	    if(sheet.getRow(rownum)==null)
    	    	sheet.createRow(rownum);
    	    row=sheet.getRow(rownum);
    	    
    	      cell=row.createCell(colnum);
    	      cell.setCellValue(data);
    	    
    	
    	 fo=new FileOutputStream(path);
    	 workbook.write(fo);
    	 workbook.close();
    	 fi.close();
    	 fo.close();
    	 
    	 
    	 
    	 
     }
     
     public static void fillGreenColor(String sheetName,int rownum,int colnum)throws IOException 
     {
    	
    	 fi=new FileInputStream(path);
    	 workbook=new XSSFWorkbook(fi);
    	 sheet=workbook.getSheet(sheetName);
    	 row=sheet.getRow(rownum);
    	 cell=row.getCell(colnum);
    	 
    	 style=workbook.createCellStyle();
    	 style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    	 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	 
    	 
    	 cell.setCellStyle(style);
//    	 fo=new FileOutputStream(xlfile);
    	  workbook.write(fo);
    	 workbook.close();
    	 fi.close();
    	 fo.close();
    	 
    	 
     }
      public static void fillRedColor(String sheetName,int rownum,int colnum)throws IOException{
    	  fi=new FileInputStream(path);
     	 workbook=new XSSFWorkbook(fi);
     	 sheet=workbook.getSheet(sheetName);
     	 row=sheet.getRow(rownum);
     	 cell=row.getCell(colnum);
     	 
     	 style=workbook.createCellStyle();
     	 style.setFillForegroundColor(IndexedColors.RED.getIndex());
     	 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
     	 
     	 
     	    cell.setCellStyle(style);
//   	       fo=new FileOutputStream(xlfile);
   	       workbook.write(fo);
   	       workbook.close();
   	       fi.close();
   	        fo.close();
      }

	
	
	

}
