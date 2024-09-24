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

public class SettingsPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

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

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]")
	WebElement merchantWallet;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]/parent::p/following-sibling::p/span")
	WebElement merchantWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Settings')]")
	WebElement pageTitle;

	@FindBy(xpath = "//h1[contains(text(),'Money Transfer')]")
	WebElement pageTitle2;

	@FindBy(xpath = "//a[@href='/newportal/np-money-transfer']/span[contains(text(),'Money Transfer')]")
	WebElement moneyTransfer;

	@FindBy(xpath = "//span[contains(text(),'Capital First')]/parent::li")
	WebElement capitalFirstIcon;

	@FindBy(id = "capital-first-money-transfer-mobile-number")
	WebElement depositorMobNum;

	@FindBy(xpath = "//label[@for='settlement-mode-retailer-credit']")
	WebElement doNotSettleRadioButton;

	@FindBy(xpath = "//label[@for='settlement-mode-bank-account']")
	WebElement bankAccountRadioButton;

	@FindBy(xpath = "//button[contains(text(),'Save Changes')]")
	WebElement saveChangesButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//*[contains(@class,'retailer-account-status')]")
	WebElement accountStatus;

	@FindBy(xpath = "//i[contains(@class,'icon-32-size')]")
	WebElement blockedIcon;

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

	@FindBy(xpath = "//additional-info-modal//h4")
	WebElement additionalInfoModal;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement settingsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div")
	WebElement settingsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement settingsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div/h4[contains(text(),'Pending')]")
	WebElement multiAccountPendingScreen;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div/h4[contains(text(),'Success')]")
	WebElement multiAccountSuccessScreen;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]//div[contains(@class,'failed')]")
	WebElement multiAccountFailedScreen;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]//div[contains(@class,'failed')]/h4")
	WebElement multiAccountFailedHeader;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]//div[contains(@class,'failed')]/following-sibling::div//span")
	WebElement multiAccountFailedMessage;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div")
	WebElement multiAccountScreenType;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]//div[contains(@class,'messages')]//strong")
	WebElement multiAccountTxnScreenMessage;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	WebElement yesButton;

	@FindBy(xpath = "//input[not(@disabled)]/following-sibling::span[@class='checkmark']")
	WebElement primaryButtonEnabled;

	@FindBy(xpath = "//input[@disabled]/following-sibling::span[@class='checkmark']")
	WebElement primaryButtonDisabled;

	@FindBy(xpath = "//input[not(@disabled)]/following-sibling::span[@class='checkmark']/parent::label/parent::div/preceding-sibling::div/label[contains(@class,'retailer-account-number')]")
	WebElement accNumForEnabledPrimary;

	@FindBy(xpath = "//input[@disabled]/following-sibling::span[@class='checkmark']/parent::label/parent::div/preceding-sibling::div/label[contains(@class,'retailer-account-number')]")
	WebElement accNumForDisabledPrimary;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//settlement-mode//p")
	WebElement noBankAccountMsg;

	@FindBy(xpath = "//p[contains(@class,'debitInfo')]")
	WebElement debitInfo;

	@FindBy(xpath = "//button[contains(text(),'Add Bank Account')]")
	WebElement addButton;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement okButton;

	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	WebElement deleteButton;

	@FindBy(xpath = "//h4[contains(text(),'Deleting an Account')]")
	WebElement deleteModal;

	@FindBy(xpath = "//div[contains(@class,'deleteView')]")
	WebElement deleteRejectButton;

	@FindBy(id = "settlement-mode-retailer-name")
	WebElement accHolderName;

	@FindBy(id = "settlement-mode-create-field-retailer-bank-ifsc-list")
	WebElement ifscCode;

	@FindBy(xpath = "//input[contains(@id,'settlement-mode-create-field-retailer-bank-ifsc-list')]/parent::div/following-sibling::span")
	WebElement ifscSearchIcon;

	@FindBy(id = "settlement-mode-retailer-account-number")
	WebElement accNumber;

	@FindBy(xpath = "//*[@name='settlement-mode-retailer-confirm-account-number']")
	WebElement confirmAccNumber;

	@FindBy(xpath = "//*[@for='file-upload']")
	WebElement uploadFile;

	@FindBy(id = "settlement-mode-retailer-file-name")
	WebElement inputFile;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//h5[contains(text(),'IFSC Search')]")
	WebElement ifscSearchScreen;

	@FindBy(xpath = "//*[@id='ifsc-search-bank']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchBankList;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchStateList;

	@FindBy(id = "ifsc-search-district")
	WebElement ifscSearchDistrict;

	@FindBy(id = "ifsc-search-branch")
	WebElement ifscSearchBranch;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	WebElement ifscSearchButton;

	@FindBy(xpath = "//div/i[contains(@class,'fa fa-arrow-left')]")
	WebElement ifscSearchBack;

	@FindBy(id = "search-bank-ifsc")
	WebElement ifscSearch;

	@FindBy(xpath = "//div[contains(@class,'ifsc-search-modal')]//button[contains(text(),'OK')]")
	WebElement ifscSearchOK;

	@FindBy(xpath = "//span[contains(@class,'custom-ul-errormessage')]/span[contains(text(),'Branch')]")
	WebElement validateIFSC;

	@FindBy(xpath = "//h1[contains(text(),'Bank Account Details')]/ancestor::div[@class='mainHeading']/following-sibling::div//div[contains(@class,'non-editable-field')]")
	WebElement bankDetailsCards;

	@FindBy(xpath = "//h1[contains(text(),'Bank Account Details')]/ancestor::div[@class='mainHeading']/following-sibling::div//div[contains(@class,'non-editable-field')][2]//label[2]")
	WebElement card2Status;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	// Load all objects
	public SettingsPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void settings(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.waitForSpinner();
			commonUtils.displayInitialBalance("cashout");

			// Updating org_stlmnt_info table as per test case
			if (usrData.get("TYPE").equalsIgnoreCase("Mode")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1", mobileNumFromIni(),
						"1", "1234567890", "NOW()");
			} else if (usrData.get("TYPE").equalsIgnoreCase("Details")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				if (usrData.get("ACCOUNT").equalsIgnoreCase("No exisitng account")) {
					System.out.println("No new entry inserted");
				} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One pending account")) {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "1", "1",
							mobileNumFromIni(), "0", "1234567890", "NOW()");
					System.out.println("One pending and non-primary account inserted");
				} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One deleted account")) {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "6", "1",
							mobileNumFromIni(), "0", "1234567890", "NOW()");
					System.out.println("One deleted account inserted");
				} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One rejected account")) {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "3", "1",
							mobileNumFromIni(), "0", "1234567890", "NOW()");
					System.out.println("One rejected account inserted");
				} else if (usrData.get("ACCOUNT").equalsIgnoreCase("Two verified accounts")) {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1",
							mobileNumFromIni(), "1", "1234567890", "NOW()");
					if (usrData.get("ASSERTION").equalsIgnoreCase("Delete Older")) {
						dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1",
								mobileNumFromIni(), "0", "1234567891", "CURDATE()-" + dbUtils.getDeleteAccountDays());
					} else {
						dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1",
								mobileNumFromIni(), "0", "1234567891", "NOW()");
					}
					System.out.println("Two verified accounts inserted");
				} else if (usrData.get("ACCOUNT").equalsIgnoreCase("Max verified accounts")) {
					int count = Integer.parseInt(dbUtils.getMaxAccounts()), accNum = 0;
					for (int i = 1; i <= count; i++) {
						accNum = 123456789 + i;
						String accountNum = String.valueOf(accNum);
						dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1",
								mobileNumFromIni(), "0", accountNum, "NOW()");
					}
					System.out.println(count + " verified accounts inserted");
				} else {
					dbUtils.insertOrgSettlementInfo(usrData.get("IFSC"), usrData.get("MODE"), "2", "1",
							mobileNumFromIni(), "1", "1234567890", "NOW()");
					System.out.println("One verified and primary account inserted");
				}
			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
				clickElement(menu);
				commonUtils.refreshBalance();
				clickElement(menu);
			}

			commonUtils.selectFeatureFromMenu1(settings, pageTitle);
			commonUtils.waitForSpinner();

			// Changing mode
			if (usrData.get("TYPE").contains("Mode")) {
				if (usrData.get("MODE").equalsIgnoreCase("DO_NOT_SETTLE")) {
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(bankAccountRadioButton);
				} else if (usrData.get("MODE").equalsIgnoreCase("TO_BANK")) {
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(doNotSettleRadioButton);
				}
				System.out.println("Radio button clicked");
				Thread.sleep(1000);
				waitUntilElementIsClickableAndClickTheElement(saveChangesButton);
				System.out.println("Save changes button clicked");
			}

			if (usrData.get("ACCOUNT").equalsIgnoreCase("One pending account")) {
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "PENDING VERIFICATION");
				System.out.println("The status is " + accountStatus.getText());
			} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One verified account")) {
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "VERIFIED");
				System.out.println("The status is " + accountStatus.getText());
			} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One deleted account")) {
				waitUntilElementIsClickableAndClickTheElement(deleteRejectButton);
				System.out.println("Delete/Reject account button clicked");
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "DELETED");
				System.out.println("The status is " + accountStatus.getText());
			} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One rejected account")) {
				waitUntilElementIsClickableAndClickTheElement(deleteRejectButton);
				System.out.println("Delete/Reject account button clicked");
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "REJECTED");
				System.out.println("The status is " + accountStatus.getText());
			}

			// Adding Bank Details
			if (usrData.get("TYPE").contains("Details")) {
				if (usrData.get("ACCOUNT").equalsIgnoreCase("No exisitng account")) {
					waitUntilElementIsVisible(noBankAccountMsg);
					System.out.println(noBankAccountMsg.getText());
					Thread.sleep(1000);
					try {
						Assert.assertTrue(noBankAccountMsg.getText().contains("There are no saved accounts"));
					} catch (Exception e) {
						Assert.assertTrue(noBankAccountMsg.getText().contains("Unable to fetch the Account Details"));
					}
				} else if (usrData.get("ACCOUNT").contains("One")) {
					Assert.assertEquals(cardsCount(), 1);
					System.out.println("One account displayed");
				} else if (usrData.get("ACCOUNT").contains("Two")) {
					Assert.assertEquals(cardsCount(), 2);
					System.out.println("Two accounts displayed");
				} else if (usrData.get("ACCOUNT").contains("Max")) {
					Assert.assertEquals(cardsCount(), Integer.parseInt(dbUtils.getMaxAccounts()));
					System.out.println(dbUtils.getMaxAccounts() + " accounts displayed");
				}
				if (usrData.get("ASSERTION").equalsIgnoreCase("Primary")) {
					waitUntilElementIsVisible(accNumForDisabledPrimary);
					Assert.assertEquals(accNumForDisabledPrimary.getText(), "1234567890");
					System.out.println("Primary account is 1234567890");
					Assert.assertEquals(accNumForEnabledPrimary.getText(), "1234567891");
					System.out.println("Non-primary account is 1234567891");
					waitUntilElementIsClickableAndClickTheElement(primaryButtonEnabled);
					System.out.println("Primary radio button selected");
					waitUntilElementIsClickableAndClickTheElement(yesButton);
					System.out.println("Yes button clicked");
					commonUtils.waitForSpinner();
					Assert.assertEquals(accNumForDisabledPrimary.getText(), "1234567891");
					System.out.println("Primary account is 1234567891");
					Assert.assertEquals(accNumForEnabledPrimary.getText(), "1234567890");
					System.out.println("Non-primary account is 1234567890");
				} else if (usrData.get("ASSERTION").contains("Delete") && !usrData.get("ASSERTION").contains("View")) {
					waitUntilElementIsClickableAndClickTheElement(deleteButton);
					System.out.println("Delete button clicked");
					if (usrData.get("ASSERTION").equalsIgnoreCase("Delete Older")) {
						waitUntilElementIsVisible(deleteModal);
						System.out.println("Delete modal displayed");
						waitUntilElementIsClickableAndClickTheElement(yesButton);
						System.out.println("Yes button clicked");
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(deleteRejectButton);
						System.out.println("Delete/Reject account button visible");
						Assert.assertEquals(cardsCount(), 1);
						System.out.println("One account deleted");
					}
				} else if (!usrData.get("ASSERTION").contains("View")) {
					waitUntilElementIsClickableAndClickTheElement(addButton);
					if (!usrData.get("ACHOLDERNAME").equalsIgnoreCase("SKIP")) {
						Thread.sleep(1000);
						Assert.assertEquals(debitInfo.getText(), "Rs. " + dbUtils.getAccountValidationCharge()
								+ " will be deducted from Cashout Wallet for Account details submission.");
						System.out.println(debitInfo.getText());

						waitUntilElementIsClickableAndClickTheElement(accHolderName);
						accHolderName.clear();
						accHolderName.sendKeys(getBeneNameFromIni(usrData.get("ACHOLDERNAME")));
						System.out.println("Account holder name '" + usrData.get("ACHOLDERNAME") + "' entered");

						waitUntilElementIsClickableAndClickTheElement(accNumber);
						accNumber.clear();
						accNumber.sendKeys(getAccountNumberFromIni(usrData.get("ACNUMBER")));
						System.out.println("Account number '" + getAccountNumberFromIni("GetNum") + "' entered");

						waitUntilElementIsClickableAndClickTheElement(confirmAccNumber);
						confirmAccNumber.clear();
						confirmAccNumber.sendKeys(usrData.get("ACNUMBER"));
						System.out.println("Confirm Account number '" + usrData.get("ACNUMBER") + "' entered");

						if (usrData.get("IFSCTYPE").equalsIgnoreCase("Manual")) {
							waitUntilElementIsClickableAndClickTheElement(ifscCode);
							ifscCode.clear();
							ifscCode.sendKeys(usrData.get("IFSC"));
							System.out.println("IFSC code '" + usrData.get("IFSC") + "' entered");
						} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Search Screen")) {
							waitUntilElementIsClickableAndClickTheElement(ifscSearchIcon);
							System.out.println("IFSC search icon clicked");
							waitUntilElementIsVisible(ifscSearchScreen);
							waitUntilElementIsVisible(ifscSearchBankList);
							ifscSearchBankList.click();
							System.out.println("IFSC bank drop down clicked");
							String ifscBank = "//li[contains(text(),'"
									+ dbUtils.ifscCodeDetails(usrData.get("IFSC"), "bank") + "')]";
							WebElement ifscSearchBank = wdriver.findElement(By.xpath(ifscBank));
							ifscSearchBank.click();
							System.out.println("IFSC bank selected");
							ifscSearchStateList.click();
							System.out.println("IFSC state drop down clicked");
							String stateFromDB = dbUtils.ifscCodeDetails(usrData.get("IFSC"), "state");
							String stateCapitalized = stateFromDB.toUpperCase();
							String ifscState = "//li[contains(text(),'" + stateCapitalized + "')]";
							WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
							ifscSearchState.click();
							System.out.println("IFSC state selected");
							ifscSearchDistrict.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSC"), "district"));
							System.out.println("IFSC district entered");
							ifscSearchBranch.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSC"), "branch"));
							System.out.println("IFSC branch entered");
							ifscSearchButton.click();
							System.out.println("Search button clicked");
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(ifscSearchBack);
							String searchCode = "//span[contains(@class,'add-beneficiary-list')][contains(text(),'"
									+ usrData.get("IFSC") + "')]/parent::li";
							WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
							waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
							System.out.println("IFSC code '" + usrData.get("IFSC") + "' entered");
							ifscSearchOK.click();
							System.out.println("OK button clicked");
						} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Drop Down")) {
							waitUntilElementIsClickableAndClickTheElement(ifscCode);
							ifscCode.clear();
							String searchCode = "//span[contains(@class,'add-beneficiary-sublist')][contains(text(),'"
									+ usrData.get("IFSC") + "')]/parent::li";
							WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
							waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
							System.out.println("IFSC code '" + usrData.get("IFSC") + "' selected");
						}
						getBankNameFromIni(dbUtils.ifscCodeDetails(usrData.get("IFSC"), "bank"));
						waitUntilElementIsVisible(validateIFSC); // wait for Branch name
						System.out.println(validateIFSC.getText());
					}
					if (!usrData.get("ASSERTION").equalsIgnoreCase("Max accounts")) {
						String buttonName = usrData.get("SETTINGSBUTTON");
						String buttonXpath = "//button[contains(text(),'" + buttonName + "')]";
						WebElement button = wdriver.findElement(By.xpath(buttonXpath));
						Thread.sleep(3000);
						waitUntilElementIsClickableAndClickTheElement(button);
						if (buttonName.equalsIgnoreCase("Clear")) {
							Thread.sleep(2000);
							System.out.println("Clear button clicked");
						} else if (buttonName.equalsIgnoreCase("Submit")) {
							System.out.println("Submit button clicked");
						}
					}
				}
			}
			if (!usrData.get("MPIN").equalsIgnoreCase("SKIP")) {
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
					if (usrData.get("TXNSCREEN").equalsIgnoreCase("Success")) {
						commonUtils.waitForSpinner();
						assertionOnSuccessScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(doneButton);
						System.out.println("Done button clicked");
						if (usrData.get("ASSERTION").contains("FCM")) {
							commonUtils.selectFeatureFromMenu1(moneyTransfer, pageTitle2);
							assertionOnFCM(usrData);
						}
//						verifyUpdatedBalance(usrData);
					} else if (usrData.get("TXNSCREEN").equalsIgnoreCase("Failed")) {
						waitUntilElementIsVisible(multiAccountFailedScreen);
						System.out.println("Failed modal displayed");
						assertionOnFailedScreen(usrData);
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
							exitButton.click();
							System.out.println("Exit button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							retryButton.click();
							waitUntilElementIsVisible(MPINScreen);
							System.out.println("MPIN screen displayed");
							Thread.sleep(1000);
							waitUntilElementIsClickableAndClickTheElement(enterMPIN);
							enterMPIN.sendKeys(getAuthfromIni("MPIN"));
							System.out.println("MPIN entered");
							waitUntilElementIsClickableAndClickTheElement(submitMPIN);
							System.out.println("Submit button clicked");
							if (usrData.get("TYPE").equalsIgnoreCase("Mode")) {
								commonUtils.waitForSpinner();
								assertionOnSuccessScreen(usrData);
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								System.out.println("Done button clicked");
							} else if (usrData.get("TYPE").equalsIgnoreCase("Details")) {
								commonUtils.pendingScreen();
								waitUntilRblAccountValElementIsVisible(additionalInfoModal);
								System.out.println("Need Additional Info modal displayed");
								waitUntilElementIsClickableAndClickTheElement(cancelButton);
								System.out.println("Cancel button clicked");
							}
						} else {
							waitUntilElementIsClickableAndClickTheElement(okButton);
							System.out.println("OK button clicked");
						}
					} else if (usrData.get("TXNSCREEN").equalsIgnoreCase("Pending")) {
						commonUtils.pendingScreen();

						waitUntilRblAccountValElementIsVisible(additionalInfoModal);
						System.out.println("Need Additional Info modal displayed");

						commonUtils.uploadFile(uploadFile);
						System.out.println("Image selected");

						waitUntilElementIsVisible(toasterMsg);
						Assert.assertEquals(toasterMsg.getText(), "Image Upload success !!");

						while (okButton.getCssValue("background-color").equals("rgba(0, 150, 197, 1)") == false) {
							okButton.click();
						}
						okButton.click();
						System.out.println("Ok button clicked");

						waitUntilElementIsVisible(multiAccountPendingScreen);
						System.out.println("Pending screen displayed");

						assertionOnPendingScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(okButton);
						System.out.println("OK button clicked");

						if (usrData.get("ACCOUNT").equalsIgnoreCase("No exisitng account")) {
							waitUntilElementIsVisible(accountStatus);
							Assert.assertEquals(accountStatus.getText(), "PENDING VERIFICATION");
							System.out.println("One account added");
						} else {
							waitUntilElementIsVisible(card2Status);
							if (usrData.get("ACCOUNT").equalsIgnoreCase("One pending account")) {
								Assert.assertEquals(card2Status.getText(), "PENDING VERIFICATION");
							} else if (usrData.get("ACCOUNT").equalsIgnoreCase("One verified account")) {
								Assert.assertEquals(card2Status.getText(), "PENDING VERIFICATION");
							}
							System.out.println("Another account added");
						}
//						verifyUpdatedBalance(usrData);
					}
				} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
					clearButton.click();
					System.out.println("Clear button clicked");
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("TYPE").equalsIgnoreCase("MODE") && usrData.get("MODE").equalsIgnoreCase("DO_NOT_SETTLE")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"Your end of the day withdrawable amount will now be credited to your Primary bank account.");
			System.out.println(settingsTxnScreenMessage.getText());
		} else if (usrData.get("TYPE").equalsIgnoreCase("MODE") && usrData.get("MODE").equalsIgnoreCase("TO_BANK")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"You can now use your withdrawable amount for transaction purposes.");
			System.out.println(settingsTxnScreenMessage.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Verified")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(), "Bank account details successfully verified");
			System.out.println(settingsTxnScreenMessage.getText());
		}
	}

	// Verify details on success screen
	public void assertionOnPendingScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		waitUntilElementIsVisible(multiAccountTxnScreenMessage);
		Assert.assertEquals(multiAccountTxnScreenMessage.getText(),
				"Bank Account details have been submitted successfully");
		System.out.println(multiAccountTxnScreenMessage.getText());
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(multiAccountFailedMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(multiAccountFailedHeader.getText(), "Minimum Balance");
			Assert.assertEquals(multiAccountFailedMessage.getText(), "Rs. " + dbUtils.getSettlementCharge()
					+ " minimum balance is not present in Cashout Wallet. Can not proceed further.");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Same Details")) {
			Assert.assertEquals(multiAccountFailedHeader.getText(), "Duplicate Submission");
			Assert.assertEquals(multiAccountFailedMessage.getText(),
					"Account Number and IFSC Code of the HDFC BANK already added.");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Max accounts")) {
			Assert.assertEquals(multiAccountFailedHeader.getText(), "Maximum Accounts");
			Assert.assertEquals(multiAccountFailedMessage.getText(),
					"Maximum numbers of accounts added. Please Delete accounts to add new account");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Delete New")) {
			Assert.assertEquals(multiAccountFailedHeader.getText(), "Alert!");
			Assert.assertEquals(multiAccountFailedMessage.getText(),
					"You can delete the bank account details ONLY after 5 days of addition to your Novopay Profile");
		}
		System.out.println(settingsTxnScreenMessage.getText());
	}

	// Verify details on info screen
	public void assertionOnInfoScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(settingsTxnScreenMessage.getText(),
				"Please change settlement mode to 'Do Not Settle' before updating bank details");
		System.out.println(settingsTxnScreenMessage.getText());
	}

	public int cardsCount() {
		waitUntilElementIsVisible(bankDetailsCards);
		int cards = wdriver.findElements(
				By.xpath("//h1[contains(text(),'Bank Account Details')]/ancestor::div[@class='mainHeading']"
						+ "/following-sibling::div//div[contains(@class,'non-editable-field')]"))
				.size();
		return cards;
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalance(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		clickElement(menu);
		commonUtils.refreshBalance();
		double initialWalletBalance = Double.parseDouble(commonUtils.getWalletBalanceFromIni("GetCashout", ""));
		double charges = Double.parseDouble(dbUtils.getSettlementCharge());
		double newWalletBal = 0.00;
		newWalletBal = initialWalletBalance - charges;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "You have updated your account details with ";
		String successFCMContent = "The bank account details have been submitted for verification.";

		fcmMethod(successFCMHeading, successFCMContent);
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText().substring(0, 43), heading);
		Assert.assertEquals(fcmContent.getText().substring(0, 62), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
