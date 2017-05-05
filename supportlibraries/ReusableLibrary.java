package supportlibraries;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cognizant.framework.CraftDataTable;
import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Settings;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.CraftDriver;
import com.cognizant.framework.selenium.SeleniumReport;
import com.cognizant.framework.selenium.WebDriverUtil;

import uimap.LoginPage;



/**
 * Abstract base class for reusable libraries created by the user
 * @author Cognizant
 */
public abstract class ReusableLibrary {
	/**
	 * The {@link CraftDataTable} object (passed from the test script)
	 */
	protected CraftDataTable dataTable;
	/**
	 * The {@link SeleniumReport} object (passed from the test script)
	 */
	protected SeleniumReport report;
	/**
	 * The {@link CraftDriver} object
	 */
	protected CraftDriver driver;

	protected WebDriverUtil driverUtil;
	
	/**
	 * The {@link ScriptHelper} object (required for calling one reusable library from another)
	 */
	protected ScriptHelper scriptHelper;
	
	/**
	 * The {@link Properties} object with settings loaded from the framework properties file
	 */
	protected Properties properties;
	/**
	 * The {@link FrameworkParameters} object
	 */
	protected FrameworkParameters frameworkParameters;
	 
	
	/**
	 * Constructor to initialize the {@link ScriptHelper} object and in turn the objects wrapped by it
	 * @param scriptHelper The {@link ScriptHelper} object
	 */
	public ReusableLibrary(ScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;
		this.dataTable = scriptHelper.getDataTable();
		this.report = scriptHelper.getReport();
		this.driver = scriptHelper.getcraftDriver();
		this.driverUtil = scriptHelper.getDriverUtil();
		
		properties = Settings.getInstance();
		frameworkParameters = FrameworkParameters.getInstance();
	}
	String text;	
	// Get Text By Locator value 
	public String GetText(By locator, String report_Header_field, String report_desc_field){
		try {
			text = driver.findElement(locator).getText();
			if(report_Header_field != null || report_Header_field == ""){
			report.updateTestLog(report_Header_field, report_desc_field + text , Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog(report_Header_field, "Exception" , Status.FAIL);
		}
		return text;
	}
	
	// Get Text By Locator value 
		public void TextVerification(By locator, String texttoverify, String report_Header_field, String report_desc_field){
		try {
			String text = driver.findElement(locator).getText();
			if(text.equalsIgnoreCase(texttoverify)){
				report.updateTestLog(report_Header_field, report_desc_field + text , Status.PASS);
			}else{
				report.updateTestLog(report_Header_field, "Mismatch in text. The text is -" +text , Status.PASS);
			}
			} catch (Exception e) {
				report.updateTestLog(report_Header_field, "Exception" , Status.FAIL);
			}
		}
	
	// Click on a WebElement By Locator value
	public void ClickAction(By locator, String report_Header_field, String report_desc_field){
		try {
			driver.findElement(locator).click();
			report.updateTestLog(report_Header_field, report_desc_field , Status.PASS);
		} catch (Exception e) {
			report.updateTestLog(report_Header_field, "Exception" , Status.FAIL);
		}	
	}

	// Clear a Field By Locator value
	public void ClearAction(By locator, String report_Header_field, String report_desc_field){
		try {
			driver.findElement(locator).clear();
			if(report_Header_field != null || report_Header_field == ""){
				report.updateTestLog(report_Header_field, report_desc_field , Status.PASS);	
			}
			
			} catch (Exception e) {
			report.updateTestLog(report_Header_field, "Exception" , Status.FAIL);
			}
	}
	
	// Send Keys to a WebElement By Locator value
	public void SendKeysAction(By locator, String input, String report_Header_field, String report_desc_field){
		try {
			driver.findElement(locator).sendKeys(input);
			report.updateTestLog(report_Header_field, report_desc_field + input , Status.PASS);
			} catch (Exception e) {
			report.updateTestLog(report_Header_field, "Exception" , Status.FAIL);
			}
		
	}
	
	// Sleep Command
	public void SleepCommand(int milliseconds){
		try {
		Thread.sleep(milliseconds);
		} catch (Exception e) {
		report.updateTestLog("Sleep Command", "Sleep Command Exception", Status.FAIL);
		}
		
	}
	
	public void OverlayClose(){
	WebElement overlayinner = driver.findElement(LoginPage.overlaycloselayout);
	if (overlayinner.isDisplayed()){
	ClickAction(LoginPage.overlaycloselayoutbutton, "Overlay", "Welcome Overlay closed successfully");
	}
	SleepCommand(1000);
	
	}
	
	
	
}