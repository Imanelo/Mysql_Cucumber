package com.cucumberTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "com.cucumberTest",
        plugin = {"pretty", "html:target/cucumber-reports"},
        tags = "@mysql"
)
public class RunCucumberTest {
}
