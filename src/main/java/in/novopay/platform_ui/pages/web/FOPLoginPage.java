package in.novopay.platform_ui.pages.web;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;

public class FOPLoginPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);

	public FOPLoginPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(id = "userName")
	WebElement userName;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//button[contains(text(),'LOGIN')]")
	WebElement login;

	public void fOPLoginPage(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {

		System.out.println("Retailer WebApp 2.0 Login page displayed");

		try {
			openNewTab("FinOps Portal", "");

			waitUntilElementIsClickableAndClickTheElement(userName);
			userName.clear();
			System.out.println("entering username");
			userName.sendKeys(usrData.get("USERNAME"));

			waitUntilElementIsClickableAndClickTheElement(password);
			password.clear();
			System.out.println("entering password");
			password.sendKeys(usrData.get("PASSWORD"));

			System.out.println("clicking on LOGIN button");
			waitUntilElementIsClickableAndClickTheElement(login);

			commonUtils.waitForSpinner();

			waitUntilElementIsVisible(menu);
			System.out.println("Page displayed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}
}
