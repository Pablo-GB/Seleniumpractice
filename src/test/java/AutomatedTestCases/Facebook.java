package AutomatedTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Facebook {

	public String path= System.getProperty("user.dir");
	public WebDriver driver;
	public String url="https://es-la.facebook.com/";
	public WebElement email;
	public WebElement password;
	public WebElement btnlogin;
	
	
	@BeforeMethod
	public void setBrowser()
	{
	System.setProperty("webdriver.chrome.driver", path+"\\drivers\\chromedriver.exe");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(url);
	}
	
	@Test
	public void loginSuccess()
	{
	email= driver.findElement(By.id("email"));
	email.sendKeys("sabri_garay@hotmail.com");
	password= driver.findElement(By.id("pass"));
	password.sendKeys("sabrinita15");
	btnlogin= driver.findElement(By.id("u_0_2"));
	btnlogin.click();
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	String title= driver.getTitle();
	Assert.assertTrue(title.contains("Facebook"));
	}
	
	@Test
	public void loginFailed()
	{
	email= driver.findElement(By.id("email"));	
	email.sendKeys("sabri_garay@hotmail.com");
	password= driver.findElement(By.id("pass"));
	password.sendKeys("sabrina");
	btnlogin= driver.findElement(By.id("u_0_2"));
	btnlogin.click();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	WebElement message= driver.findElement(By.xpath("//*[@id='globalContainer']/div[3]/div/div/div"));
	String wrongpass=message.getText();
	Assert.assertTrue(wrongpass.contains("La contraseña que ingresaste es incorrecta"));
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	}
	

