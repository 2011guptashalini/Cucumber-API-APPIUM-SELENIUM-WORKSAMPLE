package resources;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//Global Variables
	public static RequestSpecification req;
	public static WebDriver driver;
	public static AndroidDriver dr;
	public static Process p;
	public static ProcessBuilder pb;
	
	public  RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURL")).addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
	}
	
	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\gupta\\Desktop\\Upwork\\Automation\\BDD\\groceryStore\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	//Desktop testing by selenium
		
	public WebDriver getDesktopDriver() throws IOException {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chromeOptions);
		return driver;
	}
	
	//Mobile testing by appium
	
	public AndroidDriver getMobileDriver() throws IOException {
		
		//When testing needs to be performed on mobile app, some capabilities will change.
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Nexus 5X API 33");
		cap.setCapability("platformName", "Android"); 
		cap.setCapability("appPackage","com.app.hmde");
		cap.setCapability("appActivity","com.app.hmde.MainActivity");
		dr = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return dr;
	}
	
	public static void startServer() {
	    Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
	        Thread.sleep(10000);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public static void stopServer() throws InterruptedException {
	    Runtime runtime = Runtime.getRuntime();
	    try {
	    	Thread.sleep(10000);
	        runtime.exec("taskkill /F /IM node.exe");
	        runtime.exec("taskkill /F /IM cmd.exe");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void startAndroidEmulator() throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();
	    try {

	    	runtime.exec("cmd.exe /c \"cd C:\\Users\\gupta\\AppData\\Local\\Apps\\Android\\sdk\\emulator \" && emulator -avd Nexus_5X_API_33 ");
	    	   Thread.sleep(20000);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void stopAndroidEmulator() throws InterruptedException, IOException {
	
		Runtime runtime = Runtime.getRuntime(); 
		Thread.sleep(10000);
		   runtime.exec("taskkill /F /IM qemu-system-x86_64.exe");
	       
    	
	}

	
	public static String getLocator(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("C:\\Users\\gupta\\Desktop\\Upwork\\Automation\\BDD\\groceryStore\\src\\test\\java\\locators\\locators.properties");
		prop.load(fis);
		return prop.getProperty(key);
	
		
		
	}

}
