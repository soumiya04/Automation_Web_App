package in.novopay.platform_ui.pages.web;
import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;

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



  public class TataCapitalAgentPage extends BasePage {
  DBUtils dbUtils = new DBUtils();
  CommonUtils commonUtils = new CommonUtils(wdriver);
  DecimalFormat df = new DecimalFormat("#.00");

  @FindBy(xpath = "//img[@src='assets/Home/novopay.svg']")
	WebElement novopayHomePage;
  
	@FindBy(xpath = "//span[text()='Tata Capital']")
	 WebElement tatacapital;


//	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	@FindBy(xpath ="//div[@class='balance page-title' and contains(text(),'Main Wallet Balance')]")

	WebElement retailerWalletBalance;
	

	//@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	@FindBy(xpath = "//div[@class='balance page-title' and contains(text(),' Cashout Wallet Balance ')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;
       
	@FindBy(xpath = "//h1[contains(text(),'Cash Services')]")
	WebElement pageTitle;

        @FindBy(xpath ="//input[@id='agent' and @name='tata_capital' and @value='agent']")
	 WebElement agentradioButton;

        @FindBy(xpath ="//input[contains(@class, 'textbox') and @name='depositerMobNumber']")
	 WebElement depositorMobileNo;
      
        @FindBy(xpath ="//input[@id='id2' and @name='batchId' and @type='text']")
	 WebElement batchId;

        @FindBy(xpath ="//input[@id='id2' and @name='creCode' and @type='text']")
   	 WebElement creCode;
        
       @FindBy(xpath ="//button[ text()=' Proceed ']")
	 WebElement proceedbutton;
      
    
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
        
       @FindBy(xpath = "//input[@id='amountToPay']")
	WebElement amountToPay;
       
       @FindBy(xpath ="//button[ text()=' Proceed ']")
       WebElement proceed2;
       
       @FindBy(xpath ="//button[ text()=' Proceed ']")
       WebElement proceed3;
       
        @FindBy(xpath = "//p[text()='PAYMENT DETAILS']")
	WebElement customerPaymentPage;

         @FindBy(xpath ="//p[text()='CONFIRM DEPOSIT']")
	WebElement confirmDepositPage;
        
         @FindBy(xpath = "//*[@name='otp']")
          WebElement otp;
        
         @FindBy(xpath = "//button[text()=' Cancel ']")
        WebElement cancelotp;
        
          @FindBy(xpath = "//button[text()=' Confirm ']")
        WebElement confirmotp;

        @FindBy(xpath = "//h5[@class='modal-title' and contains(text(), 'Enter the OTP')]")
        WebElement otpscreen;

        @FindBy(xpath = "//div[@class='modal-header completed']/h4[text()=' Success! ']")
        WebElement successscreen;

        @FindBy(xpath ="//button[contains(@class, 'button-primary') and @role='button' and @type='button' and normalize-space(text())='Done']")
       WebElement doneButton;
        
        @FindBy(xpath =" //div[text()=' Tata Capital ']")
        WebElement tatacapitall;
		
        @FindBy(xpath = "//span[normalize-space(text())='HDB Finance']")
    	WebElement hbdCms;
        
        @FindBy(xpath = "//div[@class='cross-icon']/img[contains(@src, 'cross_icon.png')]")
    	WebElement closeButton;
        
        @FindBy(xpath = "//input[@id='site-search']")
    	WebElement selectField;
        
        
         
 
        // Load all objects
      public TataCapitalAgentPage(WebDriver wdriver) {
  		super(wdriver);
  		PageFactory.initElements(wdriver, this);
  	}


           // Perform action on page based on given commands
	public void tataCapitalAgent(Map<String, String> usrData)
	throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);
			System.out.println("Wallet balance updated");
			 commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			 commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance
		//	commonUtils.refreshBalance();
			waitUntilElementIsVisible(novopayHomePage);
			System.out.println("Home page Visible");
			waitUntilElementIsClickableAndClickTheElement(hbdCms);
			  waitUntilElementIsClickableAndClickTheElement(closeButton);
				waitUntilElementIsClickableAndClickTheElement(selectField);
				selectField.sendKeys("Tata");
			   // Click on Tata Capital  option
            waitUntilElementIsClickableAndClickTheElement(tatacapitall);
               System.out.println("Tata Capital icon clicked");
			 
			
	

            

        	// Selecting app Agent radio button        
                waitUntilElementIsClickableAndClickTheElement(agentradioButton);
                System.out.println("Customer radio button clicked");

               // Click on depositor mobile number field
                waitUntilElementIsClickableAndClickTheElement(depositorMobileNo);
                depositorMobileNo.clear();
                String mobileNumber = usrData.get("MOBILENUMBER");
                if (mobileNumber != null && !mobileNumber.isEmpty()) {
                    depositorMobileNo.sendKeys(mobileNumber);
                    System.out.println("Depositor mobile number entered");
                } else {
                    throw new IllegalArgumentException("Mobile number is null or empty");
                }
		        
			System.out.println("Depositor mobile number entered");

              // Click on batch id  field
			waitUntilElementIsClickableAndClickTheElement(batchId);
			batchId.clear();
	        String batchid = usrData.get("BATCHID");
	        if (batchid  != null && !batchid .isEmpty()) {
	        	batchId.sendKeys(batchid );
	            System.out.println("Batch id entered");
	        } else {
	            throw new IllegalArgumentException("Contract number is null or empty");
	        }
			System.out.println("Batch id entered");
			
			batchId.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			
			 // Click on CreCode field
			
			waitUntilElementIsClickableAndClickTheElement(creCode);
			creCode.clear();
	        String crecode = usrData.get("CRECODE");
	        if (crecode  != null && !crecode .isEmpty()) {
	        	creCode.sendKeys(crecode );
	            System.out.println("CRE Code entered");
	        } else {
	            throw new IllegalArgumentException("Contract number is null or empty");
	        }
			System.out.println("CRE Code entered");
			
			batchId.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			
			
	//Click on Proceed option
			
			 waitUntilElementIsClickableAndClickTheElement(proceedbutton);  
                  // Click on Proceed button
			 //if (usrData.get("proceed2").equalsIgnoreCase("YES")) {
					waitUntilElementIsClickableAndClickTheElement(proceed2);
					System.out.println("second proceed button clicked");
					
					waitUntilElementIsClickableAndClickTheElement(proceed3);
					System.out.println("Third proceed button clicked");
					
			 //}
                                 	waitUntilElementIsVisible(otpscreen);
					System.out.println("OTP screen displayed");
                    waitUntilElementIsClickableAndClickTheElement(otp);
                	String txnOtp1 = "";
                    if (usrData.get("OTP").equalsIgnoreCase("YES")) {
                    	txnOtp1 = getAuthfromIni("TataCapitalOTP");
						otp.sendKeys(getAuthfromIni("TataCapitalOTP"));
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						System.out.println("waiting for Resend OTP button");
						txnOtp1 = getAuthfromIni("TataCapitalOTP");
					} else {
						txnOtp1 = usrData.get("OTP");
                                              } 
                    otp.sendKeys(txnOtp1);
					System.out.println("OTP Entered");
					waitUntilElementIsClickableAndClickTheElement(confirmotp);
				        System.out.println("clicking on confirm button");
				                  
					if (txnOtp1.equals("342360")) {
						System.out.println("Page displayed");
                                        }

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
							+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
							+ "')]";
					WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
					System.out.println(mpinButtonName + " button clicked");
					if (mpinButtonName.equalsIgnoreCase("Cancel")) {
						System.out.println("Cancel button clicked");
					} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
						//commonUtils.processingScreen();

						waitUntilElementIsVisible(successscreen);
						System.out.println("Txn screen displayed");
						
						
					}
                                      // Update retailer wallet balance to 1000000 for scenario where amount > wallet
						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						}
 

                                               // Verify the details on transaction screen
					
                                               
						if (successscreen.getText().equalsIgnoreCase("Success!")) {
							System.out.println("success screen displayed");
							//assertionOnSuccessScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(doneButton);
							System.out.println("Done button clicked");
							
							}
				}	} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test Case Failed");
					Assert.fail();
				
		}
	
	}
		
		
					
					//		private void assertionOnSuccessScreen(Map<String, String> usrData) {
			                 // TODO Auto-generated method stub
			
		//	}


							// Assertion after success or orange screen is displayed
							public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
								double initialWalletBalance = 1000000.00;
								if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
									initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
								} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
									initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
								}
								double amount = Double.parseDouble(cmsDetailsFromIni("Amount", ""));
								double comm = amount * 2 / 1000;
								double commission = Math.round(comm * 100.0) / 100.0;
								double taxDS = commission * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000;
								double tds = Math.round(taxDS * 100.0) / 100.0;
								double newWalletBal = initialWalletBalance - amount + commission - tds;
								txnDetailsFromIni("StoreComm", String.valueOf(commission));
								txnDetailsFromIni("StoreTds", String.valueOf(tds));
								String newWalletBalance = df.format(newWalletBal);
								if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
									Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
									System.out.println("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
								} else {
									Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
									System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
								}
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
	