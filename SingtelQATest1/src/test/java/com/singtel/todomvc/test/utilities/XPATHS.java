package com.singtel.todomvc.test.utilities;

/**
 * This class stores all the XPATH constants used in TODO MVC Tests
 * 
 * @category Utility Class
 * @author ROBIN GUPTA
 *
 */
public class XPATHS {
	public static final String XPATH_REMINDER_INPUT_BOX = "//input[@placeholder='What needs to be done?']";
	public static final String XPATH_ALL_BUTTON = "//a[.='All']";
	public static final String XPATH_ACTIVE_BUTTON = "//a[.='Active']";
	public static final String XPATH_COMPLETED_BUTTON = "//a[.='Completed']";
	public static final String XPATH_REMINDER_COUNT_LABEL = "//span[@class='todo-count']/strong";
	public static final String XPATH_TOGGLE_ALL_LABEL = "//label[@for='toggle-all']";
	public static final String XPATH_DESTROY_BUTTON = "//li[./div/label[.='$REMINDERNAME$']]//button[@class='destroy']";
	public static final String XPATH_REMINDER_LABEL = "//li[./div/label[.='$REMINDERNAME$']]";
	public static final String XPATH_REMINDER_LABEL_TEXT = "//li[./div/label[.='$REMINDERNAME$']]//label";
	public static final String XPATH_REMINDER_EDIT_LABEL = "//li[./div/label[.='$REMINDERNAME$']]//input[@class='edit']";
	public static final String XPATH_REMINDER_CHECKBOX = "//li[./div/label[.='$REMINDERNAME$']]//input[@class='toggle']";
	public static final String XPATH_TODOS_HEADER = "//h1[.='todos']";
	public static final String XPATH_CLEAR_COMPLETED = "//button[@class='clear-completed']";
	public static final String TITLE = "Vue.js • TodoMVC";
	public static final String COMPLETE = "COMPLETE";
	public static final String ACTIVE = "ACTIVE";

}
