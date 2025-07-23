package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;

public class YBLDmtPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");
	
	  @FindBy(xpath = "//img[@src='assets/Home/novopay.svg']")
			WebElement novopayHomePage;
	  
	  @FindBy(xpath = "//span[text()='Money Transfer']")
	          WebElement moneyTransfer;
	  
	  @FindBy(xpath = "//h1[normalize-space()='Home > Money Transfer > Cash to Account']")
      WebElement moneyTransferPage;
	  
	  @FindBy(xpath = "//h1[text()='Cash to Account']")
	  WebElement cashToAccount;

	  @FindBy(xpath = "//input[@id='money-transfer-mobile-number']")
	  WebElement customerMobileNumber;
	  
	  @FindBy(xpath = "//span[contains(text(), 'Limit remaining for this month: ₹')]")
	  WebElement limitRemaining;
	  /*# Extract the text and parse the numeric value
limit_text = limit_element.text  # Example: "Limit remaining for this month: ₹ 25000.00"
limit_value = int(''.join(filter(str.isdigit, limit_text.split("₹")[-1])))  # Extracts 25000

# Check if transaction is allowed
if 1 <= limit_value <= 25000:
    print("✅ Transaction Allowed")
else:
    print("❌ Transaction Not Allowed")*/
	  @FindBy(xpath = "//span[contains(text(), 'Customer KYC Complete')]")
	  		WebElement customerKYC;
	  
	  @FindBy(xpath = "//button[text()=' Proceed ']")
	  WebElement proceedButton;
	
	  @FindBy(xpath = "//ng-select[@id='beneficiaryList']//div[@class='ng-select-container']")
	  WebElement beneficiaryList;
	  
	  @FindBy(xpath = "(//div[@role='option' and contains(@class,'ng-option')])[3]")
	  WebElement beneficiaryListValue;
	  
	  @FindBy(xpath = "//input[@id='money-transfer-amount-to-be-transferred']")
	  WebElement amountToBeTransferred;
	  
	  @FindBy(xpath = "//button[text()=' Submit ']")
	  WebElement submitButton;
	  
	  @FindBy(xpath = "//input[@type='radio' and @name='agent-wallet' and @id='agent-wallet']")
	  WebElement agentWallet;
	  
	  @FindBy(xpath = "//button[text()=' Submit ']")
	  WebElement submitButton2;
	  

		@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
		WebElement MPINScreen;

		//@FindBy(id = "money-transfer-mpin-number")
		@FindBy(xpath = "//input[@id='money-transfer-mpin-number']")
        WebElement enterMPIN;
		
		

		@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
		WebElement submitMPIN;
		
		@FindBy(xpath = "//div//button[contains(text(),'Cancel')]")
		WebElement cancelButton;

		@FindBy(xpath = "//pin-modal/div//button[contains(text(),'Submit')]")
		WebElement mpinSubmitButton;
		
	  
	  @FindBy(xpath = "//div[@class='modal-footer']//button[@type='submit' and contains(text(),'Submit')]")
	  WebElement submitButton3;
	  
	  @FindBy(xpath = "//button[contains(text(), 'Proceed')]")
	  WebElement proceedButton1;
	  
	  @FindBy(xpath = "//b[contains(text(), 'Enter OTP')]")
	  WebElement enterOTP;
	  
	  @FindBy(xpath = "//input[contains(@class, 'otp-input')][1]")
	  WebElement otpInput;
	
	  
	  @FindBy(xpath = "//button[contains(text(), 'Submit OTP')]")
	  WebElement submitOTP;
	  
	  @FindBy(xpath = "//button[contains(text(), ' Save ')]")
	  WebElement saveButton;
	  
	  @FindBy(xpath = "(//button[@type='button' and contains(@class, 'close')])[1]")
	  WebElement closeButton;

	public YBLDmtPage(WebDriver wdriver) {
        super(wdriver);
        PageFactory.initElements(wdriver, this);
	}
     // Perform action on page based on given commands
     		public void yblDmt(Map<String, String> usrData)
     				throws InterruptedException, AWTException, IOException, ClassNotFoundException {

     			try {
     				
     				System.out.println("TESTING");
    				
     				commonUtils.displayInitialBalance("retailer"); // display main wallet balance
    				commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance
    				System.out.println("Initial balances displayed");
    				
    				//display home page
     				waitUntilElementIsVisible(novopayHomePage);
    				System.out.println("Home page Visible");
    				
    				//Click on money transfer
    				waitUntilElementIsClickableAndClickTheElement(moneyTransfer);
    				System.out.println("Clicked on Money Transfer");
    				
    				//display moneyTransferPage
    				waitUntilElementIsVisible(moneyTransferPage);
    				System.out.println("Money Transafer page visible");
    				
    				//display cash to account
    				waitUntilElementIsVisible(cashToAccount);
    				System.out.println("Cash to Account page visible");
    				
    				//Enter customer mobile number
    				waitUntilElementIsVisible(customerMobileNumber);
    				customerMobileNumber.sendKeys(usrData.get("CUSTOMERNUMBER"));
    				System.out.println("Entered Customer Mobile Number");
    				
    				//display limit remaining
    				waitUntilElementIsVisible(customerKYC);
    				System.out.println("Limit Remaining Visible");
    				
    				//display customer KYC
    				waitUntilElementIsVisible(customerKYC);
    				System.out.println("Customer KYC Visible");
    				
    				//Click on Proceed Button
    				waitUntilElementIsClickableAndClickTheElement(proceedButton);
    				System.out.println("Clicked on Proceed Button");
    				
    				//Click on Beneficiary List
    				waitUntilElementIsClickableAndClickTheElement(beneficiaryList);
    				System.out.println("Clicked on Beneficiary List");
    				
    				//Select Beneficiary
    				waitUntilElementIsClickableAndClickTheElement(beneficiaryListValue);
    				System.out.println("Selected Beneficiary");
    				
    				//Enter Amount to be transferred
    				waitUntilElementIsVisible(amountToBeTransferred);
    				amountToBeTransferred.sendKeys(usrData.get("AMOUNT"));
    				
    					    // Continue with flow for amount less than 5000
    				System.out.println("Entered Amount");
    				
    				//Click on Submit Button
    				waitUntilElementIsClickableAndClickTheElement(submitButton);
    				System.out.println("Clicked on Submit Button");
    				
    				waitUntilElementIsClickableAndClickTheElement(submitButton);
    				System.out.println("Clicked on Submit Button");
    				 
    				 
    				//Click on Submit Button
    				waitUntilElementIsClickableAndClickTheElement(submitButton);
    				System.out.println("Clicked on Submit Button");
    				
    				waitUntilElementIsClickableAndClickTheElement(submitButton);
    				System.out.println("Clicked on Submit Button");
    			
    				
    				//Click on Agent Wallet
    				

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
    						String mpinScreenButtonXpath = "//h5[contains(text(),' Enter 4 digit PIN')]/parent::div/"
    								+ "following-sibling::div/following-sibling::div/button[contains(text(),'"
    								+ mpinButtonName + "')]";
    						WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
    						waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
    						System.out.println(mpinButtonName + " button clicked");
    						if (mpinButtonName.equalsIgnoreCase("Submit")) {
    							commonUtils.waitForSpinner();

    							
    				
    							waitUntilElementIsClickableAndClickTheElement(proceedButton1);
    							System.out.println("Clicked on Proceed Button");
    							
    							
    							//Enter OTP
    							waitUntilElementIsVisible(enterOTP);
    							System.out.println("Enter OTP Visible");
    							waitUntilElementIsClickableAndClickTheElement(otpInput);
    							otpInput.sendKeys(usrData.get("OTP"));
    		    				  System.out.println("Entered OTP");
    		    				  
    		    				  waitUntilElementIsClickableAndClickTheElement(submitOTP);
    		    				  System.out.println("Clicked on Submit OTP");
    		    				  
    		    				  waitUntilElementIsClickableAndClickTheElement(saveButton);
    		    				  System.out.println("Clicked on Save Button");
    		    				  
    		    				  waitUntilElementIsClickableAndClickTheElement(closeButton);
    		    				  System.out.println("Clicked on Close Button");
    		    				  
    							
     			}
    					}
     			}
     			catch (Exception e) {
     				e.printStackTrace();
 
     				throw (e);
     			}
     		}

}		
    