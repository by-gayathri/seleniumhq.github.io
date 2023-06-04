package dev.selenium.browsers;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class ChromeTest {
    private ChromeDriver driver;
    private final File logLocation = new File("chromedriver.log");

    @AfterEach
    public void quit() {
        if( logLocation.exists()) {
            logLocation.delete();
        }

        driver.quit();
    }

    @Test
    public void basicOptions() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Test
    public void arguments() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void excludeSwitches() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", ImmutableList.of("disable-popup-blocking"));

        driver = new ChromeDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogFile(logLocation)
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(logLocation));

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                logLocation.getAbsolutePath());

        ChromeDriverService service = new ChromeDriverService.Builder()
            .withLogLevel(ChromiumDriverLogLevel.DEBUG)
            .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("[DEBUG]:"));
    }

    @Test
    public void configureDriverLogs() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                logLocation.getAbsolutePath());
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.DEBUG.toString());

        ChromeDriverService service = new ChromeDriverService.Builder()
            .withAppendLog(true)
            .withReadableTimestamp(true)
            .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Pattern pattern = Pattern.compile("\\[\\d\\d-\\d\\d-\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
        Assertions.assertTrue(pattern.matcher(fileContent).find());
    }

    @Test
    public void disableBuildChecks() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                logLocation.getAbsolutePath());
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.DEBUG.toString());

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withBuildCheckDisabled(true)
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        String expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
        Assertions.assertTrue(fileContent.contains(expected));
    }
}
