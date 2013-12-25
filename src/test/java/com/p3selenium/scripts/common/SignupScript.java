package com.p3selenium.scripts.common;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.p3selenium.base.WebDriverFactory;
import com.p3selenium.data.Aynax_Constants;
import com.p3selenium.data.Aynax_Constants.DeltaConstants;

public class SignupScript extends WebDriverFactory {
	
	
	
	
	//@DataProvider(name ="DP1")
	public Object[][] createData1() throws Exception {
	    Object[][] retObjArr = getTableArray (Aynax_Constants.DeltaConstants.ab_csvFile,"DataPool","Signup");
	    return (retObjArr);
	}
	
	
	//@Test (dataProvider = "DP1")
	@Test
	public void testLink(String emailid, String password)throws Exception
    {

		WebDriverFactory wdFunc = new WebDriverFactory();
		wdFunc.setDriver(this.getDriver());
		getDriver().get(("https://qa.aynax.com/"));
		wdFunc.click("Sign Up", "link");
		Thread.sleep(3000);
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_emailname, emailid,"name");
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_password, password,"name");
		wdFunc.click("submit", "id");

}
	
	  public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
	        String[][] tabArray=null;
	        
	            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
	            Sheet sheet = workbook.getSheet(sheetName); 
	            int startRow,startCol, endRow, endCol,ci,cj;
	            Cell tableStart=sheet.findCell(tableName);
	            startRow=tableStart.getRow();
	            startCol=tableStart.getColumn();

	            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

	            endRow=tableEnd.getRow();
	            endCol=tableEnd.getColumn();
	            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
	                    "startCol="+startCol+", endCol="+endCol);
	            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	            ci=0;

	            for (int i=startRow+1;i<endRow;i++,ci++){
	                cj=0;
	                for (int j=startCol+1;j<endCol;j++,cj++){
	                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	                }
	            }
	        

	        return(tabArray);
	    }
	    
	
}


