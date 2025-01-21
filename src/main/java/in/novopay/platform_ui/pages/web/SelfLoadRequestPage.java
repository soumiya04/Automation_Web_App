package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
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

public class SelfLoadRequestPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

	@FindBy(xpath = "//a[contains(text(),'Self-Load Request')]")
	WebElement selfLoadRequestTab;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//i[contains(@class,'np np-refresh')]")
	WebElement refreshButton;

	@FindBy(xpath = "//i[contains(@class,'np np-sync')]")
	WebElement syncButton;

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

	@FindBy(xpath = "//h1[contains(text(),'Manage Wallet')]")
	WebElement pageTitle;

	@FindBy(id = "self-load-request-email-ip")
	WebElement emailId;

	@FindBy(xpath = "//select2[contains(@id,'self-load-request-deposit-account')]/parent::div")
	WebElement accountDropDown;

	@FindBy(xpath = "//select2[contains(@id,'self-load-request-deposit-type')]/parent::div")
	WebElement typeDropDown;

	@FindBy(id = "self-load-request-utr-ip")
	WebElement utr;

	@FindBy(xpath = "//div[4]/div//input[@id='cash-deposit']/parent::span")
	WebElement cdmRadioButton;

	@FindBy(xpath = "//div[4]/div//input[@id='bank-deposit']/parent::span")
	WebElement bankRadioButton;

	@FindBy(id = "self-load-request-term")
	WebElement termNumber;

	@FindBy(id = "self-load-request-txn-number")
	WebElement txnNumber;

	@FindBy(xpath = "//*[@id='self-load-request-branch-name']//span[@role='combobox']")
	WebElement branch;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropDownSearch;

	@FindBy(id = "self-load-request-amount-ip")
	WebElement amount;

	@FindBy(id = "self-load-wallet-deposit-date")
	WebElement date;

	@FindBy(xpath = "//*[@for='file-upload']")
	WebElement uploadFile;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement requestTxnScreen;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement requestTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'remittancetxn-modal')]/div/div/div/following-sibling::div/div[2]/span")
	WebElement requestTxnFailScreenMessage;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	// Load all objects
	public SelfLoadRequestPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void selfLoadRequest(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			if (usrData.get("ASSERTION").contains("Balance")) {
				commonUtils.refreshBalance(); // refresh wallet balances
				verifyUpdatedBalanceAfterTxn(usrData, Double.parseDouble(getWalletBalanceFromIni("GetRetailer", "")));
			} else {
				commonUtils.displayInitialBalance("retailer"); // display main wallet balance
				commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

				commonUtils.selectFeatureFromMenu1(manageWalletButton, pageTitle);

				dbUtils.updateWalletTopupRequest();

				waitUntilElementIsClickableAndClickTheElement(selfLoadRequestTab);
				System.out.println("Self Load Request Tab clicked");

				Thread.sleep(1000);
				waitUntilElementIsClickableAndClickTheElement(emailId);
				emailId.sendKeys(getEmailIdFromIni(usrData.get("EMAILID")));
				System.out.println("Email id entered");

				waitUntilElementIsClickableAndClickTheElement(accountDropDown);
				System.out.println("Drop down clicked");
				String accountXpath = "//*[contains(@id,'select2')]/li[contains(text(),'" + usrData.get("ACCOUNT")
						+ "')]";
				waitUntilElementIsClickableAndClickTheElement(wdriver.findElement(By.xpath(accountXpath)));
				System.out.println(usrData.get("ACCOUNT") + " selected");

				waitUntilElementIsClickableAndClickTheElement(typeDropDown);
				System.out.println("Drop down clicked");
				String typeXpath = "//*[contains(@id,'select2')]/li[contains(text(),'" + usrData.get("TYPE") + "')]";
				waitUntilElementIsClickableAndClickTheElement(wdriver.findElement(By.xpath(typeXpath)));
				System.out.println(usrData.get("TYPE") + " selected");

				if (usrData.get("OPTIONS").equalsIgnoreCase("RefNum")) {
					waitUntilElementIsClickableAndClickTheElement(utr);
					utr.sendKeys(generateRandomReferenceNumber());
					System.out.println("Ref Num entered");
				} else if (usrData.get("OPTIONS").equalsIgnoreCase("CDM")) {
					waitUntilElementIsClickableAndClickTheElement(cdmRadioButton);
					System.out.println("CDM radio button clicked");

					if (usrData.get("ACCOUNT").contains("AXIS")) {
						waitUntilElementIsClickableAndClickTheElement(termNumber);
						termNumber.sendKeys(getTermNumberFromIni(usrData.get("TERM")));
						System.out.println("Term number entered");
					} else if (usrData.get("ACCOUNT").contains("Federal")) {
						waitUntilElementIsClickableAndClickTheElement(branch);
						System.out.println("Drop down clicked");
						waitUntilElementIsClickableAndClickTheElement(dropDownSearch);
						dropDownSearch.sendKeys(usrData.get("TERM"));
						System.out.println("Typing " + usrData.get("TERM"));

						String reportXpath = "//li[text()='" + usrData.get("TERM") + "']";
						WebElement reportDropDown = wdriver.findElement(By.xpath(reportXpath));
						reportDropDown.click();
						System.out.println(usrData.get("TERM") + " drop down selected");
					}

					if (usrData.get("ACCOUNT").contains("AXIS") || usrData.get("ACCOUNT").contains("Federal")) {
						waitUntilElementIsClickableAndClickTheElement(txnNumber);
						txnNumber.sendKeys(getTxnNumberFromIni(usrData.get("TXNNUM")));
						System.out.println("Txn number entered");
					}

				} else if (usrData.get("OPTIONS").equalsIgnoreCase("BD")) {
					waitUntilElementIsClickableAndClickTheElement(bankRadioButton);
					System.out.println("BD radio button clicked");
				}

				waitUntilElementIsClickableAndClickTheElement(amount);
				amount.sendKeys(usrData.get("AMOUNT"));
				txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
				System.out.println("Amount entered");

				getCodeFromIni(usrData.get("CODE"));

				waitUntilElementIsClickableAndClickTheElement(date);
				String dayMonth = "";
				if (usrData.get("ASSERTION").equalsIgnoreCase("Future Date")) {
					dayMonth = getTodaysDateOfMonth(1);
				} else {
					dayMonth = getTodaysDateOfMonth(0);
				}
				date.sendKeys(dayMonth);
				if (Integer.parseInt(dayMonth) < 10) {
					date.sendKeys(getTodaysMonth());
					System.out.println("DATE entered");
				}

				commonUtils.uploadFile(uploadFile);
				System.out.println("Image selected");
				Thread.sleep(2000);

				waitUntilElementIsClickableAndClickTheElement(submitButton);
				System.out.println("Submit button clicked");

				if (usrData.get("ASSERTION").equalsIgnoreCase("Future Date")) {

				} else if (usrData.get("REQUESTBUTTON").equalsIgnoreCase("Submit")) {
					if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
						waitUntilElementIsVisible(processingScreen);
						System.out.println("Processing screen displayed");
						waitUntilElementIsVisible(processInBackgroundButton);
						processInBackgroundButton.click();
						System.out.println("Process in Background button clicked");
					} else {
						waitUntilElementIsVisible(requestTxnScreen);
						System.out.println("Txn screen displayed");

						// Verify the details on transaction screen
						if (requestTxnScreen.getText().equalsIgnoreCase("Success!")) {
							assertionOnSuccessScreen(usrData);
							waitUntilElementIsClickableAndClickTheElement(doneButton);
							System.out.println("Done button clicked");
							if (usrData.get("ASSERTION").contains("FCM")) {
								assertionOnFCM(usrData);
							}
						} else if (requestTxnScreen.getText().equalsIgnoreCase("Failed!")) {
							assertionOnFailedScreen(usrData);
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								System.out.println("Clicking exit button");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								waitUntilElementIsClickableAndClickTheElement(submitButton);
								System.out.println("Submit button clicked");
								commonUtils.processingScreen();
								waitUntilElementIsVisible(requestTxnScreen);
								System.out.println("Txn screen displayed");
								assertionOnFailedScreen(usrData);
							}
							waitUntilElementIsClickableAndClickTheElement(exitButton);
							System.out.println("Exit button clicked");
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							waitUntilElementIsVisible(requestTxnScreenMessage);
							System.out.println(requestTxnScreenMessage.getText());
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								exitButton.click();
								System.out.println("Exit button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								waitUntilElementIsClickableAndClickTheElement(submitButton);
								System.out.println("Submit button clicked");
								commonUtils.processingScreen();
								waitUntilElementIsVisible(requestTxnScreen);
								System.out.println("Txn screen displayed");
								assertionOnSuccessScreen(usrData);
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								System.out.println("Done button clicked");
							}
						}
					}
				} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
					clearButton.click();
					System.out.println("Clear button clicked");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		String successMessage = "Your Wallet load request of Rs." + txnDetailsFromIni("GetTxfAmount", "")
				+ " placed on " + dbUtils.createdDateTimeFromTopUpRequest(getEmailIdFromIni("GetEmailId"))
				+ " for Cash/Bank Transfer done to Novopay Bank Account No." + usrData.get("ACCOUNT")
				+ " has been accepted. Request id "
				+ dbUtils.requestIdFromTopUpRequest(getEmailIdFromIni("GetEmailId"));
		Assert.assertEquals(requestTxnScreenMessage.getText(), successMessage);
		System.out.println(requestTxnScreenMessage.getText());
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double totalAmount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(
				dbUtils.getSelfWalletLoadCharges(getCodeFromIni("GetCode"), txnDetailsFromIni("GetTxfAmount", "")));
		double newWalletBal = 0.00;
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success Balance")) {
			newWalletBal = initialWalletBalance + totalAmount - charges;
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Fail Balance")) {
			newWalletBal = initialWalletBalance;
		}
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
	}

	// Verify details on info screen
	public void assertionOnInfoScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
	}

	public void uploadFile(WebElement cancelledCheque) throws InterruptedException, IOException {
		System.out.println("selecting cancelled cheque image");
		Thread.sleep(2000);
		waitUntilElementIsClickableAndClickTheElement(cancelledCheque);
		Thread.sleep(500);
		String uploadFile = "./test-data/UploadFile.exe";
		Runtime.getRuntime().exec(uploadFile);
		Thread.sleep(500);
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Wallet Load Request: INITIATED";
		String successFCMContent = "Your Wallet load request of Rs." + txnDetailsFromIni("GetTxfAmount", "")
				+ " placed on " + dbUtils.createdDateTimeFromTopUpRequest(getEmailIdFromIni("GetEmailId"))
				+ " for Cash/Bank Transfer done to Novopay Bank Account No." + usrData.get("ACCOUNT")
				+ " has been accepted. Request id "
				+ dbUtils.requestIdFromTopUpRequest(getEmailIdFromIni("GetEmailId"));

		fcmMethod(successFCMHeading, successFCMContent);
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
