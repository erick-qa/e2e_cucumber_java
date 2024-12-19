package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Caminho para as features
        glue = "stepDefinition",    // Caminho para as step definitions
        plugin = {"pretty", "html:target/cucumber-report.html"} // Relatórios do Cucumber
)
public class TestRunner {
}