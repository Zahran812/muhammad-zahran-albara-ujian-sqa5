package com.juaracoding.cucumber.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model dengan Selenium PageFactory untuk Halaman Checkout (Step One).
 */
public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return pageTitle.getText().equalsIgnoreCase("Checkout: Your Information");
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        if (firstName != null && !firstName.isEmpty()) {
            firstNameInput.sendKeys(firstName);
        }
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        if (lastName != null && !lastName.isEmpty()) {
            lastNameInput.sendKeys(lastName);
        }
    }

    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeInput));
        postalCodeInput.clear();
        if (postalCode != null && !postalCode.isEmpty()) {
            postalCodeInput.sendKeys(postalCode);
        }
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        } catch (Exception e) {
            continueButton.click();
        }
    }

    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelButton);
        } catch (Exception e) {
            cancelButton.click();
        }
    }

    /**
     * Method verifikasi pesan error pada halaman checkout
     */
    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getErrorMessage() {
        return getErrorMessageText();
    }
}
