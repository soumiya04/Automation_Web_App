package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
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

public class SupportRequestPage extends BasePage {
    DBUtils dbUtils = new DBUtils();
    CommonUtils commonUtils = new CommonUtils(wdriver);

    @FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
    WebElement menu;
    @FindBy(xpath = "//*[@class='slimScrollBar']")
    WebElement scrollBar;
    @FindBy(xpath = "//span[text()='Support request']")
    WebElement supportRequest;
    @FindBy(xpath = "//h1[@class='page-title d-inline-block mb-16']")
    WebElement supportrequestpageTitle;
    @FindBy(xpath = "//input[@name='supportRequestEmailId']")
    WebElement emailIdField;
    @FindBy(xpath = "//select2[@id='categoryDropodwn']//span[@class='select2-selection__arrow']")
    WebElement categorydropdownmenu;
    @FindBy(xpath = "//ul[@class='select2-results__options']//li[text()='HDFC Digital Distribution Platform']")
    WebElement chooseHDFCDDPoption;
    @FindBy(xpath = "//select2[@name='sub-category-dropDown']//span[@class='select2-selection__arrow']")
    WebElement subCategoryDropdownMenu;
    @FindBy(xpath = "//ul[@class='select2-results__options']//li[text()='Know more about HDFC DDP']")
    WebElement hdfcDdpOption;
    @FindBy(xpath = "//label[@class='label-name font-15' and normalize-space(text())='Description (max of 1000 Characters)']")
    WebElement textTitle;
    @FindBy(xpath = "//textarea[@maxlength='1000']")
    WebElement textArea;
    @FindBy(xpath = "//button[@class='button button-primary' and @type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//button[@class='button button-basic mr-8' and @type='button']")
    WebElement clearButton;
    @FindBy(xpath = "//div[@class='modal-header ng-star-inserted completed']")
    WebElement successScreen;
    @FindBy(xpath = "//button[@class='button button-primary ng-star-inserted' and @role='button']")
    WebElement okButton;

    // Load all objects
    public SupportRequestPage(WebDriver wdriver) {
        super(wdriver);
        PageFactory.initElements(wdriver, this);
    }

    public void supportRequest(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
        try {
            clickElement(menu);
            //scrollElementDown(scrollBar, supportRequest);
            scrollToElement(supportRequest);
         waitUntilElementIsClickableAndClickTheElement(supportRequest);            
            System.out.println("supportRequest option clicked");
            waitUntilElementIsVisible(supportrequestpageTitle);
            System.out.println("Support page displayed");

            // Click on Email id field
            waitUntilElementIsClickableAndClickTheElement(emailIdField);
            emailIdField.clear();
            // Enter email id
            String emailId = usrData.get("EMAILID");
            if (emailId != null && !emailId.isEmpty()) {
                emailIdField.sendKeys(emailId);
                System.out.println("Email ID entered");
            } else {
                throw new IllegalArgumentException("Email ID is null or empty");
            }

            // Click on category dropdown
            waitUntilElementIsClickableAndClickTheElement(categorydropdownmenu);
            System.out.println("categorydropdownmenu selected");
            // Select HDFC DDP option
            waitUntilElementIsClickableAndClickTheElement(chooseHDFCDDPoption);
                        System.out.println("choose HDFC option selected");
            
            // Click on sub-category dropdown
            waitUntilElementIsClickableAndClickTheElement(subCategoryDropdownMenu);
                System.out.println("subCategoryDropdownMenu selected");
            
            // Select Know more about HDFC DDP option
            waitUntilElementIsClickableAndClickTheElement(hdfcDdpOption);
             System.out.println("hdfc selected");
            
            //Show the description page
 	           waitUntilElementIsVisible(textTitle);
 	           System.out.println("Description page displayed");
            // Enter text in text area
            String text = usrData.get("TEXT");
            if (text != null && !text.isEmpty()) {
                textArea.sendKeys(text);
                System.out.println("Text entered");
            } else {
                throw new IllegalArgumentException("Text to enter is null or empty");
            }

            // Click on submit button
            waitUntilElementIsClickableAndClickTheElement(submitButton);
            // Verify success screen
            waitUntilElementIsVisible(successScreen);
            // Click on ok button
            waitUntilElementIsClickableAndClickTheElement(okButton);
            System.out.println("Support request submitted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Case Failed");
            Assert.fail();
        }
    }
}