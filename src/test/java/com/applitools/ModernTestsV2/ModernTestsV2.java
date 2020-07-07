package com.applitools.ModernTestsV2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.applitools.CommonMethods.CommonLibrary;

public class ModernTestsV2 {
	
	CommonLibrary cl = new CommonLibrary();
	@Parameters ({"BrowserType","UrlLink"})
	@Test
	public void invokeBrowser(String browser,String url){
		cl.invokeBrowser(browser, url);
		cl.setupModern();
		cl.performTask1(browser);
		cl.performTask2(browser);
		cl.performTask3(browser);
		cl.tearDownModern();
	}

}