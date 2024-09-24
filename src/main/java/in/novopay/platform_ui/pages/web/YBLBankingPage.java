package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
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

public class YBLBankingPage extends BasePage {
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

	@FindBy(xpath = "//a[contains(@href,'ybl-banking')]/span[2][contains(text(),'Banking')]")
	WebElement banking;

	@FindBy(xpath = "//a[contains(text(),'Deposit')]")
	WebElement depositTab;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Select...')]/parent::span")
	WebElement depositDropdown;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']")
	WebElement depositMobNum;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement depositMobNumError;

	@FindBy(xpath = "//app-deposit//input[@id='aeps-deposit-aadhar-number']")
	WebElement depositAadhaarNum;

	@FindBy(xpath = "//app-deposit//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement depositAadhaarNumError;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement depositAmount;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement depositAmountError;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement depositConsentCheckbox;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement depositConsentMessage;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement depositConsentLink;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement depositConsentError;

	@FindBy(xpath = "//app-deposit//div[contains(@class,'scan_finger_container')]")
	WebElement depositScanFingerprint;

	@FindBy(xpath = "//app-deposit//h4[contains(text(),'Success!')]")
	WebElement depositScanSuccessScreen;

	@FindBy(xpath = "//app-deposit//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement depositFingerprintSuccess;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement depositFingerprintGreen;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Click to scan fingerprint')]")
	WebElement depositFingerprintUnscanned;

	@FindBy(xpath = "//app-deposit//button[contains(text(),'Ok')]")
	WebElement depositFingerprintScreenOkButton;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement mpinScreen;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mpin-number']")
	WebElement enterMpin;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement submitMpin;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement cancelMpin;

	@FindBy(xpath = "//app-deposit/div//button[contains(text(),'Submit')]")
	WebElement depositSubmit;

	@FindBy(xpath = "//app-deposit/div//button[contains(text(),'Clear')]")
	WebElement depositClear;

	@FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
	WebElement withdrawalTab;

	@FindBy(xpath = "//app-withdrawl/div//span[contains(text(),'Select...')]/parent::span")
	WebElement withdrawalDropdown;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']")
	WebElement withdrawalMobNum;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement withdrawalMobNumError;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']")
	WebElement withdrawalAadhaarNum;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement withdrawalAadhaarNumError;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']/following-sibling::ul/li")
	WebElement withdrawalAadhaarNumError2;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement withdrawalAmount;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement withdrawalAmountError;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement withdrawalConsentCheckbox;

	@FindBy(xpath = "//fapp-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement withdrawalConsentMessage;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement withdrawalConsentLink;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement withdrawalConsentError;

	@FindBy(xpath = "//app-withdrawl//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//app-withdrawl//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//app-withdrawl//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Click to scan fingerprint')]")
	WebElement withdrawalFingerprintUnscanned;

	@FindBy(xpath = "//app-withdrawl//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;

	@FindBy(xpath = "//app-withdrawl/div//button[contains(text(),'Submit')]")
	WebElement withdrawalSubmit;

	@FindBy(xpath = "//app-withdrawl/div//button[contains(text(),'Clear')]")
	WebElement withdrawalClear;

	@FindBy(xpath = "//*[contains(text(),'Confirm the details')]")
	WebElement confirmScreen;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div/div[6]//strong")
	WebElement confirmScreenAmount;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement confirmScreenSubmit;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement confirmScreenCancel;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Select...')]/parent::span")
	WebElement balanceEnquiryDropdown;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']")
	WebElement balanceEnquiryMobNum;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement balanceEnquiryMobNumError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']")
	WebElement balanceEnquiryAadhaarNum;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement balanceEnquiryAadhaarNumError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']/following-sibling::ul/li")
	WebElement balanceEnquiryAadhaarNumError2;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement balanceEnquiryAmount;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement balanceEnquiryAmountError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement balanceEnquiryConsentCheckbox;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement balanceEnquiryConsentMessage;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement balanceEnquiryConsentLink;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement balanceEnquiryConsentError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//div[contains(@class,'scan_finger_container')]")
	WebElement balanceEnquiryScanFingerprint;

	@FindBy(xpath = "//app-aepsbalanceenquiry//h4[contains(text(),'Success!')]")
	WebElement balanceEnquiryScanSuccessScreen;

	@FindBy(xpath = "//app-aepsbalanceenquiry//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement balanceEnquiryFingerprintSuccess;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement balanceEnquiryFingerprintGreen;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Click to scan fingerprint')]")
	WebElement balanceEnquiryFingerprintUnscanned;

	@FindBy(xpath = "//app-aepsbalanceenquiry//button[contains(text(),'Ok')]")
	WebElement balanceEnquiryFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Submit')]")
	WebElement balanceEnquirySubmit;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Clear')]")
	WebElement balanceEnquiryClear;

	@FindBy(xpath = "//a[contains(text(),'Mini Statement')]")
	WebElement miniStatementTab;

	@FindBy(xpath = "//ybl-mini-statement//span[contains(text(),'Select...')]/parent::span")
	WebElement miniStatementDropdown;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='money-transfer-mobile-number']")
	WebElement miniStatementMobNum;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement miniStatementMobNumError;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='aeps-deposit-aadhar-number']")
	WebElement miniStatementAadhaarNum;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement miniStatementAadhaarNumError;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='aeps-deposit-aadhar-number']/parent::div/following-sibling::div/ul/li")
	WebElement miniStatementAadhaarNumError2;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement miniStatementAmount;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement miniStatementAmountError;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement miniStatementConsentCheckbox;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement miniStatementConsentMessage;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement miniStatementConsentLink;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement miniStatementConsentError;

	@FindBy(xpath = "//ybl-mini-statement//div[contains(@class,'scan_finger_container')]")
	WebElement miniStatementScanFingerprint;

	@FindBy(xpath = "//ybl-mini-statement//h4[contains(text(),'Success!')]")
	WebElement miniStatementScanSuccessScreen;

	@FindBy(xpath = "//ybl-mini-statement//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement miniStatementFingerprintSuccess;

	@FindBy(xpath = "//ybl-mini-statement//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement miniStatementFingerprintGreen;

	@FindBy(xpath = "//ybl-mini-statement//span[contains(text(),'Click to scan fingerprint')]")
	WebElement miniStatementFingerprintUnscanned;

	@FindBy(xpath = "//ybl-mini-statement//button[contains(text(),'Ok')]")
	WebElement miniStatementFingerprintScreenOkButton;

	@FindBy(xpath = "//ybl-mini-statement/div//button[contains(text(),'Submit')]")
	WebElement miniStatementSubmit;

	@FindBy(xpath = "//ybl-mini-statement/div//button[contains(text(),'Clear')]")
	WebElement miniStatementClear;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement aepsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div")
	WebElement aepsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div/strong")
	WebElement aepsDeemedTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement aepsTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Exit')]")
	WebElement aepsTxnScreenExitButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Retry')]")
	WebElement aepsTxnScreenRetryButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Save')]")
	WebElement aepsTxnScreenSaveButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Print')]")
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

	@FindBy(xpath = "//ybl-mini-statement-modal//button[contains(@class,'button-disabled')][contains(text(),'Done')]")
	WebElement doneButtonDisabled;

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

	@FindBy(xpath = "//div[contains(@class,'deposit-aeps-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement depositRefNo;

	@FindBy(xpath = "//div[contains(@class,'withdraw-aeps-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement withdrawalRefNo;

	@FindBy(xpath = "//div[contains(@class,'enquiry-aeps-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement balanceEnquiryRefNo;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement miniStatementRefNo;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Customer A/C Balance:')]/parent::div/following-sibling::div//span")
	WebElement miniStatementCustAcBalance;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	// Load all objects
	public YBLBankingPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void yblBanking(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.selectFeatureFromMenu2(banking, pageTitle);

			String batchConfigSection = "bankingstatusenquiry";

			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFileToTomcat(batchFileConfig, usrData.get("KEY"));
			}

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				commonUtils.refreshBalance(); // refresh wallet balances
			}

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			double initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
			double initialCashoutBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));

			if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
				depositTxns(usrData, initialWalletBalance);
			} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
				withdrawalTxns(usrData, initialCashoutBalance);
			} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
				balanceEnquiryTxns(usrData, initialCashoutBalance);
			} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Mini Statement")) {
				miniStatementTxns(usrData, initialCashoutBalance);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Deposit transaction
	public void depositTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		waitUntilElementIsClickableAndClickTheElement(depositTab);
		System.out.println("Deposit tab clicked");
		waitUntilElementIsClickableAndClickTheElement(depositDropdown);
		System.out.println("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		System.out.println(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		System.out.println(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(depositMobNum);
		depositMobNum.sendKeys(getAEPSMobNum(usrData.get("MOBNUM")));
		System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
		waitUntilElementIsClickableAndClickTheElement(depositAadhaarNum);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(depositMobNumError);
			Assert.assertEquals(depositMobNumError.getText(), "Required Field");
			System.out.println(depositMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(depositMobNumError);
			Assert.assertEquals(depositMobNumError.getText(), "Invalid mobile number");
			System.out.println(depositMobNumError.getText());
		}

		depositAadhaarNum.sendKeys(getAadhaarFromIni(usrData.get("AADHAAR")));
		System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		waitUntilElementIsClickableAndClickTheElement(depositAmount);

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Required Field");
			System.out.println(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Length should be 12 digits");
			System.out.println(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			System.out.println(depositAadhaarNumError.getText());
		}

		depositAmount.sendKeys(usrData.get("AMOUNT"));
		System.out.println("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
			waitUntilElementIsClickableAndClickTheElement(depositAadhaarNum);
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(), "Insufficient wallet balance");
			System.out.println(depositAmountError.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			waitUntilElementIsClickableAndClickTheElement(depositAadhaarNum);
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹ 10,000.00");
			System.out.println(depositAmountError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			waitUntilElementIsClickableAndClickTheElement(depositAadhaarNum);
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(), "Minimum amount should be ₹ 10.00");
			System.out.println(depositAmountError.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitUntilElementIsVisible(depositConsentError);
			Assert.assertEquals(depositConsentError.getText(), "Required Field");
			System.out.println(depositConsentError.getText());
		} else {
			waitUntilElementIsClickableAndClickTheElement(depositConsentCheckbox);
			System.out.println("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", depositFingerprintUnscanned.getText());
			waitUntilElementIsClickableAndClickTheElement(depositScanFingerprint);
			System.out.println("Scan fingerprint button clicked");
			waitUntilElementIsVisible(depositScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", depositFingerprintSuccess.getText());
			System.out.println(depositFingerprintSuccess.getText());
			waitUntilElementIsClickableAndClickTheElement(depositFingerprintScreenOkButton);
			System.out.println("Ok button clicked");
			waitUntilElementIsVisible(depositFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsClickableAndClickTheElement(depositSubmit);
			System.out.println("Submit button clicked");
			waitUntilElementIsClickableAndClickTheElement(mpinScreen);
			if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
				enterMpin.sendKeys(getAuthfromIni("MPIN"));
			} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
				enterMpin.sendKeys("9999");
			}
			System.out.println("MPIN entered");
			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
			}

			if (usrData.get("MPIN").equalsIgnoreCase("Cancel")) {
				waitUntilElementIsClickableAndClickTheElement(cancelMpin);
				waitUntilElementIsVisible(depositFingerprintGreen);
				System.out.println("Cancel button clicked");
			} else {
				waitUntilElementIsClickableAndClickTheElement(submitMpin);
				System.out.println("MPIN submitted");
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
							assertionOnDepositSuccessScreen(usrData);
							if (usrData.get("ASSERTION").contains("SMS")) {
								assertionOnDepositSMS(usrData);
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
								assertionOnDepositFCM(usrData);
							}
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterDepositSuccessTxn(usrData, walletBalance);
						}
					} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						assertionOnDepositFailedScreen(usrData);
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
							System.out.println("Exit button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							System.out.println("Done button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
							System.out.println("Retry button clicked");
							waitUntilElementIsVisible(depositScanSuccessScreen);
							Assert.assertEquals("Fingerprints scanned successfully",
									depositFingerprintSuccess.getText());
							System.out.println(depositFingerprintSuccess.getText());
							waitUntilElementIsClickableAndClickTheElement(depositFingerprintScreenOkButton);
							System.out.println("Ok button clicked");
							waitUntilElementIsVisible(depositFingerprintGreen);
							Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
							waitUntilElementIsClickableAndClickTheElement(depositSubmit);
							System.out.println("Submit button clicked");
							waitUntilElementIsClickableAndClickTheElement(mpinScreen);
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								enterMpin.sendKeys(getAuthfromIni("MPIN"));
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								enterMpin.sendKeys("9999");
							}
							System.out.println("MPIN entered");
							waitUntilElementIsClickableAndClickTheElement(submitMpin);
							System.out.println("MPIN submitted");
							commonUtils.processingScreen();
							waitUntilElementIsVisible(aepsTxnScreen);
							System.out.println("Txn screen displayed");
							assertionOnDepositFailedScreen(usrData);
							if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
								System.out.println("Done button clicked");
							} else {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
								System.out.println("Exit button clicked");
							}
						}
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnDepositFCM(usrData);
						}
						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else {
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterDepositFailTxn(usrData, walletBalance);
						}
					}
				}
			}
		} else {
			waitUntilElementIsClickableAndClickTheElement(depositClear);
			System.out.println("Clear button clicked");
			waitUntilElementIsVisible(depositDropdown);
			System.out.println("Data cleared");
		}
	}

	// withdrawal transaction
	public void withdrawalTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		commonUtils.waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(withdrawalTab);
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
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError);
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Length should be 12 digits");
			System.out.println(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError2);
			Assert.assertEquals(withdrawalAadhaarNumError2.getText(), "Enter Valid Aadhaar Number");
			System.out.println(withdrawalAadhaarNumError2.getText());
		}

		withdrawalAmount.sendKeys(usrData.get("AMOUNT"));
		System.out.println("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			waitUntilElementIsClickableAndClickTheElement(withdrawalAadhaarNum);
			waitUntilElementIsVisible(withdrawalAmountError);
			Assert.assertEquals(withdrawalAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹10,000.00");
			System.out.println(withdrawalAmountError.getText());
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
						waitUntilElementIsVisible(withdrawalScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								withdrawalFingerprintSuccess.getText());
						System.out.println(withdrawalFingerprintSuccess.getText());
						waitUntilElementIsClickableAndClickTheElement(withdrawalFingerprintScreenOkButton);
						System.out.println("Ok button clicked");
						waitUntilElementIsInvisible("//app-withdrawl//button[contains(text(),'Ok')]");
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
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError);
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Length should be 12 digits");
			System.out.println(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError2);
			Assert.assertEquals(balanceEnquiryAadhaarNumError2.getText(), "Enter Valid Aadhaar Number");
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
						waitUntilElementIsVisible(balanceEnquiryScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								balanceEnquiryFingerprintSuccess.getText());
						System.out.println(balanceEnquiryFingerprintSuccess.getText());
						waitUntilElementIsClickableAndClickTheElement(balanceEnquiryFingerprintScreenOkButton);
						System.out.println("Ok button clicked");
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
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(miniStatementAadhaarNumError);
			Assert.assertEquals(miniStatementAadhaarNumError.getText(), "Enter 12 digit Aadhaar or 16 digit VID");
			System.out.println(miniStatementAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
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
				waitUntilElementIsVisible(miniStatementTxnScreen);
				System.out.println("Txn screen displayed");

				if (miniStatementTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (miniStatementTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnMiniStatementSuccessScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenPrintButton);
						System.out.println("Print button clicked");
						try {
							while (doneButtonDisabled.isDisplayed() == true) {
								miniStatementTxnScreenDoneButton.getText();
							}
							waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenDoneButton);
							System.out.println("Done button clicked");
						} catch (Exception e) {
							waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenDoneButton);
							System.out.println("Done button clicked");
						}
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnMiniStatementFCM(usrData);
						}
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterMiniStatementSuccessTxn(usrData, walletBalance);
					}
				} else if (miniStatementTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnMiniStatementFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenExitButton);
						System.out.println("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenDoneButton);
						System.out.println("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenRetryButton);
						System.out.println("Retry button clicked");
						waitUntilElementIsVisible(miniStatementScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								miniStatementFingerprintSuccess.getText());
						System.out.println(miniStatementFingerprintSuccess.getText());
						waitUntilElementIsClickableAndClickTheElement(miniStatementFingerprintScreenOkButton);
						System.out.println("Ok button clicked");
						waitUntilElementIsVisible(miniStatementFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!",
								miniStatementFingerprintGreen.getText());
						waitUntilElementIsClickableAndClickTheElement(miniStatementSubmit);
						System.out.println("Submit button clicked");
						commonUtils.processingScreen();
						waitUntilElementIsVisible(miniStatementTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnMiniStatementFailedScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(miniStatementTxnScreenExitButton);
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
		return "YBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni("RetailerMobNum");
	}

	// Verify details on success screen
	public void assertionOnDepositSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").contains("Deemed Success")) {
			Assert.assertEquals(aepsDeemedTxnScreenMessage.getText(), "deemed success:error code 91");
			System.out.println(aepsDeemedTxnScreenMessage.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash deposited to customer successfully");
			System.out.println(aepsTxnScreenMessage.getText());
		}
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
		String comm = dbUtils.getAepsComm(usrData.get("AMOUNT"), "Withdrawal", partner());
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
		Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		System.out.println(aepsTxnScreenFailureReason.getText());
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
		String successSMS = "Rs." + usrData.get("AMOUNT") + ".00 withdrawn from " + usrData.get("BANKNAME")
				+ " a/c linked to Aadhaar  XXXX XXXX " + usrData.get("AADHAAR").substring(8, 12)
				+ " . Charges Rs. 0.00. Ref No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ ". Novopay App bit.ly/33n4yrH";

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
	public void assertionOnBalanceEnquirySuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Balance enquired successfully");
		System.out.println(aepsTxnScreenMessage.getText());
		txnDetailsFromIni("StoreTxnRefNo", balanceEnquiryRefNo.getText());
	}

	// Verify details on failure screen
	public void assertionOnBalanceEnquiryFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		System.out.println(aepsTxnScreenFailureReason.getText());
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
				+ " IST is Led Bal: INR 1576.26, Ava Bal: INR 1576.26, Ref No: " + txnDetailsFromIni("GetTxnRefNo", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnBalanceEnquiryFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Balance Enquiry: SUCCESS";
		String failFCMHeading = "Balance Enquiry: FAIL";

		String successFCMContent = "Balance in " + usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " as on " + dbUtils.aepsTxnDate()
				+ " IST is Led Bal: 1576.26, Ava Bal: 1576.26 Response code: (00) SUCCESS Reference No: "
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
		Assert.assertEquals(miniStatementTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		System.out.println(miniStatementTxnScreenFailureReason.getText());
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
