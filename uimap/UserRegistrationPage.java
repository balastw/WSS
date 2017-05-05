package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class UserRegistrationPage {
	
	// Headers
	
	public static final By txtRegisterHeader1 = By.id("page-title-f789c032-d62a-3f56-8466-66cf41c3a0e2");
	
	public static final By txtNavigationBar1 = By.xpath("//*[@id='breadcrumbs-914b7be5-2173-3757-8c3a-750e686e2ecd']/span[1]/a");
	
	public static final By txtNavigationBar2 = By.xpath("//*[@id='breadcrumbs-914b7be5-2173-3757-8c3a-750e686e2ecd']/span[2]");
	
	public static final By txtRegisterHeader2 = By.xpath("//*[@id='container']/div[2]/div");
	
	public static final By txtRegisterHeader3 = By.xpath("//h1[contains(text(),'Register now')]");
	
	public static final By txtRegisterHeadertxt1 = By.xpath("//p[contains(text(),'Manage your Severn Trent Water accounts online. Make payments, change your details, manage your direct debits and much more.')]");
	
	public static final By txtRegisterHeadertxt2 = By.xpath("//*[@id='register-account-details-form']/div[1]/p");
	
	
	public static final By fldAccountNumber = By.name("account");
		
	public static final By fldPostcode = By.name("postcode");
	
	public static final By btnFindNow = By.xpath("//div[contains(@class,'form-group') and .//text()='Find now']");
	
	public static final By btnFindNowsurname = By.xpath("//*[@id='register-account-surname-form']/div[2]/button");
	
	public static final By fldSurname = By.name("surname");
	
	public static final By fldChooseSurname = By.xpath("//*[@id='register-account-legal-entity-form']//div//button[@class='button button-default']");
	
	
}