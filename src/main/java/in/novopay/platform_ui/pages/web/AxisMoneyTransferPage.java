package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AxisMoneyTransferPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");

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

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//h1[contains(text(),'Money Transfer')]")
	WebElement pageTitle;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement custMobNum;

	@FindBy(xpath = "//*[contains(text(),'Limit remaining')]")
	WebElement limitRem;

	@FindBy(id = "money-transfer-customer-name")
	WebElement custName;

	@FindBy(id = "money-transfer-customer-dob")
	WebElement dob;

	@FindBy(xpath = "//div[4]/div/span[1][contains(@class,'radiobox')]")
	WebElement genderMale;

	@FindBy(xpath = "//div[4]/div/span[2][contains(@class,'radiobox')]")
	WebElement genderFemale;

	@FindBy(id = "money-transfer-beneficiary-list")
	WebElement beneList;

	@FindBy(xpath = "//span[contains(@class,'add-beneficiary')]/parent::li")
	WebElement addNewBene;

	@FindBy(id = "money-transfer-beneficiary-name")
	WebElement beneName;

	@FindBy(id = "money-transfer-beneficiary-mobile-number")
	WebElement beneMobNum;

	@FindBy(id = "money-transfer-beneficiary-bank-ifsc-list")
	WebElement ifscCode;

	@FindBy(id = "money-transfer-beneficiary-account-number")
	WebElement beneACNum;

	@FindBy(xpath = "//button[contains(text(),'Add Bene')]")
	WebElement addBeneButton;

	@FindBy(xpath = "//button[contains(text(),'Val Bene')]")
	WebElement validateBeneButton;

	@FindBy(xpath = "//button[contains(text(),'Del Bene')]")
	WebElement deleteBeneButton;

	@FindBy(xpath = "//input[contains(@type,'checkbox')]")
	WebElement deleteBeneCheckbox;

	@FindBy(xpath = "//div[@class='card-layout']//button[contains(text(),'Clear')]/parent::div/following-sibling::div/button")
	WebElement moneyTransferSubmitButton;

	@FindBy(xpath = "//*[contains(text(),'Clear')]")
	WebElement moneyTransferClearButton;

	@FindBy(id = "allowed-transaction-type-imps")
	WebElement imps;

	@FindBy(id = "allowed-transaction-type-neft")
	WebElement neft;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement amount;

	@FindBy(xpath = "//*[@id='money-transfer-amount-to-be-transferred']/following-sibling::ul/li")
	WebElement amountErrorMsg;

	@FindBy(xpath = "//span[contains(@class,'custom-ul-errormessage')]/span[contains(text(),'Branch')]")
	WebElement validateIFSC;

	@FindBy(xpath = "//input[contains(@id,'money-transfer-beneficiary-bank-ifsc-list')]/parent::div/following-sibling::span")
	WebElement ifscSearchIcon;

	@FindBy(xpath = "//h5[contains(text(),'IFSC Search')]")
	WebElement ifscSearchScreen;

	@FindBy(xpath = "//*[@id='ifsc-search-bank']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchBankList;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchStateList;

	@FindBy(id = "ifsc-search-district")
	WebElement ifscSearchDistrict;

	@FindBy(id = "ifsc-search-branch")
	WebElement ifscSearchBranch;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	WebElement ifscSearchButton;

	@FindBy(xpath = "//div/i[contains(@class,'fa fa-arrow-left')]")
	WebElement ifscSearchBack;

	@FindBy(id = "search-bank-ifsc")
	WebElement ifscSearch;

	@FindBy(xpath = "//div[contains(@class,'ifsc-search-modal')]//button[contains(text(),'OK')]")
	WebElement ifscSearchOK;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]")
	WebElement OTPScreen;

	@FindBy(xpath = "//button[contains(@class,'input-group-addon btn-icon')]")
	WebElement applicableChargesButton;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]")
	WebElement applicableChargesScreen;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Transaction Amount')]/parent::div/following-sibling::div")
	WebElement applicableTxnAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Charges')]/parent::div/following-sibling::div")
	WebElement applicableCharges;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/p[contains(text(),'Cash to be Collected')]/following-sibling::p")
	WebElement applicableTotalAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div[2]/button")
	WebElement applicableChargesOkButton;

	@FindBy(xpath = "//*[contains(text(),'Confirm the details')]")
	WebElement confirmScreen;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div/div[7]//strong")
	WebElement confirmScreenAmount;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement confirmScreenSubmit;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement confirmScreenCancel;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement MPINScreen;

	@FindBy(id = "money-transfer-otp-number")
	WebElement enterOTP;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]/parent::div/following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'Confirm')]")
	WebElement confirmOTP;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]/parent::div/following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'Cancel')]")
	WebElement cancelOTP;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]/parent::div/button/span")
	WebElement crossOTP;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(id = "money-transfer-mpin-number")
	WebElement enterMPIN;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement submitMPIN;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement remittanceTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div")
	WebElement remittanceTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement remittanceTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//span[contains(text(),'Transferred Amount:')]/parent::div/following-sibling::div")
	WebElement remittanceTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement remittanceTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement remittanceTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement remittanceTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//span[contains(@class,'font-size-12 failure-cross')]")
	WebElement remittanceTxnScreenFailureReason;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//button[contains(text(),'Done')]")
	WebElement remittanceTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//button[contains(text(),'Exit')]")
	WebElement remittanceTxnScreenExitButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//button[contains(text(),'Retry')]")
	WebElement remittanceTxnScreenRetryButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//button[contains(text(),'Save')]")
	WebElement remittanceTxnScreenSaveButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]//button[contains(text(),'Print')]")
	WebElement remittanceTxnScreenPrintButton;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]")
	WebElement beneValidationScreen;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div/div/p")
	WebElement beneValidationMessage;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div[2]/div[1]/div[2]/span")
	WebElement beneValidationCase1Name;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div[2]/div[2]/div[1]/span[2]")
	WebElement beneNameCase2NameEntered;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div[2]/div[2]/div[2]/span[2]")
	WebElement beneNameCase2NameByBank;

	@FindBy(xpath = "//p[contains(text(),'Beneficiary Validation Failed - as')]")
	WebElement beneValidationCase4;

	@FindBy(xpath = "//strong[contains(text(),'Suggestion')]")
	WebElement beneValidationCase5;

	@FindBy(xpath = "//*[@name='useNameFromBank']")
	WebElement beneCheckbox;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div[4]/button")
	WebElement beneSuccessOkButton;

	@FindBy(xpath = "//h5[contains(text(),'Beneficiary Validation')]/parent::div/following-sibling::div[3]/button")
	WebElement beneFailOkButton;

	@FindBy(xpath = "//h5[contains(text(),'Delete Beneficiary')]")
	WebElement deleteBeneScreen;

	@FindBy(xpath = "//h5[contains(text(),'Delete Beneficiary')]/parent::div/following-sibling::div[2]/button[contains(text(),'Confirm')]")
	WebElement deleteConfirmButton;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Deletion OTP')]")
	WebElement deleteBeneOTPScreen;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Deletion OTP')]/parent::div/following-sibling::div/div/div/input")
	WebElement deleteBeneEnterOTP;

	@FindBy(xpath = "//h5[contains(text(),'Enter Bene. Deletion OTP')]/parent::div/following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'Confirm')]")
	WebElement deleteBeneConfirmOTP;

	@FindBy(xpath = "//*[contains(text(),'Similar transaction is under processing')]")
	WebElement blackout;

	@FindBy(xpath = "//table/tbody/tr/div/div[2]/label")
	WebElement refNo;

	@FindBy(xpath = "//div[contains(@class,'add-bene-retry-modal')]/form/div/div/h4[contains(text(),'!')]")
	WebElement addBeneFailedScreen;

	@FindBy(xpath = "//div[contains(@class,'add-bene-retry-modal')]/form/div/div/following-sibling::div/span")
	WebElement addBeneFailedMessage;

	@FindBy(xpath = "//div[contains(@class,'delete-bene-retry-modal')]/form/div/div/h4[contains(text(),'!')]")
	WebElement deleteBeneFailedScreen;

	@FindBy(xpath = "//div[contains(@class,'delete-bene-retry-modal')]/form/div/div/following-sibling::div/span")
	WebElement deleteBeneFailedMessage;

	@FindBy(xpath = "//div[contains(@class,'otp-modal')]//*[contains(text(),'Resend OTP')]")
	WebElement resendOTP;

	@FindBy(xpath = "//div[contains(@class,'delete-otp-modal')]//*[contains(text(),'Resend OTP')]")
	WebElement deleteBeneResendOTP;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading1;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent1;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]//strong")
	WebElement fcmHeading2;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent2;

	@FindBy(xpath = "//li[3][contains(@class,'notifications')]//strong")
	WebElement fcmHeading3;

	@FindBy(xpath = "//li[3][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent3;

	String txnID = "", batchConfigSection = "axisremittancestatusenquiry";

	// Load all objects
	public AxisMoneyTransferPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void axisMoneyTransfer(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			getPartner(partner());

			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFileToTomcat(batchFileConfig, usrData.get("KEY"));
			}

			String moneyTransferXpath = "//a[@href='/newportal/" + partner().toLowerCase()
					+ "-transfer']/span[contains(text(),'Money Transfer')]";
			WebElement moneyTransfer = wdriver.findElement(By.xpath(moneyTransferXpath));

			if (!usrData.get("REFRESH").equalsIgnoreCase("Never")) {
				commonUtils.selectFeatureFromMenu1(moneyTransfer, pageTitle);
			}

			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				clickElement(menu);
				commonUtils.refreshBalance(); // refresh wallet balances
				clickElement(menu);
			}

			if (!usrData.get("ASSERTION").equalsIgnoreCase("Processing")) {
				// display wallet balances in console
				commonUtils.displayInitialBalance("retailer"); // display main wallet balance
				commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance
			}

			if (usrData.get("ASSERTION").contains("EnableQueuing")) {
				dbUtils.updateBatchStatus("EnableRemittanceQueuing", "SUCCESS");
				dbUtils.updateBatchStatus("DisableRemittanceQueuing", "STOPPED");
			}

			customerDetails(usrData);

			// Provide beneficiary details based on user data
			if (usrData.get("BENE").equalsIgnoreCase("New")) { // when beneficiary is new
				Thread.sleep(2000);
				waitUntilElementIsClickableAndClickTheElement(beneList);
				System.out.println("Clicked on bene list drop down");
				beneList.sendKeys("add new beneficiary");
				try {
					waitUntilElementIsClickableAndClickTheElement(addNewBene);
				} catch (Exception e) {
					customerDetails(usrData);
					Thread.sleep(2000);
					waitUntilElementIsClickableAndClickTheElement(beneList);
					System.out.println("Clicked on bene list drop down");
					beneList.sendKeys("add new beneficiary");
					waitUntilElementIsClickableAndClickTheElement(addNewBene);
				}
				System.out.println("'Add New Beneficiary' selected");
				waitUntilElementIsClickableAndClickTheElement(beneName);
				beneName.sendKeys(getBeneNameFromIni(usrData.get("BENENAME")));
				System.out.println("Bene name '" + usrData.get("BENENAME") + "' entered");
				waitUntilElementIsClickableAndClickTheElement(beneMobNum);
				beneMobNum.sendKeys(getBeneNumberFromIni(usrData.get("BENENUMBER")));
				System.out.println("Bene mobile number '" + getBeneNumberFromIni("GetNum") + "' entered");
				waitUntilElementIsClickableAndClickTheElement(beneACNum);
				beneACNum.sendKeys(getAccountNumberFromIni(usrData.get("BENEACNUM")));
				System.out.println("Bene account number '" + getAccountNumberFromIni("GetNum") + "' entered");
				if (usrData.get("BENEIFSCTYPE").equalsIgnoreCase("Manual")) {
					waitUntilElementIsClickableAndClickTheElement(ifscCode);
					ifscCode.sendKeys(usrData.get("BENEIFSC"));
					System.out.println("IFSC code '" + usrData.get("BENEIFSC") + "' entered");
				} else if (usrData.get("BENEIFSCTYPE").equalsIgnoreCase("Search Screen")) {
					waitUntilElementIsClickableAndClickTheElement(ifscSearchIcon);
					System.out.println("IFSC search icon clicked");
					waitUntilElementIsVisible(ifscSearchScreen);
					waitUntilElementIsClickableAndClickTheElement(ifscSearchBankList);
					System.out.println("IFSC bank drop down clicked");
					String ifscBank = "//li[contains(text(),'"
							+ dbUtils.ifscCodeDetails(usrData.get("BENEIFSC"), "bank") + "')]";
					WebElement ifscSearchBank = wdriver.findElement(By.xpath(ifscBank));
					ifscSearchBank.click();
					System.out.println("IFSC bank selected");
					ifscSearchStateList.click();
					System.out.println("IFSC state drop down clicked");
					String stateFromDB = dbUtils.ifscCodeDetails(usrData.get("BENEIFSC"), "state");
					String stateCapitalized = stateFromDB.toUpperCase();
					String ifscState = "//li[contains(text(),'" + stateCapitalized + "')]";
					WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
					ifscSearchState.click();
					System.out.println("IFSC state selected");
					ifscSearchDistrict.sendKeys(dbUtils.ifscCodeDetails(usrData.get("BENEIFSC"), "district"));
					System.out.println("IFSC district entered");
					ifscSearchBranch.sendKeys(dbUtils.ifscCodeDetails(usrData.get("BENEIFSC"), "branch"));
					System.out.println("IFSC branch entered");
					ifscSearchButton.click();
					System.out.println("Search button clicked");
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(ifscSearchBack);
					String searchCode = "//span[contains(@class,'add-beneficiary-list')][contains(text(),'"
							+ usrData.get("BENEIFSC") + "')]/parent::li";
					WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
					waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
					System.out.println("IFSC code '" + usrData.get("BENEIFSC") + "' entered");
					ifscSearchOK.click();
					System.out.println("OK button clicked");
				} else if (usrData.get("BENEIFSCTYPE").equalsIgnoreCase("Drop Down")) {
					waitUntilElementIsClickableAndClickTheElement(ifscCode);
					String searchCode = "//span[contains(@class,'add-beneficiary-sublist')][contains(text(),'"
							+ usrData.get("BENEIFSC") + "')]/parent::li";
					WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
					waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
					System.out.println("IFSC code '" + usrData.get("BENEIFSC") + "' entered");
				}
				getBankNameFromIni(dbUtils.ifscCodeDetails(usrData.get("BENEIFSC"), "bank"));
				waitUntilElementIsVisible(validateIFSC); // wait for Branch name to be displayed
				System.out.println(validateIFSC.getText());

				// Validate beneficiary before registration
				if (usrData.get("VALIDATEBENE").equalsIgnoreCase("BEFOREREG")) {
					validateBene(usrData, commonUtils.getInitialBalance("retailer"));
				}
			}

			else if (usrData.get("BENE").equalsIgnoreCase("Existing")) { // when beneficiary is existing
				Thread.sleep(2000);
				waitUntilElementIsClickableAndClickTheElement(beneList);
				System.out.println("Clicked on bene list drop down");
				beneList.sendKeys(usrData.get("BENENAME"));
				Thread.sleep(1000);
				String beneName = usrData.get("BENENAME");
				String beneIFSC = usrData.get("BENEIFSC");
				String beneACNum = getAccountNumberFromIni("GetNum");
				String beneXpath = "//span[contains(text(),'" + beneName
						+ "')]/following-sibling::span[contains(text(),'" + beneACNum + "') and contains(text(),'"
						+ dbUtils.getBank(beneIFSC) + "')]/parent::li";
				if (usrData.get("ASSERTION").contains("Icon + Name")) {
					String beneValIconXpath = beneXpath + "//i";
					WebElement beneValIcon = wdriver.findElement(By.xpath(beneValIconXpath));
					String beneNickNameXpath = beneXpath + "/span[1]";
					WebElement beneNickNameName = wdriver.findElement(By.xpath(beneNickNameXpath));
					String accountHolderNameXpath = beneXpath + "/span[2]";
					WebElement accountHolderName = wdriver.findElement(By.xpath(accountHolderNameXpath));
					waitUntilElementIsVisible(beneValIcon);
					System.out.println("Validate icon visible");
					if (usrData.get("ASSERTION").equalsIgnoreCase("Icon + Name (Same)")) {
						Assert.assertEquals(beneNickNameName.getText().trim(), getBeneNameFromBank("GetBeneName", ""));
						Assert.assertEquals(accountHolderName.getText(),
								getBeneNameFromBank("GetBeneName", "").toUpperCase());
						System.out.println("Bene nickname and Account Holder Name are same");
					} else if (usrData.get("ASSERTION").equalsIgnoreCase("Icon + Name (Different)")) {
						Assert.assertEquals(beneNickNameName.getText().trim(), getBeneNameFromIni("GetBeneName"));
						Assert.assertEquals(accountHolderName.getText(),
								getBeneNameFromBank("GetBeneName", "").toUpperCase());
						System.out.println("Bene nickname and Account Holder Name are different");
					}
				}
				waitUntilElementIsClickableAndClickTheElement(wdriver.findElement(By.xpath(beneXpath)));
				System.out.println(beneName + " beneficiary selected");
			}

			// Click on Add Bene button and provide necessary details
			if (usrData.get("ADDBENE").equalsIgnoreCase("Indirectly")) {
				waitUntilElementIsClickableAndClickTheElement(addBeneButton);
				System.out.println("Add Bene button clicked");
				if (!usrData.get("ASSERTION").equalsIgnoreCase("Bene Limit")) {
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(OTPScreen);
					System.out.println("OTP screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterOTP);
					if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
						enterOTP.sendKeys(getAuthfromIni(otpFromIni()));
					} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")) {
						enterOTP.sendKeys("111111");
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						waitUntilElementIsClickableAndClickTheElement(resendOTP);
						enterOTP.sendKeys(getAuthfromIni(otpFromIni()));
					}
					System.out.println("OTP entered");
					String buttonName = usrData.get("OTPSCREENBUTTON");
					String otpScreenButtonXpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'"
							+ buttonName + "')]";
					WebElement otpScreenButton = wdriver.findElement(By.xpath(otpScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(otpScreenButton);
					System.out.println(buttonName + " button clicked");
					commonUtils.waitForSpinner();
					if (buttonName.equalsIgnoreCase("Confirm")) {
						if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
							// waitUntilElementIsVisible(toasterMsg));
							// System.out.println(toasterMsg.getText());
						} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")) {
							waitUntilElementIsVisible(addBeneFailedScreen);
							System.out.println(addBeneFailedMessage.getText());
							String otpFailedScreenButtonName = usrData.get("OTPFAILEDSCREENBUTTON");
							String otpFailedScreenButtonXpath = "//div[contains(@class,'add-bene-retry-modal')]//button[contains(text(),'"
									+ otpFailedScreenButtonName + "')]";
							WebElement otpFailedScreenButton = wdriver
									.findElement(By.xpath(otpFailedScreenButtonXpath));
							waitUntilElementIsClickableAndClickTheElement(otpFailedScreenButton);
							System.out.println(otpFailedScreenButtonName + " button clicked");
							if (usrData.get("OTPFAILEDSCREENBUTTON").equalsIgnoreCase("Retry")) {
								commonUtils.waitForSpinner();
								waitUntilElementIsClickableAndClickTheElement(enterOTP);
								enterOTP.sendKeys(getAuthfromIni(otpFromIni()));
								System.out.println("OTP entered");
								waitUntilElementIsClickableAndClickTheElement(confirmOTP);
								System.out.println("Confirm button clicked");
								commonUtils.waitForSpinner();
								// waitUntilElementIsVisible(toasterMsg));
								// System.out.println(toasterMsg.getText());
							}
						}
					}
				} else {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "You have reached the maximum beneficiary count for 10. "
							+ "Please delete any existing beneficiary to add a new one");
					System.out.println(toasterMsg.getText());
				}
			}
			// Validate beneficiary after registration
			if (usrData.get("VALIDATEBENE").equalsIgnoreCase("AFTERREG")) {
				validateBene(usrData, commonUtils.getInitialBalance("retailer"));
			}

			// Delete Beneficiary
			if (usrData.get("DELETEBENE").equalsIgnoreCase("YES")) {
				deleteBene(usrData, getAuthfromIni(otpFromIni()));
			}

			// Submitting all the details to perform Money Transfer
			if (usrData.get("SUBMIT").equalsIgnoreCase("YES")) {
				if (dbUtils.modeOfTransfer(usrData.get("BENEIFSC")).equals("1")) {
					Assert.assertEquals(imps.isSelected(), true);
					System.out.println("IMPS mode auto-selected");
				} else if (dbUtils.modeOfTransfer(usrData.get("BENEIFSC")).equals("0")) {
					Assert.assertEquals(neft.isSelected(), true);
					System.out.println("NEFT mode auto-selected");
				}

				waitUntilElementIsClickableAndClickTheElement(amount);
				Thread.sleep(1000);
				amount.sendKeys(usrData.get("AMOUNT"));
				System.out.println("amount entered");

				Thread.sleep(2000);
				waitUntilElementIsClickableAndClickTheElement(moneyTransferSubmitButton);
				System.out.println("Submit button clicked");

				if (usrData.get("ASSERTION").equalsIgnoreCase("Main!=0 Cashout=0")) {
					System.out.println("Cashout Balance is 0, hence money will be deducted from Main Wallet");
				} else {
					commonUtils.chooseWalletScreen(usrData);
				}
				confirmScreen(usrData);

				// Provide OTP if beneficiary is to be added during money transfer
				if (usrData.get("ADDBENE").equalsIgnoreCase("Directly")) {
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(OTPScreen);
					System.out.println("OTP screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterOTP);
					if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
						enterOTP.sendKeys(getAuthfromIni(otpFromIni()));
					} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")) {
						enterOTP.sendKeys("111111");
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						waitUntilElementIsClickableAndClickTheElement(resendOTP);
						enterOTP.sendKeys(getAuthfromIni(otpFromIni()));
					}
					System.out.println("OTP entered");
					String buttonName = usrData.get("OTPSCREENBUTTON");
					String otpScreenButtonXpath = "//h5[contains(text(),'Enter Bene. Registration OTP')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'"
							+ buttonName + "')]";
					WebElement otpScreenButton = wdriver.findElement(By.xpath(otpScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(otpScreenButton);
					System.out.println(buttonName + " button clicked");
					commonUtils.waitForSpinner();
					if (buttonName.equalsIgnoreCase("Confirm")) {
						moneyTransfer(usrData);
					}
				} else if (usrData.get("ADDBENE").equalsIgnoreCase("Indirectly")
						|| usrData.get("ADDBENE").equalsIgnoreCase("No")) {
					moneyTransfer(usrData);
				}
			} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
				waitUntilElementIsClickableAndClickTheElement(moneyTransferClearButton);
			} else if (usrData.get("SUBMIT").equalsIgnoreCase("No")) {
				if (!usrData.get("AMOUNT").equalsIgnoreCase("SKIP")) {
					waitUntilElementIsClickableAndClickTheElement(amount);
					Thread.sleep(1000);
					amount.sendKeys(usrData.get("AMOUNT"));
					System.out.println("amount entered");
				}

				// Field level validation in Amount field
				if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Both Wallets")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText(), "Insufficient wallet balance");
					System.out.println(amountErrorMsg.getText());
					dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
					dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
				}
				if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Limit")
						|| usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText().substring(0, 43),
							"Amount entered exceeds your available limit");
					System.out.println(amountErrorMsg.getText());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText(), "Minimum amount should be ₹10.00");
					System.out.println(amountErrorMsg.getText());
				}

				// Verify applicable charges
				if (usrData.get("CHARGES").equalsIgnoreCase("YES")) {
					waitUntilElementIsClickableAndClickTheElement(applicableChargesButton);
					waitUntilElementIsVisible(applicableChargesScreen);
					assertionOnApplicableCharges(usrData);
					applicableChargesOkButton.click();
					System.out.println("Charges verified");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void customerDetails(Map<String, String> usrData)
			throws InterruptedException, NumberFormatException, ClassNotFoundException {
		// Click on customer Mobile Number field
		waitUntilElementIsClickableAndClickTheElement(custMobNum);
		custMobNum.clear();
		custMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("CUSTOMERNUMBER")));
		System.out.println("Customer mobile number " + custMobNum.getText() + " entered");

		// Provide customer details based on user data
		if (usrData.get("CUSTOMERNUMBER").equalsIgnoreCase("NewNum")) { // when customer is new
			System.out.println("New customer mobile number entered");
			commonUtils.waitForSpinner();
			Thread.sleep(1000);
			limitCheck(usrData); // check limit remaining
			custMobNum.sendKeys(Keys.TAB);
			custName.sendKeys(getCustomerDetailsFromIni("NewName"));
			System.out.println("Customer name " + custName.getText() + " entered");
			custName.sendKeys(Keys.TAB);
			dob.sendKeys(usrData.get("DOB"));
			System.out.println("Date of birth entered");
			if (usrData.get("GENDER").equalsIgnoreCase("MALE")) {
				clickElement(genderMale);
			} else if (usrData.get("GENDER").equalsIgnoreCase("FEMALE")) {
				clickElement(genderFemale);
			}
			System.out.println("Gender selected");
		} else if (usrData.get("CUSTOMERNUMBER").equalsIgnoreCase("ExistingNum")
				|| usrData.get("CUSTOMERNUMBER").equalsIgnoreCase("GetBeneNum")) { // when customer is existing
			System.out.println("Existing customer mobile number entered");
			commonUtils.waitForSpinner();
			limitCheck(usrData); // check limit remaining
		}
	}

	// Method to validate beneficiary based on user data
	public void validateBene(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException, ParseException, InterruptedException {
		waitUntilElementIsClickableAndClickTheElement(validateBeneButton);
		System.out.println("validating beneficiary");
		if (usrData.get("ASSERTION").equalsIgnoreCase("Main!=0 Cashout=0")) {
			System.out.println("Cashout Balance is 0, hence money will be deducted from Main Wallet");
		} else {
			commonUtils.chooseWalletScreen(usrData);
		}
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(beneValidationScreen);
		System.out.println(beneValidationMessage.getText());
		assertionOnBeneValidationScreen(usrData, initialWalletBalance);
		if (usrData.get("ASSERTION").equalsIgnoreCase("Dont Update Bene")) {
			waitUntilElementIsClickableAndClickTheElement(beneCheckbox);
			System.out.println("Checkbox deselected");
		}
		try {
			beneSuccessOkButton.click();
		} catch (Exception e) {
			beneFailOkButton.click();
		}
		System.out.println("OK button clicked");
		if (usrData.get("ASSERTION").equalsIgnoreCase("Dont Update Bene")) {
			Assert.assertEquals(beneName.getAttribute("value"), usrData.get("BENENAME"));
			System.out.println("Bene name remains " + beneName.getAttribute("value"));
		} else {
			Assert.assertEquals(beneName.getAttribute("value"), getBeneNameFromBank("GetBeneName", ""));
			System.out.println("Bene name got replaced by " + beneName.getAttribute("value"));
		}
		if (usrData.get("ASSERTION").contains("FCM")) {
			assertionOnFCM(usrData);
		}
	}

	// Method to delete beneficiary based on user data
	public void deleteBene(Map<String, String> usrData, String OTP) {
		waitUntilElementIsClickableAndClickTheElement(deleteBeneButton);
		System.out.println("Del Bene button clicked");
		waitUntilElementIsVisible(deleteBeneScreen);
		System.out.println("Delete Bene screen displayed");
		if (usrData.get("DELETEBENETYPE").equalsIgnoreCase("HARD")) {
			waitUntilElementIsClickableAndClickTheElement(deleteBeneCheckbox);
			System.out.println("Hard Deleting Bene");
		} else {
			System.out.println("Soft Deleting Bene");
		}
		deleteConfirmButton.click();
		System.out.println("Confirm button clicked");
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(deleteBeneOTPScreen);
		System.out.println("OTP screen displayed");
		waitUntilElementIsClickableAndClickTheElement(deleteBeneEnterOTP);
		if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
			deleteBeneEnterOTP.sendKeys(getAuthfromIni(otpFromIni()));
		} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")) {
			deleteBeneEnterOTP.sendKeys("111111");
		} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
			waitUntilElementIsClickableAndClickTheElement(deleteBeneResendOTP);
			deleteBeneEnterOTP.sendKeys(getAuthfromIni(otpFromIni()));
		}
		System.out.println("OTP entered");
		String buttonName = usrData.get("OTPSCREENBUTTON");
		String otpScreenButtonXpath = "//h5[contains(text(),'Enter Bene. Deletion OTP')]/parent::div/"
				+ "following-sibling::div/following-sibling::div/div[2]/button[contains(text(),'" + buttonName + "')]";
		WebElement otpScreenButton = wdriver.findElement(By.xpath(otpScreenButtonXpath));
		waitUntilElementIsClickableAndClickTheElement(otpScreenButton);
		System.out.println(buttonName + " button clicked");
		commonUtils.waitForSpinner();
		if (buttonName.equalsIgnoreCase("Confirm")) {
			if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
				// waitUntilElementIsVisible(toasterMsg));
				// System.out.println(toasterMsg.getText());
			} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")) {
				waitUntilElementIsVisible(deleteBeneFailedScreen);
				System.out.println(deleteBeneFailedMessage.getText());
				String otpFailedScreenButtonName = usrData.get("OTPFAILEDSCREENBUTTON");
				String otpFailedScreenButtonXpath = "//div[contains(@class,'delete-bene-retry-modal')]//button[contains(text(),'"
						+ otpFailedScreenButtonName + "')]";
				WebElement otpFailedScreenButton = wdriver.findElement(By.xpath(otpFailedScreenButtonXpath));
				waitUntilElementIsClickableAndClickTheElement(otpFailedScreenButton);
				System.out.println(otpFailedScreenButtonName + " button clicked");
				if (usrData.get("OTPFAILEDSCREENBUTTON").equalsIgnoreCase("Retry")) {
					commonUtils.waitForSpinner();
					waitUntilElementIsClickableAndClickTheElement(deleteBeneEnterOTP);
					deleteBeneEnterOTP.sendKeys(getAuthfromIni(otpFromIni()));
					System.out.println("OTP entered");
					waitUntilElementIsClickableAndClickTheElement(deleteBeneConfirmOTP);
					System.out.println("Confirm button clicked");
					commonUtils.waitForSpinner();
					// waitUntilElementIsVisible(toasterMsg));
					// System.out.println(toasterMsg.getText());
				}
			}
		}
	}

	// Confirm screen
	public void confirmScreen(Map<String, String> usrData) throws InterruptedException, ClassNotFoundException {
		waitUntilElementIsVisible(confirmScreen);
		System.out.println("Confirm the details screen displayed");
		Assert.assertEquals(replaceSymbols(confirmScreenAmount.getText()), usrData.get("AMOUNT") + ".00");
		waitUntilElementIsVisible(confirmScreenSubmit);
		confirmScreenSubmit.click();
		Thread.sleep(2000);
		System.out.println("Submit button clicked");
		if (usrData.get("ASSERTION").equalsIgnoreCase("Main!=0 Cashout=0")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
		}
	}

	// Provide MPIN during money transfer and do assertion on txn screen
	public void moneyTransfer(Map<String, String> usrData)
			throws ClassNotFoundException, InterruptedException, ParseException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		waitUntilElementIsVisible(MPINScreen);
		System.out.println("MPIN screen displayed");
		waitUntilElementIsClickableAndClickTheElement(enterMPIN);
		if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
			enterMPIN.sendKeys(getAuthfromIni("MPIN"));
		} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
			enterMPIN.sendKeys("9999");
		}
		System.out.println("MPIN entered");

		if (!usrData.get("ASSERTION").equalsIgnoreCase("Processing")) {

			unblockQueuingConfig(usrData); // Unblock banks based on user data
			unblockBanks(usrData); // Update queuing_config table based on user data
			blackoutCheck(usrData); // Check if Blackout is to be enabled or not

			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
			}
		}

		String mpinButtonName = usrData.get("MPINSCREENBUTTON");
		String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
				+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName + "')]";
		WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
		waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
		System.out.println(mpinButtonName + " button clicked");
		if (mpinButtonName.equalsIgnoreCase("Cancel")) {
			commonUtils.waitForSpinner();
		} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsVisible(processingScreen);
				System.out.println("Processing screen displayed");
				waitUntilElementIsClickableAndClickTheElement(processInBackgroundButton);
				System.out.println("Process in Background button clicked");
			} else {
				if (usrData.get("BLACKOUTCHECK").equalsIgnoreCase("Yes")
						|| (usrData.get("ADDBENE").equalsIgnoreCase("Directly")
								&& (usrData.get("OTP").equalsIgnoreCase("Invalid")
										|| usrData.get("MPIN").equalsIgnoreCase("Invalid")))) {
					System.out.println("Processing screen NOT displayed");
				} else {
					commonUtils.processingScreen();
				}
				if (usrData.get("OTP").equalsIgnoreCase("Invalid")
						&& usrData.get("ADDBENE").equalsIgnoreCase("Directly")) {
					waitUntilElementIsVisible(addBeneFailedScreen);
				} else {
					waitUntilElementIsVisible(remittanceTxnScreen);
				}
				System.out.println("Txn screen displayed");

				// Verify the details on transaction screen
				if (remittanceTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (remittanceTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnSuccessScreen(usrData);
						if (usrData.get("ASSERTION").equalsIgnoreCase("EnableQueuingCheck")) {
							Assert.assertNull(dbUtils.verifyIfQueuingIsEnabled(partner()));
							System.out.println("Queuing auto-enabled");
						}
					} else if (remittanceTxnScreenType.getAttribute("class").contains("ongoing")) {
						assertionOnWarnScreen(usrData);
						if (usrData.get("ASSERTION").equalsIgnoreCase("DisableQueuingCheck")) {
							Thread.sleep(90000);
							Assert.assertEquals("Auto disabled, based on " + dbUtils.getPaymentRefCode(partner()),
									dbUtils.verifyIfQueuingIsDisabled(usrData, partner()));
							System.out.println("Queuing auto-disabled");
						}
					}
					assertionOnSMS(usrData);
					if (usrData.get("ASSERTION").equalsIgnoreCase("Check Limit")) {
						limitCheck(usrData); // check limit remaining
					}
					if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
						waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenSaveButton);
						System.out.println("Save button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
						waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenPrintButton);
						System.out.println("Print button clicked");
					}
					waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenDoneButton);
					System.out.println("Done button clicked");
					if (usrData.get("ASSERTION").contains("FCM")) {
						assertionOnFCM(usrData);
					}
					if (!usrData.get("ASSERTION").equalsIgnoreCase("Processing")) {
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
					}
				} else if (remittanceTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					if (usrData.get("OTP").equalsIgnoreCase("Valid") && usrData.get("MPIN").equalsIgnoreCase("Valid")) {
						assertionOnFailedScreen(usrData);
						if (usrData.get("ASSERTION").equalsIgnoreCase("Check Limit")) {
							limitCheck(usrData); // check limit remaining
						}
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
							waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenExitButton);
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							remittanceTxnScreenRetryButton.click();
							System.out.println("Retry button clickeda");
							waitUntilElementIsVisible(MPINScreen);
							System.out.println("MPIN screen displayed");
							waitUntilElementIsClickableAndClickTheElement(enterMPIN);
							enterMPIN.sendKeys(getAuthfromIni("MPIN"));
							System.out.println("MPIN entered");
							Thread.sleep(1000);
							waitUntilElementIsClickableAndClickTheElement(submitMPIN);
							System.out.println("Submit button clicked");
							commonUtils.processingScreen();
							waitUntilElementIsVisible(remittanceTxnScreen);
							System.out.println("Txn screen displayed");
							assertionOnFailedScreen(usrData);
							waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenExitButton);
						}
						System.out.println("Exit button clicked");
						if (usrData.get("ASSERTION").contains("FCM")) {
							assertionOnFCM(usrData);
						}
						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else {
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterFailTxn(usrData, initialWalletBalance);
						}
					} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")
							|| usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
						waitUntilElementIsVisible(remittanceTxnScreenMessage);
						System.out.println(remittanceTxnScreenMessage.getText());
						if (usrData.get("OTPFAILEDSCREENBUTTON").equalsIgnoreCase("Exit")
								|| usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
							remittanceTxnScreenDoneButton.click();
							System.out.println("Done button clicked");
						} else if (usrData.get("OTPFAILEDSCREENBUTTON").equalsIgnoreCase("Retry")
								|| usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							remittanceTxnScreenRetryButton.click();
							System.out.println("Retry button clicked");
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(MPINScreen);
							System.out.println("MPIN screen displayed");
							Thread.sleep(1000);
							waitUntilElementIsClickableAndClickTheElement(enterMPIN);
							enterMPIN.sendKeys(getAuthfromIni("MPIN"));
							System.out.println("MPIN entered");
							waitUntilElementIsClickableAndClickTheElement(submitMPIN);
							System.out.println("Submit button clicked");
							commonUtils.processingScreen();
							waitUntilElementIsVisible(remittanceTxnScreen);
							System.out.println("Txn screen displayed");
							assertionOnSuccessScreen(usrData);
							remittanceTxnScreenDoneButton.click();
							System.out.println("Done button clicked");
							if (usrData.get("ASSERTION").contains("FCM")) {
								assertionOnFCM(usrData);
							}
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
						}
					}
				} else if (remittanceTxnScreen.getText().equalsIgnoreCase("Info!")) {
					waitUntilElementIsVisible(blackout);
					System.out.println(blackout.getText());
					waitUntilElementIsClickableAndClickTheElement(remittanceTxnScreenExitButton);
					System.out.println("Exit button clicked");
					dbUtils.updateBlackoutDuration("1");
				}
			}
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (dbUtils.verifyIfQueuingIsEnabled(partner()) == null
				&& dbUtils.verifyIfTxnIsQueued(partner()).equalsIgnoreCase("NotQueued")) {
			System.out.println("Queuing for " + dbUtils.getQueuedBankName() + " is enabled");
		}
		Assert.assertEquals(remittanceTxnScreenMessage.getText(), "Funds transferred successfully");
		System.out.println(remittanceTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(remittanceTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		String chrges = dbUtils.getRemittanceCharges(usrData.get("AMOUNT"),
				dbUtils.getChargeCategory(mobileNumFromIni()), partner());
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenCharges.getText()), chrges);
		System.out.println("Charges: " + replaceSymbols(remittanceTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", chrges);
		txnDetailsFromIni("StoreTxnRefNo", refNo.getText());
		tableAssertion();
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(chrges);
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(remittanceTxnScreenTotalAmount.getText()));
		if (usrData.get("ASSERTION").contains("EnableQueuing")) {
			dbUtils.updateBatchStatus("EnableRemittanceQueuing", "STOPPED");
		}
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount + charges;
		double commission = commonUtils.commissionAndTDS("comm", 20.0, 4.0, 40.0);
		double tds = commonUtils.commissionAndTDS("tds", 20.0, 4.0, 40.0);
		double newWalletBal = 0.00;
		newWalletBal = initialWalletBalance - totalAmount + commission - tds;
		txnDetailsFromIni("StoreComm", String.valueOf(commission));
		String newWalletBalance = df.format(newWalletBal);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(remittanceTxnScreenMessage.getText(), "Funds transfer failed");
		System.out.println(remittanceTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenFailedAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Failed Amount: " + replaceSymbols(remittanceTxnScreenFailedAmount.getText()));
		txnDetailsFromIni("StoreFailAmount", usrData.get("AMOUNT"));
		txnDetailsFromIni("StoreTxnRefNo", refNo.getText());
		tableAssertion();
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	// Verify details on warn screen
	public void assertionOnWarnScreen(Map<String, String> usrData) throws ClassNotFoundException {
		if (dbUtils.verifyIfQueuingIsEnabled(partner()) == null
				&& dbUtils.verifyIfTxnIsQueued(partner()).equalsIgnoreCase("Queued")) {
			Assert.assertEquals(remittanceTxnScreenMessage.getText(),
					"Transactions have been accepted for processing, Beneficiary Bank switch down, please check the status after some time");
			txnDetailsFromIni("StoreTxnRefNo", dbUtils.paymentRefCode(partner()));
		} else {
			Assert.assertEquals(remittanceTxnScreenMessage.getText(), "Funds transferred successfully");
			txnDetailsFromIni("StoreTxnRefNo", refNo.getText());
		}
		System.out.println(remittanceTxnScreenMessage.getText());
		int i = 0;
		double successCount = 0.00, failCount = 0.00;
		int rowCount = wdriver.findElements(By.tagName("tbody")).size();
		for (i = 1; i <= rowCount; i++) {
			String amountElementXpath = "//table/tbody[" + i + "]/tr/div/div[1]";
			WebElement amountElement = wdriver.findElement(By.xpath(amountElementXpath));
			String amt = replaceSymbols(amountElement.getText());
			double amount = Double.parseDouble(amt);

			String status91ElementXpath = "//table/tbody[" + i + "]/tr/div/div[3]/label";
			WebElement status91Element = wdriver.findElement(By.xpath(status91ElementXpath));
			if (!status91Element.getText().contains("(91)")) {
				String statusElementXpath = "//table/tbody[" + i + "]/tr/div/div[3]/label/i";
				WebElement statusElement = wdriver.findElement(By.xpath(statusElementXpath));

				if (statusElement.getAttribute("class").contains("success")) {
					successCount = successCount + amount;
				} else if (statusElement.getAttribute("class").contains("fail")) {
					failCount = failCount + amount;
				}
			} else if (status91Element.getText().contains("(91)")) {
				successCount = successCount + amount;
			}
		}
		String txfAmount = df.format(successCount);
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenTxnAmount.getText()), txfAmount);
		System.out.println("Transferred Amount: " + replaceSymbols(remittanceTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", txfAmount);
		String failAmount = df.format(failCount);
		if (failAmount.equalsIgnoreCase(".00")) {
			failAmount = "0.00";
		}
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenFailedAmount.getText()), failAmount);
		System.out.println("Failed Amount: " + replaceSymbols(remittanceTxnScreenFailedAmount.getText()));
		String chrges = dbUtils.getRemittanceCharges(txfAmount, dbUtils.getChargeCategory(mobileNumFromIni()),
				partner());
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenCharges.getText()), chrges);
		System.out.println("Charges: " + replaceSymbols(remittanceTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", chrges);
		tableAssertion();
		double charges = Double.parseDouble(chrges);
		double totalAmount = successCount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(remittanceTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(remittanceTxnScreenTotalAmount.getText()));
	}

	// Verify details on applicable charges screen
	public void assertionOnApplicableCharges(Map<String, String> usrData) throws ClassNotFoundException {
		System.out.println("Verifying charges");
		Assert.assertEquals(replaceSymbols(applicableTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Transaction Amount: " + replaceSymbols(applicableTxnAmount.getText()));
		String chrges = dbUtils.getRemittanceCharges(usrData.get("AMOUNT"),
				dbUtils.getChargeCategory(mobileNumFromIni()), partner());
		Assert.assertEquals(replaceSymbols(applicableCharges.getText()), chrges);
		System.out.println("Charges: " + replaceSymbols(applicableCharges.getText()));

		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(chrges);
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(applicableTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(applicableTotalAmount.getText()));
	}

	// Verify details on Bene Validation screen
	public void assertionOnBeneValidationScreen(Map<String, String> usrData, double oldWalletBalance)
			throws ClassNotFoundException, ParseException, InterruptedException {
		try {
			if (beneValidationCase1Name.getText().equalsIgnoreCase(usrData.get("BENENAME"))) {
				System.out.println("Case 1 validated");
			}
		} catch (Exception e) {
			try {
				if (!beneNameCase2NameEntered.getText().equalsIgnoreCase(beneNameCase2NameByBank.getText())) {
					if (beneNameCase2NameByBank.getText().isEmpty()) {
						System.out.println("Case 3 validated");
					} else {
						System.out.println("Case 2 validated");
						getBeneNameFromBank("StoreBeneName", beneNameCase2NameByBank.getText());
					}
				}
			} catch (Exception f) {
				try {
					if (beneValidationCase5.isDisplayed()) {
						System.out.println("Case 5 validated");
					}
				} catch (Exception g) {
					System.out.println("Case 4 validated");
				}
			}
		}

		double newWalletBal = 0.00;
		String newWalletBalance = "";
		if (usrData.get("VALIDATEBENECASE").equalsIgnoreCase("5")) {
			newWalletBal = oldWalletBalance;
		} else {
			double beneAmt = Double.parseDouble(dbUtils.getBeneAmount(partner()));
			newWalletBal = oldWalletBalance - beneAmt;
		}
		newWalletBalance = df.format(newWalletBal);
		commonUtils.refreshBalance();
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	// Verify table details on transaction screen
	public void tableAssertion() {
		int i = 0, j = 0;
		int rowCount = wdriver.findElements(By.tagName("tbody")).size();
		System.out.println("	Amount	Ref No		Status");
		for (i = 1; i <= rowCount; i++) {
			System.out.print("Row " + i + ":	");
			for (j = 1; j <= 4; j++) {
				String tableElementXpath = "//table/tbody[" + i + "]/tr/div/div[" + j + "]";
				WebElement tableElement = wdriver.findElement(By.xpath(tableElementXpath));
				if (j == 1) {
					System.out.print(replaceSymbols(tableElement.getText()) + "	");
				}
				if (j == 2) {
					String refNo = tableElement.getText();
					if (refNo.length() >= 10) {
						System.out.print(refNo + "	");
					} else {
						System.out.print(refNo + "		");
					}
				}
				if (j == 3) {
					String status91ElementXpath = "//table/tbody[" + i + "]/tr/div/div[" + j + "]/label";
					WebElement status91Element = wdriver.findElement(By.xpath(status91ElementXpath));

					if (!status91Element.getText().contains("(91)")) {
						String statusElementXpath = "//table/tbody[" + i + "]/tr/div/div[" + j + "]/label/i";
						WebElement statusElement = wdriver.findElement(By.xpath(statusElementXpath));

						if (statusElement.getAttribute("class").contains("success")) {
							System.out.print("✔");
						} else if (statusElement.getAttribute("class").contains("fail")) {
							System.out.print("✖");
						}
					} else if (status91Element.getText().contains("(91)")) {
						System.out.print("(91)");
					}
				}
				if (j == 4) {
					try {
						String failReasonElementXpath = "//table/tbody[" + i + "]/tr/div/div[" + j + "]/span";
						WebElement failReasonElement = wdriver.findElement(By.xpath(failReasonElementXpath));
						if (failReasonElement.isDisplayed()) {
							System.out.print(" (" + failReasonElement.getText() + ")");
						}
					} catch (Exception e) {
						System.out.print("");
					}
				}
			}
			System.out.println("");
		}
	}

	// Get remitter remaining limit
	public String limitRemaining(String mobNum, String type) throws NumberFormatException, ClassNotFoundException {
		double limit = Double.parseDouble(dbUtils.getLimitRemaining(mobNum, partner())) / 100;
		String expectedLimitRem = df.format(limit);
		String actualLimitRem = replaceSymbols(limitRem.getText().substring(33)).replaceAll("\\)", "");
		if (type.equals("actual")) {
			return actualLimitRem;
		} else if (type.equals("expected")) {
			return expectedLimitRem;
		}
		return null;
	}

	// Check limit remaining
	public void limitCheck(Map<String, String> usrData) throws NumberFormatException, ClassNotFoundException {
		if (usrData.get("LIMITCHECK").equalsIgnoreCase("YES")) {
			waitUntilElementIsVisible(limitRem);
			Assert.assertEquals(limitRemaining("", "actual"),
					limitRemaining(getCustomerDetailsFromIni("ExistingNum"), "expected"));
			System.out.println(limitRem.getText());
		}
	}

	// Unblock banks based on user data
	public void unblockBanks(Map<String, String> usrData) throws InterruptedException, ClassNotFoundException {
		if (usrData.get("UNBLOCKBANKS").equalsIgnoreCase("YES")) {
			Thread.sleep(1000);
			List<String> list = dbUtils.getBankCodeToUnqueue(partner()); // get bank codes of the
																			// queued banks
			if (list != null) {
				for (String code : list) { // iterate the result
					String disableQueuingURL = "https://" + getEnvURLfromIni()
							+ ".novopay.in/portal/remittance/disable/queuing?bankcode=" + code;

					((JavascriptExecutor) wdriver).executeScript("window.open()"); // open new tab
					ArrayList<String> tabs = new ArrayList<String>(wdriver.getWindowHandles());
					Thread.sleep(1000);
					wdriver.switchTo().window(tabs.get(1)); // switch to new tab
					wdriver.get(disableQueuingURL); // hit url to disable queuing
					wdriver.close(); // close the tab
					wdriver.switchTo().window(tabs.get(0)); // switch to previous window
				}
			}
		}
	}

	// Update queuing_config table based on user data
	public void unblockQueuingConfig(Map<String, String> usrData) throws ClassNotFoundException {
		if (usrData.get("UPDATEQCONFIG").equalsIgnoreCase("YES")) {
			dbUtils.updateQueuingConfig(usrData.get("QTHRESHOLD"), partner(), usrData.get("QCODE"));
		} else if (usrData.get("UPDATEQCONFIG").equalsIgnoreCase("Yes(DeleteFRC)")) {
			// delete records from failed_remittance_code table
			dbUtils.deleteRecordsFromFRC(usrData.get("QCODE"), partner());
			dbUtils.updateQueuingConfig(usrData.get("QTHRESHOLD"), partner(), usrData.get("QCODE"));
		}
	}

	// Check if Blackout is to be enabled or not
	public void blackoutCheck(Map<String, String> usrData) throws ClassNotFoundException {
		if (usrData.get("BLACKOUTCHECK").equalsIgnoreCase("YES")) {
			dbUtils.updateBlackoutDuration("300");
		} else if (usrData.get("BLACKOUTCHECK").equalsIgnoreCase("No")) {
			dbUtils.updateBlackoutDuration("1");
		}
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Dear customer, transfer of Rs " + usrData.get("AMOUNT") + ".00" + " has been initiated to "
				+ usrData.get("BENENAME") + " (" + dbUtils.beneAccountPAN() + ", "
				+ dbUtils.getBank(usrData.get("BENEIFSC")) + "). Txn Id: " + dbUtils.getBankRefCode(partner()) + "";

		String partialSuccessSMS = "Dear customer, transfer of Rs " + txnDetailsFromIni("GetTxfAmount", "")
				+ " has been initiated to " + usrData.get("BENENAME") + " (" + dbUtils.beneAccountPAN() + ", "
				+ dbUtils.getBank(usrData.get("BENEIFSC")) + "). Txn Id: " + dbUtils.getBankRefCodePS(partner()) + "";

		String queuedTxnSMS = "Txn for Rs. " + usrData.get("AMOUNT") + ".00" + " to " + usrData.get("BENENAME") + " ("
				+ dbUtils.beneAccountPAN() + ", " + dbUtils.getBank(usrData.get("BENEIFSC"))
				+ ") is accepted, will be processed once Bank is up. Txn Id: " + dbUtils.paymentRefCode(partner()) + "";
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Partial Success SMS")) {
			Assert.assertEquals(partialSuccessSMS, dbUtils.sms());
			System.out.println(partialSuccessSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Queued Txn SMS")
				&& remittanceTxnScreenType.getAttribute("class").contains("ongoing")) {
			Assert.assertEquals(queuedTxnSMS, dbUtils.sms());
			System.out.println(queuedTxnSMS);
		}
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successSummaryFCMHeading = "Cash To Account: Success";
		String failSummaryFCMHeading = "Cash To Account: Failed";
		String successFCMHeading = "Cash To Account : SUCCESS";
		String failFCMHeading = "Cash To Account : FAIL";
		String beneSuccessFCMHeading = "Beneficiary Validation:SUCCESS";
		String beneFailFCMHeading = "Beneficiary Validation:FAIL";
		String queuingDisableFCMHeading = dbUtils.getBank(usrData.get("BENEIFSC")) + " IMPS service is working";
		String queuingEnableFCMHeading = dbUtils.getBank(usrData.get("BENEIFSC")) + " IMPS service is down";

		String balance = df.format(commonUtils.getInitialBalance("retailer"));
		double beneAmount = Double.parseDouble(dbUtils.getBeneAmount(partner().toUpperCase()));
		String beneAmt = df.format(beneAmount);
		String successSummaryFCMContent = "Cash to account request has been processed. Rs " + usrData.get("AMOUNT")
				+ ".00 transferred successfully. Click here for details";
		String failSummaryFCMContent = "Cash to account request has been processed. However, Rs "
				+ usrData.get("AMOUNT") + ".00 transfer failed. Click here for details";
		String successFCMContent = "Customer: " + getCustomerDetailsFromIni("ExistingNum") + " Money Transfer of ₹"
				+ usrData.get("AMOUNT") + ".00" + " has been accepted for IFSC:" + usrData.get("BENEIFSC") + ", A/C:"
				+ getAccountNumberFromIni("GetNum") + ", charges ₹" + txnDetailsFromIni("GetCharges", "")
				+ ", reference no. " + dbUtils.paymentRefCode(partner()) + " Agent Balance: " + balance;
		String failFCMContent = "Customer: " + getCustomerDetailsFromIni("ExistingNum") + " Transaction Failed";
		String queuedTxnFCMContent = "Customer: " + getCustomerDetailsFromIni("ExistingNum")
				+ " Transactions have been accepted for processing, Beneficiary Bank switch down, "
				+ "please check the status after some time Agent Balance: " + balance;
		String beneSuccessFCMContent = "Customer: " + getCustomerDetailsFromIni("ExistingNum") + " Account Number "
				+ getAccountNumberFromIni("GetNum") + " in HDFC BANK belongs to " + usrData.get("BENENAME")
				+ ". Please collect Rs. " + beneAmt + " from customer. Agent Balance: Rs. " + balance;
		String beneFailFCMContent = "Customer: " + getCustomerDetailsFromIni("ExistingNum")
				+ " Beneficiary name could not be fetched due to some error.";
		String queuingDisableFCMContent = dbUtils.getBank(usrData.get("BENEIFSC"))
				+ " IMPS service is working now. Pending transactions will be processed now";
		String queuingEnableFCMContent = dbUtils.getBank(usrData.get("BENEIFSC"))
				+ " IMPS service is down. Transactions are being accepted for processing. "
				+ "Processing will be done once the service is up";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			try {
				fcmMethod1(successSummaryFCMHeading, successSummaryFCMContent);
				fcmMethod2(successFCMHeading, successFCMContent);
			} catch (Exception e) {
				fcmMethod1(successFCMHeading, successFCMContent);
				fcmMethod2(successSummaryFCMHeading, successSummaryFCMContent);
			}
			break;
		case "Failed FCM":
			try {
				fcmMethod1(failSummaryFCMHeading, failSummaryFCMContent);
				fcmMethod2(failFCMHeading, failFCMContent);
			} catch (Exception e) {
				fcmMethod1(failFCMHeading, failFCMContent);
				fcmMethod2(failSummaryFCMHeading, failSummaryFCMContent);
			}
			break;
		case "Queued FCM":
			try {
				fcmMethod1(successFCMHeading, queuedTxnFCMContent);
			} catch (Exception e) {
				fcmMethod2(successFCMHeading, queuedTxnFCMContent);
			}
			break;
		case "BeneSuccess FCM":
			fcmMethod1(beneSuccessFCMHeading, beneSuccessFCMContent);
			break;
		case "BeneFailed FCM":
			fcmMethod1(beneFailFCMHeading, beneFailFCMContent);
			break;
		case "EnableQueuing FCM":
			if (fcmHeading1.getText().equals(queuingEnableFCMHeading)) {
				Assert.assertEquals(fcmContent1.getText(), queuingEnableFCMContent);
				System.out.println(fcmHeading1.getText());
				System.out.println(fcmContent1.getText());
			} else if (fcmHeading2.getText().equals(queuingEnableFCMHeading)) {
				Assert.assertEquals(fcmContent2.getText(), queuingEnableFCMContent);
				System.out.println(fcmHeading2.getText());
				System.out.println(fcmContent2.getText());
			} else if (fcmHeading3.getText().equals(queuingEnableFCMHeading)) {
				Assert.assertEquals(fcmContent3.getText(), queuingEnableFCMContent);
				System.out.println(fcmHeading3.getText());
				System.out.println(fcmContent3.getText());
			}
			break;
		case "DisableQueuing FCM":
			fcmMethod1(queuingDisableFCMHeading, queuingDisableFCMContent);
			break;
		}
	}

	public void fcmMethod1(String heading, String content) {
		Assert.assertEquals(fcmHeading1.getText(), heading);
		Assert.assertEquals(fcmContent1.getText(), content);
		System.out.println(fcmHeading1.getText());
		System.out.println(fcmContent1.getText());
	}

	public void fcmMethod2(String heading, String content) {
		Assert.assertEquals(fcmHeading2.getText(), heading);
		Assert.assertEquals(fcmContent2.getText(), content);
		System.out.println(fcmHeading2.getText());
		System.out.println(fcmContent2.getText());
	}

	public void fcmMethod3(String heading, String content) {
		Assert.assertEquals(fcmHeading3.getText(), heading);
		Assert.assertEquals(fcmContent3.getText(), content);
		System.out.println(fcmHeading3.getText());
		System.out.println(fcmContent3.getText());
	}

	// Get Partner name
	public String partner() {
		return "AXIS";
	}

	// Get otp from Ini file
	public String otpFromIni() {
		return partner().toUpperCase() + "OTP";
	}

	public void updateWalletBalance(Map<String, String> usrData) throws ClassNotFoundException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Main < Amount")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Cashout < Amount")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Both Wallets")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1");
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Main=0 Cashout!=0")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Main!=0 Cashout=0")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
		}
	}
}
