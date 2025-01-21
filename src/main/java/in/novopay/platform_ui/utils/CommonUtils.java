package in.novopay.platform_ui.utils;

import java.io.IOException;
import java.util.Map;


import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;

public class CommonUtils extends BasePage {
	DBUtils dbUtils = new DBUtils();
	

	public Map<String, String> usrData;
	
	 @FindBy(xpath = "//img[@src='assets/Home/novopay.svg']")
		WebElement novopayHomePage;

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]")
	WebElement welcomeMessage;

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]/parent::div/following-sibling::div[2]/button")
	WebElement welcomeOKButton;

	@FindBy(xpath = "//button[contains(text(),'I Will Check Later')]")
	WebElement banner;

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	//@FindBy(xpath = "//i[contains(@class,'np np-refresh')]")
	@FindBy(xpath = "//button[contains(@class,'btn ng-star-inserted') and contains(@style,'border: 1px solid #00769b; background-color: white; color: #00769b; margin-right: 10px;')]")
		
	
	WebElement refreshButton;

	@FindBy(xpath = "//i[contains(@class,'np np-sync')]")
	WebElement syncButton;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	//@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	
	@FindBy(xpath = "//span[contains(@class, 'wallet-value')]")
	//@FindBy(xpath = "//div[@class='col card']/div[@class='amount']/span[@class='d-inline-block amount_color wallet-value' and contains(text(), '₹ 10,00,000.00')][1]")

     WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")

	WebElement cashoutWallet;

	//@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	
	@FindBy(xpath = "//span[contains(@class, 'wallet-value')]")
	//@FindBy(xpath = "//div[@class='col card']/div[@class='amount']/span[@class='d-inline-block amount_color wallet-value' and contains(text(), '₹ 10,00,000.00')][1]")


	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]")
	WebElement merchantWallet;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]/parent::p/following-sibling::p/span")
	WebElement merchantWalletBalance;

	@FindBy(xpath = "//div[contains(@class,'spinner')]/parent::div")
	WebElement spinner;

	@FindBy(xpath = "//button[contains(text(),'OK. Got it!')]")
	WebElement welcomeButton;

	@FindBy(xpath = "//*[contains(text(),'Choose a Wallet')]")
	WebElement chooseWalletScreen;

	@FindBy(xpath = "//*[@for='agent-wallet']")
	WebElement mainWalletRadioButton;

	@FindBy(xpath = "//*[@for='cashout-wallet']")
	WebElement cashoutWalletRadioButton;

	@FindBy(xpath = "//h5[contains(text(),'Main Wallet')]/following-sibling::p[contains(text(),' ₹')]")
	WebElement mainWalletScreenBalance;

	@FindBy(xpath = "//h5[contains(text(),'Cashout Wallet')]/following-sibling::p[contains(text(),' ₹')]")
	
	WebElement cashoutWalletScreenBalance;

	@FindBy(xpath = "//*[contains(text(),'Choose a Wallet')]/parent::div/following-sibling::div/button[contains(text(),'Proceed')]")
	WebElement chooseWalletProceedButton;

	@FindBy(xpath = "//*[contains(text(),'Choose a Wallet')]/parent::div/following-sibling::div/button[contains(text(),'Cancel')]")
	WebElement chooseWalletCancelButton;

	@FindBy(xpath = "//*[@for='agent-wallet']//small")
	WebElement chooseWalletMainErrorMsg;

	@FindBy(xpath = "//*[@for='cashout-wallet']//small")
	WebElement chooseWalletCashoutErrorMsg;

	@FindBy(xpath = "//table//tr[contains(@class,'table-row')][1]")
	WebElement firstTxnInList;

	@FindBy(xpath = "//h4[contains(text(),'Processing')]")
	WebElement processingScreen;

	@FindBy(xpath = "//h4[contains(text(),'Pending')]")
	WebElement pendingScreen;

	@FindBy(xpath = "//button[@class='toast-close-button']")
	WebElement toastCloseButton;

	@FindBy(id = "site-search")
	WebElement searchBiller;

	public CommonUtils(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Click OK on Welcome pop-up (whenever displayed)
	public void welcomePopup() {
		if (usrData.get("WELCOMEPOPUP").equalsIgnoreCase("YES")) {
			try {
				waitUntilElementIsVisible(welcomeMessage);
				System.out.println("Welcome pop-up displayed");
				waitUntilElementIsClickableAndClickTheElement(welcomeOKButton);
				System.out.println("OK button clicked");
				waitUntilElementIsInvisible("//button[contains(text(),'OK. Got it!')]");
				System.out.println("Pop-up disappeared");
			} catch (Exception e) {
				System.out.println("No pop-up displayed");
			}
		}
	}
	
	// Switch to the new window
	public void switchtoPage() {
	  
		String currentWindowHandle = wdriver.getWindowHandle();
    for (String windowHandle : wdriver.getWindowHandles()) {
        if (!windowHandle.equals(currentWindowHandle)) {
            wdriver.switchTo().window(windowHandle);
            break;
        }
    }
    }
      
	
    //switch to the original window
    public void switchtoCurrentPage() {
    	
    	// Get the handle of the current window
    	String mainWindowHandle = wdriver.getWindowHandles().iterator().next();

    	
    	// Switch back to the main window
    	wdriver.switchTo().window(mainWindowHandle);
    }

	public void selectFeatureFromMenu1(WebElement feature, WebElement pageTitle) throws InterruptedException {
		clickElement(menu);
		scrollElementDown(scrollBar, feature);
		System.out.println(feature.getText() + " option clicked");
		waitForSpinner();
		waitUntilElementIsVisible(pageTitle);
		System.out.println(pageTitle.getText() + " page displayed");
		clickElement(menu);
	}
	
	public void Novopayvisible() throws InterruptedException {
		
		waitUntilElementIsVisible(novopayHomePage);
		System.out.println("Novopay Home page displayed");
	}
	

	public void selectFeatureFromMenu2(WebElement feature, WebElement pageTitle) throws InterruptedException {
		clickElement(menu);
	
		refreshBalance();
		scrollElementDown(scrollBar, feature);
		System.out.println(feature.getText() + " option clicked");
		waitUntilElementIsVisible(pageTitle);
		System.out.println(pageTitle.getText() + " page displayed");
		clickElement(menu);
	}
	
	// Scroll to the bottom of the page
	public void scrollDownToBottom() {
	    Actions actions = new Actions(wdriver); // assuming wdriver is your WebDriver instance
	    actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	}
	// Scroll to the top of the page
	public void scrollUpToTop() {
	    Actions actions = new Actions(wdriver); // assuming wdriver is your WebDriver instance
	    actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
	}
	

	public void selectFeatureFromMenu3(WebElement feature1, WebElement feature2, WebElement pageTitle)
			throws InterruptedException {
		clickElement(menu);
		scrollElementDown(scrollBar, feature1);
		System.out.println(feature1.getText() + " option clicked");
		scrollElementDown(scrollBar, feature2);
		System.out.println(feature2.getText() + " option clicked");
		waitUntilElementIsVisible(pageTitle);
		System.out.println(pageTitle.getText() + " page displayed");
		clickElement(menu);
	}

	// Wait for screen to complete loading
	public void waitForSpinner() {
		waitUntilElementIsInvisible("//div[contains(@class,'spinner')]/parent::div");
		System.out.println("Please wait...");
	}

	// Wait for screen to complete loading
	public void waitForLoader() {
		waitUntilElementIsInvisible("//i[contains(@class,'processing-loader')]");
		System.out.println("Please wait...");
	}

	// To refresh the wallet balance
	public void refreshBalance() throws InterruptedException {
		waitUntilElementIsClickableAndClickTheElement(refreshButton);
		waitUntilElementIsVisible(syncButton);
		waitUntilElementIsVisible(refreshButton);
		System.out.println("Balance refreshed successfully");
	}

	// Get wallet(s) balance
	@SuppressWarnings("null")
	public double getInitialBalance(String wallet) throws ClassNotFoundException {
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());

		// Converting balance from String to Double and returning the same
		if (wallet.equalsIgnoreCase("retailer")) {
			return Double.parseDouble(initialWalletBal);
		} else if (wallet.equalsIgnoreCase("cashout")) {
			return Double.parseDouble(initialCashoutBal);
		}
		return (Double) null;
	}

	// Show balances in console
	public void displayInitialBalance(String wallet) throws ClassNotFoundException {
		String walletBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "retailer");
		String walletBal = walletBalance.substring(0, walletBalance.length() - 4);
		String cashoutBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "cashout");
		String cashoutBal = cashoutBalance.substring(0, cashoutBalance.length() - 4);

		//waitUntilElementIsVisible(retailerWalletBalance);
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());

		// Compare wallet balance shown in WebApp to DB
		//Assert.assertEquals(initialWalletBal, walletBal);
		//Assert.assertEquals(initialCashoutBal, cashoutBal);

		if (wallet.equalsIgnoreCase("retailer")) {
			System.out.println("Retailer Balance: " + initialWalletBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(retailerWalletBalance.getText()));
		} else if (wallet.equalsIgnoreCase("cashout")) {
			System.out.println("Cashout Balance: " + initialCashoutBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	public double commissionAndTDS(String commOrTds, double minComm, double minCharge, double commPer)
			throws NumberFormatException, ClassNotFoundException {
		int i = 0;
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double commission = 0.0;

		if (amount <= 5000.0) {
			i = 0;
		} else if (amount > 5000.0 && amount <= 10000.0) {
			i = 1;
		} else if (amount > 10000.0 && amount <= 15000.0) {
			i = 2;
		} else if (amount > 15000.0 && amount <= 20000.0) {
			i = 3;
		} else if (amount > 20000.0 && amount <= 25000.0) {
			i = 4;
		}

		commission = Double.parseDouble(dbUtils.getRemittanceComm((amount - 5000 * i), "RBL", 1)) * (i + 1);
//		commission = minComm * i + Math.max(minCharge, (amount - 5000 * i) * commPer / 10000);

		double tds = roundTo2Decimals(
				(roundTo2Decimals(minComm * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000))
						* i
						+ roundTo2Decimals((commission - (minComm) * i)
								* Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000));

		if (commOrTds.equalsIgnoreCase("comm")) {
			return commission;
		} else
			return tds;
	}

	// Choose Wallet screen
	
	public void chooseWalletScreen(Map<String, String> usrData) throws InterruptedException, ClassNotFoundException {
		waitUntilElementIsVisible(chooseWalletScreen);
		System.out.println("Choose a Wallet screen displayed");

		// display wallet balances in console
	//	displayInitialBalance("retailer"); // display main wallet balance
	//	displayInitialBalance("cashout"); // display cashout wallet balance

		Assert.assertEquals(replaceSymbols(mainWalletScreenBalance.getText()),
				getWalletBalanceFromIni("GetRetailer", ""));
		System.out.println("Main Wallet balance: " + mainWalletScreenBalance.getText());
		if (usrData.get("ASSERTION").equalsIgnoreCase("Main < Amount")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Main=0 Cashout!=0")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Amount > Both Wallets")) {
			Assert.assertEquals(chooseWalletMainErrorMsg.getText(), "Balance is low!");
			System.out.println("Main wallet balance is low");
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		}
		//Assert.assertEquals(replaceSymbols(cashoutWalletScreenBalance.getText()),
			//	getWalletBalanceFromIni("GetCashout", ""));
		System.out.println("Cashout Wallet balance: " + cashoutWalletScreenBalance.getText());
		if (usrData.get("ASSERTION").equalsIgnoreCase("Cashout < Amount")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Amount > Both Wallets")) {
			Assert.assertEquals(chooseWalletCashoutErrorMsg.getText(), "Balance is low!");
			System.out.println("Cashout wallet balance is low");
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
		}
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			mainWalletRadioButton.click();
			System.out.println("Main wallet radio button clicked");
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			cashoutWalletRadioButton.click();
			System.out.println("Cashout wallet radio button clicked");
		}
		if (!getWalletFromIni("GetWallet", "").equalsIgnoreCase("-")) {
			waitUntilElementIsVisible(chooseWalletProceedButton);
			chooseWalletProceedButton.click();
			System.out.println("Proceed button clicked");
		} else {
			waitUntilElementIsVisible(chooseWalletCancelButton);
			chooseWalletCancelButton.click();
			System.out.println("Cancel button clicked");
		}
	}

	public void selectTxn() throws ClassNotFoundException {
		waitUntilElementIsClickableAndClickTheElement(firstTxnInList);
		System.out.println("Transaction selected");
		waitForSpinner();
	}

	public void processingScreen() {
		try {
			waitUntilElementIsVisible(processingScreen);
			System.out.println("Processing screen displayed");
		} catch (Exception e) {
			System.out.println("Processing screen skipped");
		}
	}

	public void pendingScreen() {
		try {
			waitUntilElementIsVisible(pendingScreen);
			System.out.println("Pending screen displayed");
		} catch (Exception e) {
			System.out.println("Pending screen skipped");
		}
	}

	public void uploadFile(WebElement image) throws InterruptedException, IOException {
		System.out.println("selecting an image");
		Thread.sleep(2000);
		waitUntilElementIsClickableAndClickTheElement(image);
		Thread.sleep(2000);
		String uploadFile = "./test-data/autoit/UploadFile.exe";
		Runtime.getRuntime().exec(uploadFile);
		Thread.sleep(500);
	}

	public void closeToast() {
		try {
			while (toastCloseButton.isDisplayed() == true) {
				waitUntilElementIsClickableAndClickTheElement(toastCloseButton);
				System.out.println("Toast closed");
//			Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("No toast present");
		}
	}

	public void selectCmsBiller() {
		waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(searchBiller);
		searchBiller.clear();
		searchBiller.sendKeys(cmsDetailsFromIni("CmsBiller", ""));
		String billerXpath = "//form[@id='money-transfer-credit-card-form']//div[contains(text(),'"
				+ cmsDetailsFromIni("CmsBiller", "") + "')]";
		WebElement biller = wdriver.findElement(By.xpath(billerXpath));
		waitUntilElementIsClickableAndClickTheElement(biller);
	}

	public void selectBillpayBiller() {
		waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(searchBiller);
		searchBiller.clear();
		searchBiller.sendKeys(billpayDataFromIni("BillpayCategory", ""));
		String billerXpath = "//section[@id='recharge-billers-form']//span[contains(text(),'"
				+ billpayDataFromIni("BillpayCategory", "") + "')]/parent::li";
		WebElement biller = wdriver.findElement(By.xpath(billerXpath));
		waitUntilElementIsClickableAndClickTheElement(biller);
	}

	public void verifyAndInsertValueInOrgAttribute(String key, String value) throws ClassNotFoundException {
		if (dbUtils.getOrgAttributeValue(mobileNumFromIni(), key) != null) {
			System.out.println(key + " with the value " + value + " is already present");
		} else {
			dbUtils.insertIntoOrgAttribute(mobileNumFromIni(), key, value);
		}
	}

	public void Novopayvisible1() {
		// TODO Auto-generated method stub
		
	}

	public void scrollUpToTop1() {
		// TODO Auto-generated method stub
		
	}
}
