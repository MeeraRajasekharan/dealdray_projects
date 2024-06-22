package Task_Dealsdry;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.google.common.io.Files;

public class UI_Testing_task {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//listing the urls
		List<String> urls = new ArrayList<>();
		urls.add("https://www.getcalley.com/");
		urls.add("https://www.getcalley.com/calley-call-from-browser/");
		urls.add("https://www.getcalley.com/calley-pro-features/");
		urls.add("https://www.getcalley.com/best-auto-dialer-app/");
		urls.add("https://www.getcalley.com/how-calley-auto-dialer-app-works/");

		//listing the platform
		List<Platform> platforms = new ArrayList<>();
		platforms.add(new Platform("Desktop", 1920, 1080));
		platforms.add(new Platform("Desktop", 1366, 768));
		platforms.add(new Platform("Desktop", 1536, 864));
		platforms.add(new Platform("Mobile", 360, 640));
		platforms.add(new Platform("Mobile", 414, 896));
		platforms.add(new Platform("Mobile", 375, 667));

		//listing browsers
		List<String> browsers = new ArrayList<>();
		browsers.add("chrome");
		browsers.add("firefox");
		browsers.add("edge");
		for (String url : urls) {
			for (Platform platform : platforms) {
				for (String browser : browsers) {
					WebDriver driver = getWebDriver(browser);
					driver.manage().window().setSize(new Dimension(platform.width, platform.height));
					driver.get(url);
					String screenshotFilename = captureScreenshot(driver, platform.name, url);
					System.out.println("Screenshot saved: " + screenshotFilename);
					driver.quit();
				}
			}
		}
	}
	private static WebDriver getWebDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
			return new ChromeDriver();
		case "firefox":
			return new FirefoxDriver();
		case "edge":
			return new EdgeDriver(); 
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
	private static String captureScreenshot(WebDriver driver, String platform, String url) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

		// Build the filename and timestamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateTime = dateFormat.format(new Date());
		String filename = String.format("%s_%s-%s.png", platform, getFilenameFromUrl(url), dateTime);
		// Create a folder structure 
		File folder = new File("screenshots/" + platform + "/" + driver.getClass().getSimpleName()); 
		folder.mkdirs(); 
		File destinationFile = new File(folder, filename);
		Files.copy(screenshotFile, destinationFile);

		return destinationFile.getAbsolutePath();
	}
	private static Object getFilenameFromUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}

class Platform {
	String name;
	int width;
	int height;

	public Platform(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
}



