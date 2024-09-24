package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
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
import in.novopay.platform_ui.utils.MongoDBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

public class RechargesPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Recharge')]")
	WebElement recharges;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Recharge')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'MOBILE')]/parent::li")
	WebElement mobileIcon;

	@FindBy(xpath = "//span[contains(text(),'DTH')]/parent::li")
	WebElement dthIcon;

	@FindBy(xpath = "//span[contains(text(),'DATACARD')]/parent::li")
	WebElement datacardIcon;

	@FindBy(xpath = "//div//input[@id='prepaid']/parent::span")
	WebElement prepaidRadioButton;

	@FindBy(xpath = "//div//input[@id='postpaid']/parent::span")
	WebElement postpaidRadioButton;

	@FindBy(xpath = "//input[@formcontrolname='handleValue']")
	WebElement mobileMobNum;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[@role='textbox']")
	WebElement mobileOperator;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	WebElement dropdownField;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[@role='textbox']/ancestor::div[contains(@class,'operator-wrapper')]/following-sibling::div//input")
	WebElement dthId;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[@role='textbox']/ancestor::div[contains(@class,'operator-wrapper')]/parent::div//input[@formcontrolname='handleValue']")
	WebElement datacardId;

	@FindBy(xpath = "//input[@formcontrolname='transactionAmount']")
	WebElement mobileAmount;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement dthDataCardMobNum;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//button[contains(text(),'Check Status')]")
	WebElement checkStatusButton;

	@FindBy(xpath = "//button[contains(text(),'Proceed to Pay')]")
	WebElement proceedToPayButton;

	@FindBy(xpath = "//div[contains(@class,'modal-footer')]/button[contains(text(),'Submit')]")
	WebElement submitButton;

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

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement rechTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]/div/div/div")
	WebElement rechTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//div[contains(@class,'transaction-title')]")
	WebElement rechTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//i[contains(@class,'failure-cross')]/parent::span")
	WebElement rechTxnScreenFailureMessage;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Operator Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenOperatorName;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Operator ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenOperatorId;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Amount')]/parent::div/following-sibling::div/div")
	WebElement txnScreenBillAmount;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Charges')]/parent::div/following-sibling::div/div")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Txn ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenTxnId;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//p[contains(@class,'cashCollected')]")
	WebElement txnScreenFinalAmount;

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

	@FindBy(xpath = "//label[@for='specialrecharge']")
	WebElement specialRecharge;

	@FindBy(xpath = "//label[@for='normal']")
	WebElement talktime;

	@FindBy(xpath = "//p[text()='View Available Plans']")
	WebElement viewAvailablePlansLink;

	@FindBy(xpath = "//h4[text()='Available Plans']")
	WebElement availablePlansScreen;

	@FindBy(xpath = "//div[contains(@class,'location')]//span[@role='textbox']")
	WebElement locationDropdown;

	@FindBy(xpath = "//div[@class='plans']//span[@role='combobox']")
	WebElement planTypesDropdown;

	@FindBy(xpath = "//div[@class='custom-scroll']//td[1]")
	WebElement planAmount;

	// Load all objects
	public RechargesPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void recharges(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);

			commonUtils.selectFeatureFromMenu2(recharges, pageTitle);

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			mongoDbUtils.updateRechargeVendor(usrData.get("OPERATOR"), usrData.get("VENDOR"));

			// Click on icon
			switch (usrData.get("RECHARGETYPE")) {
			case "MOBILE":
				waitUntilElementIsClickableAndClickTheElement(mobileIcon);
				System.out.println("Mobile icon clicked");
				break;
			case "DTH":
				waitUntilElementIsClickableAndClickTheElement(dthIcon);
				System.out.println("DTH icon clicked");
				break;
			case "DATACARD":
				waitUntilElementIsClickableAndClickTheElement(datacardIcon);
				System.out.println("Datacard icon clicked");
				break;
			}

			// Select prepaid/postpaid radio button
			if (usrData.get("MOBILETYPE").equalsIgnoreCase("PREPAID")
					|| usrData.get("MOBILETYPE").equalsIgnoreCase("POSTPAID")) {
				if (usrData.get("MOBILETYPE").equalsIgnoreCase("PREPAID")) {
					waitUntilElementIsClickableAndClickTheElement(prepaidRadioButton);
					prepaidRadioButton.click();
					System.out.println("Prepaid radio button selected");
				} else if (usrData.get("MOBILETYPE").equalsIgnoreCase("POSTPAID")) {
					waitUntilElementIsClickableAndClickTheElement(postpaidRadioButton);
					postpaidRadioButton.click();
					System.out.println("Postpaid radio button selected");
				}

				// Click on payer mobile number field
				waitUntilElementIsClickableAndClickTheElement(mobileMobNum);
				mobileMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERMOBNUM")));
				System.out.println("Payer mobile number " + mobileMobNum.getAttribute("value") + " entered");
				rechargeDataFromIni("StoreMobNum", mobileMobNum.getAttribute("value"));
			} else {
				waitUntilElementIsClickableAndClickTheElement(dthDataCardMobNum);
				dthDataCardMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERMOBNUM")));
				System.out.println("Payer mobile number " + dthDataCardMobNum.getAttribute("value") + " entered");
				rechargeDataFromIni("StoreMobNum", dthDataCardMobNum.getAttribute("value"));
			}

			waitUntilElementIsClickableAndClickTheElement(mobileOperator);
			System.out.println("Clicked on Operator dropdown");

			waitUntilElementIsClickableAndClickTheElement(dropdownField);
			dropdownField.sendKeys(usrData.get("OPERATOR"));
			System.out.println("Operator " + usrData.get("OPERATOR") + " entered");

			String operatorXpath = "//*[@class='select2-results']/ul/li[contains(text(),'" + usrData.get("OPERATOR")
					+ "')]";
			WebElement operator = wdriver.findElement(By.xpath(operatorXpath));
			waitUntilElementIsClickableAndClickTheElement(operator);

			if (usrData.get("RECHARGETYPE").equalsIgnoreCase("DTH")) {
				waitUntilElementIsClickableAndClickTheElement(dthId);
				String dthId1 = generateRandomMobileNumber();
				dthId.sendKeys(dthId1);
				System.out.println("DTH Id " + dthId.getText() + " entered");
				rechargeDataFromIni("StoreCustomerId", dthId1);
			} else if (usrData.get("RECHARGETYPE").equalsIgnoreCase("DATACARD")) {
				waitUntilElementIsClickableAndClickTheElement(datacardId);
				String datacardId1 = generateRandomMobileNumber();
				datacardId.sendKeys(datacardId1);
				System.out.println("Datacard Id " + datacardId.getText() + " entered");
				rechargeDataFromIni("StoreDatacardNum", datacardId1);
			}

			// Click on payer mobile number field
			waitUntilElementIsClickableAndClickTheElement(mobileAmount);
			mobileAmount.sendKeys(usrData.get("AMOUNT"));
			System.out.println("Amount " + mobileAmount.getText() + " entered");

			if (usrData.get("ASSERTION").contains("VAP")) {
				viewAvailablePlansLink.click();
				System.out.println("View Available Plans link clicked");
				waitUntilElementIsVisible(availablePlansScreen);
				System.out.println("Avialble Plan screen displayed");
				commonUtils.waitForSpinner();
				Thread.sleep(2000);
				if (!usrData.get("LOCATION").equalsIgnoreCase("SKIP")) {
					waitUntilElementIsClickableAndClickTheElement(locationDropdown);
					waitUntilElementIsClickableAndClickTheElement(dropdownField);
					dropdownField.sendKeys(usrData.get("LOCATION"));

					String locationXpath = "//*[@class='select2-results']/ul/li[contains(text(),'"
							+ usrData.get("LOCATION") + "')]";
					WebElement location = wdriver.findElement(By.xpath(locationXpath));
					waitUntilElementIsClickableAndClickTheElement(location);

					commonUtils.waitForSpinner();
				}
				waitUntilElementIsClickableAndClickTheElement(planTypesDropdown);
				waitUntilElementIsClickableAndClickTheElement(dropdownField);
				dropdownField.sendKeys(usrData.get("PLANS"));

				String plansXpath = "//*[@class='select2-results']/ul/li[contains(text(),'" + usrData.get("PLANS")
						+ "')]";
				WebElement plans = wdriver.findElement(By.xpath(plansXpath));
				waitUntilElementIsClickableAndClickTheElement(plans);

				waitUntilElementIsClickableAndClickTheElement(planAmount);
				System.out.println("Plan Amount selected");

				if (usrData.get("ASSERTION").contains(" and Edit")) {
					waitUntilElementIsClickableAndClickTheElement(mobileAmount);
					mobileAmount.clear();
					mobileAmount.sendKeys(usrData.get("AMOUNT"));
					System.out.println("Amount " + mobileAmount.getText() + " entered");
				}
			}

			rechargeDataFromIni("StoreAmount", mobileAmount.getAttribute("value"));

			if (usrData.get("ASSERTION").equalsIgnoreCase("Special")) {
				specialRecharge.click();
				System.out.println("Special Recharge radio button clicked");
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Talktime")) {
				talktime.click();
				System.out.println("Talktime radio button clicked");
			}

			if (usrData.get("PROCEEDBUTTON").equalsIgnoreCase("Yes")) {

				// Click on Proceed to pay button
				waitUntilElementIsClickableAndClickTheElement(proceedToPayButton);

//				commonUtils.waitForLoader();

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

							waitUntilElementIsVisible(rechTxnScreen);
							System.out.println("Txn screen displayed");

							// Update retailer wallet balance to 1000000 for scenario where amount > wallet
							if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
								dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
							}

							// Verify the details on transaction screen
							if (rechTxnScreen.getText().equalsIgnoreCase("Success!")
									|| rechTxnScreen.getText().equalsIgnoreCase("Pending!")) {
								assertionOnSuccessScreen(usrData);
								//if (usrData.get("ASSERTION").contains("SMS")) {
								//	assertionOnSMS(usrData);
								}
								if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Print")) {
									printButton.click();
									System.out.println("Print button clicked");
								}
								if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Check Status Now")) {
									String batchConfigSection = "";
									if (usrData.get("VENDOR").equalsIgnoreCase("Gorecharge")) {
										batchConfigSection = "goRechargeStatusEnquiry";
									} else if (usrData.get("VENDOR").equalsIgnoreCase("Multilink")) {
										batchConfigSection = "multilinkStatusEnquiry";
									}
									HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
									batchFileConfig = readSectionFromIni(batchConfigSection);
									if (usrData.get("ASSERTION").equalsIgnoreCase("Success")
											|| usrData.get("ASSERTION").equalsIgnoreCase("Pending")
											|| usrData.get("ASSERTION").equalsIgnoreCase("Failure")
											|| usrData.get("ASSERTION").equalsIgnoreCase("Failed")) {
										srvUtils.uploadFileToNode(batchFileConfig, usrData.get("ASSERTION"),
												"node_simulator");
									}
									checkStatusButton.click();
									System.out.println("Check Status Now button clicked");
									commonUtils.waitForSpinner();
									waitUntilElementIsVisible(rechTxnScreen);
									System.out.println("Pending screen to Txn screen displayed");
									if (usrData.get("ASSERTION").equalsIgnoreCase("Success")
											|| usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
										assertionOnSuccessScreen(usrData);
										doneButton.click();
										System.out.println("Done button clicked");
										commonUtils.refreshBalance();
										verifyUpdatedBalanceAfterSuccessTxn(usrData);
									} else if (usrData.get("ASSERTION").equalsIgnoreCase("Failure")) {
										assertionOnFailedScreen(usrData);
										exitButton.click();
										System.out.println("Exit button clicked");
										commonUtils.refreshBalance();
										verifyUpdatedBalanceAfterFailTxn(usrData);
									}
								} else {
									doneButton.click();
									System.out.println("Done button clicked");
								}
								if (usrData.get("ASSERTION").contains("FCM")) {
									assertionOnFCM(usrData);
								}
							} else if (rechTxnScreen.getText().equalsIgnoreCase("Failed!")) {
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
										waitUntilElementIsVisible(rechTxnScreen);
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
			//}
		} 
	catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (rechTxnScreen.getText().equalsIgnoreCase("Success!")) {
			if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Check Status Now")) {
				Assert.assertEquals(rechTxnScreenMessage.getText(), "Transaction completed successfully.");
				System.out.println("Check status success screen displayed");
				/*Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()),
						rechargeDataFromIni("GetAmount", ""));*/
			} else {
				Assert.assertEquals(rechTxnScreenMessage.getText(), "Transaction Completed Successfully.");
				System.out.println("Normal success screen displayed");
				Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()),
						rechargeDataFromIni("GetAmount", "") + ".00");
			}
			/*if (usrData.get("VENDOR").equalsIgnoreCase("Gorecharge")
					&& !usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Check Status Now"))
				rechargeDataFromIni("StoreOperatorId", txnScreenOperatorId.getText());
		} else if (rechTxnScreen.getText().equalsIgnoreCase("Pending!")) {
			Assert.assertEquals(rechTxnScreenMessage.getText(), "Transaction is pending.");
			Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()), rechargeDataFromIni("GetAmount", ""));
		}*/
		System.out.println(rechTxnScreenMessage.getText());
		System.out.println("Recharge Amount: " + replaceSymbols(txnScreenBillAmount.getText()));
		}
		/*String chrges = dbUtils.getRechargeChargesAndComm("charges", usrData.get("VENDOR"), usrData.get("MOBILETYPE"),
				usrData.get("OPERATOR"));
		if (!chrges.equals("0.00")) {
			Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), chrges);
			System.out.println("Charges: " + chrges);
		}
		txnDetailsFromIni("StoreCharges", chrges);
		txnDetailsFromIni("StoreTxnRefNo", txnScreenTxnId.getText());

		double amount = Double.parseDouble(rechargeDataFromIni("GetAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(txnScreenFinalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(txnScreenFinalAmount.getText()));*/
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(rechTxnScreenFailureMessage.getText(),
					"Invalid MPIN. Please retry with the unique 4-digit MPIN.  ");
			System.out.println(rechTxnScreenFailureMessage.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(rechTxnScreenFailureMessage.getText(), "Insufficient balance  ");
			System.out.println(rechTxnScreenFailureMessage.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else {
			if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Check Status Now")) {
				Assert.assertEquals(rechTxnScreenMessage.getText(), "Transaction is unsuccessful.");
				System.out.println(rechTxnScreenMessage.getText());
				Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()),
						rechargeDataFromIni("GetAmount", ""));
			} else {
				Assert.assertEquals(rechTxnScreenMessage.getText(), "Bill Payment failed");
				System.out.println(rechTxnScreenMessage.getText());
				Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()),
						rechargeDataFromIni("GetAmount", "") + ".00");
			}
			System.out.println("Recharge Amount: " + replaceSymbols(txnScreenBillAmount.getText()));
			txnDetailsFromIni("StoreTxfAmount", replaceSymbols(txnScreenBillAmount.getText()));

			String chrges = dbUtils.getRechargeChargesAndComm("charges", usrData.get("VENDOR"),
					usrData.get("MOBILETYPE"), usrData.get("OPERATOR"));
			if (!chrges.equals("0.00")) {
				Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), chrges);
				System.out.println("Charges: " + chrges);
			}
			txnDetailsFromIni("StoreCharges", chrges);
			txnDetailsFromIni("StoreTxnRefNo", txnScreenTxnId.getText());
		}
	}

	// SMS assertion
	/*public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String mobiletype = "", rechargeType = "", id = "";
		if (usrData.get("MOBILETYPE").equalsIgnoreCase("DTH")) {
			mobiletype = "Dth";
			rechargeType = "Customer Id";
			id = rechargeDataFromIni("GetCustomerId", "");
		} else if (usrData.get("MOBILETYPE").equalsIgnoreCase("Datacard")) {
			mobiletype = usrData.get("MOBILETYPE");
			rechargeType = "Datacard";
			id = rechargeDataFromIni("GetDatacardNum", "");
		} else {
			mobiletype = usrData.get("MOBILETYPE");
			rechargeType = "Mobile";
			id = rechargeDataFromIni("GetMobNum", "");
		}
		String successSms1 = "", successSms2 = "", pendingSms1 = "", pendingSms2 = "";
		if (usrData.get("VENDOR").equalsIgnoreCase("Gorecharge")) {
			successSms1 = "Success! Rs." + rechargeDataFromIni("GetAmount", "") + ".00 paid for "
					+ usrData.get("OPERATOR") + " " + mobiletype + " " + id + " at Novopay agent outlet, Txn Ref ID "
					+ txnDetailsFromIni("GetTxnRefNo", "") + ", Operator ID " + rechargeDataFromIni("GetOperatorId", "")
					+ " on " + dbUtils.doTransferDate() + ".";
			successSms2 = "Success! Rs." + rechargeDataFromIni("GetAmount", "") + ".00 paid for "
					+ usrData.get("OPERATOR") + " " + mobiletype + " " + id + " at Novopay agent outlet, Txn Ref ID "
					+ txnDetailsFromIni("GetTxnRefNo", "") + ", Operator ID " + rechargeDataFromIni("GetOperatorId", "")
					+ " on " + dbUtils.doTransferDateWithIncreasedTime() + ".";
		} else if (usrData.get("VENDOR").equalsIgnoreCase("Multilink")) {
			successSms1 = "Success! Rs." + rechargeDataFromIni("GetAmount", "") + ".00 paid for "
					+ usrData.get("OPERATOR") + " " + mobiletype + " " + id + " at Novopay agent outlet, Txn Ref ID "
					+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate() + ".";
			successSms2 = "Success! Rs." + rechargeDataFromIni("GetAmount", "") + ".00 paid for "
					+ usrData.get("OPERATOR") + " " + mobiletype + " " + id + " at Novopay agent outlet, Txn Ref ID "
					+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDateWithIncreasedTime() + ".";
		}
		pendingSms1 = "Your " + mobiletype + " recharge of Rs." + rechargeDataFromIni("GetAmount", "") + ".00 to "
				+ rechargeType + " " + id + " at Novopay Outlet is pending. Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate()
				+ ". Check status after some time.";
		pendingSms2 = "Your " + mobiletype + " recharge of Rs." + rechargeDataFromIni("GetAmount", "") + ".00 to "
				+ rechargeType + " " + id + " at Novopay Outlet is pending. Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDateWithIncreasedTime()
				+ ". Check status after some time.";
		if (usrData.get("ASSERTION").contains("Success")) {
			try {
				System.out.println("Trying with Actual time");
				Assert.assertEquals(dbUtils.sms(), successSms1);
			} catch (AssertionError e) {
				System.out.println("Trying with Increased time");
				Assert.assertEquals(dbUtils.sms(), successSms2);
			}
		} else if (usrData.get("ASSERTION").contains("Pending")) {
			try {
				Assert.assertEquals(dbUtils.sms(), pendingSms1);
				System.out.println("Actual time taken");
			} catch (AssertionError e) {
				Assert.assertEquals(dbUtils.sms(), pendingSms2);
				System.out.println("Increased time taken");
			}
		}
		System.out.println(dbUtils.sms());
	}*/

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		double amount = Double.parseDouble(rechargeDataFromIni("GetAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		String comm = dbUtils.getRechargeChargesAndComm("comm", usrData.get("VENDOR"), usrData.get("MOBILETYPE"),
				usrData.get("OPERATOR"));
		double comm1 = amount * Double.parseDouble(comm) / 100;
		double commission = Math.round(comm1 * 100.0) / 100.0;
		double taxDS = commission * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000;
		double tds = Math.round(taxDS * 100.0) / 100.0;
		double newWalletBal = initialWalletBalance - amount - charges + commission - tds;
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
		String message = "", status = "";
		if (usrData.get("ASSERTION").contains("Success")) {
			status = "successful";
			message = "";
		} else if (usrData.get("ASSERTION").contains("Pending")) {
			status = "pending";
			message = ". Please do a status enquiry after some time.";
		}
		String fcmHeading = "Recharge", fcmContent = "";
		if (usrData.get("MOBILETYPE").equalsIgnoreCase("DTH")) {
			fcmContent = "Dth recharge of Rs. " + rechargeDataFromIni("GetAmount", "") + ".00 is " + status
					+ ". Txn Ref ID " + txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate()
					+ message;
		} else if (usrData.get("MOBILETYPE").equalsIgnoreCase("Prepaid")) {
			fcmContent = "Prepaid recharge of Rs. " + rechargeDataFromIni("GetAmount", "") + ".00 is " + status
					+ ". Txn Ref ID " + txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.doTransferDate()
					+ message;
		} else {
			fcmContent = usrData.get("MOBILETYPE") + " recharge of Rs. " + rechargeDataFromIni("GetAmount", "")
					+ ".00 is " + status + ". Txn Ref ID " + txnDetailsFromIni("GetTxnRefNo", "") + " on "
					+ dbUtils.doTransferDate() + message;
		}
		Assert.assertEquals(fcmHeading1.getText(), fcmHeading);
		Assert.assertEquals(fcmContent1.getText(), fcmContent);
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