@Checkout
Feature: Proses Checkout di Portal SauceDemo dengan PageFactory
  Sebagai pengguna yang telah menambahkan produk ke keranjang belanja
  Saya ingin melanjutkan ke proses checkout
  Agar saya dapat memverifikasi validasi pengisian data pengiriman

  Background:
    Given Pengguna membuka portal login "https://www.saucedemo.com/"
    When Pengguna mengisi username "standard_user"
    And Pengguna mengisi password "secret_sauce"
    And Pengguna mengklik tombol Login
    Then Pengguna berhasil diarahkan ke halaman katalog dengan judul "Products"
    When Pengguna memilih produk "Sauce Labs Backpack" untuk ditambahkan ke keranjang
    And Pengguna membuka halaman keranjang belanja
    And Pengguna mengklik tombol Checkout

  @Negative @Regression
  Scenario: Validasi Pesan Kesalahan saat Kode Pos Dikosongkan
    When Pengguna mengisi data nama depan "Zahran"
    And Pengguna mengisi data nama belakang "Albara"
    And Pengguna mengosongkan data kode pos
    And Pengguna mengklik tombol Continue
    Then Sistem menampilkan pesan kesalahan checkout "Error: Postal Code is required"

