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

public class ReportsPage extends BasePage {
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DBUtils dbUtils = new DBUtils();

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//label[contains(text(),'Select Report to View')]")
	WebElement reportsPage;

	@FindBy(xpath = "//span[contains(text(),'Money Transfer - Status Enquiry')]")
	WebElement reportsDropdown;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropDownSearch;

	@FindBy(xpath = "//li[contains(text(),'Money Transfer - Queued Transactions')]")
	WebElement queuedTxnsDropDown;

	@FindBy(xpath = "//li[contains(text(),'Money Transfer - Banks in Queue')]")
	WebElement banksInQueueDropDown;

	@FindBy(xpath = "//li[contains(text(),'Money Transfer - Timeout')]")
	WebElement timeoutDropDown;

	@FindBy(xpath = "//li[contains(text(),'Refund Report')]")
	WebElement refundReportDropDown;

	@FindBy(xpath = "//th[contains(text(),'TXN DATE')]")
	WebElement txnDateColumn;

	@FindBy(xpath = "//th[contains(text(),'txn date')]")
	WebElement txndateColumn;

	@FindBy(xpath = "//th[contains(text(),'bank name')]")
	WebElement bankNameColumn;

	@FindBy(xpath = "//tbody/tr[1]/td[1]")
	WebElement body;

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(id = "wallet-type")
	WebElement walletType;

	@FindBy(id = "money-transfer-customer-startDate")
	WebElement startDate;

	@FindBy(id = "money-transfer-customer-endDate")
	WebElement endDate;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submit;

	public ReportsPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	public void reports(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		try {
			clickElement(menu);
			scrollElementDown(scrollBar, reports);
			System.out.println("Reports option clicked");
			waitUntilElementIsVisible(reportsPage);
			System.out.println("Reports page displayed");
			waitUntilElementIsVisible(reportsDropdown);
			clickElement(menu);

			if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Queued Transactions")
					&& !usrData.get("STATUS").equalsIgnoreCase("INQUEUE")) {
				dbUtils.updateBatchStatus("DisableRemittanceQueuing", "SUCCESS");
				Thread.sleep(10000);
			}
			if (usrData.get("STATUS").equalsIgnoreCase("UNKNOWN") && usrData.get("PARTNER").equalsIgnoreCase("RBL")) {
				dbUtils.updateRBLTxnStatus(dbUtils.selectPaymentRefCode());
			}

			waitUntilElementIsClickableAndClickTheElement(reportsDropdown);
			System.out.println("Drop down clicked");
			waitUntilElementIsClickableAndClickTheElement(dropDownSearch);
			dropDownSearch.sendKeys(usrData.get("REPORTTYPE"));
			System.out.println("Typing " + usrData.get("REPORTTYPE"));

			String reportXpath = "//li[contains(text(),'" + usrData.get("REPORTTYPE") + "')]";
			WebElement reportDropDown = wdriver.findElement(By.xpath(reportXpath));
			reportDropDown.click();
			System.out.println(usrData.get("REPORTTYPE") + " drop down selected");
			if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Timeout")
					|| (usrData.get("REPORTTYPE").equalsIgnoreCase("Refund Report")
							|| (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")))) {
				if (usrData.get("STATUS").equalsIgnoreCase("withdrwal")) {
					Select wallet = new Select(walletType);
					wallet.selectByVisibleText("Cashout Wallet");
					System.out.println("Cashout wallet selected");
				} else {
					System.out.println("Main wallet remain selected");
				}
				waitUntilElementIsVisible(startDate);
				startDate.sendKeys(currentDate());
				System.out.println(currentDate() + " entered");
				endDate.sendKeys(todayDate());
				System.out.println(todayDate() + " entered");
				submit.click();
				System.out.println("Submit button clicked");
				Thread.sleep(3000);
			}
			commonUtils.waitForSpinner();
			if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Banks in Queue")) {
				waitUntilElementIsVisible(bankNameColumn);
			} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")) {
				waitUntilElementIsVisible(txndateColumn);
			}
			Thread.sleep(2000);
			if (body.getText().equalsIgnoreCase("No Record Found")) {
				System.out.println(body.getText());
			} else {
				reportsData(usrData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		List<WebElement> rowsXpath = wdriver.findElements(By.xpath("//tbody/tr"));
		int rowCount = rowsXpath.size();
		System.out.println("Total no. of rows are " + rowCount);

		if (!usrData.get("STATUS").equalsIgnoreCase("ALL")) {
			rowCount = 1;
		}

		List<WebElement> columnsXpath = wdriver.findElements(By.xpath("//thead/tr/th"));
		int columnCount = columnsXpath.size();
		System.out.println("Total no. of columns are " + columnCount);

		String[][] dataFromUI = new String[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				String dataXpath = "//tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]";
				WebElement data = wdriver.findElement(By.xpath(dataXpath));
				if (j == 5 || j == 6 || j == 7 || j == 8 || j == 9) {
					dataFromUI[i][j] = replaceSymbols(data.getText());
				} else if (j == 1 && usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")) {
					dataFromUI[i][j] = data.getText().substring(0, 5);
				} else {
					dataFromUI[i][j] = data.getText();
				}
			}
		}

		if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Banks in Queue")
				&& usrData.get("STATUS").equalsIgnoreCase("INQUEUE")) {
			Assert.assertEquals(dataFromUI[0][columnCount - 1], "NA");
		} else if (!usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& !usrData.get("STATUS").equalsIgnoreCase("Date")) {
			Assert.assertEquals(dataFromUI[0][columnCount - 1], usrData.get("STATUS").toUpperCase());
		} else {
			if (usrData.get("STATUS").equalsIgnoreCase("withdrwal")) {
				try {
					Assert.assertEquals(replaceSymbols(dataFromUI[0][columnCount - 1] + "0000"),
							dbUtils.closingBalance(mobileNumFromIni(), "CASH_OUT_WALLET_ACCOUNT_NUMBER"));
				} catch (AssertionError e) {
					Assert.assertEquals(replaceSymbols(dataFromUI[0][columnCount - 1] + "00000"),
							dbUtils.closingBalance(mobileNumFromIni(), "CASH_OUT_WALLET_ACCOUNT_NUMBER"));
				}
			} else if (usrData.get("STATUS").equalsIgnoreCase("Deposit")) {
				try {
					Assert.assertEquals(replaceSymbols(dataFromUI[0][columnCount - 1] + "0000"),
							dbUtils.closingBalance(mobileNumFromIni(), "WALLET_ACCOUNT_NUMBER"));
				} catch (AssertionError e) {
					Assert.assertEquals(replaceSymbols(dataFromUI[0][columnCount - 1] + "00000"),
							dbUtils.closingBalance(mobileNumFromIni(), "WALLET_ACCOUNT_NUMBER"));
				}
			}
		}

		List<String> listFromUI = new ArrayList<String>();
		for (String[] array : dataFromUI) {
			listFromUI.addAll(Arrays.asList(array));
		}

		List<String[]> list = new ArrayList<String[]>();

		if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Queued Transactions")) {
			List<String[]> queuedTxnList = dbUtils.queuedTxnReport(mobileNumFromIni(), 10);
			list = queuedTxnList;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Banks in Queue")) {
			List<String[]> queuedBankList = dbUtils.queuedBankReport();
			list = queuedBankList;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Money Transfer - Timeout")) {
			List<String[]> timeoutList = new ArrayList<String[]>();
			if (usrData.get("PARTNER").equalsIgnoreCase("RBL")) {
				timeoutList = dbUtils.timeoutReport(mobileNumFromIni(), "SUCCESS");
			} else {
				timeoutList = dbUtils.timeoutReport(mobileNumFromIni(), "UNKNOWN");
			}
			list = timeoutList;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Refund Report")) {
			List<String[]> refundList = dbUtils.refundReport(mobileNumFromIni());
			list = refundList;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& usrData.get("STATUS").equalsIgnoreCase("MT")) {
			if (getPartner("GetPartner").equalsIgnoreCase("RBL")) {
				List<String[]> accountStatementMT = dbUtils.accountStatementMT(mobileNumFromIni());
				list = accountStatementMT;
			} else if (getPartner("GetPartner").equalsIgnoreCase("FINO")) {
				List<String[]> accountStatementMT = dbUtils.accountStatementMTFt(mobileNumFromIni());
				list = accountStatementMT;
			}
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& usrData.get("STATUS").equalsIgnoreCase("BEN_VAL")) {
			List<String[]> accountStatementBV = dbUtils.accountStatementBV(mobileNumFromIni());
			list = accountStatementBV;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& (usrData.get("STATUS").equalsIgnoreCase("Deposit")
						|| usrData.get("STATUS").equalsIgnoreCase("withdrwal"))) {
			List<String[]> accountStatementAEPS = dbUtils.accountStatementAEPS(mobileNumFromIni(),
					usrData.get("STATUS"));
			list = accountStatementAEPS;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& usrData.get("STATUS").contains("CMS")) {
			List<String[]> accountStatementCMS = dbUtils.accountStatementCMS(mobileNumFromIni());
			list = accountStatementCMS;
		} else if (usrData.get("REPORTTYPE").equalsIgnoreCase("Account Statement")
				&& usrData.get("STATUS").contains("Electricity")) {
			List<String[]> accountStatementBP = dbUtils.accountStatementBP(mobileNumFromIni());
			list = accountStatementBP;
		}
		List<String> listFromDB = new ArrayList<String>();
		for (String[] data : list) {
			for (String s : data) {
				listFromDB.add(s);
			}
		}
		HashMap<String, String> map = new HashMap<String, String>();
		Iterator<String> iUI = listFromUI.iterator();
		Iterator<String> iDB = listFromDB.iterator();
		while (iUI.hasNext() && iDB.hasNext()) {
			map.put(iUI.next(), iDB.next());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Assert.assertEquals(entry.getKey(), entry.getValue());
		}
		for (int i = 0; i < listFromUI.size(); i++) {
			if (i != 0 && i % columnCount == 0) {
				System.out.println();
			}
			System.out.print(listFromUI.get(i) + " | ");
		}
		System.out.println();
	}
}
