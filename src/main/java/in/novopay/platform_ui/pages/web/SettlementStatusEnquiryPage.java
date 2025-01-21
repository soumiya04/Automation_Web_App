package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
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

public class SettlementStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'OnDemand Settlement')]")
	WebElement settlementProduct;

	@FindBy(xpath = "//div[@status-enquiry='']//button")
	WebElement statusEnquirySubmitButton;

	@FindBy(id = "searchByTxnID")
	WebElement pageTxnId;

	@FindBy(xpath = "//*[@id='reports-list-status-enquiry']//button")
	WebElement pageSubmitButton;

	@FindBy(xpath = "//span[contains(text(),'OnDemand Settlement - Status Enquiry')]")
	WebElement reportPage;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement seTxnTitle;

	@FindBy(xpath = "//div[contains(@class,'ondemand-details-modal')]//span[contains(text(),'Amount')]/parent::div/following-sibling::div/span")
	WebElement txnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'ondemand-details-modal')]//span[contains(text(),'Charges')]/parent::div/following-sibling::div/span")
	WebElement txnScreenCharges;

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

	@FindBy(xpath = "//li[contains(text(),'OnDemand Settlement - Status Enquiry')]")
	WebElement settlementDropdown;

	@FindBy(xpath = "//iframe[@flow='auth']")
	WebElement dashboard;

	@FindBy(id = "gauth")
	WebElement googleLogin;

	@FindBy(xpath = "//*[@type='email']")
	WebElement email;

	@FindBy(xpath = "//*[@type='password']")
	WebElement password;
	
	@FindBy(xpath = "//*[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//span[contains(text(),'Next')]/parent::button")
	WebElement nextButton;

	@FindBy(id = "profileDropdown")
	WebElement profileButton;

	@FindBy(xpath = "//span[contains(text(),'Test Mode')]/parent::a//button")
	WebElement testModeSwitch;

	@FindBy(xpath = "//*[@class='Test-mode-banner']//span[contains(text(),'TEST MODE')]")
	WebElement testMode;

	@FindBy(xpath = "//*[@href='/payouts']")
	WebElement payoutsButton;

	@FindBy(xpath = "//*[@aria-current='page'][@href='/payouts']")
	WebElement payoutsPage;

	@FindBy(xpath = "//*[@class='BucketListView-bucket']//div[contains(@class,'Table-row--clickable')][1]")
	WebElement txnRecord;

	@FindBy(xpath = "//*[contains(text(),'Show more details')]")
	WebElement showDetailsButton;

	@FindBy(xpath = "//*[contains(text(),'Reference ID')]/following-sibling::div")
	WebElement refId;

	@FindBy(xpath = "//*[@class='PayoutsDetailsView-content']//*[contains(text(),'Change Status')]/parent::button")
	WebElement changeStatusButton;

	@FindBy(xpath = "//span[contains(text(),'Change Payout State')]/parent::div//span[contains(text(),'TEST')]")
	WebElement changePayoutModal;

	@FindBy(xpath = "//span[contains(@class,'flow-chart-step')]//span[contains(text(),'processed')]")
	WebElement processedButton;

	@FindBy(xpath = "//span[contains(@class,'active')]//span[contains(text(),'reversed')]")
	WebElement reversedButton;

	@FindBy(xpath = "//span[contains(text(),'Update')]/parent::button[contains(@class,'Button--disabled')]")
	WebElement updateButtonDisabled;

	@FindBy(xpath = "//span[contains(text(),'Update')]")
	WebElement updateButton;

	@FindBy(xpath = "//div[contains(@class,'timeline-collapsed')]/div[1]/div[1]/span")
	WebElement payoutStatus;

	@FindBy(xpath = "//div[contains(text(),'Payout status updated successfully')]")
	WebElement payoutToast;

	String txnID = txnDetailsFromIni("GetTxnRefNo", "");

	public SettlementStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void settlementStatusEnquiry(Map<String, String> usrData)
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
				dropDownSearch.sendKeys("OnDemand Settlement - Status Enquiry");
				System.out.println("Typing Wallet Load - Status Enquiry");
				waitUntilElementIsClickableAndClickTheElement(settlementDropdown);
				System.out.println("OnDemand Settlement - Status Enquiry drop down selected");

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
				Assert.assertEquals("Invalid input reference number", toasterMsg.getText());
			} else {
				reportsData(usrData);
				if (usrData.get("PARTNER").equalsIgnoreCase("RBL")) {
					if (usrData.get("STATUS").equalsIgnoreCase("Pending to Success")) {
						dbUtils.updateSettlementStatus("Success", txnDetailsFromIni("GetTxnRefNo", ""));
					} else if (usrData.get("STATUS").equalsIgnoreCase("Pending to Failed")) {
						dbUtils.updateSettlementStatus("Failure", txnDetailsFromIni("GetTxnRefNo", ""));
					}
				} else if (usrData.get("PARTNER").equalsIgnoreCase("RazorpayX")) {
					if (usrData.get("STATUS").contains("Success") || usrData.get("STATUS").contains("Failed")) {
						openNewTab("RazorpayX Portal", "");
						try {
//							wdriver.switchTo().frame(dashboard);

							// Store the current window handle
//							String winHandleBefore = wdriver.getWindowHandle();

							// Perform the click operation that opens new window
//							waitUntilElementIsClickableAndClickTheElement(googleLogin);
//							System.out.println("Login with Google button clicked");

							// Switch to new window opened
//							for (String winHandle : wdriver.getWindowHandles()) {
//								wdriver.switchTo().window(winHandle);
//							}

							// Perform the actions on new window
							waitUntilElementIsClickableAndClickTheElement(email);
							email.sendKeys("ankush.khanna@novopay.in");
							System.out.println("Entering email id");

//							waitUntilElementIsClickableAndClickTheElement(nextButton);
//							System.out.println("Next button clicked");

							waitUntilElementIsClickableAndClickTheElement(password);
							password.sendKeys("Razor123$");
							System.out.println("Entering password");
							
							waitUntilElementIsClickableAndClickTheElement(loginButton);
							System.out.println("Login button clicked");

//							waitUntilElementIsClickableAndClickTheElement(nextButton);
//							System.out.println("Next button clicked");

							// Switch back to original browser (first window)
//							wdriver.switchTo().window(winHandleBefore);

							// Continue with original browser (first window)
							waitUntilElementIsClickableAndClickTheElement(profileButton);
							System.out.println("Profile button clicked");

							waitUntilElementIsClickableAndClickTheElement(testModeSwitch);
							System.out.println("Toggle button clicked");

							waitUntilElementIsVisible(testMode);
							System.out.println("Page displayed in test mode");
						} catch (Exception e) {
							waitUntilElementIsVisible(testMode);
							System.out.println("Page displayed in test mode");
						}
						waitUntilElementIsClickableAndClickTheElement(payoutsButton);
						System.out.println("Payouts button clicked");

						waitUntilElementIsVisible(payoutsPage);
						System.out.println("Payouts page displayed");

						waitUntilElementIsClickableAndClickTheElement(txnRecord);
						System.out.println("Transaction selected");

						waitUntilElementIsVisible(payoutStatus);
						Assert.assertEquals(payoutStatus.getText(), "Processing");
						System.out.println("Status is Processing");

						waitUntilElementIsClickableAndClickTheElement(showDetailsButton);
						System.out.println("Clicked Show more details button");

						Assert.assertEquals(refId.getText(), txnDetailsFromIni("GetTxnRefNo", ""));
						System.out.println("Ref Id is " + txnDetailsFromIni("GetTxnRefNo", ""));

						waitUntilElementIsClickableAndClickTheElement(changeStatusButton);
						System.out.println("Change Status Button clicked");

						waitUntilElementIsVisible(changePayoutModal);
						System.out.println("Change status modal displayed");

						if (usrData.get("STATUS").contains("Success")) {
							waitUntilElementIsClickableAndClickTheElement(processedButton);
							System.out.println("Processed button clicked");

							try {
								while (updateButtonDisabled.isDisplayed() == true) {
									updateButton.getText();
								}
								waitUntilElementIsClickableAndClickTheElement(updateButton);
								System.out.println("Update button clicked");
							} catch (Exception e) {
								waitUntilElementIsClickableAndClickTheElement(updateButton);
								System.out.println("Update button clicked");
							}

							waitUntilElementIsVisible(payoutToast);
							waitUntilElementIsVisible(payoutStatus);
							Assert.assertEquals(payoutStatus.getText(), "Processed");
						} else if (usrData.get("STATUS").contains("Failed")) {
							waitUntilElementIsClickableAndClickTheElement(reversedButton);
							System.out.println("Reversed button clicked");

							try {
								while (updateButtonDisabled.isDisplayed() == true) {
									updateButton.getText();
								}
								waitUntilElementIsClickableAndClickTheElement(updateButton);
								System.out.println("Update button clicked");
							} catch (Exception e) {
								waitUntilElementIsClickableAndClickTheElement(updateButton);
								System.out.println("Update button clicked");
							}

							waitUntilElementIsVisible(payoutToast);
							waitUntilElementIsVisible(payoutStatus);
							Thread.sleep(2000);
							Assert.assertEquals(payoutStatus.getText(), "Reversed");
						}

						wdriver.close(); // close the tab
						ArrayList<String> tabs = new ArrayList<String>(wdriver.getWindowHandles());
						wdriver.switchTo().window(tabs.get(0)); // switch to previous window
					}
				}
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

		waitUntilElementIsClickableAndClickTheElement(settlementProduct);
		System.out.println("Wallet Load selected");

		waitUntilElementIsClickableAndClickTheElement(statusEnquirySubmitButton);
		System.out.println("Submit button clicked");
		commonUtils.waitForSpinner();
		waitUntilElementIsVisible(reportPage);
		System.out.println("Report page displayed");
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String statusXpath = "//tbody/tr[1]/td[5]";
		commonUtils.waitForSpinner();
		waitUntilElementIsClickable(wdriver.findElement(By.xpath(statusXpath)));
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(statusData.getText(), "SUCCESS");
		} else if (usrData.get("STATUS").contains("Pending")) {
			Assert.assertEquals(statusData.getText(), "PENDING");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(statusData.getText(), "FAILED");
		}
		System.out.println(statusData.getText());
	}

	// Verify details on txn screen
	public void assertionOnStatusEnquiryScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("STATUS").contains("Success")) {
			Assert.assertEquals(seTxnTitle.getText(), "Success!");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Pending")) {
			Assert.assertEquals(seTxnTitle.getText(), "Pending!");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(seTxnTitle.getText(), "Failed!");
		}
		System.out.println("Title: " + seTxnTitle.getText());

		if (usrData.get("STATUS").contains("Success") || usrData.get("STATUS").equalsIgnoreCase("Pending")) {
			Assert.assertEquals(replaceSymbols(txnScreenTxnAmount.getText()),
					txnDetailsFromIni("GetTxfAmount", "") + ".00");
			System.out.println("Txn Amount: " + txnDetailsFromIni("GetTxfAmount", ""));

			Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
			System.out.println("Charges: " + txnDetailsFromIni("GetCharges", ""));
		}
	}

}