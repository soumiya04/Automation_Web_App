package tests.ui;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import in.novopay.platform_ui.pages.web.YBLTwoFAPage;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.JavaUtils;

public class YBLTwoFATest {

	String featureName = "Ybl 2FA page";
	public WebDriver wdriver;
	private BasePage mBasePage = new BasePage(wdriver);
    private YBLTwoFAPage wYBLTwoFAPage;
    private Map<String, String> usrData;
	public String sheetname = "YblTwoFAPage", workbook = "WebAppUITestData";
	private JavaUtils javaUtils = new JavaUtils();
	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
	}
	@Test(dataProvider = "getData")
	public void yBLTwoFATest(HashMap<String, String> usrData)throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		this.usrData = usrData;
		if (wdriver == null) {
			System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
			wdriver = mBasePage.launchBrowser();
		} else if (wdriver != null) {
			System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
		}

		wYBLTwoFAPage = new YBLTwoFAPage(wdriver);
		wYBLTwoFAPage.yblTwofa(usrData);
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		return mBasePage.returnAllUniqueValuesInMap(workbook, sheetname, "no-check");
	}

	@AfterMethod
	public void result(ITestResult result) throws InvalidFormatException, IOException {

		String failureReason = "";

		if (!result.isSuccess()) {
			failureReason = result.getThrowable() + "";
		}

		String[] executionDetails = { JavaUtils.configProperties.get("buildNumber"), featureName, usrData.get("TCID"),
				usrData.get("DESCRIPTION"), javaUtils.getExecutionResultStatus(result.getStatus()), failureReason };
		javaUtils.writeExecutionStatusToExcel(executionDetails);
	}
	}