package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for SignOnPage 
 */
public class LoginPage {
	
	// Text boxes
	public static final By txtUsername = By.name("userName");
	public static final By txtPassword = By.name("password");
	
	// Buttons
	public static final By btnLogin = By.name("login");
	
	public static By overlaycloselayout = By.xpath("//*[@id='welcomeOverlay']");
	public static By overlaycloselayoutbutton = By.xpath("//*[@id='welcomeOverlay']/div/a/span[1]");
	
	public static final By login_link = By.xpath("//div[@class='menu-top-right']/span/a");
	
	public static final By gotit = By.id("okayGotIt");
	
	public static final By login_button = By.xpath("//div[@class='containerButton']/input");
	
	public static final By uname = By.name("j_username");
	
	public static final By pwd = By.name("j_password");
}