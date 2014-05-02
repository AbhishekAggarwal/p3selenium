package com.p3selenium.scripts.pname.testcase;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.p3selenium.assests.pname.product_family_level.common_lib.functions.TestBase;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.UtilityFactory;

public class ReadWriteExcel extends TestBase{

	@Test
	public void readwriteExcel() throws BiffException, WriteException, IOException
	{
		UtilityFactory uf = new UtilityFactory();
		System.out.println(getSheet());
		uf.logData(getSheet(), "testcaseName", "testCaseID", "expectedResult",
		"actualResult", "fail", "Comment");
		
	}
}
