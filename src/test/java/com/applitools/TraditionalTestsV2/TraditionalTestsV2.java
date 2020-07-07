package com.applitools.TraditionalTestsV2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.applitools.CommonMethods.CommonLibrary;

public class TraditionalTestsV2 {
	
	CommonLibrary cl = new CommonLibrary();
	@Parameters ({"BrowserType","UrlLink"})
	@Test
	public void traditionalTestsV2(String browser,String url){
		cl.invokeBrowser(browser, url);
		cl.setupTraditional();
		cl.performTask1(browser);
		cl.performTask2(browser);
		cl.performTask3(browser);
		cl.tearDownTraditional();
	}

}