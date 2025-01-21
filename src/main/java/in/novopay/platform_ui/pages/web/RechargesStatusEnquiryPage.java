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

public class RechargesStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Recharge')]")
	WebElement rechargeProduct;

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

	@FindBy(xpath = "//span[contains(text(),'Recharge - Status Enquiry')]")
	WebElement reportPage;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement seTxnTitle;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//strong[contains(text(),'Customer Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenCustomerName;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//span[contains(text(),'Amount')]/parent::strong/parent::div/following-sibling::div/div")
	WebElement txnScreenBillAmount;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//span[contains(text(),'Charges')]/parent::strong/parent::div/following-sibling::div/div")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'billpay-modal')]//span[contains(text(),'Txn ID')]/parent::strong/parent::div/following-sibling::div/div")
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

	@FindBy(xpath = "//li[contains(text(),'Recharge - Status Enquiry')]")
	WebElement rechargeDropdown;

	String txnID = txnDetailsFromIni("GetTxnRefNo", "");

	public RechargesStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void rechargesStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			if (!usrData.get("VENDOR").equalsIgnoreCase("")) {
				String batchConfigSection = "";
				if (usrData.get("VENDOR").equalsIgnoreCase("Gorecharge")) {
					batchConfigSection = "goRechargeStatusEnquiry";
				} else if (usrData.get("VENDOR").equalsIgnoreCase("Multilink")) {
					batchConfigSection = "multilinkStatusEnquiry";
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
				dropDownSearch.sendKeys("Recharge - Status Enquiry");
				System.out.println("Typing Recharge - Status Enquiry");
				waitUntilElementIsClickableAndClickTheElement(rechargeDropdown);
				System.out.println("Recharge - Status Enquiry drop down selected");

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
				Assert.assertEquals("No transaction history for this transaction id", toasterMsg.getText());
			} else {
				reportsData(usrData);
				commonUtils.selectTxn();
				System.out.println("Status enquiry of " + usrData.get("STATUS") + " Transaction");
				Thread.sleep(1000);
				waitUntilElementIsVisible(seTxnTitle);
				assertionOnStatusEnquiryScreen(usrData);
				if (usrData.get("STATUS").equalsIgnoreCase("Success") || usrData.get("STATUS").contains("Pending")) {
					if (usrData.get("ASSERTION").equalsIgnoreCase("Print")) {
						sePrintBtn.click();
					}
					seOkBtn.click();
				} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
					seExitBtn.click();
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

		waitUntilElementIsClickableAndClickTheElement(rechargeProduct);
		System.out.println("Recharges selected");

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
		String statusXpath = "//tbody/tr[1]/td[7]";
		String operatorIdXpath = "//tbody/tr[1]/td[5]";
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(wdriver.findElement(By.xpath(statusXpath)));
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		WebElement operatorIdData = wdriver.findElement(By.xpath(operatorIdXpath));
		Assert.assertEquals(statusData.getText(),
				mongoDbUtils.getBillPayTxnStatus(txnDetailsFromIni("GetTxnRefNo", "")));
		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(statusData.getText(), "SUCCESS");
			if (usrData.get("VENDOR").equalsIgnoreCase("Gorecharge"))
				Assert.assertEquals(operatorIdData.getText(), rechargeDataFromIni("GetOperatorId", ""));
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
		} else if (usrData.get("STATUS").contains("Failed")) {
			Assert.assertEquals(seTxnTitle.getText(), "Failed!");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Pending")) {
			Assert.assertEquals(seTxnTitle.getText(), "Pending!");
		}
		System.out.println("Title: " + seTxnTitle.getText());

		Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()), rechargeDataFromIni("GetAmount", ""));
		System.out.println("Bill Amount: " + rechargeDataFromIni("GetAmount", ""));
	}
}