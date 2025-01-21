package tests.ui;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.novopay.platform_ui.pages.web.FingpayTwoFAPage;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.JavaUtils;

public class FingpayTwoFATest {

	String featureName = "Fingpay 2FA page";
	public WebDriver wdriver;
	private BasePage mBasePage = new BasePage(wdriver);
    private FingpayTwoFAPage wFingpayTwoFAPage;
    private Map<String, String>usrData;
	public String sheetname = "FingpayTwoFa", workbook = "WebAppUITestData";
	private JavaUtils javaUtils = new JavaUtils();

	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
	}
	@Test(dataProvider = "getData")
	public void fingpayTwoFATest(HashMap<String, String> usrData)throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		this.usrData = usrData;
		if (wdriver == null) {
			System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
			wdriver = mBasePage.launchBrowser();
		} else if (wdriver != null) {
			System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
		}

		wFingpayTwoFAPage = new FingpayTwoFAPage(wdriver);
		wFingpayTwoFAPage.fingpayTwoFA(usrData);
	}
}