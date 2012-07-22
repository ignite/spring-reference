package com.gmail.at.zhuikov.aleksandr.it;

import static org.springframework.util.StringUtils.hasText;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucelabs.selenium.client.factory.SeleniumFactory;

public abstract class AbstractWebDriverTest {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractWebDriverTest.class);
	
	protected WebDriver driver;
	
	protected WebDriver createDriver() {
		
		LOG.info("Creating WebDriver");

		String seleniumDriverUri = System.getenv("SELENIUM_DRIVER");

		if (hasText(seleniumDriverUri)) {
			seleniumDriverUri += "&username=" + System.getenv("SAUCE_ONDEMAND_USERNAME");
			seleniumDriverUri += "&access-key=" + System.getenv("SAUCE_ONDEMAND_ACCESS_KEY");
			System.setProperty("SELENIUM_DRIVER", seleniumDriverUri);
		}
		
		LOG.info("env SELENIUM_DRIVER=" + System.getenv("SELENIUM_DRIVER"));
		LOG.info("sys SELENIUM_DRIVER=" + System.getProperty("SELENIUM_DRIVER"));
		
		return SeleniumFactory.createWebDriver();
	}
	
	@Before
	public void openPage() {
		driver = createDriver();
	}
	
	@After
	public void closePage() {
		driver.quit();
	}
	
	protected String getUniqueCharString() {
		String millis = String.valueOf(System.currentTimeMillis());
	    
	    char[] random = new char[millis.length()];
	    for (int i = 0; i < millis.length(); i++) {
	      random[i] = (char) (millis.charAt(i) - '0' + 'a');
	    }

		return new String(random);
	}
}
