package in.novopay.platform_ui.pages.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;

public class FOPWalletLoadPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);

	public FOPWalletLoadPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//h1[contains(text(),'Welcome')]")
	WebElement welcomeTitle;

	@FindBy(xpath = "//div[contains(text(),'Finance')]")
	WebElement finance;

	@FindBy(xpath = "//h1[contains(text(),'Wallet Load')]")
	WebElement walletLoadTitle;

	@FindBy(xpath = "//div[contains(text(),'Wallet Load')]")
	WebElement walletLoad;

	@FindBy(xpath = "//a[contains(text(),'Pending with Bank Statement')]")
	WebElement pendingBankStmntTab;

	@FindBy(xpath = "//a[contains(text(),'Pending')]")
	WebElement pendingTab;

	@FindBy(xpath = "//a[contains(text(),'History')]")
	WebElement historyTab;

	@FindBy(xpath = "//select[@name='bankName']")
	WebElement bankname;

	@FindBy(xpath = "//select[@name='txnType']")
	WebElement txnType;

	@FindBy(xpath = "//h5[contains(text(),'Bank Statements')]/parent::div/following-sibling::div//tr[@class='table-row']")
	WebElement bankStatementRow;

	@FindBy(xpath = "//h5[contains(text(),'Requests')]/parent::div/following-sibling::div//tr[@class='table-row']")
	WebElement requestRow;

	@FindBy(xpath = "//h5[contains(text(),'Bank')]/parent::div/following-sibling::div//tr[@class='table-row']/td[2]")
	WebElement description;

	@FindBy(xpath = "//button[contains(text(),'Action')]")
	WebElement actionButton;

	@FindBy(xpath = "//*[@id='action-approve']/parent::span")
	WebElement approveRadioButton;

	@FindBy(xpath = "//*[@id='action-reject']/parent::span")
	WebElement rejectRadioButton;

	@FindBy(xpath = "//select2[contains(@id,'ifsc-search-bank')]/parent::div")
	WebElement rejectReasonDropdown;

	@FindBy(xpath = "//li[contains(text(),'Duplicate Request')]")
	WebElement rejectReasonValue;

	@FindBy(xpath = "//h4[contains(text(),'Wallet Load Action')]")
	WebElement actionScreen;

	@FindBy(xpath = "//h4[contains(text(),'Wallet Load Action')]/parent::div/following-sibling::div//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//div[@class='toast-message']/div")
	WebElement toastMessage;

	public void fOPWalletLoadPage(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {

		try {
			waitUntilElementIsVisible(welcomeTitle);
			System.out.println("Welcome page displayed");

			commonUtils.selectFeatureFromMenu3(finance, walletLoad, walletLoadTitle);
			commonUtils.waitForSpinner();

			String desc = "CASH DEPOSIT-" + getTermNumberFromIni("GetTerm") + "/" + getTxnNumberFromIni("GetTxn")
					+ "-Bengaluru";

			dbUtils.updateCdmWalletLoad();
			if (usrData.get("PARTNER").equals("AXIS")) {
				dbUtils.insertCdmWalletLoadEntries(usrData.get("PARTNER"), getTermNumberFromIni("GetTerm"),
						getTxnNumberFromIni("GetTxn"), txnDetailsFromIni("GetTxfAmount", "") + "00",
						usrData.get("TYPE"), desc);
			} else if (usrData.get("PARTNER").equals("FED")) {
				dbUtils.insertCdmWalletLoadEntries(usrData.get("PARTNER"), "EROOR", getTxnNumberFromIni("GetTxn"),
						txnDetailsFromIni("GetTxfAmount", "") + "00", usrData.get("TYPE"), desc);
			} else {
				dbUtils.insertCdmWalletLoadEntries(usrData.get("PARTNER"), "(NULL)", "(NULL)",
						txnDetailsFromIni("GetTxfAmount", "") + "00", usrData.get("TYPE"), desc);
			}

			if (usrData.get("TAB").equalsIgnoreCase("Pending with Bank Statement")) {
				waitUntilElementIsClickableAndClickTheElement(pendingBankStmntTab);
				System.out.println("Pending with Bank Statement Tab clicked");
			}

			Select bank = new Select(bankname);
			bank.selectByVisibleText(usrData.get("BANK"));
			commonUtils.waitForSpinner();

			Select type = new Select(txnType);
			type.selectByVisibleText(usrData.get("OPTIONS"));
			commonUtils.waitForSpinner();

			waitUntilElementIsClickable(bankStatementRow);
			Assert.assertEquals(description.getText(), desc);
			waitUntilElementIsClickableAndClickTheElement(bankStatementRow);
			commonUtils.waitForSpinner();

			waitUntilElementIsVisible(requestRow);

			waitUntilElementIsClickableAndClickTheElement(actionButton);
			System.out.println("Action button clicked");

			waitUntilElementIsVisible(actionScreen);
			if (usrData.get("ACTION").equalsIgnoreCase("Approve")) {
				waitUntilElementIsClickableAndClickTheElement(approveRadioButton);
				System.out.println("Approve radio button selected");
			} else if (usrData.get("ACTION").equalsIgnoreCase("Reject")) {
				waitUntilElementIsClickableAndClickTheElement(rejectRadioButton);
				System.out.println("Reject radio button selected");

				waitUntilElementIsClickableAndClickTheElement(rejectReasonDropdown);
				System.out.println("Reject reason dowpdown clicked");

				waitUntilElementIsClickableAndClickTheElement(rejectReasonValue);
				System.out.println("Reject Reason selected");
			}
			waitUntilElementIsClickableAndClickTheElement(submitButton);
			System.out.println("Submit button clicked");

			waitUntilElementIsVisible(toastMessage);
			System.out.println(toastMessage.getText());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String[][] dataFromUI = new String[1][9];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 9; j++) {
				String dataXpath = "//tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]";
				waitUntilElementIsVisible(wdriver.findElement(By.xpath(dataXpath)));
				WebElement data = wdriver.findElement(By.xpath(dataXpath));
				if (j == 3) {
					dataFromUI[i][j] = replaceSymbols(data.getText());
				} else {
					dataFromUI[i][j] = data.getText();
				}
			}
		}
		String statusXpath = "//tbody/tr[1]/td[10]";
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		if (usrData.get("STATUS").equalsIgnoreCase("Success")
				|| usrData.get("STATUS").equalsIgnoreCase("Queued to Success")) {
			Assert.assertEquals(statusData.getText(),
					usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID") ? "TXN_SUCCESS" : "SUCCESS");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Auto-Refunded")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
			Assert.assertEquals(statusData.getText(), "91");
		} else if (usrData.get("STATUS").equalsIgnoreCase("To_Be_Refunded")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSAL_INITIATED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Late-Refunded")
				|| usrData.get("STATUS").equalsIgnoreCase("Queued to Fail")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Queued")) {
			Assert.assertEquals(statusData.getText(), "TXN_INQUEUE");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(statusData.getText(), "TXN_FAIL");
		}
		System.out.println(statusData.getText());

		List<String> listFromUI = new ArrayList<String>();
		for (String[] array : dataFromUI) {
			listFromUI.addAll(Arrays.asList(array));
		}
		/*
		 * for (String t : listFromUI) { System.out.print(t + " | "); }
		 * System.out.println();
		 */

		List<String[]> list = new ArrayList<String[]>();
//		List<String[]> mtStatusEnquiry = dbUtils.mtStatusEnquiry(txnID);
//		list = mtStatusEnquiry;

		List<String> listFromDB = new ArrayList<String>();
		for (String[] data : list) {
			for (String s : data) {
				// System.out.print(s + " | ");
				listFromDB.add(s);
			}
		}
		// System.out.println();

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
		for (String u : listFromUI) {
			System.out.print(u + " | ");
		}
		System.out.println();
	}
}
