package utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverFactory {
    private WebDriver driver;

    // Método para instanciar o WebDriver
    public WebDriver getDriver(String browser) {
        if (driver == null) {  // Garantir que só um driver seja criado
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Navegador não suportado: " + browser);
            }
        }
        return driver;
    }

    // Método para fechar o navegador
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Método para instanciar e configurar o navegador antes do teste
    public void initializeDriver(String browser) {
        getDriver(browser);  // Apenas chama getDriver uma vez
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
    }

    // Método para destacar a borda do elemento com verde
    private void highlightElementBorderInGreen(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border = '3px solid green';", element);
    }

    // Método para validar se o elemento está visível e pintar a borda de verde
    public void validateElementPass(By elementLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            // Espera até que o elemento esteja visível
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

            // Verifica se o elemento está visível
            Assert.assertTrue("Elemento não está visível na tela.", element.isDisplayed());

            // Aplica a borda verde
            highlightElementBorderInGreen(element);

            System.out.println("Elemento encontrado.");
        } catch (Exception e) {
            Assert.fail("Elemento não encontrado ou não está visível na tela: " + e.getMessage());
        }
    }

    // Método personalizado para esperar e clicar em um elemento
    public void clickElement(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));

            // Chama validateElementPass para verificar e pintar de verde
            validateElementPass(elementLocator, 10);

            // Clica no elemento após a espera
            element.click();
        } catch (TimeoutException e) {
            System.out.println("O elemento não ficou disponível para clique dentro do tempo limite.");
            throw e; // Pode lançar novamente a exceção ou tomar outra ação
        }
    }

    // Método personalizado para esperar e digitar no elemento
    public void typeToElement(By elementLocator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));

            // Chama validateElementPass para verificar e pintar de verde
            validateElementPass(elementLocator, 10);

            // Limpa o campo de texto e digita o texto fornecido
            element.clear();
            element.sendKeys(text);

            // Asserção para verificar se o texto foi inserido corretamente
            Assert.assertEquals("Texto não inserido corretamente no campo!", text, element.getAttribute("value"));
        } catch (TimeoutException e) {
            System.out.println("O campo de entrada não ficou disponível para digitação dentro do tempo limite.");
            throw e; // Pode lançar novamente a exceção ou tomar outra ação
        }
    }

    // Método para esperar e clicar no dropdown e selecionar a opção com base na posição
    public void selectDropdownOption(By dropdownLocator, int position) {
        // Espera até que o dropdown esteja visível e clicável
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));

        // Chama validateElementPass para verificar e pintar de verde
        validateElementPass(dropdownLocator, 10);

        // Clica no dropdown para expandir as opções
        dropdown.click();

        // Localiza todas as opções dentro do dropdown
        List<WebElement> options = driver.findElements(By.xpath(dropdownLocator + "/following-sibling::div//div[@class='oxd-select-dropdown']//span"));

        // Verifica se a posição solicitada é válida
        if (position >= 0 && position < options.size()) {
            WebElement selectedOption = options.get(position);

            // Destacar a opção selecionada
            highlightElementBorderInGreen(selectedOption);

            selectedOption.click(); // Clica na opção desejada
        } else {
            throw new IllegalArgumentException("Posição inválida: " + position);
        }
    }

    // Método para aguardar a mensagem na página, usando parte do texto esperado
    public void waitForMessage(String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            // Aguarda até que qualquer elemento que contenha o texto esperado apareça
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//*[contains(text(), '" + expectedText + "')]"), expectedText));
        } catch (TimeoutException e) {
            // Falha o teste se a mensagem não for encontrada
            Assert.fail("Mensagem não encontrada: " + expectedText); // Falha o teste
        }
    }

    // Método para clicar em um elemento baseado em texto
    public void clickElementWithText(String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Usamos XPath para localizar qualquer elemento que contenha o texto
            By elementLocator = By.xpath("//*[contains(text(), '" + text + "')]");

            // Chama validateElementPass para verificar e pintar de verde
            validateElementPass(elementLocator, 10);

            // Espera até o elemento ser clicável e então clica
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
            element.click();
        } catch (Exception e) {
            System.out.println("Erro ao clicar no elemento com o texto: " + text);
            throw e;
        }
    }

    // Método para rolar até o elemento e clicar
    public void scrollAndClickElement(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Espera até que o elemento seja visível
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

            // Chama validateElementPass para verificar e pintar de verde
            validateElementPass(elementLocator, 10);

            // Usa JavaScript para rolar até o elemento
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            // Aguarda mais um pouco para garantir que o elemento esteja visível antes de clicar
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Clica no elemento
            element.click();
        } catch (Exception e) {
            System.out.println("Erro ao rolar até o elemento e clicar: " + e.getMessage());
            throw e;
        }
    }

    // Método para rolar a página para baixo
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250);");
    }

    // Método para rolar até o final da página
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Método para lidar com alertas
    public void handleAlert(String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assert.assertTrue("Texto do alerta não encontrado", alert.getText().contains(expectedText));
            alert.accept();
        } catch (TimeoutException e) {
            Assert.fail("Alerta não apareceu dentro do tempo limite");
        }
    }

    // Método para esperar a mensagem em um popup
    public void waitForMessageInPopup(String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.tagName("body"), expectedText));

            WebElement closeButton = driver.findElement(By.xpath("//button[contains(@class, 'close')]"));
            closeButton.click();
        } catch (TimeoutException e) {
            Assert.fail("Mensagem não encontrada: " + expectedText);
        }
    }

    // Método para verificar se um elemento está visível
    public void assertElementIsVisible(By elementLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            Assert.assertTrue("Elemento não está visível na tela.", element.isDisplayed());

            // Chama validateElementPass para verificar e pintar de verde
            validateElementPass(elementLocator, 10);
        } catch (Exception e) {
            Assert.fail("O elemento não foi encontrado ou não está visível na tela: " + e.getMessage());
        }
    }
}
