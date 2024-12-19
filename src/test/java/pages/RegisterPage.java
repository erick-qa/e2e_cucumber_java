package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class RegisterPage {

    private WebDriver driver;
    private DriverFactory driverFactory;

    // Elementos da página de registro
    private By userNameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div.oxd-form-actions.orangehrm-login-action > button");
    private By recruitmentButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > aside > nav > div.oxd-sidepanel-body > ul > li:nth-child(5) > a > span");
    private By addButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.orangehrm-paper-container > div.orangehrm-header-container > button");
    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField = By.name("lastName");
    private By emailField = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(3) > div > div:nth-child(1) > div > div:nth-child(2) > input");
    private By vacancyDropdown = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > div.oxd-select-text-input");
    private By keywordsField = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(5) > div > div.oxd-grid-item.oxd-grid-item--gutters.orangehrm-save-candidate-page-full-width > div > div:nth-child(2) > input");
    private By consentCheckbox = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(7) > div > div > div > div:nth-child(2) > div > label > span");
    private By saveButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    private By popUp = By.cssSelector("#oxd-toaster_1 > div");


    // Construtor que recebe WebDriver e DriverFactory
    public RegisterPage(WebDriver driver, DriverFactory driverFactory) {
        this.driver = driver;
        this.driverFactory = driverFactory; // Inicializando corretamente o driverFactory
    }

    // Métodos para interagir com os elementos
    public void visit() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    public void login() {
        driverFactory.typeToElement(userNameField, "Admin");
        driverFactory.typeToElement(passwordField, "admin123");
        driverFactory.clickElement(loginButton);
    }
    public void registerCandidate() {
        driverFactory.clickElement(recruitmentButton);
        driverFactory.clickElement(addButton);
        driverFactory.typeToElement(firstNameField, "Erick");
        driverFactory.typeToElement(middleNameField, "Silva");
        driverFactory.typeToElement(lastNameField, "Santos");
        driverFactory.typeToElement(emailField, "example@example.com");
        driverFactory.clickElement(vacancyDropdown);
        driverFactory.clickElementWithText("Senior QA Lead");
        driverFactory.typeToElement(keywordsField, "New Candidate");
        driverFactory.scrollToBottom();
        driverFactory.clickElement(consentCheckbox);
        driverFactory.clickElement(saveButton);
    }
    public void validateSuccessMessage(){
        driverFactory.assertElementIsVisible(popUp, 5);

    }

}
