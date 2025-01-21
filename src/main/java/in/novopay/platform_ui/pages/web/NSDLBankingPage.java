package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

public class NSDLBankingPage extends BasePage {
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
	
	@FindBy(xpath = "//img[@src='assets/Home/novopay.svg']")
	WebElement novopayHomePage;
	
	@FindBy(xpath = "//span[contains(text(),'Withdrawal')]")
	WebElement withdrawalTab;
	
	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']")
	WebElement withdrawalMobNum;
	

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement withdrawalMobNumError;
	
	@FindBy(xpath = "//*//div[@class='ng-value-container']//div[@class='ng-input']/input")
	WebElement bankfield;
	
	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement withdrawalAmount;
	
	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement withdrawalAmountError;
	

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement withdrawalAadhaarNumError;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']/following-sibling::ul/li")
	WebElement withdrawalAadhaarNumError2;


	@FindBy(xpath = "//app-withdrawl//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Click to scan fingerprint*')]")
	WebElement withdrawalFingerprintUnscanned;
	
	@FindBy(xpath = "//app-withdrawl//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//app-withdrawl//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//app-withdrawl//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;

	@FindBy(xpath = "//app-withdrawl/div//button[contains(text(),'Submit')]")
	WebElement withdrawalSubmit;
	
	@FindBy(xpath ="//button[contains(text(),' Submit ')]")
	WebElement submitButton;
	
	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;
	
	@FindBy(xpath = "//app-withdrawl//input[@name='aeps-deposit-aadhar-number']")
	WebElement withdrawalAadhaarNum;

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


	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Print')]")
	WebElement aepsTxnScreenPrintButton;
	
	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Save')]")
	WebElement aepsTxnScreenSaveButton;
	
	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;
	
	@FindBy(xpath = "//div[contains(@class,'withdraw-aeps-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement withdrawalRefNo;
	

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//a[contains(@href, 'balance-enquiry') and contains(., 'Balance Enquiry')]")
	WebElement  balanceEnquiryTab;
			
	
	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement confirmScreenCancel;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Select...')]/parent::span")
	WebElement balanceEnquiryDropdown;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']")
	WebElement balanceEnquiryMobNum;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement balanceEnquiryMobNumError;

	//@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']")
	@FindBy(xpath = "//input[@id='nsdl-balEnq-aadharNumber']")
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

	@FindBy(xpath = "//app-aepsbalanceenquiry//div[contains(text(),'Customer Fingerprint*')]")
	WebElement balanceEnquiryFingerprintUnscanned;

	@FindBy(xpath = "//app-aepsbalanceenquiry//button[contains(text(),'Ok')]")
	WebElement balanceEnquiryFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Submit')]")
	WebElement balanceEnquirySubmit;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Clear')]")
	WebElement balanceEnquiryClear;
	

	@FindBy(xpath = "//div[contains(@class,'enquiry-aeps-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement balanceEnquiryRefNo;
	

	@FindBy(xpath = "//span[contains(text(),'Mini Statement')]")
	WebElement miniStatementTab;

	@FindBy(xpath = "//ybl-mini-statement//span[contains(text(),'Select...')]/parent::span")
	WebElement miniStatementDropdown;

	@FindBy(xpath = "//nsdl-mini-statement//input[@id='money-transfer-mobile-number']")
	WebElement miniStatementMobNum;

	@FindBy(xpath = "//ybl-mini-statement//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement miniStatementMobNumError;

	//@FindBy(xpath = "//ybl-mini-statement//input[@id='aeps-deposit-aadhar-number']")
	@FindBy(xpath = "//input[@id='nsdl-miniStatement-aadharNumber']")
	WebElement miniStatementAadhaarNum;

	@FindBy(xpath = "//nsdl-mini-statement//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement miniStatementAadhaarNumError;

	@FindBy(xpath = "//nsdl-mini-statement//input[@id='aeps-deposit-aadhar-number']/parent::div/following-sibling::div/ul/li")
	WebElement miniStatementAadhaarNumError2;

	@FindBy(xpath = "//nsdl-mini-statement//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement miniStatementAmount;

	@FindBy(xpath = "//nsdl-mini-statement//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement miniStatementAmountError;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement miniStatementConsentCheckbox;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement miniStatementConsentMessage;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement miniStatementConsentLink;

	@FindBy(xpath = "//ybl-mini-statement//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement miniStatementConsentError;

	@FindBy(xpath = "//nsdl-mini-statement//div[contains(@class,'scan_finger_container')]")
	WebElement miniStatementScanFingerprint;

	@FindBy(xpath = "//nsdl-mini-statement//h4[contains(text(),'Success!')]")
	WebElement miniStatementScanSuccessScreen;

	@FindBy(xpath = "//nsdl-mini-statement//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement miniStatementFingerprintSuccess;

	@FindBy(xpath = "//nsdl-mini-statement//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement miniStatementFingerprintGreen;

	@FindBy(xpath = "//nsdl-mini-statement//div[contains(text(),'Authentication')]")
	WebElement miniStatementFingerprintUnscanned;

	@FindBy(xpath = "//nsdl-mini-statement//button[contains(text(),'Ok')]")
	WebElement miniStatementFingerprintScreenOkButton;

	@FindBy(xpath = "//nsdl-mini-statement/div//button[contains(text(),'Submit')]")
	WebElement miniStatementSubmit;

	@FindBy(xpath = "//nsdl-mini-statement/div//button[contains(text(),'Clear')]")
	WebElement miniStatementClear;
	

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

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//strong[contains(text(),'Reference ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement miniStatementRefNo;

	@FindBy(xpath = "//div[contains(@class,'mini-statement-modal')]//span[contains(text(),'Customer A/C Balance:')]/parent::div/following-sibling::div//span")
	WebElement miniStatementCustAcBalance;


	
	// Load all objects
		public NSDLBankingPage(WebDriver wdriver) {
			super(wdriver);
			PageFactory.initElements(wdriver, this);
		}
		// Perform action on page based on given commands
		public void nsdlBanking(Map<String, String> usrData)
				throws InterruptedException, AWTException, IOException, ClassNotFoundException {

			try {
				//commonUtils.selectFeatureFromMenu2(banking, pageTitle);
				
			
				waitUntilElementIsVisible(novopayHomePage);
				System.out.println("Home page Visible");
				

				String batchConfigSection = "bankingstatusenquiry";

				HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
				if (!usrData.get("KEY").isEmpty()) {
					srvUtils.uploadFileToTomcat(batchFileConfig, usrData.get("KEY"));
				}

				commonUtils.displayInitialBalance("retailer"); // display main wallet balance
				commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance
				System.out.println("Initial balances displayed");
				System.out.println("sample test");
				
	

				if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
				    // Deposit logic
				    System.out.println("Deposit transaction");

				} else if(usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
				    // Withdrawal logic
				    System.out.println("Withdrawal transaction tab clicked");
				    waitUntilElementIsClickableAndClickTheElement(withdrawalTab);
				    System.out.println("Withdrawal tab clicked");

				    // Mobile Number Entry
				    waitUntilElementIsClickableAndClickTheElement(withdrawalMobNum);
				    withdrawalMobNum.sendKeys(usrData.get("MOBNUM"));
				    System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
				    
				    // Field Level Validation for Mobile Number
				    if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
				        waitUntilElementIsVisible(withdrawalMobNumError);
				        Assert.assertEquals(withdrawalMobNumError.getText(), "Required Field");
				        System.out.println(withdrawalMobNumError.getText());
				    } else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits") ||
				               usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
				        waitUntilElementIsVisible(withdrawalMobNumError);
				        Assert.assertEquals(withdrawalMobNumError.getText(), "Invalid mobile number");
				        System.out.println(withdrawalMobNumError.getText());
				    }
				 // Bank Entry
				    waitUntilElementIsClickableAndClickTheElement(bankfield);
				    System.out.println("Bank Field clicked");
				    bankfield.sendKeys(usrData.get("BANKNAME"));
				    System.out.println(usrData.get("BANKNAME") + " entered");
				    bankfield.sendKeys(Keys.ENTER);
				    Thread.sleep(1000);
				    System.out.println(usrData.get("BANKNAME") + " selected");
				 // Amount Entry
				    waitUntilElementIsClickableAndClickTheElement(withdrawalAmount);
				    withdrawalAmount.sendKeys(usrData.get("AMOUNT"));
				    System.out.println("Amount " + usrData.get("AMOUNT") + " entered");

				    // Field Level Validation for Amount
				    if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
				        waitUntilElementIsVisible(withdrawalAmountError);
				        Assert.assertEquals(withdrawalAmountError.getText(),
				                "Amount entered exceeds your transaction limit ₹10,000.00");
				        System.out.println(withdrawalAmountError.getText());
				    } else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
				        waitUntilElementIsVisible(withdrawalAmountError);
				        Assert.assertEquals(withdrawalAmountError.getText(), "Minimum amount should be ₹100.00");
				        System.out.println(withdrawalAmountError.getText());
				    }


				    // Aadhaar Entry
				    waitUntilElementIsClickableAndClickTheElement(withdrawalAadhaarNum);
				    withdrawalAadhaarNum.sendKeys(usrData.get("AADHAAR"));
				    System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");

				    // Field Level Validation for Aadhaar
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

				    // Fingerprint Scanning
				    if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
				        Assert.assertEquals("Click to scan fingerprint*", withdrawalFingerprintUnscanned.getText());
				        waitUntilElementIsClickableAndClickTheElement(withdrawalScanFingerprint);
				        System.out.println("Scan fingerprint button clicked");
				        waitUntilElementIsVisible(withdrawalScanSuccessScreen);
				        Assert.assertEquals("Fingerprints scanned successfully", withdrawalFingerprintSuccess.getText());
				        waitUntilElementIsClickableAndClickTheElement(withdrawalFingerprintScreenOkButton);
				    }
				    
				    // Submission
				    if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
						System.out.println("Submit button clicked");
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(submitButton);
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
								
					}
						
					} 
					}
				   }
				}

				else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
				    // Balance Enquiry logic
				    System.out.println("Balance Enquiry transaction tab clicked");
				    waitUntilElementIsClickableAndClickTheElement(balanceEnquiryTab);
				    System.out.println("Balance Enquiry tab clicked");	
				    // Mobile number entry
					waitUntilElementIsClickableAndClickTheElement(balanceEnquiryMobNum);
					balanceEnquiryMobNum.sendKeys(usrData.get("MOBNUM"));
					System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");

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
					//bank  entry
					waitUntilElementIsClickableAndClickTheElement(bankfield);
				    System.out.println("Bank Field clicked");
				    bankfield.sendKeys(usrData.get("BANKNAME"));
				    System.out.println(usrData.get("BANKNAME") + " entered");
				    bankfield.sendKeys(Keys.ENTER);
				    Thread.sleep(1000);
				    System.out.println(usrData.get("BANKNAME") + " selected");
				 // Aadhaar Entry
				    waitUntilElementIsClickableAndClickTheElement(balanceEnquiryAadhaarNum);
				    balanceEnquiryAadhaarNum.sendKeys(usrData.get("AADHAAR"));
				    System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");

				    // Field Level Validation for Aadhaar
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

				 // Fingerprint Scanning
				    if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
						Assert.assertEquals("Customer Fingerprint*", balanceEnquiryFingerprintUnscanned.getText());
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
				    // Submission
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
								}
							}
						}
				    }
				} 
				else if (usrData.get("TXNTYPE").equalsIgnoreCase("Mini Statement")) {
				    // Mini Statement logic
				    System.out.println("Mini Statement transaction");
				    
				    waitUntilElementIsClickableAndClickTheElement(miniStatementTab);
				    System.out.println("Mini Statement tab clicked");
				    
				    // Mobile number entry
					waitUntilElementIsClickableAndClickTheElement(miniStatementMobNum);
					miniStatementMobNum.sendKeys(usrData.get("MOBNUM"));
					System.out.println("Mobile number " + usrData.get("MOBNUM") + " entered");
				

					// Field level validation in Mobile field
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
					//bank  entry
					waitUntilElementIsClickableAndClickTheElement(bankfield);
				    System.out.println("Bank Field clicked");
				    bankfield.sendKeys(usrData.get("BANKNAME"));
				    System.out.println(usrData.get("BANKNAME") + " entered");
				    bankfield.sendKeys(Keys.ENTER);
				    Thread.sleep(1000);
				    System.out.println(usrData.get("BANKNAME") + " selected");
				    
				    
				    // Aadhaar Entry
				    waitUntilElementIsClickableAndClickTheElement(miniStatementAadhaarNum);
					miniStatementAadhaarNum.sendKeys(usrData.get("AADHAAR"));
					System.out.println("Aadhaar number " + usrData.get("AADHAAR") + " entered");
					

					// Field level validation in Aadhaar field
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
						// Fingerprint Scanning
					if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
						Assert.assertEquals("Biometric Authentication", miniStatementFingerprintUnscanned.getText());
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
				                        e.printStackTrace();
				                    }
				                }
				            }
				        }
				    }
				}
				
			
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test Case Failed");
				Assert.fail();
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
				
				// Get Partner name
				public String partner() {
					return "NSDL";
				}

				// Get mobile number from Ini file
				public String mobileNumFromIni() {
					return getLoginMobileFromIni("RetailerMobNum");
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
				
				public void fcmMethod(String heading, String content) {
					Assert.assertEquals(fcmHeading.getText(), heading);
					Assert.assertEquals(fcmContent.getText(), content);
					System.out.println(fcmHeading.getText());
					System.out.println(fcmContent.getText());
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



}