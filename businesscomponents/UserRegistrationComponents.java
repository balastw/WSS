package businesscomponents;

import supportlibraries.*;
import uimap.*;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;


/**
 * Class for storing business components related to the user registration functionality
 * @author Cognizant
 */
public class UserRegistrationComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";
	
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public UserRegistrationComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	
	public void clickRegister() {
		report.updateTestLog("Click Register", "Click on the REGISTER link", Status.DONE);
		driver.findElement(MasterPage.lnkRegister).click();
	}
	
	public void registerUser() {
		// Test Data from excel
		String accountnumber = dataTable.getData("General_Data", "Account Number");
		String postcode = dataTable.getData("General_Data", "Postcode");
		String surname = dataTable.getData("General_Data", "Surname");
		String firstname = dataTable.getData("General_Data", "First Name");
		String emailid = dataTable.getData("General_Data", "Email ID");
		String password = dataTable.getData("General_Data", "Password");
		String fullname = firstname + " " + surname;
		System.out.println(fullname);

		// Closing the Welcome overlay if any
		OverlayClose();

		// Get Header Texts
		GetText(UserRegistrationPage.txtRegisterHeader1, "Header1", "Header 1 is successfully displayed and it is - ");
		GetText(UserRegistrationPage.txtRegisterHeader2, "Header2", "Header 2 is successfully displayed and it is - ");
		GetText(UserRegistrationPage.txtRegisterHeader3, "Header3", "Header 3 is successfully displayed and it is - ");

		// Navigation Header
		GetText(UserRegistrationPage.txtNavigationBar1, "Nav Header1",
				"Navigation Header 1 is successfully displayed and it is - ");
		GetText(UserRegistrationPage.txtNavigationBar2, "Nav Header2",
				"Navigation Header 2 successfully displayed and it is - ");

		// Input Account Number and Postcode
		ClearAction(UserRegistrationPage.fldAccountNumber, null, null);
		SendKeysAction(UserRegistrationPage.fldAccountNumber, accountnumber, "Account no field",
				"Account Number field is updated with - ");

		ClearAction(UserRegistrationPage.fldPostcode, null, null);
		SendKeysAction(UserRegistrationPage.fldPostcode, postcode, "Postcode field",
				"Postcode field is updated with - ");

		ClickAction(UserRegistrationPage.btnFindNow, "Find Now", "FIND NOW button clicked successfully");

		SleepCommand(500);

		// Input Surname
		ClearAction(UserRegistrationPage.fldSurname, null, null);
		SendKeysAction(UserRegistrationPage.fldSurname, surname, "Surname field", "Surname field is updated with - ");

		ClickAction(UserRegistrationPage.btnFindNow, "Find Now", "FIND NOW button clicked successfully");

	}
	
	public void verifyRegistration() {
		String userName = dataTable.getData(GENERAL_DATA, "Username");
		
		if(driverUtil.isTextPresent("^[\\s\\S]*Dear " +
					dataTable.getExpectedResult("FirstName") + " " +
					dataTable.getExpectedResult("LastName") + "[\\s\\S]*$")) {
			report.updateTestLog("Verify Registration",
										"User " + userName + " registered successfully", Status.PASS);
		} else {
			throw new FrameworkException("Verify Registration",
											"User " + userName + " registration failed");
		}
	}
	
	public void clickSignIn() {
		report.updateTestLog("Click sign-in", "Click the sign-in link", Status.DONE);
		driver.findElement(UserRegistrationConfirmationPage.lnkSignIn).click();
	}
}