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

public class SettlementPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

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

	@FindBy(id = "money-transfer-mobile-number")
	WebElement cashoutBalanceField;

	@FindBy(xpath = "//select2[contains(@id,'wallet-transfer-amount-to-be-transferred-to-account')]/parent::div")
	WebElement toDropDown;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Bank account')]")
	WebElement bankAccountDropDownValue;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Bank account')][1]")
	WebElement bankAccountDropDownValue1;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Bank account')][2]")
	WebElement bankAccountDropDownValue2;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Wallet Balance')]")
	WebElement retailerCreditDropDownValue;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement amountField;

	@FindBy(xpath = "//*[@id='money-transfer-amount-to-be-transferred']/parent::div/following-sibling::div//li")
	WebElement amountErrorMsg;

	@FindBy(xpath = "//button[contains(@class,'input-group-addon btn-icon')]")
	WebElement applicableChargesButton;

	@FindBy(id = "transferMode-imps")
	WebElement imps;

	@FindBy(id = "transferMode-neft")
	WebElement neft;

	@FindBy(xpath = "//*[@id='cashout-balance-form']//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//*[contains(@class,'retailer-account-status')]")
	WebElement accountStatus;

	@FindBy(xpath = "//p[contains(@class,'failure')]")
	WebElement blockedMessage;

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

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]")
	WebElement applicableChargesScreen;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Transaction Amount')]/parent::div/following-sibling::div")
	WebElement applicableTxnAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Charges')]/parent::div/following-sibling::div")
	WebElement applicableCharges;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/p[contains(text(),'Amount to be Transferred')]/following-sibling::p")
	WebElement applicableTotalAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div[2]/button")
	WebElement applicableChargesOkButton;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement settlementTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div")
	WebElement settlementTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement settlementTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div[2]/span")
	WebElement settlementTxnFailScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenRequestedAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Ref.ID:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenRefId;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//p[contains(text(),'Amount Transferred')]/parent::div/p[2]")
	WebElement settlementTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement settlementTxnScreenFailureReason;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//button[contains(text(),'Edit')]")
	WebElement editButton;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	WebElement okButton;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]//strong")
	WebElement fcmHeading2;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent2;

	@FindBy(xpath = "//a[contains(text(),'Cashout Settlement')]")
	WebElement cashoutTab;

	// Load all objects
	public SettlementPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void settlement(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			// Updating org_stlmnt_info table as per test case
			if (usrData.get("STATUS").equalsIgnoreCase("Verified")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				if (usrData.get("ASSERTION").contains("off 2 accounts")) {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "2", "1", mobileNumFromIni(), "1",
							"1234567890", "NOW()");
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "2", "1", mobileNumFromIni(), "1",
							"1234567891", "NOW()");
				} else {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "2", "1", mobileNumFromIni(), "1",
							"1234567890", "NOW()");
				}
			} else if (usrData.get("STATUS").equalsIgnoreCase("Pending")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "1", "1", mobileNumFromIni(), "0",
						"1234567890", "NOW()");
			} else if (usrData.get("STATUS").equalsIgnoreCase("Rejected")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "3", "1", mobileNumFromIni(), "0",
						"1234567890", "NOW()");
			} else if (usrData.get("STATUS").equalsIgnoreCase("Deleted")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), "TO_BANK", "6", "1", mobileNumFromIni(), "0",
						"1234567890", "NOW()");
			}

			dbUtils.updateAepsPartner(usrData.get("AEPSPARTNER").toUpperCase(),
					getLoginMobileFromIni("RetailerMobNum"));
			dbUtils.updateSettlementPartner(usrData.get("SETPARTNER").toUpperCase(),
					usrData.get("AEPSPARTNER").toLowerCase(), usrData.get("MODE"));

			dbUtils.updateIfscMode(usrData.get("IFSC"), usrData.get("MODE"));

			if (usrData.get("ASSERTION").equalsIgnoreCase("Public Holiday")) {
				dbUtils.updatePublicHoliday(usrData.get("SETPARTNER"), "CURDATE()");
			} else {
				dbUtils.updatePublicHoliday(usrData.get("SETPARTNER"), "CURDATE() - INTERVAL 1 DAY");
			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Non-Working Hours")) {
				dbUtils.updateSetllementStartAndEndTime(usrData.get("SETPARTNER"), usrData.get("MODE"),
						"TIME_FORMAT(CURTIME()-1, '%H:%i:%s')", "CURTIME()");
			} else {
				dbUtils.updateSetllementStartAndEndTime(usrData.get("SETPARTNER"), usrData.get("MODE"), "CURTIME()",
						"DATE_ADD(CURTIME(), INTERVAL 1 HOUR)");
			}

			commonUtils.selectFeatureFromMenu1(manageWalletButton, pageTitle);

			waitUntilElementIsClickableAndClickTheElement(cashoutTab);
			System.out.println("Cashout tab clicked");

			commonUtils.waitForSpinner();

			if (usrData.get("STATUS").equalsIgnoreCase("Pending") || usrData.get("STATUS").equalsIgnoreCase("Rejected")
					|| usrData.get("STATUS").equalsIgnoreCase("Deleted")) {
				waitUntilElementIsVisible(blockedMessage);
				Assert.assertEquals(blockedMessage.getText(),
						"Not allowed as settlement is blocked. Please contact customer support.");
				System.out.println(blockedMessage.getText());
			} else if (usrData.get("STATUS").equalsIgnoreCase("Verified")) {
				waitUntilElementIsVisible(cashoutBalanceField);
				waitUntilElementIsClickableAndClickTheElement(toDropDown);
				System.out.println("Drop down clicked");

				if (usrData.get("ASSERTION").equalsIgnoreCase("1st off 2 accounts")) {
					bankAccountDropDownValue1.click();
					System.out.println("1st Bank account selected");
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("2nd off 2 accounts")) {
					bankAccountDropDownValue2.click();
					System.out.println("2nd Bank account selected");
				} else {
					bankAccountDropDownValue.click();
					System.out.println("Bank account selected");
				}

				waitUntilElementIsClickableAndClickTheElement(amountField);
				amountField.sendKeys(usrData.get("AMOUNT"));
				System.out.println("Amount entered");

				// Field level validation in Amount field
				if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText().substring(0, 49),
							"Amount entered exceeds your cashout balance limit");
					System.out.println(amountErrorMsg.getText());
					dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText(), "Minimum amount should be ₹10.00");
					System.out.println(amountErrorMsg.getText());
				}

				if (dbUtils.modeOfTransfer(usrData.get("IFSC")).equals("1")) {
					Assert.assertEquals(imps.isSelected(), true);
					System.out.println("IMPS mode auto-selected");
				} else if (dbUtils.modeOfTransfer(usrData.get("IFSC")).equals("0")) {
					Assert.assertEquals(neft.isSelected(), true);
					System.out.println("NEFT mode auto-selected");
				}

				if (!(usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("SKIP")
						|| (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")))) {
					String buttonName = usrData.get("SETTLEMENTBUTTON");
					String buttonXpath = "//*[@id='cashout-balance-form']//button[contains(text(),'" + buttonName
							+ "')]";
					WebElement button = wdriver.findElement(By.xpath(buttonXpath));
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(button);
					if (buttonName.equalsIgnoreCase("Clear")) {
						Thread.sleep(2000);
						System.out.println("Clear button clicked");
					} else if (buttonName.equalsIgnoreCase("Submit")) {
						System.out.println("Submit button clicked");
					}
				}

				if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Submit")) {
					waitUntilElementIsVisible(MPINScreen);
					System.out.println("MPIN screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterMPIN);
					if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
						enterMPIN.sendKeys(getAuthfromIni("MPIN"));
					} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
						enterMPIN.sendKeys("9999");
					}
					System.out.println("MPIN entered");

					if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
						dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
					}

					String mpinButtonName = usrData.get("MPINSCREENBUTTON");
					String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
							+ "')]";
					WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
					System.out.println(mpinButtonName + " button clicked");
					if (mpinButtonName.equalsIgnoreCase("Cancel")) {
						commonUtils.waitForSpinner();
					} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
						if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
							waitUntilElementIsVisible(processingScreen);
							System.out.println("Processing screen displayed");
							waitUntilElementIsVisible(processInBackgroundButton);
							processInBackgroundButton.click();
							System.out.println("Process in Background button clicked");
						} else {
							try {
								waitUntilElementIsVisible(settlementTxnScreen);
								System.out.println("Txn screen displayed");
							} catch (Exception e) {
								System.out.println("30 sec wait time elapsed");
								try {
									waitUntilElementIsVisible(settlementTxnScreen);
									System.out.println("Txn screen displayed");
								} catch (Exception f) {
									System.out.println("60 sec wait time elapsed");
									waitUntilElementIsVisible(settlementTxnScreen);
									System.out.println("Txn screen displayed");
								}
							}

							// Verify the details on transaction screen
							if (settlementTxnScreen.getText().equalsIgnoreCase("Success!")
									|| settlementTxnScreen.getText().equalsIgnoreCase("Pending!")) {
								if (settlementTxnScreen.getText().equalsIgnoreCase("Success!")) {
									assertionOnSuccessScreen(usrData);
								} else if (settlementTxnScreen.getText().equalsIgnoreCase("Pending!")) {
									assertionOnWarnScreen(usrData);
								}
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								System.out.println("Done button clicked");
								commonUtils.waitForSpinner();
								verifyUpdatedBalanceAfterSuccessTxn(usrData);
								assertionOnSMS(usrData);
								if (usrData.get("ASSERTION").contains("FCM")) {
									assertionOnFCM(usrData);
								}
							} else if (settlementTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
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
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settlementTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnFailedScreen(usrData);
									}
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									System.out.println("Exit button clicked");
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									waitUntilElementIsVisible(settlementTxnFailScreenMessage);
									System.out.println(settlementTxnFailScreenMessage.getText());
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										System.out.println("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										Thread.sleep(1000);
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.click();
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settlementTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										waitUntilElementIsClickableAndClickTheElement(doneButton);
										System.out.println("Done button clicked");
										commonUtils.waitForSpinner();
										verifyUpdatedBalanceAfterSuccessTxn(usrData);
									}
								}
							}
						}
					}

					dbUtils.updateAepsPartner("RBL", getLoginMobileFromIni("RetailerMobNum"));
					dbUtils.updateSetllementStartAndEndTime(usrData.get("SETPARTNER"), usrData.get("MODE"),
							"'00:00:00'", "'23:59:59'");

				} else if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")) {
					waitUntilElementIsClickableAndClickTheElement(applicableChargesButton);
					waitUntilElementIsVisible(applicableChargesScreen);
					assertionOnApplicableCharges(usrData);
					applicableChargesOkButton.click();
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("SETPARTNER").equalsIgnoreCase("RBL") && usrData.get("TYPE").equalsIgnoreCase("IMPS")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer request successful.");
		} else if (usrData.get("SETPARTNER").equalsIgnoreCase("RBL") && usrData.get("TYPE").equalsIgnoreCase("NEFT")
				&& dbUtils.getSettlementNeftConfig().equals("NO")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText().substring(29),
					"Please check your bank account after 4 hours for updated balance.");
		} else if (usrData.get("SETPARTNER").equalsIgnoreCase("YBL")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "");
		}
		System.out.println(settlementTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(settlementTxnScreenRequestedAmount.getText()),
				usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(settlementTxnScreenRequestedAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(settlementTxnScreenCharges.getText()), dbUtils
				.getOnDemandSettlementCharges(usrData.get("MODE"), usrData.get("SETPARTNER"), usrData.get("AMOUNT")));
		System.out.println("Charges: " + replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreTxnRefNo", settlementTxnScreenRefId.getText());
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(settlementTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Amount Transferred: " + replaceSymbols(settlementTxnScreenTotalAmount.getText()));
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialCashoutWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double newCashoutWalletBal = initialCashoutWalletBalance - amount;
		String newCashoutWalletBalance = df.format(newCashoutWalletBal);
		waitUntilElementIsVisible(toDropDown);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newCashoutWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		getWalletBalanceFromIni("cashout", newCashoutWalletBalance);
	}

	// Verify details on success screen
	public void assertionOnWarnScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("MODE").equals("IMPS") || usrData.get("SETPARTNER").equals("YBL")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer initiated successfully.");
		} else if (usrData.get("MODE").equals("NEFT") && usrData.get("SETPARTNER").equals("RBL")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText(),
					"Fund Transfer Initiated through NEFT. Status of the Transaction will be updated within 1-2 hours.");
		}
		System.out.println(settlementTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(settlementTxnScreenRequestedAmount.getText()),
				usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(settlementTxnScreenRequestedAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(settlementTxnScreenCharges.getText()), dbUtils
				.getOnDemandSettlementCharges(usrData.get("MODE"), usrData.get("SETPARTNER"), usrData.get("AMOUNT")));
		System.out.println("Charges: " + replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreTxnRefNo", settlementTxnScreenRefId.getText());
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(settlementTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Amount Transferred: " + replaceSymbols(settlementTxnScreenTotalAmount.getText()));
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient balance")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Insufficient balance");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Public Holiday")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Non-Working Hours")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(),
					dbUtils.settlementServiceUnavailableMessage());
		} else {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Transaction has failed. Try again later!");
		}
		System.out.println(settlementTxnFailScreenMessage.getText());
	}

	// Verify details on applicable charges screen
	public void assertionOnApplicableCharges(Map<String, String> usrData) throws ClassNotFoundException {
		System.out.println("Verifying charges");
		Assert.assertEquals(replaceSymbols(applicableTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Transaction Amount: " + replaceSymbols(applicableTxnAmount.getText()));
		String chrges = dbUtils.getOnDemandSettlementCharges(usrData.get("MODE"), usrData.get("SETPARTNER"),
				usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(applicableCharges.getText()), chrges);
		System.out.println("Charges: " + replaceSymbols(applicableCharges.getText()));

		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(chrges);
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(applicableTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(applicableTotalAmount.getText()));
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String SMSsuccessIMPS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		String SMSpendingIMPS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request deemed successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		String SMSsuccessNEFT = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request successful. "
				+ "Please check your bank account after 4 hours for updated balance. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("SMS IMPS Success")) {
			Assert.assertEquals(dbUtils.sms(), SMSsuccessIMPS);
			System.out.println(SMSsuccessIMPS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("SMS IMPS Pending")) {
			Assert.assertEquals(dbUtils.sms(), SMSpendingIMPS);
			System.out.println(SMSpendingIMPS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("SMS NEFT Success")) {
			Assert.assertEquals(dbUtils.sms(), SMSsuccessNEFT);
			System.out.println(SMSsuccessNEFT);
		}
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String bankFCMHeading = "Balance transfer: INR " + txnDetailsFromIni("GetTxfAmount", "")
				+ " (Withdrawable balance->Bank account)";
		String bankFCMContent = "", bankFCMContentPending = "";

		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Success")) {
			bankFCMContent = "Transfer request successful. Ref#" + txnDetailsFromIni("GetTxnRefNo", "")
					+ ", charges: INR " + txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
					+ getWalletBalanceFromIni("GetCashout", "");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Pending")) {
			bankFCMContent = "Transfer request deemed successful. Ref#" + txnDetailsFromIni("GetTxnRefNo", "")
					+ ", charges: INR " + txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
					+ getWalletBalanceFromIni("GetCashout", "");
			bankFCMContentPending = "Transfer request deemed successful.";
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("FCM NEFT Success")) {
			bankFCMContent = "Please check your bank account after 4 hours for updated balance. Ref#"
					+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
					+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		}

		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Pending")) {
			try {
				fcmMethod1(usrData, bankFCMHeading, bankFCMContent);
				fcmMethod2(bankFCMHeading, bankFCMContentPending);
			} catch (AssertionError e) {
				fcmMethod1(usrData, bankFCMHeading, bankFCMContentPending);
				fcmMethod2(bankFCMHeading, bankFCMContent);
			}
		} else {
			fcmMethod1(usrData, bankFCMHeading, bankFCMContent);
		}

	}

	public void fcmMethod1(Map<String, String> usrData, String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM NEFT Success")) {
			Assert.assertEquals(fcmContent.getText().substring(29), content);
		} else {
			Assert.assertEquals(fcmContent.getText(), content);
		}
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	public void fcmMethod2(String heading, String content) {
		Assert.assertEquals(fcmHeading2.getText(), heading);
		Assert.assertEquals(fcmContent2.getText(), content);
		System.out.println(fcmHeading2.getText());
		System.out.println(fcmContent2.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
