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

public class RBLEKYCPage extends BasePage {
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

	@FindBy(xpath = "//h1[contains(text(),'Banking')]")
	WebElement pageTitle;

	@FindBy(xpath = "//a[contains(@href,'banking')]/span[2][contains(text(),'Banking')]")
	WebElement banking;

	@FindBy(xpath = "//h5[contains(text(),'Verify KYC Details')]")
	WebElement kycModal;

	@FindBy(xpath = "//div[contains(text(),'Aadhar Authentication failed. Please try again!')]")
	WebElement authFailed;

	@FindBy(xpath = "//verify-details-modal//button[contains(text(),'Proceed')]")
	WebElement proceedButton;

	@FindBy(xpath = "//h4[contains(text(),'Please Wait')]")
	WebElement pleaseWaitModal;

	@FindBy(xpath = "//h5[contains(text(),'Verify Aadhaar')]")
	WebElement aadhaarModal;

	@FindBy(xpath = "//rbl-interface//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//rbl-interface//button[contains(@class,'button-disabled')][contains(text(),'Done')]")
	WebElement doneButtonDisabled;

	@FindBy(xpath = "//div[@id='DivMain']/preceding-sibling::div")
	WebElement uidaiPage;

	@FindBy(id = "txtaadharnumber")
	WebElement aadhaarNumberField;

	@FindBy(id = "txtreenteraadharnumber")
	WebElement reenterAadhaarNumberField;

	@FindBy(id = "txtdob")
	WebElement dateOfBirth;

	@FindBy(xpath = "//select[@class='picker__select--year']")
	WebElement year;

	@FindBy(xpath = "//select[@class='picker__select--month']")
	WebElement month;

	@FindBy(xpath = "//*[@id='txtgender']//input[@value='M']/following-sibling::label")
	WebElement genderMale;

	@FindBy(xpath = "//*[@id='txtgender']//input[@value='F']/following-sibling::label")
	WebElement genderFemale;

	@FindBy(id = "lblchktms")
	WebElement checkbox;

	@FindBy(id = "btnSubmit")
	WebElement submitButton;

	@FindBy(xpath = "//p[contains(text(),'Verified successfully')]")
	WebElement verifiedSuccessMessage;

	@FindBy(xpath = "//p[contains(text(),'Pi (basic) attributes of demographic data did not match')]")
	WebElement attributeMismatchedMessage;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement OKButton;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//p[contains(text(),'Aadhaar captured successfully.')]")
	WebElement aadhaarCapturedMessage;

	@FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
	WebElement withdrawalTab;

	@FindBy(xpath = "//ekyc-biometric-scan-modal/div//span[contains(text(),'Select...')]/parent::span")
	WebElement withdrawalDropdown;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='money-transfer-mobile-number']")
	WebElement withdrawalMobNum;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement withdrawalMobNumError;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='aeps-deposit-aadhar-number']")
	WebElement withdrawalAadhaarNum;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement withdrawalAadhaarNumError;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='aeps-deposit-aadhar-number']/parent::div/following-sibling::ul/li")
	WebElement withdrawalAadhaarNumError2;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement withdrawalAmount;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement withdrawalAmountError;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement withdrawalConsentCheckbox;

	@FindBy(xpath = "//fekyc-biometric-scan-modal//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement withdrawalConsentMessage;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement withdrawalConsentLink;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement withdrawalConsentError;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//span[contains(text(),'Click to scan fingerprint')]")
	WebElement withdrawalFingerprintUnscanned;

	@FindBy(xpath = "//ekyc-biometric-scan-modal//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;

	@FindBy(xpath = "//ekyc-biometric-scan-modal/div//button[contains(text(),'Submit')]")
	WebElement withdrawalSubmit;

	@FindBy(xpath = "//ekyc-biometric-scan-modal/div//button[contains(text(),'Clear')]")
	WebElement withdrawalClear;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement aepsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]/div/div/div")
	WebElement aepsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]//button[contains(text(),'Exit')]")
	WebElement aepsTxnScreenExitButton;

	@FindBy(xpath = "//div[contains(@class,'response-modal')]//button[contains(text(),'Retry')]")
	WebElement aepsTxnScreenRetryButton;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//h1[contains(text(),'Recharge')]")
	WebElement rechargesScreen;

	String batchConfigSection = "rblaepsstatusenquiry";

	// Load all objects
	public RBLEKYCPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void rblEkyc(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.selectFeatureFromMenu2(banking, pageTitle);

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				commonUtils.refreshBalance(); // refresh wallet balances
			}

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			commonUtils.waitForSpinner();
			waitUntilElementIsInvisible("//h4[contains(text(),'Please Wait')]");
			System.out.println("Please Wait modal displayed");
			waitUntilElementIsVisible(kycModal);
			System.out.println("KYC modal clicked");
			waitUntilElementIsClickableAndClickTheElement(proceedButton);
			System.out.println("Proceed button clicked");
			waitUntilElementIsInvisible("//h4[contains(text(),'Please Wait')]");
			System.out.println("Please Wait modal displayed");
			waitUntilElementIsVisible(aadhaarModal);

			ArrayList<String> tabs = new ArrayList<String>(wdriver.getWindowHandles());
			Thread.sleep(1000);
			wdriver.switchTo().window(tabs.get(1));

			waitUntilElementIsVisible(uidaiPage);
			System.out.println("Switched to RBL interface");
			aadhaarNumberField.sendKeys(usrData.get("AADHAAR"));
			reenterAadhaarNumberField.sendKeys(usrData.get("AADHAAR"));
			try {
				dateOfBirth.sendKeys(usrData.get("DOB"));
			} catch (Exception e) {
				waitUntilElementIsClickableAndClickTheElement(dateOfBirth);
				waitUntilElementIsVisible(year);
				Select yearValue = new Select(year);
				yearValue.selectByValue(usrData.get("YEAR"));
				waitUntilElementIsVisible(month);
				Select monthValue = new Select(month);
				monthValue.selectByVisibleText(usrData.get("MONTH"));
				String dayXpath = "//table[@class='picker__table']/tbody/tr/td/div[@class='picker__day picker__day--infocus'][text()='"
						+ usrData.get("DAY") + "']";
				WebElement day = wdriver.findElement(By.xpath(dayXpath));
				waitUntilElementIsClickableAndClickTheElement(day);
			}
			if (usrData.get("GENDER").equalsIgnoreCase("Male")) {
				genderMale.click();
			} else if (usrData.get("GENDER").equalsIgnoreCase("Female")) {
				genderFemale.click();
			}
			checkbox.click();
			submitButton.click();
			System.out.println("Submitted Aadhaar details");

			if (usrData.get("ASSERTION").equalsIgnoreCase("FAIL")) {
				waitUntilElementIsVisible(attributeMismatchedMessage);
				System.out.println(attributeMismatchedMessage.getText());
				Thread.sleep(3000);
				OKButton.click();
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("SUCCESS")) {
				waitUntilElementIsVisible(verifiedSuccessMessage);
				System.out.println(verifiedSuccessMessage.getText());
				Thread.sleep(3000);
				OKButton.click();
			}
			waitUntilElementIsVisible(aadhaarCapturedMessage);

			wdriver.close(); // close the tab
			wdriver.switchTo().window(tabs.get(0)); // switch to previous window
			Thread.sleep(1000);

			waitUntilElementIsVisible(aadhaarModal);
			System.out.println("Switched back to previous window");
			try {
				while (doneButtonDisabled.isDisplayed() == true) {
					doneButton.getText();
				}
				waitUntilElementIsClickableAndClickTheElement(doneButton);
				System.out.println("Done button clicked");
			} catch (Exception e) {
				waitUntilElementIsClickableAndClickTheElement(doneButton);
				System.out.println("Done button clicked");
			}
			waitUntilElementIsInvisible("//h4[contains(text(),'Please Wait')]");
			System.out.println("Please wait modal dismissed");

			if (usrData.get("ASSERTION").equalsIgnoreCase("FAIL")) {
				waitUntilElementIsVisible(authFailed);
				waitUntilElementIsClickableAndClickTheElement(exitButton);
				System.out.println("Exit button clicked");
				waitUntilElementIsVisible(rechargesScreen);
				System.out.println("Redirected to recharges screen");
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("SUCCESS")) {
				waitUntilElementIsVisible(kycModal);
				waitUntilElementIsClickableAndClickTheElement(proceedButton);
				System.out.println("Proceed button clicked");

				waitUntilElementIsInvisible("//h4[contains(text(),'Please Wait')]");
				System.out.println("Please wait modal dismissed");

				waitUntilElementIsVisible(withdrawalScanSuccessScreen);
				Assert.assertEquals("Fingerprints scanned successfully", withdrawalFingerprintSuccess.getText());
				System.out.println(withdrawalFingerprintSuccess.getText());
				withdrawalFingerprintScreenOkButton.click();
				System.out.println("Ok button clicked");

				waitUntilElementIsVisible(aepsTxnScreen);
				System.out.println("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnWithdrawalSuccessScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
						Assert.assertEquals(dbUtils.getRBLEKYCStatus(mobileNumFromIni()), "APPROVED");
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnWithdrawalFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
						System.out.println("Retry button clicked");
						waitUntilElementIsVisible(withdrawalScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								withdrawalFingerprintSuccess.getText());
						System.out.println(withdrawalFingerprintSuccess.getText());
						withdrawalFingerprintScreenOkButton.click();
						System.out.println("Ok button clicked");
						waitUntilElementIsInvisible("//ekyc-biometric-scan-modal//button[contains(text(),'Ok')]");
						waitUntilElementIsVisible(withdrawalFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
						waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
						System.out.println("Submit button clicked");
						commonUtils.processingScreen();
						waitUntilElementIsVisible(aepsTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnWithdrawalFailedScreen(usrData);
						if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							System.out.println("Done button clicked");
						} else {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
							System.out.println("Exit button clicked");
						}
					}
				}
			}
			dbUtils.updateRBLEKYCStatus("APPROVED", mobileNumFromIni());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void dropdownSelect(Map<String, String> usrData) {
		String dropdownXpath = "//li[contains(text(),'" + usrData.get("BANKNAME") + "')]";
		WebElement dropdownValue = wdriver.findElement(By.xpath(dropdownXpath));
		dropdownValue.click();
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni("RetailerMobNum");
	}

	// Verify details on success screen
	public void assertionOnWithdrawalSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(),
				"Your Biometric Authentication has been completed successfully");
		System.out.println(aepsTxnScreenMessage.getText());
	}

	// Verify details on failure screen
	public void assertionOnWithdrawalFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		System.out.println(aepsTxnScreenFailureReason.getText());
	}
}
