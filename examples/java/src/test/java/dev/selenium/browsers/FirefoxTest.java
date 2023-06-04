package dev.selenium.browsers;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirefoxTest extends BaseTest {
    private FirefoxDriver driver;
    private final File logLocation = new File("geckodriver.log");
    private final File tempPath = new File("temp");

    @AfterEach
    public void quit() {
        if( logLocation.exists()) {
            logLocation.delete();
        }
        if( tempPath.exists()) {
            tempPath.delete();
        }
        driver.quit();
    }

    @Test
    public void basicOptions() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    @Test
    public void arguments() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        driver = new FirefoxDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogFile(logLocation)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(logLocation));

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                logLocation.getAbsolutePath());

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogLevel(FirefoxDriverLogLevel.DEBUG)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("Marionette\tDEBUG"));
    }

    @Test
    public void stopsTruncatingLogs() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                logLocation.getAbsolutePath());
        System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY,
                FirefoxDriverLogLevel.DEBUG.toString());

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withTruncatedLogs(false)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertFalse(fileContent.contains(" ... "));
    }

    @Test
    public void setProfileLocation() {
        tempPath.mkdir();

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withProfileRoot(tempPath)
                .build();

        driver = new FirefoxDriver(service);

        String location = (String) driver.getCapabilities().getCapability("moz:profile");
        String[] directories = location.split("/");
        String last = directories[directories.length-1];
        Assertions.assertEquals(tempPath.getAbsolutePath() + "/" + last, location);
    }

    @Test
    public void installAddon() {
        driver = new FirefoxDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
        driver.installExtension(xpiPath);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void uninstallAddon() {
        driver = new FirefoxDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
        String id = driver.installExtension(xpiPath);
        driver.uninstallExtension(id);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        Assertions.assertEquals(driver.findElements(By.id("webextensions-selenium-example")).size(), 0);
    }

    @Test
    public void installUnsignedAddonPath() {
        driver = new FirefoxDriver();
        Path path = Paths.get("src/test/resources/extensions/selenium-example");
        driver.installExtension(path, true);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = getLocatedElement(driver, By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }
}
