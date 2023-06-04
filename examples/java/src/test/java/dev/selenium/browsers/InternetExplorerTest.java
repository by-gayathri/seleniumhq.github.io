package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

@EnabledOnOs(OS.WINDOWS)
public class InternetExplorerTest {
    public InternetExplorerDriver driver;
    private final File logLocation = new File("iedriver.log");
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
    public void basicOptionsWin10() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath(System.getProperty("webdriver.edge.binary"));
        driver = new InternetExplorerDriver(options);
    }

    @Test
    public void basicOptionsWin11() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogFile(logLocation)
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("InternetExplorerDriver	INFO	Listening on"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(logLocation));

        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("InternetExplorerDriver	INFO	Listening on"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY,
                logLocation.getAbsolutePath());

        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogLevel(InternetExplorerDriverLogLevel.DEBUG)
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
        Assertions.assertTrue(fileContent.contains("Marionette\tDEBUG"));
    }

    @Test
    public void supportingFilesLocation() {
        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withExtractPath(tempPath)
                .build();

        driver = new InternetExplorerDriver(service);
    }
}
