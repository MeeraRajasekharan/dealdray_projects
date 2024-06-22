package Task_Dealsdry;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import com.google.common.io.Files;

public class Function_testing {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub
		EdgeDriver driver= new EdgeDriver ( );
		driver.get("https://demo.dealsdray.com/");
		driver.manage().window().maximize();

		driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Order']")).click();

		Thread.sleep(1000);
		driver.findElement(By.linkText("Orders")).click();
		Thread.sleep(3000);
		WebElement hiddenButton = driver.findElement(By.cssSelector("div.css-11evj4h button.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-sizeMedium.css-adjfm3")); // Replace with your actual locator

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", hiddenButton);
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-uodm64']")).click();

		Thread.sleep(3000);

		Robot robot =new Robot();
		StringSelection stringSelection = new StringSelection("C:\\Users\\hrish\\Downloads\\demo-data.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Import']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		File f =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f, new File("C:\\Users\\hrish\\eclipse-workspace\\Dealsdray_task\\screenshots\\TASK2\\validate.png"));

		Thread.sleep(3000);
		driver.quit();
	}

}
