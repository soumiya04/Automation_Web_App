package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RBLAEPSStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Banking')]")
	WebElement bankingProduct;

	@FindBy(xpath = "//h1[contains(text(),'Banking')]")
	WebElement pageTitle2;

	@FindBy(xpath = "//a[contains(@href,'banking')]/span[2][contains(text(),'Banking')]")
	WebElement banking;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement txnId;

	@FindBy(xpath = "//div[contains(@class,'clearfix')]/div[contains(@class,'pull-right')]/button")
	WebElement statusEnquirySubmitButton;

	@FindBy(id = "searchByMobileNumber")
	WebElement pageCustMobNum;

	@FindBy(id = "searchByTxnID")
	WebElement pageTxnId;

	@FindBy(xpath = "//*[@id='reports-list-status-enquiry']//button")
	WebElement pageSubmitButton;

	@FindBy(xpath = "//select2[contains(@id,'reportsDropDown')]")
	WebElement reportPage;

	@FindBy(xpath = "//table//tr[contains(@class,'table-row')]")
	WebElement firstTxnInList;

	@FindBy(xpath = "//h4[contains(text(),'Success!')]")
	WebElement successSeTxn;

	@FindBy(xpath = "//h4[contains(text(),'Failed!')]")
	WebElement failSeTxn;

	@FindBy(xpath = "//h4[contains(text(),'Refunded!')]")
	WebElement refundedSeTxn;

	@FindBy(xpath = "//div/button[contains(text(),'Done')]")
	WebElement seDoneBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Retry')]")
	WebElement seRetryBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Exit')]")
	WebElement seExitBtn;

	@FindBy(xpath = "//h4[contains(text(),'Failed!')]/parent::div/following-sibling::div//span")
	WebElement failSeTxnMsg;

	@FindBy(xpath = "//div/span/button[contains(text(),'Resend OTP')]")
	WebElement seResendOTPBtn;

	@FindBy(xpath = "//button[contains(text(),'Initiate Refund')]")
	WebElement initiateRefundBtn;

	@FindBy(xpath = "//h4[contains(text(),'Initiate Refund')]")
	WebElement initiateRefundScreen;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement mobNum;

	@FindBy(id = "aeps-deposit-aadhar-number")
	WebElement aadhaarNum;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement aadhaarNumError;

	@FindBy(xpath = "//div[contains(@class,'scan_finger_container')]")
	WebElement fingerprintScan;

	@FindBy(xpath = "//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement fingerprintGreen;

	@FindBy(xpath = "//span[contains(text(),'Click to scan fingerprint')]")
	WebElement fingerprintUnscanned;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedBtn;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitBtn;

	@FindBy(xpath = "//*[@id='money-transfer-otp-number']/parent::div//li")
	WebElement custOTPError;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//label[contains(text(),'Select Report to View')]")
	WebElement reportsPage;

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//span[contains(text(),' - Status Enquiry')]")
	WebElement reportsDropdown;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement MPINScreen;

	@FindBy(id = "money-transfer-mpin-number")
	WebElement enterMPIN;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement mpinSubmitButton;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Cancel')]")
	WebElement mpinCancelButton;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement refundTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div/strong")
	WebElement aepsDeemedTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//span[contains(text(),'Refund Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenRefundAmount;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

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

	@FindBy(xpath = "//tbody/tr[1]/td[6]")
	WebElement noTxnAvailable;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropDownSearch;

	@FindBy(xpath = "//li[contains(text(),'Banking - Status Enquiry')]")
	WebElement billpaymentDropdown;

	String txnID = "";

	public RBLAEPSStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void rBLAEPSStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			String batchConfigSection = "bankingstatusenquiry";

			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFileToTomcat(batchFileConfig, usrData.get("KEY"));
			}

			if (usrData.get("STATUS").equalsIgnoreCase("REFUNDED")
					|| usrData.get("ASSERTION").equalsIgnoreCase("Refund Success FCM")) {
				txnID = txnDetailsFromIni("GetTxnRefNo", "");
			} else {
				txnID = dbUtils.aepsRefNum();
			}

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			double initialWalletBalance = commonUtils.getInitialBalance("retailer"); // store main wallet balance

//			if (!usrData.get("STATUS").equalsIgnoreCase("REFUNDED")) {
//				statusEnquirySection(usrData);
//				Thread.sleep(1000);
//			}

			if (usrData.get("TYPE").equalsIgnoreCase("Section")) {
				statusEnquirySection(usrData);
				clickElement(menu);
				clickElement(menu);
				Thread.sleep(2000);
			} else if (usrData.get("TYPE").equalsIgnoreCase("Page")) {
				clickElement(menu);
				clickElement(menu);
				Thread.sleep(2000);
				clickElement(menu);
				scrollElementDown(scrollBar, reports);
				System.out.println("Reports option clicked");
				waitUntilElementIsVisible(reportsPage);
				clickElement(menu);
				Thread.sleep(1000);
				waitUntilElementIsClickableAndClickTheElement(reportsDropdown);
				System.out.println("Drop down clicked");
				Thread.sleep(1000);
				waitUntilElementIsClickableAndClickTheElement(dropDownSearch);
				dropDownSearch.sendKeys("Banking - Status Enquiry");
				System.out.println("Typing Banking - Status Enquiry");
				waitUntilElementIsClickableAndClickTheElement(billpaymentDropdown);
				System.out.println("Banking - Status Enquiry drop down selected");

				if (usrData.get("TXNDETAILS").equalsIgnoreCase("MobNum")) {
					waitUntilElementIsClickableAndClickTheElement(pageCustMobNum);
					pageCustMobNum.clear();
					pageCustMobNum.sendKeys(getCustomerDetailsFromIni("ExistingNum"));
					System.out.println("Customer mobile number entered");
				} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
					waitUntilElementIsClickableAndClickTheElement(pageTxnId);
					pageTxnId.clear();
					pageTxnId.sendKeys(txnID);
					System.out.println("Txn ID entered");
				} else {
					waitUntilElementIsClickableAndClickTheElement(pageTxnId);
					pageTxnId.clear();
					pageTxnId.sendKeys(usrData.get("TXNDETAILS"));
				}

				waitUntilElementIsVisible(pageSubmitButton);
				pageSubmitButton.click();
				System.out.println("Submit button clicked");
				Thread.sleep(3000);
				commonUtils.waitForSpinner();

//			waitUntilElementIsVisible(firstTxnInList);
				if (usrData.get("ASSERTION").equalsIgnoreCase("11112222")) {
					pageTxnId.click();
					pageTxnId.sendKeys("11112222");
					System.out.println("Txn ID entered");
					pageSubmitButton.click();
					System.out.println("Submit button clicked");
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(firstTxnInList);
					Assert.assertEquals(noTxnAvailable.getText(),
							"Transaction with RRN number 11112222 does not exist");
					System.out.println(noTxnAvailable.getText());
				} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222")) {
					Assert.assertEquals(noTxnAvailable.getText(),
							"Transaction with RRN number 11112222 does not exist");
					System.out.println(noTxnAvailable.getText());
				} else {
					reportsData(usrData);
					if (usrData.get("ASSERTION").contains("FCM")) {
						commonUtils.selectFeatureFromMenu1(banking, pageTitle2);
						assertionOnStatusEnquiryFCM(usrData);
					}
				}
				if (usrData.get("STATUS").equalsIgnoreCase("Refund")) {
					initiateRefundBtn.click();
					waitUntilElementIsVisible(initiateRefundScreen);
					if (usrData.get("AADHAAR").equalsIgnoreCase("Valid")) {
						aadhaarNum.click();
						aadhaarNum.sendKeys(getAadhaarFromIni("GetAadhaarNum"));
						System.out.println("Aadhaar Number entered");
						Assert.assertEquals("Click to scan fingerprint", fingerprintUnscanned.getText());
						fingerprintScan.click();
						System.out.println("Scan fingerprint button clicked");
						waitUntilElementIsVisible(fingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!", fingerprintGreen.getText());
						waitUntilElementIsVisible(proceedBtn);
						proceedBtn.click();
						waitUntilElementIsVisible(MPINScreen);
						System.out.println("MPIN screen displayed");
						waitUntilElementIsClickableAndClickTheElement(enterMPIN);
						if (usrData.get("MPIN").equalsIgnoreCase("Cancel")) {
							mpinCancelButton.click();
						} else {
							enterMPIN.click();
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
							} else {
								enterMPIN.sendKeys("0000");
							}
							System.out.println(usrData.get("MPIN") + " entered");
							mpinSubmitButton.click();
							System.out.println("MPIN button clicked");
							waitUntilElementIsVisible(refundTxnScreen);
							System.out.println("Txn screen displayed");
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								if (!usrData.get("KEY").equalsIgnoreCase("fail")) {
									assertionOnRefundSuccessScreen(usrData);
									System.out.println("Refund successful");
									assertionOnRefundSMS(usrData);
									waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
									System.out.println("Done button clicked");
									commonUtils.refreshBalance();
									verifyUpdatedBalanceAfterRefundSuccessTxn(usrData, initialWalletBalance);
								} else {
									assertionOnRefundFailedScreen(usrData);
									System.out.println("Refund failed");
									seExitBtn.click();
									System.out.println("Exit button clicked");
								}
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								assertionOnRefundFailedScreen(usrData);
								seDoneBtn.click();
								System.out.println("Done button clicked");
							} else if (usrData.get("MPIN").equalsIgnoreCase("Retry")) {
								assertionOnRefundFailedScreen(usrData);
								seRetryBtn.click();
								waitUntilElementIsVisible(initiateRefundScreen);
								aadhaarNum.click();
								aadhaarNum.sendKeys(getAadhaarFromIni("GetAadhaarNum"));
								System.out.println("Aadhaar Number entered");
								Assert.assertEquals("Click to scan fingerprint", fingerprintUnscanned.getText());
								fingerprintScan.click();
								System.out.println("Scan fingerprint button clicked");
								waitUntilElementIsVisible(fingerprintGreen);
								Assert.assertEquals("Fingerprint scanned successfully!", fingerprintGreen.getText());
								waitUntilElementIsVisible(proceedBtn);
								proceedBtn.click();
								waitUntilElementIsVisible(MPINScreen);
								System.out.println("MPIN screen displayed");
								waitUntilElementIsClickableAndClickTheElement(enterMPIN);
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								System.out.println(usrData.get("MPIN") + " entered");
								mpinSubmitButton.click();
								System.out.println("MPIN button clicked");
								waitUntilElementIsVisible(processingScreen);
								System.out.println("Processing screen displayed");
								waitUntilElementIsVisible(refundTxnScreen);
								System.out.println("Txn screen displayed");
								assertionOnRefundSuccessScreen(usrData);
								System.out.println("Refund successful");
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
								System.out.println("Done button clicked");
								commonUtils.refreshBalance();
								verifyUpdatedBalanceAfterRefundSuccessTxn(usrData, initialWalletBalance);
							}
						}
					} else if (usrData.get("AADHAAR").equalsIgnoreCase("Exit")) {
						exitBtn.click();
						System.out.println("Exit button clicked");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void statusEnquirySection(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		waitUntilElementIsVisible(product);
		product.click();
		System.out.println("Status Enquiry drop down clicked");

		waitUntilElementIsVisible(bankingProduct);
		bankingProduct.click();
		System.out.println("Banking selected");
		waitUntilElementIsVisible(txnId);

		if (usrData.get("STATUS").equalsIgnoreCase("Refund")
				|| usrData.get("STATUS").equalsIgnoreCase("Ready For Refund")) {
			dbUtils.updateAEPSTxn(txnDetailsFromIni("GetTxnRefNo", ""));
		}

		if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
			txnId.click();
			txnId.clear();
			txnId.sendKeys(txnID);
			System.out.println("Txn ID entered");
		} else {
			txnId.click();
			txnId.clear();
			txnId.sendKeys(usrData.get("TXNDETAILS"));
		}

		waitUntilElementIsVisible(statusEnquirySubmitButton);
		statusEnquirySubmitButton.click();
		System.out.println("Submit button clicked");
		commonUtils.waitForSpinner();
	}

	// SMS assertion
	public void assertionOnRefundSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successRefundSMS = "Dear Customer, Refund for aadhaar  XXXX XXXX "
				+ getAadhaarFromIni("GetAadhaarNum").substring(8, 12) + " and RRN " + txnID
				+ " is successful. Please collect the cash from Aryan";

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successRefundSMS, dbUtils.sms());
			System.out.println(successRefundSMS);
		}
	}

	// FCM assertion
	public void assertionOnStatusEnquiryFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String balance = df.format(commonUtils.getInitialBalance("retailer"));

		String successSEFCMHeading = "Transaction Status enquiry:SUCCESS";
		String failSEFCMHeading = "Transaction Status enquiry:FAIL";
		String successRefundFCMHeading = "Cash Deposit Refund: SUCCESS";
		String failRefundFCMHeading = "Cash Deposit Refund: FAIL";

		String successSEFCMContent = "Request for Transaction status enquiry with RRN " + txnID
				+ " is completed and the status of the transaction is SUCCESS Response code: (00) SUCCESS Reference No: "
				+ txnID + " Balance after txn: " + balance;
		String failSEFCMContent = "Status enquiry - txn has failed: Failed to perform transaction(M3)";
		String deemedSuccessSEFCMContent = "Request for Transaction status enquiry with RRN " + txnID
				+ " is completed and the status of the transaction is DEEMED SUCCESS(Error Code 91)."
				+ " Reference No: " + txnID + " Agent Wallet Balance after txn: " + balance;
		String laterFailedSEFCMContent = "AEPS deposit of Rs. " + txnDetailsFromIni("GetTxfAmount", "")
				+ ".00 for mobile " + getAEPSMobNum("GetAEPSMobNum") + " & Txn id "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.aepsStatusEnquiryDate(txnID)
				+ " was deemed success before and has failed now. Please refund cash via Refund option.";
		String successRefundFCMContent = "Refund for customer with mobile " + getAEPSMobNum("GetAEPSMobNum")
				+ " and RRN " + txnID + " is successful. Please return the cash to customer";
		String failRefundFCMContent = "Refund for customer with Aadhaar XXXX XXXX "
				+ getAadhaarFromIni("GetAadhaarNum").substring(8, 12) + " and RRN " + txnID
				+ " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successSEFCMHeading, successSEFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failSEFCMHeading, failSEFCMContent);
			break;
		case "Deemed Success FCM":
			fcmMethod(successSEFCMHeading, deemedSuccessSEFCMContent);
			break;
		case "Later Failed FCM":
			fcmMethod(failSEFCMHeading, laterFailedSEFCMContent);
			break;
		case "Refund Success FCM":
			fcmMethod(successRefundFCMHeading, successRefundFCMContent);
			break;
		case "Refund Fail FCM":
			fcmMethod(failRefundFCMHeading, failRefundFCMContent);
			break;
		}
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String[][] dataFromUI = new String[1][7];
		String[][] dataFromUIwithoutMessage = new String[1][6];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 7; j++) {
				String dataXpath = "//tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]";
				WebElement data = wdriver.findElement(By.xpath(dataXpath));
				dataFromUI[i][j] = data.getText();
				if (j == 5) {
					dataFromUIwithoutMessage[i][j] = wdriver.findElement(By.xpath("//tbody/tr[1]/td[7]")).getText();
				} else if (j != 6) {
					dataFromUIwithoutMessage[i][j] = data.getText();
				}
			}
		}

		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(dataFromUI[0][5],
					"transaction with RRN: " + txnDetailsFromIni("GetTxnRefNo", "") + " is successful");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(dataFromUI[0][5], "Status enquiry - txn has failed: Failed to perform transaction(M3)");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Deemed Success")) {
			Assert.assertEquals(dataFromUI[0][5], "transaction with RRN " + txnDetailsFromIni("GetTxnRefNo", "")
					+ " is considered as deemed success(91) and amount will be credited to customer shortly.");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Ready For Refund")) {
			Assert.assertEquals(dataFromUI[0][5],
					"AEPS deposit of Rs. " + txnDetailsFromIni("GetTxfAmount", "") + ".00 for mobile "
							+ getAEPSMobNum("GetAEPSMobNum") + " & Txn id " + txnDetailsFromIni("GetTxnRefNo", "")
							+ " on " + dbUtils.aepsStatusEnquiryDate(txnDetailsFromIni("GetTxnRefNo", ""))
							+ " was deemed success before and has failed now. Please refund cash via Refund option.");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Refunded")) {
			Assert.assertEquals(dataFromUI[0][5],
					"transaction with rrn " + txnDetailsFromIni("GetTxnRefNo", "") + " has been refunded");
		}

		List<String> listFromUIWithMessage = new ArrayList<String>();
		for (String[] array : dataFromUI) {
			listFromUIWithMessage.addAll(Arrays.asList(array));
		}

		List<String> listFromUI = new ArrayList<String>();
		for (String[] array : dataFromUIwithoutMessage) {
			listFromUI.addAll(Arrays.asList(array));
		}
		/*
		 * for (String t : listFromUI) { System.out.print(t + " | "); }
		 * System.out.println();
		 */

		List<String[]> list = new ArrayList<String[]>();
		List<String[]> aepsStatusEnquiry = dbUtils.aepsStatusEnquiry(txnID);
		list = aepsStatusEnquiry;

		List<String> listFromDB = new ArrayList<String>();
		for (String[] data : list) {
			for (String s : data) {
//				System.out.print(s + " | ");
				listFromDB.add(s);
			}
		}
//		System.out.println();

		HashMap<String, String> map = new HashMap<String, String>();
		Iterator<String> iUI = listFromUI.iterator();
		Iterator<String> iDB = listFromDB.iterator();
		while (iUI.hasNext() && iDB.hasNext()) {
			map.put(iUI.next(), iDB.next());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Assert.assertEquals(entry.getKey(), entry.getValue());
		}
		/*
		 * for (int i = 0; i < listFromUI.size(); i++) {
		 * System.out.print(listFromUI.get(i) + " | "); } System.out.println();
		 */
		for (String u : listFromUIWithMessage) {
			System.out.print(u + " | ");
		}
		System.out.println();
	}

	// Verify details on success screen
	public void assertionOnRefundSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash refunded to customer successfully");
		System.out.println(aepsTxnScreenMessage.getText());
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), df.format(amount));
		System.out.println("Refund Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), df.format(amount));
		System.out.println("Cash to be Refunded: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnRefundFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("MPIN").equalsIgnoreCase("Invalid") || usrData.get("MPIN").equalsIgnoreCase("Retry")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Authentication Failed Invalid MPIN");
			System.out.println(aepsTxnScreenFailureReason.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
			System.out.println(aepsTxnScreenFailureReason.getText());
			double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
			Assert.assertEquals(replaceSymbols(aepsTxnScreenRefundAmount.getText()), df.format(amount));
			System.out.println("Refund Amount: " + replaceSymbols(aepsTxnScreenRefundAmount.getText()));
		}
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterRefundSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance + amount + charges - comm + tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}