package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Caminho para as features
        glue = "stepDefinition",    // Caminho para as step definitions
        plugin = {"json:target/cucumber.json"})

public class TestRunner {
}