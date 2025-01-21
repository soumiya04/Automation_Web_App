package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.MongoDBUtils;
import in.novopay.platform_ui.utils.ServerUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ElectricityStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Bill Payment')]")
	WebElement billPaymentProduct;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement txnId;

	@FindBy(id = "status-enquiry-customer-mobile-number")
	WebElement seCustMobNum;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement enterSetxnId;

	@FindBy(xpath = "//div[@status-enquiry='']//button")
	WebElement statusEnquirySubmitButton;

	@FindBy(id = "searchByMobileNumber")
	WebElement pageCustMobNum;

	@FindBy(id = "searchByTxnID")
	WebElement pageTxnId;

	@FindBy(xpath = "//*[@id='reports-list-status-enquiry']//button")
	WebElement pageSubmitButton;

	@FindBy(xpath = "//span[contains(text(),'Bill Payment - Status Enquiry')]")
	WebElement reportPage;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement seTxnTitle;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Biller Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenBillerName;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Account ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenAccountID;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Consumer Number')]/parent::div/following-sibling::div/div")
	WebElement txnScreenConsumerNumber;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Bill Number')]/parent::div/following-sibling::div/div")
	WebElement txnScreenBillNumber;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Customer Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenCustomerName;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//span[contains(text(),'Bill Amount')]/parent::strong/parent::div/following-sibling::div/span")
	WebElement txnScreenBillAmount;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//span[contains(text(),'Total Amount')]/parent::strong/parent::div/following-sibling::div/div")
	WebElement refundScreenBillAmount;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Charges')]/parent::div/following-sibling::div/span")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'billpayreport-modal')]//strong[contains(text(),'Txn ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenTxnId;

	@FindBy(xpath = "//div/button[contains(text(),'Ok')]")
	WebElement seOkBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Print')]")
	WebElement sePrintBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Retry')]")
	WebElement seRetryBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Exit')]")
	WebElement seExitBtn;

	@FindBy(xpath = "//h4[contains(text(),'Failed!')]/parent::div/following-sibling::div//span")
	WebElement failSeTxnMsg;

	@FindBy(xpath = "//div/span/button[contains(text(),'Resend OTP')]")
	WebElement seResendOTPBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Initiate Refund')]")
	WebElement failSeInitiateRefundBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Close')]")
	WebElement closeRefundBtn;

	@FindBy(xpath = "//h4[contains(text(),'Confirm Refund')]")
	WebElement confirmRefund;

	@FindBy(xpath = "//div/button[contains(text(),'Ok')]")
	WebElement confirmRefundOkBtn;

	@FindBy(xpath = "//h5[contains(text(),'Enter Customer OTP')]")
	WebElement custOTPScreen;

	@FindBy(id = "money-transfer-otp-number")
	WebElement custOTP;

	@FindBy(xpath = "//*[@id='money-transfer-otp-number']/parent::div//li")
	WebElement custOTPError;

	@FindBy(xpath = "//div/button[contains(text(),'Confirm')]")
	WebElement otpConfirmBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Cancel')]")
	WebElement otpCancelBtn;

	@FindBy(xpath = "//div//button[contains(text(),'Resend')]")
	WebElement otpResendBtn;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//label[contains(text(),'Select Report to View')]")
	WebElement reportsPage;

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//span[contains(text(),' - Status Enquiry')]")
	WebElement reportsDropdown;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropDownSearch;

	@FindBy(xpath = "//li[contains(text(),'Bill Payment - Status Enquiry')]")
	WebElement billpaymentDropdown;

	String txnID = txnDetailsFromIni("GetTxnRefNo", "");

	public ElectricityStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void electricityStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			if (!usrData.get("VENDOR").equalsIgnoreCase("")) {
				String batchConfigSection = "";
				if (usrData.get("VENDOR").equalsIgnoreCase("Cyberplat")) {
					batchConfigSection = "cyberplatBbpsStatusEnquiry";
				} else if (usrData.get("VENDOR").equalsIgnoreCase("Billavenue")) {
					batchConfigSection = "billavenueBbpsStatusEnquiry";
				} else if (usrData.get("VENDOR").equalsIgnoreCase("Axis")) {
					batchConfigSection = "axisBbpsStatusEnquiry";
				}
				HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
				batchFileConfig = readSectionFromIni(batchConfigSection);
				if (usrData.get("KEY").equalsIgnoreCase("Success") || usrData.get("KEY").equalsIgnoreCase("Pending")
						|| usrData.get("KEY").equalsIgnoreCase("Failure")) {
					srvUtils.uploadFileToNode(batchFileConfig, usrData.get("KEY"), "node_simulator");
				}
			}

			if (usrData.get("TYPE").equalsIgnoreCase("Section")) {
				statusEnquirySection(usrData);
				clickElement(menu);
				clickElement(menu);
				Thread.sleep(2000);
			} else if (usrData.get("TYPE").equalsIgnoreCase("Page")) {
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
				dropDownSearch.sendKeys("Bill Payment - Status Enquiry");
				System.out.println("Typing Bill Payment - Status Enquiry");
				waitUntilElementIsClickableAndClickTheElement(billpaymentDropdown);
				System.out.println("Bill Payment - Status Enquiry drop down selected");

				if (usrData.get("TXNDETAILS").equalsIgnoreCase("MobNum")) {
					waitUntilElementIsClickableAndClickTheElement(pageCustMobNum);
					pageCustMobNum.sendKeys(getCustomerDetailsFromIni("ExistingNum"));
					System.out.println("Customer mobile number entered");
				} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
					waitUntilElementIsClickableAndClickTheElement(pageTxnId);
					pageTxnId.clear();
					pageTxnId.sendKeys(txnID);
					System.out.println("Txn ID entered");
				} else {
					waitUntilElementIsClickableAndClickTheElement(pageTxnId);
					pageTxnId.sendKeys(usrData.get("TXNDETAILS"));
				}

				waitUntilElementIsClickableAndClickTheElement(pageSubmitButton);
				System.out.println("Submit button clicked");
				Thread.sleep(3000);
				commonUtils.waitForSpinner();
			}
			if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222")) {
				waitUntilElementIsVisible(toasterMsg);
				Assert.assertEquals("No transaction history for this transaction Id", toasterMsg.getText());
			} else {
				reportsData(usrData);
				commonUtils.selectTxn();
				System.out.println("Status enquiry of " + usrData.get("STATUS") + " Transaction");
				Thread.sleep(1000);
				waitUntilElementIsVisible(seTxnTitle);
				assertionOnStatusEnquiryScreen(usrData);
				if (usrData.get("STATUS").equalsIgnoreCase("Success")
						|| (usrData.get("STATUS").equalsIgnoreCase("Failed")
								|| usrData.get("STATUS").contains("Pending")
								|| usrData.get("STATUS").contains("Refunded"))) {
					if (usrData.get("ASSERTION").equalsIgnoreCase("Print")) {
						sePrintBtn.click();
					}
					seOkBtn.click();
					if (usrData.get("ASSERTION").equalsIgnoreCase("SMS")) {
						assertionOnSMS();
					}
				} else if (usrData.get("STATUS").equalsIgnoreCase("Refund")) {
					if (usrData.get("ASSERTION").equalsIgnoreCase("SMS")) {
						assertionOnSMS();
					}
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(failSeInitiateRefundBtn);
					System.out.println("Initiate Refund button clicked");
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(confirmRefundOkBtn);
					System.out.println("Ok button clicked on Confirm Screen");
					waitUntilElementIsVisible(custOTPScreen);
					waitUntilElementIsClickableAndClickTheElement(custOTP);
					System.out.println("OTP field clicked");
					if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
						custOTP.sendKeys(getAuthfromIni(otpFromIni()));
						System.out.println("Refund OTP entered");
						waitUntilElementIsClickableAndClickTheElement(otpConfirmBtn);
						commonUtils.waitForSpinner();
						Thread.sleep(2000);
						waitUntilElementIsVisible(seTxnTitle);
						assertionOnRefundScreen(usrData);
						seOkBtn.click();
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(pageTxnId);
						System.out.println("Refund successful");
					} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")
							|| usrData.get("OTP").equalsIgnoreCase("Retry")) {
						custOTP.sendKeys("111111");
						System.out.println("Refund OTP entered");
						waitUntilElementIsClickableAndClickTheElement(otpConfirmBtn);
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(seTxnTitle);
						Assert.assertEquals("OTP does not match", failSeTxnMsg.getText());
						System.out.println(failSeTxnMsg.getText());
						if (usrData.get("OTP").equalsIgnoreCase("Retry")) {
							Thread.sleep(2000);
							seRetryBtn.click();
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(custOTPScreen);
							custOTP.click();
							custOTP.sendKeys(getAuthfromIni(otpFromIni()));
							System.out.println("Refund OTP entered");
							waitUntilElementIsClickableAndClickTheElement(otpConfirmBtn);
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(seTxnTitle);
							assertionOnRefundScreen(usrData);
							seOkBtn.click();
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(pageTxnId);
							System.out.println("Refund successful");
						} else {
							seExitBtn.click();
						}
					} else if (usrData.get("OTP").equalsIgnoreCase("Cancel")) {
						otpCancelBtn.click();
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						waitUntilElementIsClickableAndClickTheElement(otpResendBtn);
						waitUntilElementIsVisible(custOTPScreen);
						custOTP.click();
						custOTP.sendKeys(getAuthfromIni(otpFromIni()));
						System.out.println("Refund OTP entered");
						waitUntilElementIsClickableAndClickTheElement(otpConfirmBtn);
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(seTxnTitle);
						assertionOnRefundScreen(usrData);
						seOkBtn.click();
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(pageTxnId);
						System.out.println("Refund successful");
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
		waitUntilElementIsClickableAndClickTheElement(product);
		System.out.println("Status Enquiry drop down clicked");

		waitUntilElementIsClickableAndClickTheElement(billPaymentProduct);
		System.out.println("Bill Payment selected");

		if (usrData.get("TXNDETAILS").equalsIgnoreCase("MobNum")) {
			waitUntilElementIsClickableAndClickTheElement(seCustMobNum);
			seCustMobNum.sendKeys(getCustomerDetailsFromIni("ExistingNum"));
			System.out.println("Customer mobile number entered");
		} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
			waitUntilElementIsClickableAndClickTheElement(enterSetxnId);
			enterSetxnId.sendKeys(txnID);
			System.out.println("Txn ID entered");
		} else {
			waitUntilElementIsClickableAndClickTheElement(enterSetxnId);
			enterSetxnId.sendKeys(usrData.get("TXNDETAILS"));
		}

		waitUntilElementIsClickableAndClickTheElement(statusEnquirySubmitButton);
		System.out.println("Submit button clicked");
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(reportPage);
		System.out.println("Report page displayed");
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String statusXpath = "//tbody/tr[1]/td[6]";
		commonUtils.waitForSpinner();
		waitUntilElementIsClickable(wdriver.findElement(By.xpath(statusXpath)));
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		Assert.assertEquals(statusData.getText(),
				mongoDbUtils.getBillPayTxnStatus(txnDetailsFromIni("GetTxnRefNo", "")));
		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(statusData.getText(), "SUCCESS");
		} else if (usrData.get("STATUS").contains("Pending") || usrData.get("STATUS").equalsIgnoreCase("Refund")) {
			Assert.assertEquals(statusData.getText(), "PENDING");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")
				|| usrData.get("STATUS").equalsIgnoreCase("Refunded")) {
			Assert.assertEquals(statusData.getText(), "FAILED");
		}
		System.out.println(statusData.getText());
	}

	// Verify details on txn screen
	public void assertionOnStatusEnquiryScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("STATUS").contains("Success")) {
			Assert.assertEquals(seTxnTitle.getText(), "Success!");
		} else if (usrData.get("STATUS").contains("Failed") || (usrData.get("STATUS").equalsIgnoreCase("Refund"))
				|| usrData.get("STATUS").equalsIgnoreCase("Refunded")) {
			Assert.assertEquals(seTxnTitle.getText(), "Failed!");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Pending")) {
			Assert.assertEquals(seTxnTitle.getText(), "Pending!");
		}
		System.out.println("Title: " + seTxnTitle.getText());

		Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()), txnDetailsFromIni("GetTxfAmount", ""));
		System.out.println("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));
	}

	// Verify details on txn screen
	public void assertionOnRefundScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(seTxnTitle.getText(), "Success!");
		System.out.println("Title: " + seTxnTitle.getText());

		Assert.assertEquals(replaceSymbols(refundScreenBillAmount.getText()), txnDetailsFromIni("GetTxfAmount", ""));
		System.out.println("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));
	}

	// SMS assertion
	public void assertionOnSMS() throws ClassNotFoundException, InterruptedException {
		String pendingToFailedSMS = "Dear User, Payment of Rs. "
				+ txnDetailsFromIni("GetTxfAmount", "").substring(0, txnDetailsFromIni("GetTxfAmount", "").length() - 3)
				+ " against " + billpayDataFromIni("BillpayBiller", "") + " has failed with Txn ID " + txnID
				+ ". Please use this Verification Code " + getAuthfromIni(otpFromIni())
				+ " to avail refund at Novopay Outlet.";
		Thread.sleep(5000);
		Assert.assertEquals(pendingToFailedSMS, dbUtils.sms());
		System.out.println(pendingToFailedSMS);
	}

	// Get otp from Ini file
	public String otpFromIni() {
		return "LoginOTP";
	}

}