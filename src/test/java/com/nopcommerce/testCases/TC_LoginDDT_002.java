package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData",groups={"master"})
	public void loginTestDDt(String user,String pwd,String exp) throws InterruptedException
	{
		logger.info("*******   Starting TC_LoginDDT_002 *******");
		
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.info("****** login test passed *****");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(true);
			}
			else if(exp.equals("Fail"))
			{
				logger.info("****** login test failed *****");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(false);
			}
			
		else if(!exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.warn("***** login failed *****");
					Assert.assertTrue(false);
				}
				else if(exp.equals("Fail"))
				{
					logger.info("***** login passed *****");
					Assert.assertTrue(true);
				}
			}
			
		}
		
		
		logger.info("*******  Finished TC_LoginDDT_002 ********");
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/LoginData.xlsx";
		
		int totalrows=XLUtils.getRowCount(path, "Sheet1");   //4
		int totalcols=XLUtils.getCellCount(path, "Sheet1", 1); //3
		
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0
			}
		}
		
		return logindata;
	}
	
}










