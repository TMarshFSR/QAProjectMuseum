package com.qa.museum.selenium;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

// Will be updated to use base SQL Scripts
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("Test")
public class SeleniumTests {

	@LocalServerPort
	private int port;

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	void testRead() {

		this.driver.get("http://127.0.0.1:5500/index.html");

		WebElement homeButton = this.driver.findElementByXPath("/html/body/nav/div/div/ul/li[1]");

		assertThat(homeButton.getText()).isEqualTo("Home");

	}

	@Test
	void testCreate() {
		this.driver.get("http://127.0.0.1:5500/index.html");

		WebDriverWait implicitWait = new WebDriverWait(driver, 10);

		WebElement speciesInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[1]");
		WebElement originInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[2]");
		WebElement storageLocationInput = this.driver
				.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[3]");
		WebElement dateReceivedInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[4]");
		WebElement descriptionInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[5]");

		speciesInput.sendKeys("Howler Monkey");
		originInput.sendKeys("Borneo");
		storageLocationInput.sendKeys("Primate Display");
		dateReceivedInput.sendKeys("22/09/1997");
		descriptionInput.sendKeys("Complete Mount");

		descriptionInput.sendKeys(Keys.RETURN);

		WebElement idResult = implicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/td[1]")));

		WebElement table = this.driver.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody");

		Assertions.assertTrue(table.getText().contains("Howler Monkey"));
		Assertions.assertTrue(table.getText().contains("Borneo"));
		Assertions.assertTrue(table.getText().contains("Primate Display"));
		Assertions.assertTrue(table.getText().contains("1997-09-22"));
		Assertions.assertTrue(table.getText().contains("Complete Mount"));

		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/button[1]"))
				.sendKeys(Keys.RETURN);
	}

	@Test
	void testUpdate() throws InterruptedException {

		this.driver.get("http://127.0.0.1:5500/index.html");

		WebDriverWait implicitWait = new WebDriverWait(driver, 10);

		// CREATE
		WebElement speciesInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[1]");
		WebElement originInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[2]");
		WebElement storageLocationInput = this.driver
				.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[3]");
		WebElement dateReceivedInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[4]");
		WebElement descriptionInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[5]");
		speciesInput.sendKeys("Howler Monkey");
		originInput.sendKeys("Borneo");
		storageLocationInput.sendKeys("Primate Display");
		dateReceivedInput.sendKeys("22/09/1997");
		descriptionInput.sendKeys("Complete Mount");
		descriptionInput.sendKeys(Keys.RETURN);

		// UPDATE
		WebElement updateButton = implicitWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/button[2]")));
		updateButton.click();

		// left in for wait
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
		updateDescriptionInput.sendKeys(Keys.RETURN);

		WebElement table = this.driver.findElementByXPath("/html/body/div[1]/div/div[1]/div/div/table/tbody");

		Assertions.assertTrue(table.getText().contains("Chimpanzee"));
		Assertions.assertTrue(table.getText().contains("Borneo"));
		Assertions.assertTrue(table.getText().contains("Primate Display"));
		Assertions.assertTrue(table.getText().contains("1997-09-22"));
		Assertions.assertTrue(table.getText().contains("Complete Mount"));

		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr[1]/button[1]"))
				.sendKeys(Keys.RETURN);

	}

	@Test
	void testDelete() throws InterruptedException {

		this.driver.get("http://127.0.0.1:5500/index.html");
		WebDriverWait implicitWait = new WebDriverWait(driver, 10);

		WebElement speciesInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[1]");
		WebElement originInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[2]");
		WebElement storageLocationInput = this.driver
				.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[3]");
		WebElement dateReceivedInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[4]");
		WebElement descriptionInput = this.driver.findElementByXPath("/html/body/div[1]/div/div[2]/div/form/input[5]");

		speciesInput.sendKeys("Giraffa");
		originInput.sendKeys("Gambia");
		storageLocationInput.sendKeys("Main Hall");
		dateReceivedInput.sendKeys("19/01/2001");
		descriptionInput.sendKeys("Complete Mount");
		descriptionInput.submit();

		WebElement deleteButton1 = implicitWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr/button[1]")));

		deleteButton1.click();
		Thread.sleep(1000);

		Boolean giraffaGone = implicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div/div/table/tbody/tr[2]")));

		String pageSource = this.driver.getPageSource();

		Assertions.assertFalse(pageSource.contains("Giraffa"));
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
