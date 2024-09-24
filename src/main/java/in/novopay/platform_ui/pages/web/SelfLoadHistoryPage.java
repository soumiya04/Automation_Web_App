package in.novopay.platform_ui.pages.web;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.MongoDBUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SelfLoadHistoryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//div[contains(text(),'Load History')]")
	WebElement sectionTitle;

	@FindBy(xpath = "//label[contains(text(),'Org. Code:')]/span")
	WebElement orgCode;

	@FindBy(xpath = "//tbody/tr[1]/td[2]")
	WebElement loadAmount;

	@FindBy(xpath = "//tbody/tr[1]/td[3]")
	WebElement status;

	@FindBy(xpath = "//tbody/tr[1]/td[4]")
	WebElement comments;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//label[contains(text(),'Select Report to View')]")
	WebElement reportsPage;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

	@FindBy(xpath = "//h1[contains(text(),'Manage Wallet')]")
	WebElement pageTitle;

	public SelfLoadHistoryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void selfLoadHistory(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			commonUtils.selectFeatureFromMenu1(reports, reportsPage);

			commonUtils.selectFeatureFromMenu1(manageWalletButton, pageTitle);

			waitUntilElementIsVisible(sectionTitle);
			System.out.println(sectionTitle.getText() + " section dispayed");

			Assert.assertEquals(orgCode.getText(),
					dbUtils.getOrgCodeFromMobNum(getLoginMobileFromIni("RetailerMobNum")));
			System.out.println("Org Code: " + orgCode.getText());

			System.out.println("Checking history of " + usrData.get("ASSERTION") + " transaction");

			Assert.assertEquals(replaceSymbols(loadAmount.getText()), txnDetailsFromIni("GetTxfAmount", "") + ".00");
			System.out.println("Amount: " + loadAmount.getText());

			assertTrue(status.getText().contains(usrData.get("ASSERTION")));
			System.out.println("Status: " + status.getText());

			if (usrData.get("ASSERTION").equalsIgnoreCase("Rejected")) {
				Assert.assertEquals(comments.getText(), "Duplicate Request");
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Success")) {
				Assert.assertEquals(comments.getText(), "Wallet Loaded. Old UTR: "
						+ dbUtils.requestIdFromTopUpRequest(getEmailIdFromIni("GetEmailId")));
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
				Assert.assertEquals(comments.getText(), "NA");
			}
			System.out.println("Comments: " + comments.getText());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}
}