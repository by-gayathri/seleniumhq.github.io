---
title: "Driver Sessions"
linkTitle: "Drivers"
weight: 3
---

Starting and stopping a session is for opening and closing a browser.

## Creating Sessions

Creating a new session corresponds with the W3C command for [New session](https://w3c.github.io/webdriver/#new-session)

The session is created automatically by initializing a new Driver class object.

Each language allows a session to be created with arguments from one of these classes (or equivalent):

* [Options]({{< ref "options.md" >}}) to describe the kind of session you want; default values are used for local, but this is required for remote
* Some form of [CommandExecutor]({{< ref "executors.md" >}}) (the implementation varies between languages)
* [Listeners]({{< ref "listeners.md" >}})

### Local Driver

The primary unique argument for starting a local driver includes information about starting the required driver service
on the local machine.

* [Service]({{< ref "service.md" >}}) object applies only to local drivers and provides information about the browser driver

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeDriverService service = new ChromeDriverService.Builder()
        .withLogOutput(System.out)
        .build();
ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
options.addArguments("--start-maximized");
driver = new ChromeDriver(service, options);
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
service = webdriver.chrome.service.Service(log_path=log_path)
options = webdriver.ChromeOptions()
options.add_argument("--remote-allow-origins=*")
options.add_argument("--start-maximized")
driver = webdriver.Chrome(service=service, options=options)
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
service = Selenium::WebDriver::Service.chrome(
  :log_output => File.open('chromedriver.log', 'w')
options = Selenium::WebDriver::Chrome::Options.new
options.add_argument("--remote-allow-origins=*")
options.add_argument("--start-maximized")
driver = Selenium::WebDriver.for :chrome, service: service, options: options
driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
const { Builder, Capabilities } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const service = new chrome.ServiceBuilder()
  .loggingTo('chromedriver.log')
  .build();
const options = new chrome.Options();
options.addArguments("--remote-allow-origins=*");
options.addArguments("--start-maximized");
const capabilities = Capabilities.chrome().set('goog:chromeOptions', options);
const driver = new Builder()
  .forBrowser('chrome')
  .setChromeService(service)
  .withCapabilities(capabilities)
  .build();
driver.quit();  
{{< /tab >}}
{{< tab header="Kotlin" >}}
val service = ChromeDriverService.Builder()
    .withLogFile(java.io.File("chromedriver.log"))
    .build()
val options = ChromeOptions()
options.addArguments("--remote-allow-origins=*")
options.addArguments("--start-maximized")
val driver = ChromeDriver(service, options)
driver.quit()
{{< /tab >}}
{{< /tabpane >}}

### Remote Driver

The primary unique argument for starting a remote driver includes information about where to execute the code.
Read the details in the [Remote Driver Section]({{< ref "remote_webdriver.md" >}})


## Quitting Sessions

Quitting a session corresponds to W3C command for [Deleting a Session](https://w3c.github.io/webdriver/#delete-session).

Important note: the `quit` method is different from the `close` method, 
and it is recommended to always use `quit` to end the session

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
    driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

