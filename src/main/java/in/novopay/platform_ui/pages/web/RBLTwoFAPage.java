package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

public class RBLTwoFAPage extends BasePage {

	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");

        @FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

        @FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]")
	WebElement merchantWallet;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]/parent::p/following-sibling::p/span")
	WebElement merchantWalletBalance;

	@FindBy(xpath = "////a[@class='ng-star-inserted']//span[contains(text(), 'Banking (AEPS)')]")
	WebElement BankingAeps;

        @FindBy(xpath = "//h5[contains(text(), 'Verify KYC Details')]")
	WebElement TwoFAPage;
       
       
       @FindBy(xpath = "//button[contains(text(), 'Proceed')]")
	WebElement ProceedButton;
     
       @FindBy(xpath = "//ekyc-biometric-scan-modal//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;
	
	@FindBy(xpath = "//ekyc-biometric-scan-modal//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;
	
       @FindBy(xpath = "//button[contains(text(), ' Ok ')]")
	WebElement OkButton;

       @FindBy(xpath = "//h4[contains(text(), ' Success!')]")
	WebElement Success;

        @FindBy(xpath = "//button[contains(text(), ' Done ')]")
	WebElement DoneButton;


	// Load all objects
	public RBLTwoFAPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}
        // Perform action on page based on given commands
	public void rblTwoFA(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
		    // Click on the menu
			waitUntilElementIsClickableAndClickTheElement(menu);
			System.out.println("Menu clicked");

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				//commonUtils.refreshBalance(); // refresh wallet balances
			}
   
              commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			commonUtils.waitForSpinner();
                waitUntilElementIsClickableAndClickTheElement(BankingAeps);
                 BankingAeps.click();
                 System.out.println("Rbl Banking option clicked");
                 waitUntilElementIsVisible(TwoFAPage);
                 System.out.println("2FA Page displayed");
                 waitUntilElementIsClickableAndClickTheElement(ProceedButton);
                 System.out.println("Proceed clicked");
                 waitUntilElementIsVisible(Success);
                 System.out.println("Success Page displayed");
                 waitUntilElementIsClickableAndClickTheElement(OkButton);
             	System.out.println("Ok button clicked");   
                if (usrData.get("ASSERTION").equalsIgnoreCase("SUCCESS")) {
				waitUntilElementIsVisible(Success);
				System.out.println(Success.getText());
				Thread.sleep(3000);
				DoneButton.click();
				Thread.sleep(3000);
				System.out.println("Done button clicked");
				System.out.println("2FA verification Success");
			        } else if (usrData.get("ASSERTION").equalsIgnoreCase("FAIL")) {
				System.out.println("2FA verification Failed");
				DoneButton.click();

			        }
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}
}
	

	                          
                 



                 
			
 

       
       


