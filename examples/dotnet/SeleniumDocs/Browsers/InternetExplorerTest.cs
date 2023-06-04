using System;
using System.IO;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.IE;
using SeleniumDocs.TestSupport;

namespace SeleniumDocs.Browsers
{
    [TestClassCustom]
    [EnabledOnOs("WINDOWS")]
    public class InternetExplorerTest
    {
        private InternetExplorerDriver driver;

        private readonly string _logLocation =
            Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "../../../selenium.log");

        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }

        [TestMethod]
        public void BasicOptionsWin10()
        {
            var options = new InternetExplorerOptions();
            options.AttachToEdgeChrome = true;
            options.EdgeExecutablePath = Environment.GetEnvironmentVariable("EDGE_BINARY");
            driver = new InternetExplorerDriver(options);
        }

        [TestMethod]
        public void BasicOptionsWin11()
        {
            var options = new InternetExplorerOptions();
            driver = new InternetExplorerDriver(options);
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToFile()
        {
            var service = InternetExplorerDriverService.CreateDefaultService();
            service.LogFile = _logLocation;

            driver = new InternetExplorerDriver(service);
            var lines = File.ReadLines(_logLocation);
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("geckodriver	INFO	Listening on")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToConsole()
        {
            var stringWriter = new StringWriter();
            var originalOutput = Console.Out;
            Console.SetOut(stringWriter);

            var service = InternetExplorerDriverService.CreateDefaultService();

            //service.LogToConsole = true;

            driver = new InternetExplorerDriver(service);
            Assert.IsTrue(stringWriter.ToString().Contains("geckodriver	INFO	Listening on"));
            Console.SetOut(originalOutput);
            stringWriter.Dispose();
        }

        [TestMethod]
        public void LogsLevel()
        {
            var service = InternetExplorerDriverService.CreateDefaultService();
            service.LogFile = _logLocation;

            service.LoggingLevel = InternetExplorerDriverLogLevel.Debug;

            driver = new InternetExplorerDriver(service);
            var lines = File.ReadLines(_logLocation);
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Marionette\tDEBUG")));
        }

        [TestMethod]
        public void SupportingFilesLocation()
        {
            var service = InternetExplorerDriverService.CreateDefaultService();
            string tempPath = "/temp";

            service.LibraryExtractionPath = tempPath;

            driver = new InternetExplorerDriver(service);
        }
    }
}