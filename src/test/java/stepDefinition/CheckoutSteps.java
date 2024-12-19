package stepDefinition;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import utils.DriverFactory;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private DriverFactory driverFactory;

    @Before
    public void setUp() {
        driverFactory = new DriverFactory();
        driverFactory.initializeDriver("chrome");  // Inicializa o driver apenas uma vez
        driver = driverFactory.getDriver("chrome"); // Não é mais necessário chamar getDriver aqui, pois initializeDriver já cuida disso
        checkoutPage = new CheckoutPage(driver, driverFactory);  // Passa o driver e o driverFactory para o RegisterPage
    }

    @Given("Eu visito a loja")
    public void visitarSite() {
        checkoutPage.visit();  // Visita o site
        checkoutPage.login();
    }
    @When("Eu realizo a compra de um produto")
    public void realizarCompra() {
        checkoutPage.addProductToCartAndGoToCheckout();

    }

    @Then("Eu devo ver a mensagem de sucesso")
    public void mensagemDeSucesso() {
        checkoutPage.validateSuccessMessage();

    }

    @After
    public void tearDown() {
        driverFactory.closeDriver();
    }
}

