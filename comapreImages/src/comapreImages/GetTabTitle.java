package comapreImages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class GetTabTitle 
{

	public static void main(String args[])
	{
		
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://yahooo.com");
		System.out.println(driver.getTitle());
		
     
		
		
	}
	
	
	
	
}
