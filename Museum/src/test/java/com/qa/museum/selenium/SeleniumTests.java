package com.qa.museum.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTests {

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	void testCreate() {
		this.driver.get("http://127.0.0.1:5500/index.html");

		WebDriverWait implicitWait = new WebDriverWait(driver, 10);

		WebElement speciesInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[1]");
		speciesInput.sendKeys("Howler Monkey");

		WebElement originInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[2]");
		originInput.sendKeys("Borneo");

		WebElement storageLocationInput = this.driver
				.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[3]");
		storageLocationInput.sendKeys("Primate Display");

		WebElement dateReceivedInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[4]");
		dateReceivedInput.sendKeys("22/09/1997");

		WebElement descriptionInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[5]");
		descriptionInput.sendKeys("Complete Mount");

		descriptionInput.submit();

		// Left in for wait
		WebElement idResult = implicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/td[1]")));

		WebElement table = this.driver.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody");

		Assertions.assertTrue(table.getText().contains("Howler Monkey"));
		Assertions.assertTrue(table.getText().contains("Borneo"));
		Assertions.assertTrue(table.getText().contains("Primate Display"));
		Assertions.assertTrue(table.getText().contains("1997-09-22"));
		Assertions.assertTrue(table.getText().contains("Complete Mount"));
	}

	@Test
	void testUpdate() {
		this.driver.get("http://127.0.0.1:5500/index.html");

		WebDriverWait implicitWait = new WebDriverWait(driver, 10);

		WebElement updateButton = this.driver
				.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/button[2]");
		updateButton.click();

		WebElement closeButton = implicitWait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/form/button[1]")));

		WebElement updateSpeciesInput = this.driver
				.findElementByXPath("/html/body/div[2]/div/div/div[2]/form/input[1]");
		WebElement updateOriginInput = this.driver.findElementByXPath("/html/body/div[2]/div/div/div[2]/form/input[2]");
		WebElement updateStorageLocationInput = this.driver
				.findElementByXPath("/html/body/div[2]/div/div/div[2]/form/input[3]");
		WebElement updateDateReceivedInput = this.driver
				.findElementByXPath("/html/body/div[2]/div/div/div[2]/form/input[4]");
		WebElement updateDescriptionInput = this.driver
				.findElementByXPath("/html/body/div[2]/div/div/div[2]/form/input[5]");

		updateSpeciesInput.sendKeys("Chimpanzee");
		updateOriginInput.sendKeys("Borneo");
		updateStorageLocationInput.sendKeys("Primate Display");
		updateDateReceivedInput.sendKeys("22/09/1997");
		updateDescriptionInput.sendKeys("Complete Mount");
		updateDescriptionInput.sendKeys(Keys.ENTER);

		WebElement table = this.driver.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody");

		Assertions.assertTrue(table.getText().contains("Chimpanzee"));
		Assertions.assertTrue(table.getText().contains("Borneo"));
		Assertions.assertTrue(table.getText().contains("Primate Display"));
		Assertions.assertTrue(table.getText().contains("1997-09-22"));
		Assertions.assertTrue(table.getText().contains("Complete Mount"));

	}

//	@Test
//	void testDelete() {
//
//		WebDriverWait implicitWait = new WebDriverWait(driver, 10);
//
//		WebElement deleteButton1 = this.driver
//				.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/button[1]");
//
//		deleteButton1.click();
//
//		WebElement resetButton = implicitWait.until(
//				ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div/form/button[1]")));
//		resetButton.click();
//
//		String pageSource = this.driver.getPageSource();
//
//		Assertions.assertFalse(pageSource.contains("Chimpanzee"));
//		Assertions.assertFalse(pageSource.contains("Complete Mount"));
//		Assertions.assertFalse(pageSource.contains("1997-09-22"));
//
//	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
