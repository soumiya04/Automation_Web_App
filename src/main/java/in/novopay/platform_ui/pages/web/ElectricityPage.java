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
import in.novopay.platform_ui.utils.MongoDBUtils;

public class ElectricityPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Bill Payment')]")
	WebElement billPayments;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Billers')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'ELECTRICITY')]/parent::li")
	WebElement electricityIcon;

	@FindBy(id = "payerMobNumberId")
	WebElement payerMobNum;

	@FindBy(id = "payerNameID")
	WebElement payerName;

	@FindBy(xpath = "//div[contains(text(),'Pay New Bill')]")
	WebElement payNewBillButton;

	@FindBy(className = "biller-cards")
	WebElement billerCards;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[contains(text(),'Select..')]/parent::span")
	WebElement billerList;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//input)[1]")
	WebElement id1;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//input)[2]")
	WebElement id2;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//input)[3]")
	WebElement id3;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//label)[1]")
	WebElement idLabel1;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//label)[2]")
	WebElement idLabel2;

	@FindBy(xpath = "(//label[contains(text(),'Electricity Biller')]/parent::div/following-sibling::div//label)[3]")
	WebElement idLabel3;

	@FindBy(xpath = "//div[contains(text(),'Biller Name')]/following-sibling::div")
	WebElement fetchedBillerName;

	@FindBy(xpath = "//div[contains(text(),'Account ID')]/following-sibling::div")
	WebElement fetchedBillerIdBescom;

	@FindBy(xpath = "//div[contains(text(),'Consumer Number')]/following-sibling::div")
	WebElement fetchedBillerIdMsedc;

	@FindBy(xpath = "//div[contains(text(),'Biller Number')]/following-sibling::div")
	WebElement fetchedBillNumber;

	@FindBy(xpath = "//div[contains(text(),'Customer Name')]/following-sibling::div")
	WebElement fetchedCustomerName;

	@FindBy(xpath = "//div[contains(text(),'Bill Amount')]/following-sibling::div")
	WebElement fetchedBillAmount;

	@FindBy(xpath = "//div[contains(text(),'Charges')]/following-sibling::div")
	WebElement fetchedCharges;

	@FindBy(id = "amount")
	WebElement payableAmount;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

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

	@FindBy(xpath = "//novopay-electricity//h4[contains(@class,'modal-title')]")
	WebElement elecTxnScreen;

	@FindBy(xpath = "//novopay-electricity//h4[contains(@class,'modal-title')]/parent::div")
	WebElement elecTxnScreenType;

	@FindBy(xpath = "//novopay-electricity//div[contains(@class,'transaction-title')]")
	WebElement elecTxnScreenMessage;

	@FindBy(xpath = "//novopay-electricity//i[contains(@class,'failure-cross')]/parent::span")
	WebElement elecTxnScreenFailureMessage;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Biller Name')]/parent::div/following-sibling::div")
	WebElement txnScreenBillerName;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Account ID')]/parent::div/following-sibling::div")
	WebElement txnScreenAccountID;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Consumer Number')]/parent::div/following-sibling::div")
	WebElement txnScreenConsumerNumber;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Bill Number')]/parent::div/following-sibling::div")
	WebElement txnScreenBillNumber;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Customer Name')]/parent::div/following-sibling::div")
	WebElement txnScreenCustomerName;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Bill Amount')]/parent::div/following-sibling::div")
	WebElement txnScreenBillAmount;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Charges')]/parent::div/following-sibling::div")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//novopay-electricity//strong[contains(text(),'Txn ID')]/parent::div/following-sibling::div")
	WebElement txnScreenTxnId;

	@FindBy(xpath = "//novopay-electricity//div[@class='amount-value']/span")
	WebElement txnScreenFinalAmount;

	@FindBy(xpath = "//h4[contains(text(),'Confirm Payment')]/parent::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//button[contains(text(),'Print Receipt')]")
	WebElement printButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading1;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent1;

	// Load all objects
	public ElectricityPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void electricity(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);

			commonUtils.selectFeatureFromMenu2(billPayments, pageTitle);

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			if (usrData.get("ASSERTION").equalsIgnoreCase("Clear")) {
				clickElement(clearButton);
			}

			// Click on electricity icon
			commonUtils.selectBillpayBiller();
			System.out.println("Electricity icon clicked");

			commonUtils.waitForSpinner();

			// Click on payer mobile number field
			waitUntilElementIsClickableAndClickTheElement(payerMobNum);
			payerMobNum.clear();
			payerMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERMOBILENUMBER")));
			System.out.println("Payer mobile number " + payerMobNum.getAttribute("value") + " entered");

			commonUtils.waitForSpinner();

			waitUntilElementIsClickableAndClickTheElement(payerName);
			payerName.clear();
			payerName.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERNAME")));
			System.out.println("Payer name " + payerName.getAttribute("value") + " entered");

			billpayDataFromIni("StoreBillpayBiller", usrData.get("BILLERNAME"));

			if (usrData.get("BILLTYPE").equalsIgnoreCase("Existing")) {
				waitUntilElementIsClickableAndClickTheElement(billerCards);
				System.out.println("Biller Card is clicked");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Co. Ltd (BESCOM)")) {
					waitUntilElementIsVisible(idLabel1);
					Assert.assertTrue(idLabel1.getText().contains("Account ID"));
					System.out.println("Account Id verified");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					waitUntilElementIsVisible(idLabel1);
					Assert.assertTrue(idLabel1.getText().contains("Consumer Number"));
					System.out.println("Consumer Number verified");
					waitUntilElementIsVisible(idLabel2);
					Assert.assertTrue(idLabel2.getText().contains("Billing Unit"));
					System.out.println("Billing Unit verified");
					waitUntilElementIsVisible(idLabel3);
					Assert.assertTrue(idLabel3.getText().contains("Processing Cycle"));
					System.out.println("Processing cycle verified");
				}
			} else if (usrData.get("BILLTYPE").equalsIgnoreCase("New")) {
				// Click on pay new bill button
//				waitUntilElementIsClickableAndClickTheElement(payNewBillButton);
//				System.out.println("Pay New Bill button clicked");
//				Thread.sleep(1000);

				waitUntilElementIsClickableAndClickTheElement(billerList);
				System.out.println("Biller drop down clicked");
				String ifscState = "//li[contains(text(),'" + usrData.get("BILLERNAME") + "')]";
				WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
				ifscSearchState.click();
				System.out.println(usrData.get("BILLERNAME") + " selected");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Co. Ltd (BESCOM)")) {
					waitUntilElementIsClickableAndClickTheElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					System.out.println(usrData.get("ACCOUNTID") + " Account Id entered");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					waitUntilElementIsClickableAndClickTheElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					System.out.println(usrData.get("ACCOUNTID") + " Account Id entered");
					waitUntilElementIsClickableAndClickTheElement(id2);
					id2.sendKeys(usrData.get("BILLINGUNIT"));
					System.out.println("Billing Unit entered");
					waitUntilElementIsClickableAndClickTheElement(id3);
					id3.sendKeys(usrData.get("PROSCYCLE"));
					System.out.println("Processing cycle entered");
				}
			}

			mongoDbUtils.updateBillpayVendor(usrData.get("BILLERNAME"), usrData.get("VENDOR"));

			if (usrData.get("FETCHBUTTON").equalsIgnoreCase("YES")) {
				// Click on Proceed button
				waitUntilElementIsClickableAndClickTheElement(proceedButton);

				commonUtils.waitForSpinner();

				if (usrData.get("ASSERTION").equalsIgnoreCase("Bill not fetched")) {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Failed to fetch bill details");
					System.out.println(toasterMsg.getText());
				} else {
					waitUntilElementIsVisible(fetchedBillerName);
					Assert.assertEquals(fetchedBillerName.getText(), usrData.get("BILLERNAME"));
					System.out.println("Biller name is " + usrData.get("BILLERNAME"));

					if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Co. Ltd (BESCOM)")) {
						Assert.assertEquals(fetchedBillerIdBescom.getText(), usrData.get("ACCOUNTID"));
						System.out.println("Account Id fetched: " + usrData.get("ACCOUNTID"));

						if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
							Assert.assertEquals(fetchedBillNumber.getText(), usrData.get("BILLNUMBER"));
							System.out.println("Bill Number fetched: " + usrData.get("BILLNUMBER"));

							Assert.assertEquals(fetchedCustomerName.getText(), usrData.get("CUSTOMERNAME"));
							System.out.println("Customer Name fetched: " + usrData.get("CUSTOMERNAME"));
						}

					} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
						Assert.assertEquals(fetchedBillerIdMsedc.getText(), usrData.get("ACCOUNTID"));
						System.out.println("Consumer Number fetched: " + usrData.get("ACCOUNTID"));

						if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
							Assert.assertEquals(fetchedCustomerName.getText(), usrData.get("CUSTOMERNAME"));
							System.out.println("Customer Name fetched: " + usrData.get("CUSTOMERNAME"));
						}
					}

					Assert.assertEquals(replaceSymbols(fetchedBillAmount.getText()), usrData.get("BILLAMOUNT"));
					System.out.println("Bill Amount fetched: " + usrData.get("BILLAMOUNT"));
					txnDetailsFromIni("StoreTxfAmount", replaceSymbols(fetchedBillAmount.getText()));

					String chrges = dbUtils.getBillPaymentCharges(usrData.get("VENDOR"));
					if (!chrges.equals("0.00")) {
						Assert.assertEquals(replaceSymbols(fetchedCharges.getText()), chrges);
					}
					System.out.println("Charges fetched: " + chrges);
					txnDetailsFromIni("StoreCharges", chrges);

					double amount = Double.parseDouble(replaceSymbols(fetchedBillAmount.getText()));
					double charges = Double.parseDouble(chrges);
					double totalAmount = amount + charges;
					String cashToBeCollected = df.format(totalAmount);
					if (usrData.get("VENDOR").equalsIgnoreCase("CYBERPLAT")) {
						Assert.assertEquals(replaceSymbols(payableAmount.getAttribute("value")), cashToBeCollected);
					} else {
						Assert.assertEquals(replaceSymbols(payableAmount.getAttribute("value") + ".00"),
								cashToBeCollected);
					}
					System.out.println("Bill Amount Payable: " + replaceSymbols(payableAmount.getAttribute("value")));
				}
			} else if (usrData.get("FETCHBUTTON").equalsIgnoreCase("Clear")) {
				// Click on Clear button
				clickElement(clearButton);
			}

			if (usrData.get("PAYBUTTON").equalsIgnoreCase("Yes")) {

				// Click on Proceed to pay button
				waitUntilElementIsClickableAndClickTheElement(proceedButton);

				if (usrData.get("CONFIRMBUTTON").equalsIgnoreCase("YES")) {
					waitUntilElementIsClickableAndClickTheElement(submitButton);
					System.out.println("Submit button clicked on Confirm Screen");

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
								+ "following-sibling::div/following-sibling::div/button[contains(text(),'"
								+ mpinButtonName + "')]";
						WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
						waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
						System.out.println(mpinButtonName + " button clicked");
						if (mpinButtonName.equalsIgnoreCase("Submit")) {
							commonUtils.waitForSpinner();

							waitUntilElementIsVisible(elecTxnScreen);
							System.out.println("Txn screen displayed");

							// Update retailer wallet balance to 1000000 for scenario where amount > wallet
							if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
								dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
							}

							// Verify the details on transaction screen
							if (elecTxnScreen.getText().equalsIgnoreCase("Success!")
									|| elecTxnScreen.getText().equalsIgnoreCase("Pending")) {
								assertionOnSuccessScreen(usrData);
								if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
									assertionOnSMS(usrData);
								}
								if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Print")) {
									printButton.click();
									System.out.println("Print button clicked");
								}
								doneButton.click();
								System.out.println("Done button clicked");
								if (usrData.get("ASSERTION").contains("FCM")) {
									assertionOnFCM(usrData);
								}
								commonUtils.refreshBalance();
								verifyUpdatedBalanceAfterSuccessTxn(usrData);
							} else if (elecTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									System.out.println("Exit button clicked");
									commonUtils.refreshBalance();
									verifyUpdatedBalanceAfterFailTxn(usrData);
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									assertionOnFailedScreen(usrData);
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										System.out.println("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										waitUntilElementIsVisible(elecTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										doneButton.click();
										System.out.println("Done button clicked");
										commonUtils.refreshBalance();
										verifyUpdatedBalanceAfterSuccessTxn(usrData);
									}
								}
							}
						}
					}
				} else if (usrData.get("CONFIRMBUTTON").equalsIgnoreCase("Cancel")) {
					waitUntilElementIsClickable(cancelButton);
					cancelButton.click();
					System.out.println("Cancel button clicked on Confirm Screen");
				}
			} else if (usrData.get("PAYBUTTON").equalsIgnoreCase("Clear")) {
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
		if (elecTxnScreen.getText().equalsIgnoreCase("Success!")) {
			Assert.assertEquals(elecTxnScreenMessage.getText(), "Bill Payment Successful!");
		} else if (elecTxnScreen.getText().equalsIgnoreCase("Pending!")) {
			Assert.assertEquals(elecTxnScreenMessage.getText(),
					"Bill Payment Awaiting Confirmation. Use status enquiry to get updated status of this transaction.");
		}
		System.out.println(elecTxnScreenMessage.getText());

		Assert.assertEquals(txnScreenBillerName.getText(), usrData.get("BILLERNAME"));
		System.out.println("Biller Name: " + txnScreenBillerName.getText());

		if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Co. Ltd (BESCOM)")) {
			Assert.assertEquals(txnScreenAccountID.getText(), usrData.get("ACCOUNTID"));
			System.out.println("Account Id: " + usrData.get("ACCOUNTID"));

			if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
				Assert.assertEquals(txnScreenBillNumber.getText(), usrData.get("BILLNUMBER"));
				System.out.println("Bill Number: " + usrData.get("BILLNUMBER"));

				Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
				System.out.println("Customer Name: " + usrData.get("CUSTOMERNAME"));
			}
		} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
			Assert.assertEquals(txnScreenConsumerNumber.getText(), usrData.get("ACCOUNTID"));
			System.out.println("Consumer Number: " + usrData.get("ACCOUNTID"));

			if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
				Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
				System.out.println("Customer Name: " + usrData.get("CUSTOMERNAME"));
			}
		}

		Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()) + ".00",
				txnDetailsFromIni("GetTxfAmount", ""));
		System.out.println("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));

		if (!txnDetailsFromIni("GetCharges", "").equals("0.00")) {
			Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
		}
		System.out.println("Charges: " + txnDetailsFromIni("GetCharges", ""));

		txnDetailsFromIni("StoreTxnRefNo", txnScreenTxnId.getText());

		double amount = Double.parseDouble(replaceSymbols(fetchedBillAmount.getText()));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(txnScreenFinalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(txnScreenFinalAmount.getText()));
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(elecTxnScreenMessage.getText(),
					"Invalid MPIN. Please retry with the unique 4-digit MPIN.");
			System.out.println(elecTxnScreenMessage.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(elecTxnScreenFailureMessage.getText(), "Insufficient balance  ");
			System.out.println(elecTxnScreenFailureMessage.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else {
			Assert.assertEquals(elecTxnScreenMessage.getText(), "Bill Payment Failed!");
			System.out.println(elecTxnScreenMessage.getText());

			Assert.assertEquals(txnScreenBillerName.getText(), usrData.get("BILLERNAME"));
			System.out.println("Biller Name: " + usrData.get("BILLERNAME"));

			if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Co. Ltd (BESCOM)")) {
				Assert.assertEquals(txnScreenAccountID.getText(), usrData.get("ACCOUNTID"));
				System.out.println("Customer ID / Account Id: " + usrData.get("ACCOUNTID"));

				if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
					Assert.assertEquals(txnScreenBillNumber.getText(), usrData.get("BILLNUMBER"));
					System.out.println("Bill Number: " + usrData.get("BILLNUMBER"));

					Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
					System.out.println("Customer Name: " + usrData.get("CUSTOMERNAME"));
				}
			} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
				Assert.assertEquals(txnScreenConsumerNumber.getText(), usrData.get("ACCOUNTID"));
				System.out.println("Consumer Number: " + usrData.get("ACCOUNTID"));

				if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
					Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
					System.out.println("Customer Name: " + usrData.get("CUSTOMERNAME"));
				}
			}

			Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()) + ".00",
					txnDetailsFromIni("GetTxfAmount", ""));
			System.out.println("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));

			if (!txnDetailsFromIni("GetCharges", "").equals("0.00")) {
				Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
			}
			System.out.println("Charges: " + txnDetailsFromIni("GetCharges", ""));

			txnDetailsFromIni("StoreTxnRefNo", txnScreenTxnId.getText());
		}
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String id = "";
		if (usrData.get("VENDOR").equalsIgnoreCase("CYBERPLAT")) {
			id = "Account ID";
		} else if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
			id = "Customer ID / Account ID";
		}
		String successSms1 = "", successSms2 = "";
		successSms1 = "Success! Rs." + txnDetailsFromIni("GetTxfAmount", "") + " paid for " + usrData.get("BILLERNAME")
				+ " at Novopay agent outlet, " + id + " - " + usrData.get("ACCOUNTID") + ", Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate() + ".";
		successSms2 = "Success! Rs." + txnDetailsFromIni("GetTxfAmount", "") + " paid for " + usrData.get("BILLERNAME")
				+ " at Novopay agent outlet, " + id + " - " + usrData.get("ACCOUNTID") + ", Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDateWithIncreasedTime() + ".";
		try {
			System.out.println("Trying with Actual time");
			Assert.assertEquals(dbUtils.sms(), successSms1);
		} catch (AssertionError e) {
			System.out.println("Trying with Increased time");
			Assert.assertEquals(dbUtils.sms(), successSms2);
		}
		System.out.println(dbUtils.sms());
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double newWalletBal = initialWalletBalance - amount - charges;
		String newWalletBalance = df.format(newWalletBal);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterFailTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		double newWalletBal = initialWalletBalance;
		String newWalletBalance = df.format(newWalletBal);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successSummaryFCMHeading = "Bill Payment";
		String successSummaryFCMContent = "Bill payment of ₹ " + txnDetailsFromIni("GetTxfAmount", "") + " against "
				+ usrData.get("BILLERNAME") + ", " + usrData.get("ACCOUNTID") + ", Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate() + " is successful.";
		Assert.assertEquals(fcmHeading1.getText(), successSummaryFCMHeading);
		Assert.assertEquals(fcmContent1.getText(), successSummaryFCMContent);
		System.out.println(fcmHeading1.getText());
		System.out.println(fcmContent1.getText());
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