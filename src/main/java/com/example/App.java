package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        // Automatically download and configure ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configure Chrome for headless execution (suitable for Jenkins/Linux)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.saucedemo.com/");

            // Explicit wait for better synchronization
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            // Verify successful login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

            System.out.println("Login successful!");
            System.out.println("Page Title: " + driver.getTitle());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
