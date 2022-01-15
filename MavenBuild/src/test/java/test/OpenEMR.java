package test;

import java.time.Duration;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenEMR {
	
@Test //(priority=1)write this so that test cases execute in this order according to the priority.
	
	public void createPatient() throws InterruptedException{
WebDriverManager.chromedriver().setup();//THIS IS THE ONLY CHANGE THAT NEEDS TO BE MADE FOR MAVEN PROJECT FOR THE LIBRARIES WE ALREADY DOWNLOADED(WEBDRIVERMANAGER)
		 WebDriver driver=new ChromeDriver();
		 Actions act=new Actions(driver);
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 
		 driver.get("https://demo.openemr.io/a/openemr");	
	
	     driver.findElement(By.id("authUser")).sendKeys("admin");
	     driver.findElement(By.id("clearPass")).sendKeys("pass");
	     driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/div[1]/div[4]/button")).click();
	     act.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/div"))).perform();
	    driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/ul/li[2]/div")).click();
	    driver.switchTo().frame(driver.findElement(By.name("pat")));
	
		Select sal=new Select(driver.findElement(By.id("form_title")));
		sal.selectByValue("Mr.");
		
		driver.findElement(By.id("form_fname")).sendKeys("John");
		driver.findElement(By.id("form_lname")).sendKeys("Smith");
		
		
		driver.findElement(By.id("form_DOB")).sendKeys("2022-01-11");// In alternate to send Keys this can be written as below as well:
		//driver.findElement(By.className("xdsoft_today")).click(); For today's date
		//driver.findElement(By.id("form_DOB")).click();
		Thread.sleep(1000);
		//driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/tbody/tr[3]/td[3]/div")).click();
			
		Select gender=new Select(driver.findElement(By.id("form_sex")));
		gender.selectByVisibleText("Male");		
		
		driver.findElement(By.id("create")).click();
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.id("modalframe")));
	    driver.findElement(By.className("btn-primary")).click();
	    driver.switchTo().defaultContent();
	    
	    wait.until(ExpectedConditions.alertIsPresent());
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    
	    //Thread.sleep(1000);
	    //driver.switchTo().frame(driver.findElement(By.id("modalframe")));//Logout inner frame
	    //driver.findElement(By.className(""))
	    //driver.switchTo().alert().dismiss();
	    
	    driver.findElement(By.xpath("//*[@id=\"bdayreminder\"]/div/div/div[1]")).click();
	    act.moveToElement(driver.findElement(By.id("username"))).perform();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@id=\"userdropdown\"]/li[4]")).click();
	    
	//@Test(priority=5,invocationCount=4)  So that this test case can run multiple times and it will considered as 4 test cases and each test case is considered as fail/pass.  
	    //public void physicianLogin() {
}
	    
			
	
	}

	
