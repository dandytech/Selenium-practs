package Greenmouse.CaptureTestFailed;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFailure {

	public WebDriver driver;

	@BeforeMethod
	public WebDriver setUp() {
		// Set up WebDriver and other configurations
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver = new ChromeDriver(options);
		return driver;
	}

	@Test
	public void testMethod() {
		// Test code goes here
		driver.get("https://donandchyke.com.ng/");
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("greenmouseapp+assmgDan@gmail.com");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//label[text()='Remember Me']")).click();
		driver.findElement(By.xpath("//button[text()='Logi']")).click();
	
	}
	//login is wrong



	private void captureScreenshot(String screenshotName) {
			try {
				File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destinationFile = new File("//Users//user//eclipse-workspace//CaptureTestFailed//reports//" + screenshotName + ".png");
				Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Screenshot captured: " + destinationFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result.getName());
		}
		driver.quit();
	}



}
