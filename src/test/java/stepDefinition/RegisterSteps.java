package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;
import utils.DriverFactory;

public class RegisterSteps {
    private WebDriver driver;
    private RegisterPage registerPage;
    private DriverFactory driverFactory;

    @Before
    public void setUp() {
        driverFactory = new DriverFactory();
        driverFactory.initializeDriver("chrome");  // Inicializa o driver apenas uma vez
        driver = driverFactory.getDriver("chrome"); // Não é mais necessário chamar getDriver aqui, pois initializeDriver já cuida disso
        registerPage = new RegisterPage(driver, driverFactory);  // Passa o driver e o driverFactory para o RegisterPage
    }

    @Given("Eu visito o site")
    public void visitarSite() {
        registerPage.visit();  // Visita o site
        registerPage.login();
    }
    @When("Eu cadastro um novo canditato")
    public void cadastrarUsuario() {
        registerPage.registerCandidate();

    }

    @Then("Eu devo ver uma mensagem de sucesso")
    public void mensagemDeSucesso() {
        registerPage.validateSuccessMessage();

    }

    @After
    public void tearDown() {
        driverFactory.closeDriver();
    }
}
