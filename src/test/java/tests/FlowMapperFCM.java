package tests;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.JavaUtils;
import in.novopay.platform_ui.utils.Log;

public class FlowMapperFCM {
	public WebDriver wdriver;
	private Set<String> flows;
	private Map<String, String> usrData;
	private DBUtils dbUtils = new DBUtils();
	private JavaUtils javaUtils = new JavaUtils();
	private BasePage wBasePage = new BasePage(wdriver);
	private CommonUtils commonUtils = new CommonUtils(wdriver);
	private Object obj;
	private String sheetName = "FLOWMAPPERFCM";
	private String workbook = "WebAppUITestData";
	private String currentPackage = "", classNameWithPackage, pack;
	private String errMsg, stepNo = "", className = "", testCaseID = "";

	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
	}

	@Test(dataProvider = "getData")
	public void flowMapperTest(HashMap<String, String> usrData) throws Throwable {
		this.usrData = usrData;
		Log.info("Executing --> " + usrData.get("TCID"));
		String contract = usrData.get("CONTRACT");
		javaUtils.getContract(contract);
		if (!contract.equalsIgnoreCase("-")) {
			dbUtils.modifyContract(contract, javaUtils.getLoginMobileFromIni("RetailerMobNum"));
		}
		if (usrData.get("FEATURE").equalsIgnoreCase("EKYC")) {
			dbUtils.updateAepsPartner("RBL", mobileNumFromIni());
			dbUtils.updateRBLEKYCStatus("PENDING", mobileNumFromIni());
			dbUtils.updateDmtBcAgentId("NOV2160858", mobileNumFromIni());
		} else if (!usrData.get("FEATURE").equalsIgnoreCase("-")) {
			dbUtils.updateRBLEKYCStatus("APPROVED", mobileNumFromIni());
			dbUtils.updateDmtPartner("RBL", mobileNumFromIni());
			dbUtils.updateAepsPartner("RBL", mobileNumFromIni());
			if (usrData.get("FEATURE").equalsIgnoreCase("Money Transfer")) {
				dbUtils.updateDmtBcAgentId("NOV1000704", mobileNumFromIni());
			} else if (usrData.get("FEATURE").equalsIgnoreCase("Banking")) {
				dbUtils.updateAepsPartner(contract, mobileNumFromIni());
				if (contract.equalsIgnoreCase("RBL")) {
					commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_TERMINAL_ID", "567765667126757");
					commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_AGENT_ID", "NOV112200");
					commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_DEVICE_ID", "UP000102");
					commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_AGENT_PASSWORD",
							"40bd001563085fc35165329ea1ff5c5ecbdbbeef");
				} else if (contract.equalsIgnoreCase("YBL")) {
					commonUtils.verifyAndInsertValueInOrgAttribute("YBL_REMITTANCE_TUTORIAL_WATCHED", "YES");
					commonUtils.verifyAndInsertValueInOrgAttribute("YBL_AEPS_USER_ID", "42");
				}
			} else if (contract.equalsIgnoreCase("CMS")) {
				javaUtils.cmsDetailsFromIni("StoreCmsBiller", usrData.get("FEATURE"));
			} else if (contract.equalsIgnoreCase("BILLPAY")) {
				javaUtils.billpayDataFromIni("StoreBillpayCategory", usrData.get("FEATURE"));
				if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
					commonUtils.verifyAndInsertValueInOrgAttribute("BILL_AVENUE_AGENT_ID", "123456");
				} else if (usrData.get("VENDOR").equalsIgnoreCase("AXIS")) {
					commonUtils.verifyAndInsertValueInOrgAttribute("AXIS_BBPS_AGENT_ID", "1234567");
				}
			}
		}

		javaUtils.getWalletFromIni("StoreWallet", usrData.get("WALLET"));

		for (String flowTestID : flows) {
			if ((!usrData.get(flowTestID).equalsIgnoreCase("SKIP")) && (!usrData.get(flowTestID).isEmpty())) {
				testCaseID = usrData.get(flowTestID);
				currentPackage = getClass().getPackage().getName();
				className = testCaseID.split("_")[0];
				classNameWithPackage = currentPackage + ".api." + className;
				Class<?> flow = null;
				stepNo = flowTestID;

				try {
					flow = Class.forName(classNameWithPackage);
					pack = "api";
				} catch (ClassNotFoundException e) {
					classNameWithPackage = currentPackage + ".ui." + className;
					flow = Class.forName(classNameWithPackage);
					pack = "ui";
				}

				String pattern = Character.toLowerCase((className + "Test").charAt(0))
						+ (className + "Test").substring(1, (className).length());
				Pattern r = Pattern.compile(pattern);

				try {
					obj = flow.getDeclaredConstructor().newInstance();
					Method[] method = obj.getClass().getDeclaredMethods();
					for (int i = 0; i < method.length; i++) {
						String message = method[i].toString();
						Matcher m = r.matcher(message);
						if (m.find()) {
							String sheetname = obj.getClass().getDeclaredField("sheetname").get(obj).toString();
							String workbook = obj.getClass().getDeclaredField("workbook").get(obj).toString();
							// write if condition to switch if api class is
							if (pack.equals("api")) {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));
								method[i].invoke(obj, data);
							} else {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));
								Field webDriver = obj.getClass().getDeclaredField("wdriver");
								webDriver.set(obj, wdriver);
								method[i].invoke(obj, data);
								wdriver = (WebDriver) webDriver.get(obj);
							}
						}
					}
				} catch (IllegalAccessException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (SecurityException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (InstantiationException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (WebDriverException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (Exception e) {
					wdriver.navigate().refresh();
					throw e.getCause();
				}
			}
		}
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] data = javaUtils.returnAllUniqueValuesInMap(workbook, sheetName, "no-check");
		if (data.length != 0) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> datamap = (HashMap<String, String>) data[0][0];
			flows = new TreeSet<>(datamap.keySet().stream().filter(s -> s.toLowerCase().startsWith("step"))
					.collect(Collectors.toSet()));
		}
		return data;
	}

	@AfterClass
	public void killDriver() {
		if (wdriver != null) {
			wBasePage.closeBrowser();
		}
	}

	// STORING EXECUTION RESULTS IN EXCEL
	@AfterMethod
	public void result(ITestResult result) throws InvalidFormatException, IOException, ClassNotFoundException {
		String failureReason = "";
		String testStartTime = javaUtils.getTestExcutionTime(result.getStartMillis());
		String testEndTime = javaUtils.getTestExcutionTime(result.getEndMillis());
		wBasePage.captureScreenshotOnFailedTest(result, testCaseID);
		if (!result.isSuccess()) {
			failureReason = errMsg;
			failureReason = stepNo + ": " + testCaseID + ": " + result.getThrowable() + "";
		}
		String[] execeutionDtls = { usrData.get("TCID"), usrData.get("DESCRIPTION"), usrData.get("CONTRACT"),
				usrData.get("FEATURE"), usrData.get("VENDOR"), javaUtils.getExecutionResultStatus(result.getStatus()),
				failureReason, testStartTime, testEndTime };
		javaUtils.writeExecutionStatusToExcel(execeutionDtls);
		if (!usrData.get("CONTRACT").equalsIgnoreCase("-")) {
			System.out.println("Inserting all contracts");
			dbUtils.insertContract(javaUtils.getLoginMobileFromIni("RetailerMobNum"));
		}
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return dbUtils.getLoginMobileFromIni("RetailerMobNum");
	}
}