package com.singtel.todomvc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.singtel.todomvc.pages.BasePage;
import com.singtel.todomvc.test.utilities.SetupWebDriverInstance;
import com.singtel.todomvc.test.utilities.XPATHS;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private Logger LOG = Logger.getLogger(StepDefinitions.class);
	private SetupWebDriverInstance driverSetupClass;
	private BasePage basePage;

	@After
	public void closeBrowser(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driverSetupClass.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Failed Screenshot");
			}
		} catch (Exception e) {
		} finally {
			if (driverSetupClass.getDriver() != null) {
				LOG.info("Closing Browser Now");
				driverSetupClass.getDriver().quit();
			}
			driverSetupClass = null;
			basePage = null;
		}

	}

	@Given("User launchs {string} Browser")
	public void user_launchs_chrome_browser(String browserType) {
		LOG.info("Launching " + browserType + " Browser");
		driverSetupClass = new SetupWebDriverInstance();
		driverSetupClass.launchBrowser(browserType);
		assertNotNull("Driver instance could not be created", driverSetupClass.getDriver());
		assertNotNull("Could not setup implicit wait for driver", driverSetupClass.getWait());
		LOG.info("Browser launched succesfully");
	}

	@When("User navigate to {string}")
	public void user_navigate_to(String url) {
		LOG.info("Navigating to URL : " + url);
		driverSetupClass.getDriver().get(url);
		LOG.info("URL Launched");
	}

	@Then("User should see the ToDo MVC Home Page")
	public void user_should_see_the_to_do_mvc_home_page() {
		LOG.info("Validations Started for ToDo MVC Home Page");
		basePage = new BasePage(driverSetupClass);
		basePage.validateReminderInputBox();
		basePage.validateTodosHeader();
		basePage.validateTitle();
		LOG.info("Validations Completed for ToDo MVC Home Page");
	}

	@Given("I am on the TODOMVC Home Page")
	public void i_am_on_the_todomvc_home_page() {
		assertNotNull("Driver object is Null -> There is no browser opened", driverSetupClass.getDriver());
	}

	@When("I add {string} as a new reminder")
	public void i_enter_in_the_reminder_input_box(String reminderLabel) {
		assertNotNull("Base Page is not being set -> User is not on home page Or Validations are failed", basePage);
		basePage.inputNewReminder(reminderLabel);
		basePage.pressEnterForReminderAddition();
		sleepFor(1);
	}

	@Then("I should see {string} in the reminder list")
	public void i_can_see_in_the_reminder_list(String reminderLabel) {
		assertTrue(basePage.validateReminderPresent(reminderLabel));
	}

	@When("I add {int} reminders")
	public void i_add_reminders(Integer reminderCount, DataTable dataTable) {
		List<String> reminders = dataTable.asList();
		for (String reminder : reminders) {
			basePage.inputNewReminder(reminder);
			basePage.pressEnterForReminderAddition();
			sleepFor(1);
		}
	}

	@Then("I should see Reminder count as {int}")
	public void i_should_see_reminder_count_as(Integer reminderCount) {
		basePage.validateReminderCount(reminderCount);
	}

	@Then("I should see Reminder count as blank")
	public void i_should_see_reminder_count_as() {
		basePage.validateBlankReminderCount();
	}

	private static void sleepFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("I add {int} random tasks to my to-do list")
	public void i_add_random_tasks_to_my_to_do_list(int taskCount) {
		for (int counter = 1; counter <= taskCount; counter++) {
			basePage.inputNewReminder("Reminder " + counter);
			basePage.pressEnterForReminderAddition();
		}
	}

	@Then("I should not see any count")
	public void i_should_not_see_any_count() {
		basePage.validateCountNotVisible();
	}

	@When("I delete {string} task")
	public void i_delete_task(String reminderLabel) {
		basePage.deleteReminder(reminderLabel);
	}

	@Then("I should not see {string} in the reminder list")
	public void i_should_not_see_in_the_reminder_list(String reminderLabel) {
		assertFalse(basePage.validateReminderPresent(reminderLabel));
	}

	@When("I complete {string} task")
	public void i_complete_task(String reminderLabel) {
		basePage.completeReminder(reminderLabel);
	}

	@Then("I should see {string} task status as complete")
	public void i_should_see_task_status_as_complete(String reminderLabel) {
		assertEquals("Task Status is not as expected", XPATHS.COMPLETE, basePage.getReminderStatus(reminderLabel));
	}

	@Then("I should see following task status as active")
	public void i_should_see_following_task_status_as_active(DataTable listOfReminders) {
		List<String> reminders = listOfReminders.asList();
		for (String reminder : reminders) {
			assertEquals("Task Status is not as expected for \"" + reminder + "\" task", XPATHS.ACTIVE,
					basePage.getReminderStatus(reminder));
		}
	}

	@Then("I should see Clear Completed button")
	public void i_should_see_clear_completed_button() {
		assertTrue("Clear Completed Button is not visible", basePage.validateIsClearCompletedVisible());
	}

	@When("I click on clear completed button")
	public void i_click_on_clear_completed_button() {
		basePage.clickClearCompleted();
	}

	@When("I uncheck the {string} task to retain it")
	public void i_uncheck_the_task_to_retain_it(String reminderLabel) {
		basePage.activeReminder(reminderLabel);
	}

	@Then("I should see {string} task as Active")
	public void i_should_see_task_as_active(String reminderLabel) {
		assertEquals("Task Status is not as expected for \"" + reminderLabel + "\" task", XPATHS.ACTIVE,
				basePage.getReminderStatus(reminderLabel));
	}

	@When("I change the task name from {string} to {string}")
	public void i_change_the_task_name_from_to(String originalReminder, String targetReminder) {
		basePage.changeReminderText(originalReminder, targetReminder);
	}

	@Then("I should see cursor focus on {string}")
	public void I_should_see_cursor_focus_on(String elementName) {
		assertTrue(basePage.validateIfElementIsFocused(elementName));
	}

	@When("I click on Complete Filter")
	public void iClickOnCompleteFilter() {
		basePage.clickCompleteFilter();
	}

	@When("I click on Active Filter")
	public void iClickOnActiveFilter() {
		basePage.clickActiveFilter();
	}

	@When("I click on All Filter")
	public void iClickOnAllFilter() {
		basePage.clickAllFilter();
	}

	@When("I click on complete all todo")
	public void iClickOnCompleteAllTodo() {
		basePage.clickOnToggleAll();
	}
}
