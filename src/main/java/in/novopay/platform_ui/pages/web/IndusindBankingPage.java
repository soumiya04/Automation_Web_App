package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

public class IndusindBankingPage extends BasePage {
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

	@FindBy(xpath = "//a[contains(@href,'indusind-banking')]/span[2][contains(text(),'Banking')]")
	WebElement banking;

	@FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
	WebElement withdrawalTab;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//span[contains(text(),'Select...')]/parent::span")
	WebElement withdrawalDropdown;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']")
	WebElement withdrawalMobNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']/parent::div//li")
	WebElement withdrawalMobNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']")
	WebElement withdrawalAadhaarNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div//li")
	WebElement withdrawalAadhaarNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div/ul/li")
	WebElement withdrawalAadhaarNumError2;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='5']")
	WebElement withdrawalAmount;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='5']/parent::div/following-sibling::div//li")
	WebElement withdrawalAmountError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='5']/parent::div/following-sibling::div//span")
	WebElement withdrawalAmountError2;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']")
	WebElement withdrawalConsentCheckbox;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span")
	WebElement withdrawalConsentMessage;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span/a")
	WebElement withdrawalConsentLink;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::ul/li")
	WebElement withdrawalConsentError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//app-aepscomponent//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//app-aepscomponent//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Click to scan fingerprint')]")
	WebElement withdrawalFingerprintUnscanned;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Submit')]")
	WebElement withdrawalSubmit;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Clear')]")
	WebElement withdrawalClear;

	@FindBy(xpath = "//*[contains(text(),'Confirm the details')]")
	WebElement confirmScreen;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div//div[contains(text(),'Transaction Amount')]/following-sibling::div")
	WebElement confirmScreenAmount;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement confirmScreenSubmit;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement confirmScreenCancel;

	@FindBy(xpath = "//a[contains(text(),'Balance Enquiry')]")
	WebElement balanceEnquiryTab;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//span[contains(text(),'Select...')]/parent::span")
	WebElement balanceEnquiryDropdown;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']")
	WebElement balanceEnquiryMobNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']/parent::div//li")
	WebElement balanceEnquiryMobNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']")
	WebElement balanceEnquiryAadhaarNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div//li")
	WebElement balanceEnquiryAadhaarNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div/ul/li")
	WebElement balanceEnquiryAadhaarNumError2;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']")
	WebElement balanceEnquiryConsentCheckbox;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span")
	WebElement balanceEnquiryConsentMessage;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span/a")
	WebElement balanceEnquiryConsentLink;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::ul/li")
	WebElement balanceEnquiryConsentError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//div[contains(@class,'scan_finger_container')]")
	WebElement balanceEnquiryScanFingerprint;

	@FindBy(xpath = "//app-aepscomponent//h4[contains(text(),'Success!')]")
	WebElement balanceEnquiryScanSuccessScreen;

	@FindBy(xpath = "//app-aepscomponent//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement balanceEnquiryFingerprintSuccess;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement balanceEnquiryFingerprintGreen;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Click to scan fingerprint')]")
	WebElement balanceEnquiryFingerprintUnscanned;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Ok')]")
	WebElement balanceEnquiryFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Submit')]")
	WebElement balanceEnquirySubmit;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Clear')]")
	WebElement balanceEnquiryClear;

	@FindBy(xpath = "//a[contains(text(),'Mini Statement')]")
	WebElement miniStatementTab;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//span[contains(text(),'Select...')]/parent::span")
	WebElement miniStatementDropdown;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']")
	WebElement miniStatementMobNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='2']/parent::div//li")
	WebElement miniStatementMobNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']")
	WebElement miniStatementAadhaarNum;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div//li")
	WebElement miniStatementAadhaarNumError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='3']/parent::div/ul/li")
	WebElement miniStatementAadhaarNumError2;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']")
	WebElement miniStatementConsentCheckbox;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span")
	WebElement miniStatementConsentMessage;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::span/a")
	WebElement miniStatementConsentLink;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//*[@id='4']/parent::span/following-sibling::ul/li")
	WebElement miniStatementConsentError;

	@FindBy(xpath = "//*[@id='aeps-withdrawl-form']//div[contains(@class,'scan_finger_container')]")
	WebElement miniStatementScanFingerprint;

	@FindBy(xpath = "//app-aepscomponent//h4[contains(text(),'Success!')]")
	WebElement miniStatementScanSuccessScreen;

	@FindBy(xpath = "//app-aepscomponent//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement miniStatementFingerprintSuccess;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement miniStatementFingerprintGreen;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Click to scan fingerprint')]")
	WebElement miniStatementFingerprintUnscanned;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Ok')]")
	WebElement miniStatementFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Submit')]")
	WebElement miniStatementSubmit;

	@FindBy(xpath = "//app-aepscomponent/div//button[contains(text(),'Clear')]")
	WebElement miniStatementClear;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//app-aepscomponent//h4[contains(text(),'!')]")
	WebElement aepsTxnScreen;

	@FindBy(xpath = "//app-aepscomponent//h4[contains(text(),'!')]/parent::div")
	WebElement aepsTxnScreenType;

	@FindBy(xpath = "//app-aepscomponent//div[contains(@class,'transaction-title')]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenTxnAmount;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenCharges;

	@FindBy(xpath = "//app-aepscomponent//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div/span")
	WebElement aepsTxnScreenFailedAmount;

	@FindBy(xpath = "//app-aepscomponent//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement aepsTxnScreenTotalAmount;

	@FindBy(xpath = "//app-aepscomponent/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Exit')]")
	WebElement aepsTxnScreenExitButton;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Retry')]")
	WebElement aepsTxnScreenRetryButton;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Save')]")
	WebElement aepsTxnScreenSaveButton;

	@FindBy(xpath = "//app-aepscomponent//button[contains(text(),'Print')]")
	WebElement aepsTxnScreenPrintButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement miniStatementTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]/div/div/div")
	WebElement miniStatementTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement miniStatementTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]/div/div/div/following-sibling::div/div/strong")
	WebElement miniStatementDeemedTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement miniStatementTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement miniStatementTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement miniStatementTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement miniStatementTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement miniStatementTxnScreenFailureReason;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Done')]")
	WebElement miniStatementTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Done')][contains(@class,'disabled')]")
	WebElement miniStatementTxnScreenDisabledDoneButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Exit')]")
	WebElement miniStatementTxnScreenExitButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Retry')]")
	WebElement miniStatementTxnScreenRetryButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Save')]")
	WebElement miniStatementTxnScreenSaveButton;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//button[contains(text(),'Print')]")
	WebElement miniStatementTxnScreenPrintButton;

	@FindBy(xpath = "//div[contains(@class,'deposit-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement depositRefNo;

	@FindBy(xpath = "//div[contains(@class,'withdraw-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement withdrawalRefNo;

	@FindBy(xpath = "//div[contains(@class,'enquiry-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement balanceEnquiryRefNo;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//strong[contains(text(),'Ref. ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement miniStatementRefNo;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Customer A/C Balance:')]/parent::div/following-sibling::div//span")
	WebElement miniStatementCustAcBalance;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	String batchConfigSection = "rblaepsstatusenquiry";

	// Load all objects
	public IndusindBankingPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void indusindBanking(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.selectFeatureFromMenu2(banking, pageTitle);

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				commonUtils.refreshBalance(); // refresh wallet balances
			}

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			double initialCashoutBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));

			if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
				dbUtils.deleteAndInsertBioAuthDetails(getLoginMobileFromIni("GetRetailerMobNum"));
				withdrawalTxns(usrData, initialCashoutBalance);
			} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
				dbUtils.deleteAndInsertBioAuthDetails(getLoginMobileFromIni("GetRetailerMobNum"));
				balanceEnquiryTxns(usrData, initialCashoutBalance);
			} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Mini Statement")) {
				dbUtils.deleteAndInsertBioAuthDetails(getLoginMobileFromIni("GetRetailerMobNum"));
				miniStatementTxns(usrData, initialCashoutBalance);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// withdrawal transaction
	public void withdrawalTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		commonUtils.waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(withdrawalTab);
		commonUtils.waitForSpinner();
		System.out.println("Withdrawal tab clicked");
		waitUntilElementIsClickableAndClickTheElement(withdrawalDropdown);
		System.out.println("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		System.out.println(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		System.out.println(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(withdrawalMobNum);
		withdrawalMobNum.sendKeys(usrData.get("MOBNUM"));
		System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
		waitUntilElementIsClickableAndClickTheElement(withdrawalAadhaarNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(withdrawalMobNumError);
			Assert.assertEquals(withdrawalMobNumError.getText(), "Required Field");
			System.out.println(withdrawalMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(withdrawalMobNumError);
			Assert.assertEquals(withdrawalMobNumError.getText(), "Invalid mobile number");
			System.out.println(withdrawalMobNumError.getText());
		}

		withdrawalAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		waitUntilElementIsClickableAndClickTheElement(withdrawalAmount);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError);
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Required Field");
			System.out.println(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError2);
			Assert.assertEquals(withdrawalAadhaarNumError2.getText(), "Enter 12 digit Aadhaar or 16 digit VID");
			System.out.println(withdrawalAadhaarNumError2.getText());
		}

		withdrawalAmount.sendKeys(usrData.get("AMOUNT"));
		System.out.println("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			waitUntilElementIsClickableAndClickTheElement(withdrawalAadhaarNum);
			waitUntilElementIsVisible(withdrawalAmountError2);
			Assert.assertEquals(withdrawalAmountError2.getText(),
					"Amount entered exceeds your transaction limit ₹10,000.00");
			System.out.println(withdrawalAmountError2.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			waitUntilElementIsClickableAndClickTheElement(withdrawalAadhaarNum);
			waitUntilElementIsVisible(withdrawalAmountError);
			Assert.assertEquals(withdrawalAmountError.getText(), "Minimum amount should be ₹100.00");
			System.out.println(withdrawalAmountError.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitUntilElementIsVisible(withdrawalConsentError);
			Assert.assertEquals(withdrawalConsentError.getText(), "Required Field");
			System.out.println(withdrawalConsentError.getText());
		} else {
			waitUntilElementIsClickableAndClickTheElement(withdrawalConsentCheckbox);
			System.out.println("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", withdrawalFingerprintUnscanned.getText());
			waitUntilElementIsClickableAndClickTheElement(withdrawalScanFingerprint);
			System.out.println("Scan fingerprint button clicked");
			waitUntilElementIsVisible(withdrawalScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", withdrawalFingerprintSuccess.getText());
			System.out.println(withdrawalFingerprintSuccess.getText());
			waitUntilElementIsClickableAndClickTheElement(withdrawalFingerprintScreenOkButton);
			System.out.println("Ok button clicked");
			waitUntilElementIsVisible(withdrawalFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
			System.out.println("Submit button clicked");

			confirmScreen(usrData);

			commonUtils.processingScreen();

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsClickableAndClickTheElement(processInBackgroundButton);
				System.out.println("Process in Background button clicked");
			} else {
				waitUntilElementIsVisible(aepsTxnScreen);
				System.out.println("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnWithdrawalSuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnWithdrawalSMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
							System.out.println("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
							System.out.println("Print button clicked");
						}
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnWithdrawalFCM(usrData);
						}
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterWithdrawalSuccessTxn(usrData, walletBalance);
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
						waitUntilElementIsVisible(withdrawalFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
						waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
						System.out.println("Submit button clicked");
						confirmScreen(usrData);
						commonUtils.processingScreen();
						waitUntilElementIsVisible(aepsTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnWithdrawalFailedScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					}
					if (usrData.get("ASSERTION").contains("FCM")) {
						assertionOnWithdrawalFCM(usrData);
					}
					if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
						if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
						}
					} else {
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterWithdrawalFailTxn(usrData, walletBalance);
					}
				} else {
					waitUntilElementIsClickableAndClickTheElement(withdrawalClear);
					System.out.println("Clear button clicked");
					waitUntilElementIsVisible(withdrawalDropdown);
					System.out.println("Data cleared");
				}
			}
		}
	}

	// Balance Enquiry transaction
	public void balanceEnquiryTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		commonUtils.waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryTab);
		commonUtils.waitForSpinner();
		System.out.println("Balance Enquiry tab clicked");
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryDropdown);
		System.out.println("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		System.out.println(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		System.out.println(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryMobNum);
		balanceEnquiryMobNum.sendKeys(usrData.get("MOBNUM"));
		System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryAadhaarNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(balanceEnquiryMobNumError);
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Required Field");
			System.out.println(balanceEnquiryMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(balanceEnquiryMobNumError);
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Invalid mobile number");
			System.out.println(balanceEnquiryMobNumError.getText());
		}

		balanceEnquiryAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryMobNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError);
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Required Field");
			System.out.println(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError2);
			Assert.assertEquals(balanceEnquiryAadhaarNumError2.getText(), "Enter a valid Aadhaar number or VID");
			System.out.println(balanceEnquiryAadhaarNumError2.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitUntilElementIsVisible(balanceEnquiryConsentError);
			Assert.assertEquals(balanceEnquiryConsentError.getText(), "Required Field");
			System.out.println(balanceEnquiryConsentError.getText());
		} else {
			waitUntilElementIsClickableAndClickTheElement(balanceEnquiryConsentCheckbox);
			System.out.println("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", balanceEnquiryFingerprintUnscanned.getText());
			waitUntilElementIsClickableAndClickTheElement(balanceEnquiryScanFingerprint);
			System.out.println("Scan fingerprint button clicked");
			waitUntilElementIsVisible(balanceEnquiryScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", balanceEnquiryFingerprintSuccess.getText());
			System.out.println(balanceEnquiryFingerprintSuccess.getText());
			waitUntilElementIsClickableAndClickTheElement(balanceEnquiryFingerprintScreenOkButton);
			System.out.println("Ok button clicked");
			waitUntilElementIsVisible(balanceEnquiryFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", balanceEnquiryFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsVisible(balanceEnquirySubmit);
			waitUntilElementIsClickableAndClickTheElement(balanceEnquirySubmit);
			System.out.println("Submit button clicked");
			commonUtils.processingScreen();

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsVisible(processInBackgroundButton);
				waitUntilElementIsClickableAndClickTheElement(processInBackgroundButton);
				System.out.println("Process in Background button clicked");
			} else {
				waitUntilElementIsVisible(aepsTxnScreen);
				System.out.println("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnBalanceEnquirySuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnBalanceEnquirySMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
							System.out.println("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
							System.out.println("Print button clicked");
						}
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnBalanceEnquiryFCM(usrData);
						}
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterBalanceEnquirySuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnBalanceEnquiryFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
						System.out.println("Retry button clicked");
						waitUntilElementIsVisible(balanceEnquiryFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!",
								balanceEnquiryFingerprintGreen.getText());
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(balanceEnquirySubmit);
						System.out.println("Submit button clicked");
						commonUtils.processingScreen();
						waitUntilElementIsVisible(aepsTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnBalanceEnquiryFailedScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					}
					if (usrData.get("ASSERTION").contains("FCM")) {
						assertionOnBalanceEnquiryFCM(usrData);
					}
					commonUtils.refreshBalance();
					verifyUpdatedBalanceAfterBalanceEnquiryFailTxn(usrData, walletBalance);
				} else {
					waitUntilElementIsClickableAndClickTheElement(balanceEnquiryClear);
					System.out.println("Clear button clicked");
					waitUntilElementIsVisible(balanceEnquiryDropdown);
					System.out.println("Data cleared");
				}
			}
		}
	}

	// Mini Statement transaction
	public void miniStatementTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		commonUtils.waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(miniStatementTab);
		commonUtils.waitForSpinner();
		System.out.println("Mini Statement tab clicked");
		waitUntilElementIsClickableAndClickTheElement(miniStatementDropdown);
		System.out.println("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		System.out.println(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		System.out.println(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(miniStatementMobNum);
		miniStatementMobNum.sendKeys(usrData.get("MOBNUM"));
		System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
		waitUntilElementIsClickableAndClickTheElement(miniStatementAadhaarNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(miniStatementMobNumError);
			Assert.assertEquals(miniStatementMobNumError.getText(), "Required Field");
			System.out.println(miniStatementMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(miniStatementMobNumError);
			Assert.assertEquals(miniStatementMobNumError.getText(), "Invalid mobile number");
			System.out.println(miniStatementMobNumError.getText());
		}

		miniStatementAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		waitUntilElementIsClickableAndClickTheElement(miniStatementMobNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(miniStatementAadhaarNumError);
			Assert.assertEquals(miniStatementAadhaarNumError.getText(), "Required Field");
			System.out.println(miniStatementAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(miniStatementAadhaarNumError2);
			Assert.assertEquals(miniStatementAadhaarNumError2.getText(), "Enter a valid Aadhaar number or VID");
			System.out.println(miniStatementAadhaarNumError2.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitUntilElementIsVisible(miniStatementConsentError);
			Assert.assertEquals(miniStatementConsentError.getText(), "Required Field");
			System.out.println(miniStatementConsentError.getText());
		} else {
			waitUntilElementIsClickableAndClickTheElement(miniStatementConsentCheckbox);
			System.out.println("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", miniStatementFingerprintUnscanned.getText());
			waitUntilElementIsClickableAndClickTheElement(miniStatementScanFingerprint);
			System.out.println("Scan fingerprint button clicked");
			waitUntilElementIsVisible(miniStatementScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", miniStatementFingerprintSuccess.getText());
			System.out.println(miniStatementFingerprintSuccess.getText());
			waitUntilElementIsClickableAndClickTheElement(miniStatementFingerprintScreenOkButton);
			System.out.println("Ok button clicked");
			waitUntilElementIsVisible(miniStatementFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", miniStatementFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsVisible(miniStatementSubmit);
			waitUntilElementIsClickableAndClickTheElement(miniStatementSubmit);
			System.out.println("Submit button clicked");
			commonUtils.processingScreen();

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsVisible(processInBackgroundButton);
				waitUntilElementIsClickableAndClickTheElement(processInBackgroundButton);
				System.out.println("Process in Background button clicked");
			} else {
				waitUntilElementIsVisible(aepsTxnScreen);
				System.out.println("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnMiniStatementSuccessScreen(usrData);
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
							System.out.println("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
							System.out.println("Print button clicked");
						}
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnMiniStatementFCM(usrData);
						}
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterMiniStatementSuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnMiniStatementFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						System.out.println("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
						System.out.println("Retry button clicked");
						waitUntilElementIsVisible(miniStatementFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!",
								miniStatementFingerprintGreen.getText());
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(miniStatementSubmit);
						System.out.println("Submit button clicked");
						commonUtils.processingScreen();
						waitUntilElementIsVisible(aepsTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnMiniStatementFailedScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						System.out.println("Exit button clicked");
					}
					if (usrData.get("ASSERTION").contains("FCM")) {
						assertionOnMiniStatementFCM(usrData);
					}
					commonUtils.refreshBalance();
					verifyUpdatedBalanceAfterMiniStatementFailTxn(usrData, walletBalance);
				} else {
					waitUntilElementIsClickableAndClickTheElement(miniStatementClear);
					System.out.println("Clear button clicked");
					waitUntilElementIsVisible(miniStatementDropdown);
					System.out.println("Data cleared");
				}
			}
		}
	}

	public void dropdownSelect(Map<String, String> usrData) {
		String dropdownXpath = "//li[contains(text(),'" + usrData.get("BANKNAME") + "')]";
		WebElement dropdownValue = wdriver.findElement(By.xpath(dropdownXpath));
		waitUntilElementIsClickableAndClickTheElement(dropdownValue);
	}

	// Get Partner name
	public String partner() {
		return "INDUSIND";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni("RetailerMobNum");
	}

	// Verify details on success screen
	public void assertionOnDepositSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash deposited to customer successfully");
		System.out.println(aepsTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenCharges.getText()), "0.00");
		System.out.println("Charges: " + replaceSymbols(aepsTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", "0.00");
		txnDetailsFromIni("StoreTxnRefNo", depositRefNo.getText());
		String comm = dbUtils.getAepsComm(usrData.get("AMOUNT"), "Deposit", partner());
		txnDetailsFromIni("StoreComm", comm);
		double tds = Double.parseDouble(comm) * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni()))
				/ 10000;
		txnDetailsFromIni("StoreTds", df.format(tds));
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = 0.00;
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(aepsTxnScreenTotalAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnDepositFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(),
					"Agent Wallet Debit Failed :Insufficient account balance.");
			System.out.println(aepsTxnScreenFailureReason.getText());
		} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Authentication Failed Invalid Agent MPIN");
			System.out.println(aepsTxnScreenFailureReason.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
			System.out.println(aepsTxnScreenFailureReason.getText());
			Assert.assertEquals(replaceSymbols(aepsTxnScreenFailedAmount.getText()), usrData.get("AMOUNT") + ".00");
			System.out.println("Failed Amount: " + replaceSymbols(aepsTxnScreenFailedAmount.getText()));
		}
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterDepositSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance - amount - charges + comm - tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterDepositFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnDepositSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Cash Deposit: INR " + usrData.get("AMOUNT") + ".00 deposited successfully in your "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + ". Reference Number: "
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", Charges: 0.00, Date: " + dbUtils.aepsTxnDate() + " IST";
		String deemedSuccessSMS = "Cash Deposit of INR " + usrData.get("AMOUNT") + ".00 to your "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12)
				+ " has been initiated and will be processed shortly. Reference Number: "
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", Charges: 0.00, Date: " + dbUtils.aepsTxnDate() + " IST";

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Deemed Success SMS")) {
			Assert.assertEquals(deemedSuccessSMS, dbUtils.sms());
			System.out.println(deemedSuccessSMS);
		}
	}

	// FCM assertion
	public void assertionOnDepositFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Cash Deposit: SUCCESS";
		String failFCMHeading = "Cash Deposit: FAIL";

		String balance = df.format(commonUtils.getInitialBalance("retailer"));
		String successFCMContent = "Request of cash deposit: INR " + usrData.get("AMOUNT") + ".00 deposited in "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges: INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code:(00)SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Agent Wallet Balance after txn: INR " + balance;
		String deemedSuccessFCMContent = "Request of cash deposit: INR " + usrData.get("AMOUNT") + ".00 deposited in "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges: INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code:(Error Code 91)SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Agent Wallet Balance after txn: INR " + balance;
		String failFCMContent = "deposit for customer account linked with aadhaar no. XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Error - Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Deemed Success FCM":
			fcmMethod(successFCMHeading, deemedSuccessFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	// Verify details on success screen
	public void assertionOnWithdrawalSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash withdrawn from customer successfully");
		System.out.println(aepsTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Withdrawn Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenCharges.getText()), "0.00");
		System.out.println("Charges: " + replaceSymbols(aepsTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", "0.00");
		txnDetailsFromIni("StoreTxnRefNo", withdrawalRefNo.getText());
		String comm = dbUtils.getAepsComm(usrData.get("AMOUNT"), "Deposit", partner());
		txnDetailsFromIni("StoreComm", comm);
		double tds = Double.parseDouble(comm) * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni()))
				/ 10000;
		txnDetailsFromIni("StoreTds", df.format(tds));
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = 0.00;
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Given: " + replaceSymbols(aepsTxnScreenTotalAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnWithdrawalFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText().substring(0, 68),
				"Cash withdrawn from customer failed - Failed to perform transaction(");
		System.out.println(aepsTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(aepsTxnScreenFailedAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Failed Amount: " + replaceSymbols(aepsTxnScreenFailedAmount.getText()));
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterWithdrawalSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance + amount - charges + comm - tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterWithdrawalFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnWithdrawalSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = usrData.get("AMOUNT") + ".00 Rs. withdrawn from your " + usrData.get("BANKNAME")
				+ " a/c linked to Aadhaar  XXXX XXXX " + usrData.get("AADHAAR").substring(8, 12)
				+ ". 0.00 Rs. are the Charges. Ref No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ ". Track the status of all your transactions anytime on our app https://bit.ly/Novopay_Grahak";
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnWithdrawalFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Cash Withdrawal: SUCCESS";
		String failFCMHeading = "Cash Withdrawal: FAIL";

		String balance = df.format(commonUtils.getInitialBalance("cashout"));
		String successFCMContent = "Request for cash withdrawal: INR " + usrData.get("AMOUNT")
				+ ".00 withdrawn from your " + usrData.get("BANKNAME") + " a/c linked with Aadhaar/VID XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code: (00) SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Balance after txn: INR " + balance;
		String failFCMContent = "Withdrawal for customer with Aadhaar/VID XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Failed to perform transaction(68)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	// Verify details on success screen
	public void assertionOnBalanceEnquirySuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Balance enquired successfully");
		System.out.println(aepsTxnScreenMessage.getText());
		txnDetailsFromIni("StoreTxnRefNo", balanceEnquiryRefNo.getText());
	}

	// Verify details on failure screen
	public void assertionOnBalanceEnquiryFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText().substring(0, 55),
				"Balance enquiry failed - Failed to perform transaction(");
		System.out.println(aepsTxnScreenMessage.getText());
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterBalanceEnquirySuccessTxn(Map<String, String> usrData,
			double initialWalletBalance) throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterBalanceEnquiryFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnBalanceEnquirySMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Balance in " + usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " as on " + dbUtils.aepsTxnDate()
				+ " IST is Led Bal: INR 1511.00, Ava Bal: INR 1511.00, Ref No: " + txnDetailsFromIni("GetTxnRefNo", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnBalanceEnquiryFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Balance Enquiry: SUCCESS";
		String failFCMHeading = "Balance Enquiry: FAIL";

		String successFCMContent = "Balance in " + usrData.get("BANKNAME") + " a/c linked with Aadhaar/VID XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " as on " + dbUtils.aepsTxnDate()
				+ " IST is Led Bal: 1511.00, Ava Bal: 1511.00 Response code: (00) SUCCESS Reference No: "
				+ txnDetailsFromIni("GetTxnRefNo", "");
		String failFCMContent = "Balance Enquiry for customer with Aadhaar/VID XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	// Verify details on success screen
	public void assertionOnMiniStatementSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(miniStatementTxnScreenMessage.getText(), "Mini Statement generated Successfully");
		System.out.println(miniStatementTxnScreenMessage.getText());
		txnDetailsFromIni("StoreTxnRefNo", miniStatementRefNo.getText());
		try {
			System.out.println("Customer A/C Balance: " + replaceSymbols(miniStatementCustAcBalance.getText()));
		} catch (Exception e) {
			System.out.println("Customer A/C Balance not visible");
		}
		Assert.assertTrue(miniStatementTxnScreenDisabledDoneButton.isDisplayed());
		System.out.println("Done button is disabled");
	}

	// Verify details on failure screen
	public void assertionOnMiniStatementFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText().substring(0, 65),
				"Mini Statement generation failed - Failed to perform transaction(");
		System.out.println(aepsTxnScreenMessage.getText());
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterMiniStatementSuccessTxn(Map<String, String> usrData,
			double initialWalletBalance) throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterMiniStatementFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// FCM assertion
	public void assertionOnMiniStatementFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Mini Statement: SUCCESS";
		String failFCMHeading = "Mini Statement: FAIL";

		String successFCMContent = "Mini Statement for " + usrData.get("BANKNAME")
				+ " a/c linked with Aadhaar/VID XXXX XXXX " + usrData.get("AADHAAR").substring(8, 12) + " as on "
				+ dbUtils.aepsTxnDate() + " IST is successful.Response code: (00) SUCCESS Reference No: "
				+ txnDetailsFromIni("GetTxnRefNo", "");
		String failFCMContent = "Mini Statement for customer account linked with Aadhaar/VID XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	// Confirm screen
	public void confirmScreen(Map<String, String> usrData) throws InterruptedException {
		waitUntilElementIsVisible(confirmScreen);
		System.out.println("Confirm the details screen displayed");
		Assert.assertEquals(replaceSymbols(confirmScreenAmount.getText()), usrData.get("AMOUNT") + ".00");
		waitUntilElementIsVisible(confirmScreenSubmit);
		waitUntilElementIsClickableAndClickTheElement(confirmScreenSubmit);
		Thread.sleep(2000);
		System.out.println("Submit button clicked");
	}
}
