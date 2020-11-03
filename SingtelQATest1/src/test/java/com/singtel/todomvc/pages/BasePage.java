package com.singtel.todomvc.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.singtel.todomvc.test.utilities.SetupWebDriverInstance;
import com.singtel.todomvc.test.utilities.XPATHS;

public class BasePage {

	private static Logger LOG = Logger.getLogger(BasePage.class);

	public BasePage(SetupWebDriverInstance driverInstanceClass) {
		LOG.info("Instantiating Base Page Class");
		driver = driverInstanceClass.getDriver();
		wait = driverInstanceClass.getWait();
	}

	private WebDriver driver;

	private WebDriverWait wait;

	private WebElement reminderInputBox;
	private WebElement allButton;
	private WebElement activeButton;
	private WebElement completedButton;
	private WebElement reminderCountLabel;
	private WebElement toggelAllLabel;
	private WebElement destroyButton;
	private WebElement todosHeader;
	private WebElement clearCompleted;

	private static final String TODOS_HEADER = "TODOS_HEADER";
	private static final String REMINDER_INPUT = "REMINDER_INPUT";
	private static final String ACTIVE_BUTTON = "ACTIVE_BUTTON";
	private static final String COMPLETED_BUTTON = "COMPLETED_BUTTON";
	private static final String REMINDER_COUNT_LABEL = "REMINDER_COUNT_LABEL";
	private static final String TOGGLE_ALL_LABEL = "TOGGLE_ALL_LABEL";
	private static final String REMINDERNAME = "$REMINDERNAME$";
	private static final String CLEAR_COMPLETED = "CLEAR_COMPLETED";
	private static final String ALL_BUTTON = "ALL_BUTTON";

	public void validateReminderInputBox() {
		setElement(REMINDER_INPUT);
	}

	public void validateTodosHeader() {
		setElement(TODOS_HEADER);
	}

	public void validateTitle() {
		assertEquals("Validation Failed - Title", XPATHS.TITLE.trim(), driver.getTitle().trim());
		LOG.info("Validation Successful - Title");
	}

	public WebElement getReminderInputBox() {
		return reminderInputBox;
	}

	public void setReminderInputBox(WebElement reminderInputBox) {
		this.reminderInputBox = reminderInputBox;
	}

	public WebElement getAllButton() {
		return allButton;
	}

	public void setAllButton(WebElement allButton) {
		this.allButton = allButton;
	}

	public WebElement getActiveButton() {
		return activeButton;
	}

	public void setActiveButton(WebElement activeButton) {
		this.activeButton = activeButton;
	}

	public WebElement getCompletedButton() {
		return completedButton;
	}

	public void setCompletedButton(WebElement completedButton) {
		this.completedButton = completedButton;
	}

	public WebElement getReminderCountLabel() {
		return reminderCountLabel;
	}

	public void setReminderCountLabel(WebElement reminderCountLabel) {
		this.reminderCountLabel = reminderCountLabel;
	}

	public WebElement getToggelAllLabel() {
		return toggelAllLabel;
	}

	public void setToggelAllLabel(WebElement toggelAllLabel) {
		this.toggelAllLabel = toggelAllLabel;
	}

	public WebElement getDestroyButton() {
		return destroyButton;
	}

	public void setDestroyButton(WebElement destroyButton) {
		this.destroyButton = destroyButton;
	}

	public WebElement getTodosHeader() {
		return todosHeader;
	}

	public void setTodosHeader(WebElement todosHeader) {
		this.todosHeader = todosHeader;
	}

	public void inputNewReminder(String reminderLabel) {
		setElement(REMINDER_INPUT);
		getReminderInputBox().sendKeys(reminderLabel);
	}

	public void pressEnterForReminderAddition() {
		setElement(REMINDER_INPUT);
		getReminderInputBox().sendKeys(Keys.RETURN);
	}

	public boolean validateReminderPresent(String reminderLabel) {
		try {
			driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_LABEL.replace(REMINDERNAME, reminderLabel)));
		} catch (Exception e) {
//			LOG.error("Reminder not found - " + reminderLabel,
//					new RuntimeException("Error while finding Reminder in the list", e));
			return false;
		}
		return true;
	}

	public void validateReminderCount(Integer reminderCount) {
		setElement(REMINDER_COUNT_LABEL);
		assertEquals("Reminder Count did not match", String.valueOf(reminderCount),
				getReminderCountLabel().getText().trim());
	}

	private void setElement(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case REMINDER_INPUT:
				setReminderInputBox(driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_INPUT_BOX)));
				break;
			case ALL_BUTTON:
				setAllButton(driver.findElement(By.xpath(XPATHS.XPATH_ALL_BUTTON)));
				break;
			case ACTIVE_BUTTON:
				setActiveButton(driver.findElement(By.xpath(XPATHS.XPATH_ACTIVE_BUTTON)));
				break;
			case COMPLETED_BUTTON:
				setCompletedButton(driver.findElement(By.xpath(XPATHS.XPATH_COMPLETED_BUTTON)));
				break;
			case REMINDER_COUNT_LABEL:
				setReminderCountLabel(driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_COUNT_LABEL)));
				break;
			case TOGGLE_ALL_LABEL:
				setToggelAllLabel(driver.findElement(By.xpath(XPATHS.XPATH_TOGGLE_ALL_LABEL)));
				break;
			case TODOS_HEADER:
				setTodosHeader(driver.findElement(By.xpath(XPATHS.XPATH_TODOS_HEADER)));
				break;
			case CLEAR_COMPLETED:
				setClearCompleted(driver.findElement(By.xpath(XPATHS.XPATH_CLEAR_COMPLETED)));
				break;
			default:
				throw new RuntimeException("Element not configured");
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Error while finding " + elementName.toUpperCase() + " on the page", e);
		}
	}

	public WebElement getClearCompleted() {
		return clearCompleted;
	}

	public void setClearCompleted(WebElement clearCompleted) {
		this.clearCompleted = clearCompleted;
	}

	public void validateCountNotVisible() {
		try {
			setElement(REMINDER_COUNT_LABEL);
			assertFalse("Validation Failed - Count label is visible", true);
		} catch (Exception e) {
			LOG.info("Validation Passed - Count label is not visible");

		}
	}

	public void deleteReminder(String reminderLabel) {
		assertTrue("Reminder is not present in the list", validateReminderPresent(reminderLabel));
		setFocusTo(driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_LABEL.replace(REMINDERNAME, reminderLabel))));
		WebElement destroyCross = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(XPATHS.XPATH_DESTROY_BUTTON.replace(REMINDERNAME, reminderLabel))));
		assertNotNull("Destro Cross is not found for Reminder - " + reminderLabel, destroyCross);
		destroyCross.click();
	}

	private void setFocusTo(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void completeReminder(String reminderLabel) {
		assertTrue("Reminder is not present in the list", validateReminderPresent(reminderLabel));
		driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_CHECKBOX.replace(REMINDERNAME, reminderLabel))).click();
	}

	public String getReminderStatus(String reminderLabel) {
		assertTrue("Reminder is not present in the list", validateReminderPresent(reminderLabel));
		String status = driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_LABEL.replace(REMINDERNAME, reminderLabel)))
				.getAttribute("class");
		if (status.trim().equalsIgnoreCase("todo"))
			return XPATHS.ACTIVE;
		return XPATHS.COMPLETE;
	}

	public boolean validateIsClearCompletedVisible() {
		try {
			setElement(CLEAR_COMPLETED);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void clickClearCompleted() {
		assertTrue("Clear Completed Button is not visible", validateIsClearCompletedVisible());
		getClearCompleted().click();
	}

	public void activeReminder(String reminderLabel) {
		assertTrue("Reminder not present in the todo list", validateReminderPresent(reminderLabel));
		if (getReminderStatus(reminderLabel).equalsIgnoreCase(XPATHS.COMPLETE))
			driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_CHECKBOX.replace(REMINDERNAME, reminderLabel))).click();
	}

	public void changeReminderText(String originalReminder, String targetReminder) {
		assertTrue("Reminder not present in the todo list", validateReminderPresent(originalReminder));
		Actions actions = new Actions(driver);
		actions.doubleClick(
				driver.findElement(By.xpath(XPATHS.XPATH_REMINDER_LABEL_TEXT.replace(REMINDERNAME, originalReminder))))
				.perform();
		try {
			WebElement editBox = driver
					.findElement(By.xpath(XPATHS.XPATH_REMINDER_EDIT_LABEL.replace(REMINDERNAME, originalReminder)));
			assertNotNull("Edit box could not be found for Reminder - " + originalReminder, editBox);
			editBox.sendKeys(Keys.CONTROL, "a", Keys.NULL);
			editBox.sendKeys(targetReminder, Keys.RETURN);
		} catch (Exception e) {
			throw new RuntimeException("Error occurred while renaming the Reminder", e);
		}
		assertTrue("Reminder not present in the todo list", validateReminderPresent(targetReminder));

	}

	public boolean validateIfElementIsFocused(String elementName) {
		switch (elementName) {
		case REMINDER_INPUT:
			validateReminderInputBox();
			return (getReminderInputBox().equals(driver.switchTo().activeElement()));
		default:
			throw new RuntimeException("Element name is not valid for this validation");
		}

	}

	public void validateBlankReminderCount() {
		setElement(REMINDER_COUNT_LABEL);
		assertEquals("Reminder Count is not blank", String.valueOf(""), getReminderCountLabel().getText().trim());
	}

	public void clickActiveFilter() {
		setElement(ACTIVE_BUTTON);
		getActiveButton().click();
	}

	public void clickAllFilter() {
		setElement(ALL_BUTTON);
		getAllButton().click();
	}

	public void clickCompleteFilter() {
		setElement(COMPLETED_BUTTON);
		getCompletedButton().click();
	}

}
