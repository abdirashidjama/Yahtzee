package com.AbdirashidJamaA1.Yahtzee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
		plugin= {"pretty", "html:target/cucumber"},
		features = "src/test/java"
		)

public class AllTests {

}
