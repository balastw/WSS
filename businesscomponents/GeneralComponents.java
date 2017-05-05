package businesscomponents;


import supportlibraries.*;
import uimap.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Report;
import com.cognizant.framework.Status;
import com.itextpdf.text.log.SysoCounter;


/**
 * Class for storing general purpose business components
 * @author Cognizant
 */
public class GeneralComponents extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public GeneralComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	/**
	 * Method for Invoking the Web Application and Set the Browser settings
	 * @author Bala Vignesh (Cognizant)
	 */
	
	public void invokeApplication() {
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
				properties.getProperty("ApplicationUrl"), Status.SCREENSHOT);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	/**
	 * Method for Online Registration
	 * @dataneeded -  accountnumber, postcode, surname, firstname, emailid & password
	 */
	public void webRegistration(){
		
		// Test Data from excel
		String accountnumber = dataTable.getData("General_Data", "Account Number");
		String postcode  = dataTable.getData("General_Data", "Postcode");
		String surname  = dataTable.getData("General_Data", "Surname");
		String firstname  = dataTable.getData("General_Data", "First Name");
		String emailid  = dataTable.getData("General_Data", "Email ID");
		String password  = dataTable.getData("General_Data", "Password");
		String fullname = firstname + " " + surname;
		System.out.println(fullname);
		
		// Closing the Welcome overlay if any
		OverlayClose();
		
		
		// Verification Headers and Texts 
		TextVerification(UserRegistrationPage.txtRegisterHeader1, "Register", "Registration Header", "Registration Header 1 verified successfully - ");
		TextVerification(UserRegistrationPage.txtRegisterHeader2, "Register", "Registration Header", "Registration Header 2 verified successfully - ");
		TextVerification(UserRegistrationPage.txtRegisterHeader3, "Register now", "Registration Header", "Registration Header 3 verified successfully - ");
		TextVerification(UserRegistrationPage.txtRegisterHeadertxt1, "Manage your Severn Trent Water accounts online. Make payments, change your details, "
				+ "manage your direct debits and much more.", "Registration Header Text", "Registration Header Text 1 verified successfully - ");
		TextVerification(UserRegistrationPage.txtRegisterHeadertxt2, "Add your Severn Trent Water account number without spaces or special characters (for example: 1234567890)", 
				"Registration Header Text", "Registration Header Text 2 verified successfully - ");
		
		// Navigation Header
		String Navheader1 = GetText(UserRegistrationPage.txtNavigationBar1, "Nav Header1", "Navigation Header 1 is successfully displayed and it is - ");
		String Naviheader2 = GetText(UserRegistrationPage.txtNavigationBar2, "Nav Header2", "Navigation Header 2 successfully displayed and it is - ");
		
		String Navigation = Navheader1 + " > " + Naviheader2;
		
		report.updateTestLog("Navigation", "Navigation is - " + Navigation, Status.PASS);
		
		
		// Input Account Number and Postcode
		ClearAction(UserRegistrationPage.fldAccountNumber, null, null);
		SendKeysAction(UserRegistrationPage.fldAccountNumber, accountnumber, "Account no field", "Account Number field is updated with - ");
		
		ClearAction(UserRegistrationPage.fldPostcode,null, null);
		SendKeysAction(UserRegistrationPage.fldPostcode, postcode, "Postcode field", "Postcode field is updated with - ");

		ClickAction(UserRegistrationPage.btnFindNow,"Find Now", "FIND NOW button clicked successfully");
		SleepCommand(1000);
		
		// Input Surname
		ClearAction(UserRegistrationPage.fldSurname,null, null);
		SendKeysAction(UserRegistrationPage.fldSurname, surname, "Surname field", "Surname field is updated with - ");
		
		ClickAction(UserRegistrationPage.btnFindNowsurname,"Find Now", "FIND NOW button clicked successfully");
		SleepCommand(2000);
		
		// Validate surname
		
		/*int element = driver.findElements(UserRegistrationPage.fldChooseSurname).size();
		System.out.println(element);*/
		
		int a = driver.findElements(By.xpath("//*[@id='register-account-legal-entity-form']/div/div/button")).size();;
		System.out.println(a);
	}
	
	public void login() {

		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");
		
		System.out.println(userName);
		System.out.println(password);
		
		SleepCommand(1000);

		//WebElement tour = driver.findElement(LoginPage.overlayclosebutton);
		
		/*if (tour.isDisplayed()){
		tour.click();	
			}*/
		
		SleepCommand(1000);
		System.out.println("Title is - " + driver.getTitle());
		
		driver.findElement(LoginPage.login_link).click();
		report.updateTestLog("Login", "Clicked on Log-in Link", Status.PASS);
		
		SleepCommand(1000);
		
		driver.findElement(LoginPage.gotit).click();
		
		SleepCommand(1000);
		
		driver.findElement(LoginPage.uname).sendKeys(userName);
		driver.findElement(LoginPage.pwd).sendKeys(password);
		
		driver.findElement(LoginPage.login_button).click();
		
		SleepCommand(5000);
		
	}
	
	public void verifyLoginSuccessful() {
		
	}
	
	public void verifyLoginFailed() {
		if(!driverUtil.objectExists(MasterPage.lnkSignOff)) {
			report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
		} else {
			report.updateTestLog("Verify Login", "Login succeeded for invalid user", Status.FAIL);
		}
	}
	
	public void logout() {
		report.updateTestLog("Logout", "Click the sign-off link", Status.DONE);
		driver.findElement(MasterPage.lnkSignOff).click();
	}
}