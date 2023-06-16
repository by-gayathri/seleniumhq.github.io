---
title: "Firefox specific functionality"
linkTitle: "Firefox"
weight: 6
description: >-
    These are capabilities and features specific to Mozilla Firefox browsers.
aliases: [
"/documentation/capabilities/firefox"
]
---

Selenium 4 requires Firefox 78 or greater. It is recommended to always use the latest version of geckodriver.

## Options

Capabilities common to all browsers are described on the [Options page]({{< ref "../drivers/options.md" >}}).

Capabilities unique to Firefox can be found at Mozilla's page for [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions)

Starting a Firefox session with basic defined options looks like this:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L37-L38" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L10-L11" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L28-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openFirefoxTest.spec.js#L10-L13">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Arguments

The `args` parameter is for a list of Command line switches used when starting the browser.  
Commonly used args include `-headless` and `"-profile", "/path/to/profile"`

Add an argument to options:

<div>
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L44" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L18" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L36" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.js#L7-L10">}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
</div>

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. For example, with this parameter you can
use geckodriver to drive Firefox Nightly instead of the production version when both are present on your computer.

Add a browser location to options:

{{< alert-code />}}

### Profiles

There are several ways to work with Firefox profiles

<div>
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
{{< badge-examples >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< badge-examples >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< badge-examples >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< badge-examples >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-examples >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-examples >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}
</div>

## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java also allows setting file output by System Property with:
`GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L51" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L25" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L33" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Console output

To change the logging output to display in the console:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java also allows setting console output by System Property: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`,
valid values include `DriverService.LOG_STDOUT` and `DriverService.LOG_STDERR`
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L65" >}}
Can also pass log output to `STDERR` using `withLogOutput(System.err)`
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L42" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Log level
There are 7 available log levels: `fatal`, `error`, `warn`, `info`, `config`, `debug`, `trace`.
If logging is specified the level defaults to `info`.

Note that `-v` is equivalent to `-log debug` and `-vv` is equivalent to `log trace`,
so this examples is just for setting the log level generically:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java also allows setting log level by System Property: `GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY`,
valid values can be obtained like: `FirefoxDriverLogLevel.DEBUG.toString()`
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L80" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Truncated Logs

The driver logs everything that gets sent to it, including string representations of large binaries, so
Firefox truncates lines by default. To turn off truncation:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java also allows turning off truncation by System Property: `GeckoDriverService.GECKO_DRIVER_LOG_NO_TRUNCATE`,
with a boolean string value.
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L97" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L59" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L62" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Profile Root

The default directory for profiles is the system temporary directory. If you do not have access to that directory,
or want profiles to be created some place specific, you can change the profile root directory:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java also allows setting Profile Root by System Property with: `GeckoDriverService.GECKO_DRIVER_PROFILE_ROOT`.
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L111" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L71" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L71" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Special Features
Some browsers have implemented additional features that are unique to them.

### Add-ons

Unlike Chrome, Firefox extensions are not added as part of capabilities as mentioned in
[this issue](https://github.com/mozilla/geckodriver/issues/1476),
they are created after starting the driver.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

#### Installation

A signed xpi file you would get from [Mozilla Addon page](https://addons.mozilla.org/en-US/firefox/) 

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L126" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L85" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L120" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L83" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Uninstallation

Uninstalling an addon requires knowing its id. The id can be obtained from the return value when installing the add-on.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L138" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L98" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L136" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L93" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Unsigned installation

When working with an unfinished or unpublished extension, it will likely not be signed. As such, it can only
be installed as "temporary." This can be done by passing in either a zip file or a directory, here's an 
example with a directory:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L148" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L108" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L149" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L101" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Full page screenshots

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< alert-code />}}

### Context

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< alert-code />}}
