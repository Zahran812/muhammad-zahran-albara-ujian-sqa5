package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.pages.CartPage;
import com.juaracoding.cucumber.pages.CheckoutPage;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final CartPage cartPage = new CartPage(driver);
    private final CheckoutPage checkoutPage = new CheckoutPage(driver);

    @When("Pengguna mengklik tombol Checkout")
    public void penggunaMengklikTombolCheckout() {
        cartPage.clickCheckoutButton();
        ExtentTestManager.getTest().log(Status.INFO, "Mengklik tombol Checkout di halaman keranjang");
    }

    @When("Pengguna mengisi data nama depan {string}")
    public void penggunaMengisiDataNamaDepan(String firstName) {
        checkoutPage.enterFirstName(firstName);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input nama depan: " + firstName);
    }

    @When("Pengguna mengisi data nama belakang {string}")
    public void penggunaMengisiDataNamaBelakang(String lastName) {
        checkoutPage.enterLastName(lastName);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input nama belakang: " + lastName);
    }

    @When("Pengguna mengisi data kode pos {string}")
    public void penggunaMengisiDataKodePos(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input kode pos: " + postalCode);
    }

    @When("Pengguna mengosongkan data kode pos")
    public void penggunaMengosongkanDataKodePos() {
        checkoutPage.enterPostalCode("");
        ExtentTestManager.getTest().log(Status.INFO, "Mengosongkan input kode pos");
    }

    @When("Pengguna mengklik tombol Continue")
    public void penggunaMengklikTombolContinue() {
        checkoutPage.clickContinueButton();
        ExtentTestManager.getTest().log(Status.INFO, "Mengklik tombol Continue pada halaman Checkout");
    }

    @Then("Sistem menampilkan pesan kesalahan checkout {string}")
    public void sistemMenampilkanPesanKesalahanCheckout(String expectedErrorMessage) {
        String actualError = checkoutPage.getErrorMessageText();
        Assert.assertTrue(actualError.contains(expectedErrorMessage),
                "Pesan error checkout aktual '" + actualError + "' tidak memuat '" + expectedErrorMessage + "'");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi pesan error checkout berhasil: " + actualError);
    }
}

