package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
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

public class FingpayBankingPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");
	
	   @FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
		WebElement menu;

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
		
		@FindBy(xpath = "//span[contains(text(),'Banking (AEPS)')]")
		WebElement BankingOption;
		
	
	
	
	// Load all objects
	public FingpayBankingPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}
	// Perform action on page based on given commands
		public void rblBanking(Map<String, String> usrData)
				throws InterruptedException, AWTException, IOException, ClassNotFoundException {

			try {
				
}
		