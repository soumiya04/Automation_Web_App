package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.MongoDBUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LMNStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Wallet Load')]")
	WebElement walletLoadProduct;

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

	@FindBy(xpath = "//span[contains(text(),'Wallet Load - Status Enquiry')]")
	WebElement reportPage;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement seTxnTitle;

	@FindBy(xpath = "//div[contains(@class,'wallet-load-modal')]//span[contains(text(),'Transaction Amount')]/parent::div/following-sibling::div/span")
	WebElement txnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'wallet-load-modal')]//span[contains(text(),'Charges')]/parent::div/following-sibling::div/span")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'wallet-load-modal')]//span[contains(text(),'Wallet Load Amount')]/parent::div/following-sibling::div/span")
	WebElement txnScreenLoadAmount;

	@FindBy(xpath = "//div/button[contains(text(),'Ok')]")
	WebElement seOkBtn;

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

	@FindBy(xpath = "//li[contains(text(),'Wallet Load - Status Enquiry')]")
	WebElement walletLoadDropdown;

	String txnID = txnDetailsFromIni("GetTxnRefNo", "");

	public LMNStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void lMNStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
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
				dropDownSearch.sendKeys("Wallet Load - Status Enquiry");
				System.out.println("Typing Wallet Load - Status Enquiry");
				waitUntilElementIsClickableAndClickTheElement(walletLoadDropdown);
				System.out.println("Wallet Load - Status Enquiry drop down selected");

				if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
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
				Assert.assertEquals("Invalid reference number", toasterMsg.getText());
			} else {
				reportsData(usrData);
				commonUtils.selectTxn();
				System.out.println("Status enquiry of " + usrData.get("STATUS") + " Transaction");
				Thread.sleep(1000);
				waitUntilElementIsVisible(seTxnTitle);
				assertionOnStatusEnquiryScreen(usrData);
				seOkBtn.click();
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

		waitUntilElementIsClickableAndClickTheElement(walletLoadProduct);
		System.out.println("Wallet Load selected");

		waitUntilElementIsClickableAndClickTheElement(statusEnquirySubmitButton);
		System.out.println("Submit button clicked");
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(reportPage);
		System.out.println("Report page displayed");
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String statusXpath = "//tbody/tr[1]/td[4]";
		commonUtils.waitForSpinner();
		waitUntilElementIsClickable(wdriver.findElement(By.xpath(statusXpath)));
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		Assert.assertEquals(statusData.getText(), dbUtils.getOrderId(loadMoneyNowDataFromIni("GetOrderId", "")));
		System.out.println(statusData.getText());
	}

	// Verify details on txn screen
	public void assertionOnStatusEnquiryScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("STATUS").contains("Success")) {
			Assert.assertEquals(seTxnTitle.getText(), "Success!");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Pending")) {
			Assert.assertEquals(seTxnTitle.getText(), "Pending!");
		}
		System.out.println("Title: " + seTxnTitle.getText());

		Assert.assertEquals(replaceSymbols(txnScreenTxnAmount.getText()),
				loadMoneyNowDataFromIni("GetAmount", "") + ".00");
		System.out.println("Txn Amount: " + loadMoneyNowDataFromIni("GetAmount", ""));
		Assert.assertEquals(replaceSymbols(txnScreenLoadAmount.getText()),
				loadMoneyNowDataFromIni("GetLoadAmount", ""));
		System.out.println("Load Amount: " + loadMoneyNowDataFromIni("GetLoadAmount", ""));
	}

}