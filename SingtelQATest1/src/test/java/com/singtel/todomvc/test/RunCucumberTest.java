package com.singtel.todomvc.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "html:target/html-reports.html", "pretty:target/pretty-reports.log",
		"junit:target/junit-reports.xml", "json:target/json-reports.json",
		"testng:target/testng-reports.xml" }, features = "src/test/resources/features/ReminderManagement.feature", monochrome = false, strict = true, snippets = SnippetType.CAMELCASE, dryRun = false)
public class RunCucumberTest {

}
