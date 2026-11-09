package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.pages.CartPage;
import com.juaracoding.cucumber.pages.InventoryPage;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddToCartStep {

    private final WebDriver driver = DriverManager.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);
    private final CartPage cartPage = new CartPage(driver);

    @When("Pengguna memilih produk {string} untuk ditambahkan ke keranjang")
    public void userAddProductToCart(String productName) {
        inventoryPage.addProductToCart(productName);
        ExtentTestManager.getTest().log(Status.INFO, "Pengguna memilih produk: " + productName);
    }

    @Then("Ikon keranjang belanja menampilkan angka {string}")
    public void cartIconShowBadgeCount(String expectedCount){
        String actualCount = inventoryPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount, "Jumlah badge keranjang tidak sesuai");
        ExtentTestManager.getTest().log(Status.INFO, "Ikon keranjang belanja menampilkan angka: " + expectedCount);
    }

    @When("Pengguna membuka halaman keranjang belanja")
    public void userOpenCartPage(){
        inventoryPage.openCart();
        ExtentTestManager.getTest().log(Status.INFO, "Pengguna membuka halaman keranjang belanja");
    }

    @Then("Produk {string} terdaftar di dalam daftar keranjang belanja")
    public void listProductOnCartPage(String expectedProductName){
        String actualProductName = cartPage.getTxtProductName();
        Assert.assertEquals(actualProductName, expectedProductName, "Nama produk di keranjang tidak sesuai");
        ExtentTestManager.getTest().log(Status.PASS, "Produk " + expectedProductName + " terdaftar di dalam daftar keranjang belanja");
    }
}
