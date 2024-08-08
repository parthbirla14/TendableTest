package stepDefination;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import utils.BaseClass;

import java.time.Duration;



import java.util.List;





public  class HomeStepDefination {

    private WebDriver driver;

    public HomeStepDefination() {
        this.driver = BaseClass.getDriver();
    }

    @Given("I open the Tendable website")
    public void i_open_the_tendable_website() throws InterruptedException {
        driver.get("https://www.tendable.com");
        Thread.sleep(5000);

    }
    @Then("I should see the top-level menu items")
    public void i_should_see_the_top_level_menu_items() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized!");
        }

        List<WebElement> menuItems = driver.findElements(By.cssSelector(".menu-item"));
        for (WebElement item : menuItems) {
            System.out.println(item.getText());
        }
    }

    @Then("I should see the {string} button active on each top-level menu page")
    public void i_should_see_the_button_active_on_each_top_level_menu_page(String buttonName) {
        String[] menuItems = {"Home", "Our Story", "Our Solution", "Why Tendable"};

        for (String item : menuItems) {
            try {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement menuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(item)));
                menuElement.click();


                WebElement requestDemoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + buttonName + "')]")));


                Assert.assertTrue(buttonName + " button is not displayed on " + item + " page.", requestDemoButton.isDisplayed());
                Assert.assertTrue(buttonName + " button is not enabled on " + item + " page.", requestDemoButton.isEnabled());
            } catch (NoSuchElementException e) {

                System.err.println("Element not found: " + e.getMessage());
            } catch (TimeoutException e) {

                System.err.println("Timeout waiting for element: " + e.getMessage());
            }
        }
    }


    @Given("I navigate to the Contact Us section")
    public void i_navigate_to_the_contact_us_section() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.findElement(By.linkText("Contact Us")).click();
    }

    @Given("I choose {string}")
    public void i_choose(String reason) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


            String marketingTextXpath = String.format("//div[contains(@class, 'text-center')]//div[contains(@class, 'text-xl') and contains(@class, 'text-aqua-600') and text()='%s']", reason);
            String contactButtonXpath = String.format("//div[contains(@class, 'text-center')]//div[contains(@class, 'text-xl') and contains(@class, 'text-aqua-600') and text()='%s']/following-sibling::div[contains(@class, 'flex')]//button[text()='Contact']", reason);


            WebElement marketingText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(marketingTextXpath)));
            System.out.println(reason + " text is displayed: " + marketingText.isDisplayed());

            // Find the "Contact" button using the parent-child XPath combination
            WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactButtonXpath)));
            System.out.println("Contact button is displayed: " + contactButton.isDisplayed());

            // Click the "Contact" button
            contactButton.click();
            System.out.println("Contact button clicked.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Given("I fill out the Contact Us form excluding the {string} field")
    public void i_fill_out_the_contact_us_form_excluding_the_field(String excludedField) {
        driver.findElement(By.name("fullName")).sendKeys("Test User");
        driver.findElement(By.name("organisationName")).sendKeys("Indusindbank");
        driver.findElement(By.name("cellPhone")).sendKeys("1234567890");
        driver.findElement(By.name("jobRole")).sendKeys("QA");
        driver.findElement(By.name("message")).sendKeys("tendable");
       // driver.findElement(By.name("consentAgreed")).click();

    }
    @Then("I {string} the T&C checkbox")
    public void i_check_the_terms_and_conditions_checkbox(String action) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Locate the checkbox element
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("terms")));

            // Perform the action based on the input
            if ("check".equalsIgnoreCase(action) && !checkbox.isSelected()) {
                checkbox.click();
            } else if ("uncheck".equalsIgnoreCase(action) && checkbox.isSelected()) {
                checkbox.click();
            }

            System.out.println("T&C checkbox is " + (checkbox.isSelected() ? "checked" : "unchecked"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        driver.findElement(By.cssSelector("div[class='toggle-form toggle-163701'] button:nth-child(1)")).click();
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'Sorry, there was an error submitting the form. Ple')]"));
        assert(errorMessage.isDisplayed());
    }



}