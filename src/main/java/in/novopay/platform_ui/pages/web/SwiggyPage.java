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

public class SwiggyPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Cash Services')]")
	WebElement cashServices;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Cash Services')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'Swiggy')]")
	WebElement swiggyIcon;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement depositorMobNum;

	@FindBy(xpath = "//button[contains(text(),'Get Amount')]")
	WebElement getAmountButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement fetchedAmount;

	@FindBy(xpath = "//swiggy-collection/div//button[contains(text(),'Submit')]")
	WebElement swgSubmitButton;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement MPINScreen;

	@FindBy(id = "money-transfer-mpin-number")
	WebElement enterMPIN;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement submitMPIN;

	@FindBy(xpath = "//div//button[contains(text(),'Cancel')]")
	WebElement cancelButton;

	@FindBy(xpath = "//pin-modal/div//button[contains(text(),'Submit')]")
	WebElement mpinSubmitButton;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement cmsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div")
	WebElement cmsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement cmsTxnScreenMessage;
	
	@FindBy(xpath = "//div[contains(@class,'cms-modal')]//strong[contains(text(),'Reference ID:')]/parent::div/following-sibling::div")
	WebElement cmsTxnScreenRefId;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement cmsTxnScreenAmount;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading1;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent1;

	// Load all objects
	public SwiggyPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void swiggy(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);

			commonUtils.selectFeatureFromMenu2(cashServices, pageTitle);

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			// Click on Swiggy icon
//			waitUntilElementIsClickableAndClickTheElement(swiggyIcon);
			commonUtils.selectCmsBiller();
			System.out.println("Swiggy icon clicked");

			// Click on depositor mobile number field
			waitUntilElementIsClickableAndClickTheElement(depositorMobNum);
			depositorMobNum.sendKeys(usrData.get("MOBILENUMBER"));
			System.out.println("Depositor mobile number entered");

			if (usrData.get("GETAMOUNT").equalsIgnoreCase("YES")) {
				// Click on Get Amount button
				waitUntilElementIsClickableAndClickTheElement(getAmountButton);

				commonUtils.waitForSpinner();

				if (usrData.get("MOBILENUMBER").equalsIgnoreCase("6000000001")) {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Invalid delivery boy");
					System.out.println(toasterMsg.getText());
				} else {
					// Store fetched amount
					waitUntilElementIsVisible(fetchedAmount);
					String amount = fetchedAmount.getAttribute("value");
					System.out.println("Amount is " + amount);
					cmsDetailsFromIni("StoreSwiggyAmount", replaceSymbols(amount));
				}
			} else if (usrData.get("GETAMOUNT").equalsIgnoreCase("Clear")) {
				// Click on Clear button
				clickElement(clearButton);
			}

			if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
				if (!usrData.get("AMOUNT").equalsIgnoreCase("SKIP")) {
					fetchedAmount.clear();
					Thread.sleep(1000);
					fetchedAmount.sendKeys(usrData.get("AMOUNT"));
					cmsDetailsFromIni("StoreSwiggyAmount", usrData.get("AMOUNT"));
				}

				// Click on Submit button
				waitUntilElementIsClickableAndClickTheElement(swgSubmitButton);

				if (getWalletBalanceFromIni("GetCashout", "").equals("0.00")) {
					System.out.println("Cashout Balance is 0, hence money will be deducted from Main Wallet");
				} else {
					commonUtils.chooseWalletScreen(usrData);
				}

				if (!getWalletFromIni("GetWallet", "").equalsIgnoreCase("-")) {
					waitUntilElementIsVisible(MPINScreen);
					System.out.println("MPIN screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterMPIN);
					if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
						enterMPIN.sendKeys(getAuthfromIni("MPIN"));
					} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
						enterMPIN.sendKeys("9999");
					}
					System.out.println("MPIN entered");

					String mpinButtonName = usrData.get("MPINSCREENBUTTON");
					String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
							+ "')]";
					WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
					System.out.println(mpinButtonName + " button clicked");
					if (mpinButtonName.equalsIgnoreCase("Cancel")) {
						System.out.println("Cancel button clicked");
					} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
						commonUtils.processingScreen();

						waitUntilElementIsVisible(cmsTxnScreen);
						System.out.println("Txn screen displayed");

						// Verify the details on transaction screen
						if (cmsTxnScreen.getText().equalsIgnoreCase("Success!")) {
							assertionOnSuccessScreen(usrData);
							assertionOnSMS(usrData);

							waitUntilElementIsClickableAndClickTheElement(doneButton);
							System.out.println("Done button clicked");
							if (usrData.get("ASSERTION").contains("FCM")) {
								assertionOnFCM(usrData);
							}
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterSuccessTxn(usrData);
						} else if (cmsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								assertionOnFailedScreen(usrData);
								assertionOnSMS(usrData);
								if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
									System.out.println("Clicking exit button");
								} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
									retryButton.click();
									Thread.sleep(1000);
									waitUntilElementIsVisible(MPINScreen);
									System.out.println("MPIN screen displayed");
									waitUntilElementIsClickableAndClickTheElement(enterMPIN);
									enterMPIN.sendKeys(getAuthfromIni("MPIN"));
									System.out.println("MPIN entered");
									waitUntilElementIsClickableAndClickTheElement(submitMPIN);
									System.out.println("Submit button clicked");
									commonUtils.processingScreen();
									waitUntilElementIsVisible(cmsTxnScreen);
									System.out.println("Txn screen displayed");
									assertionOnFailedScreen(usrData);
								}
								waitUntilElementIsClickableAndClickTheElement(exitButton);
								System.out.println("Exit button clicked");
								if (usrData.get("ASSERTION").contains("FCM")) {
									assertionOnFCM(usrData);
								}
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								waitUntilElementIsVisible(cmsTxnScreenMessage);
								System.out.println(cmsTxnScreenMessage.getText());
								if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
									exitButton.click();
									System.out.println("Exit button clicked");
								} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
									retryButton.click();
									Thread.sleep(1000);
									waitUntilElementIsVisible(MPINScreen);
									System.out.println("MPIN screen displayed");
									waitUntilElementIsClickableAndClickTheElement(enterMPIN);
									enterMPIN.sendKeys(getAuthfromIni("MPIN"));
									System.out.println("MPIN entered");
									waitUntilElementIsClickableAndClickTheElement(submitMPIN);
									System.out.println("Submit button clicked");
									commonUtils.processingScreen();
									waitUntilElementIsVisible(cmsTxnScreen);
									System.out.println("Txn screen displayed");
									assertionOnSuccessScreen(usrData);
									doneButton.click();
									System.out.println("Done button clicked");
									commonUtils.refreshBalance();
								}
							}
						}
					}
				}
			} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
				clearButton.click();
				System.out.println("Clear button clicked");
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
		Assert.assertEquals(cmsTxnScreenMessage.getText(), "Deposit to Swiggy successful.");
		System.out.println(cmsTxnScreenMessage.getText());
		txnDetailsFromIni("StoreTxnRefNo", cmsTxnScreenRefId.getText());
		Assert.assertEquals(replaceSymbols(cmsTxnScreenAmount.getText()), cmsDetailsFromIni("Amount", "") + ".00");
		System.out.println(cmsTxnScreenAmount.getText());
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(cmsTxnScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(cmsTxnScreenMessage.getText(), "Insufficient balance");
		} else {
			Assert.assertEquals(cmsTxnScreenMessage.getText(), "Deposit to Swiggy failed.");
			txnDetailsFromIni("StoreTxnRefNo", cmsTxnScreenRefId.getText());
		}
		System.out.println(cmsTxnScreenMessage.getText());
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String successSMS = "Success! Deposit of Rs " + cmsDetailsFromIni("Amount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " was successful.";
		String failSMS = "Failure! Deposit of Rs " + cmsDetailsFromIni("Amount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " failed.";
		Thread.sleep(5000);
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			System.out.println(successSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Fail SMS")) {
			Assert.assertEquals(failSMS, dbUtils.sms());
			System.out.println(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Swiggy: SUCCESS";
		String failFCMHeading = "Swiggy: FAIL";

		String successFCM = "Success! Deposit of Rs " + cmsDetailsFromIni("Amount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " was successful.";
		String failFCM = "Failure! Deposit of Rs " + cmsDetailsFromIni("Amount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " failed.";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCM);
			break;
		case "Fail FCM":
			fcmMethod(failFCMHeading, failFCM);
			break;
		}
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading1.getText(), heading);
		Assert.assertEquals(fcmContent1.getText(), content);
		System.out.println(fcmHeading1.getText());
		System.out.println(fcmContent1.getText());
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		double amount = Double.parseDouble(cmsDetailsFromIni("Amount", ""));
		double comm = amount * 2 / 1000;
		double commission = Math.round(comm * 100.0) / 100.0;
		double taxDS = commission * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000;
		double tds = Math.round(taxDS * 100.0) / 100.0;
		double newWalletBal = initialWalletBalance - amount + commission - tds;
		txnDetailsFromIni("StoreComm", String.valueOf(commission));
		txnDetailsFromIni("StoreTds", String.valueOf(tds));
		String newWalletBalance = df.format(newWalletBal);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
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