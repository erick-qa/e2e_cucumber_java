package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class CheckoutPage {

    private final WebDriver driver;
    private final DriverFactory driverFactory;

    // Elementos da página de registro
    private final By userNameField = By.name("user-name");
    private final By passwordField = By.name("password");
    private final By loginButton = By.name("login-button");
    private final By addToCartButton = By.name("add-to-cart-sauce-labs-backpack");
    private final By cartButton = By.cssSelector("[data-test='shopping-cart-link']");
    private final By nameText = By.cssSelector("[data-test='inventory-item-name']");
    private final By nameTextCart = By.cssSelector("[data-test='inventory-item-name']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By zipField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By nameTextFinish = By.cssSelector("[data-test='inventory-item-name']");
    private final By successMessageText = By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver, DriverFactory driverFactory) {
        this.driver = driver;
        this.driverFactory = driverFactory;
    }

    // Métodos para interagir com os elementos
    public void visit() {
        driver.get("https://www.saucedemo.com/");
    }
    public void login() {
        driverFactory.typeToElement(userNameField, "standard_user");
        driverFactory.typeToElement(passwordField, "secret_sauce");
        driverFactory.clickElement(loginButton);
    }
    public void addProductToCartAndGoToCheckout() {
        String capturedText = driverFactory.getElementText(nameText, 3);

        driverFactory.clickElement(addToCartButton);

        driverFactory.clickElement(cartButton);

        driverFactory.validateTextMatch(capturedText, nameTextCart, 3);
        driverFactory.clickElement(checkoutButton);
        driverFactory.typeToElement(firstNameField, "Erick");
        driverFactory.typeToElement(lastNameField, "Santos");
        driverFactory.typeToElement(zipField, "03635001");
        driverFactory.clickElement(continueButton);
        driverFactory.validateTextMatch(capturedText, nameTextFinish, 3);
        driverFactory.clickElement(finishButton);

    }
    public void validateSuccessMessage(){
        driverFactory.validateElementContainsText(successMessageText, "Thank you for your order!", 3);

    }

}
