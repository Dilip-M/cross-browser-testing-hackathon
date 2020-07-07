package com.applitools.ModernTestsV1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.CommonMethods.CommonLibrary;

public class ModernTestsV1 {
	CommonLibrary cl = new CommonLibrary();
	@Parameters ({"BrowserType","UrlLink"})
	@Test
	public void modernTestsV1(String browser,String url){
		cl.invokeBrowser(browser, url);
		cl.setupModern();
		cl.performTask1(browser);
		cl.performTask2(browser);
		cl.performTask3(browser);
		cl.tearDownModern();
	}
}