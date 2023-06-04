import os
import subprocess

import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By


def test_basic_options():
    options = webdriver.FirefoxOptions()
    driver = webdriver.Firefox(options=options)

    driver.quit()


def test_arguments():
    options = webdriver.FirefoxOptions()
    options.add_argument("-headless")

    driver = webdriver.Firefox(options=options)
    driver.quit()


def test_log_to_file(log_path):
    service = webdriver.firefox.service.Service(log_path=log_path)

    driver = webdriver.Firefox(service=service)

    with open(log_path, 'r') as fp:
        assert "geckodriver	INFO	Listening on" in fp.readline()

    driver.quit()


@pytest.mark.skip(reason="this is not supported, yet")
def test_log_to_stdout(capfd):
    service = webdriver.firefox.service.Service(log_output=subprocess.STDOUT)

    driver = webdriver.Firefox(service=service)

    out, err = capfd.readouterr()
    assert "geckodriver	INFO	Listening on" in out

    driver.quit()


def test_log_level(log_path):
    service = webdriver.firefox.service.Service(service_args=['--log', 'debug'], log_path=log_path)

    driver = webdriver.Firefox(service=service)

    with open(log_path, 'r') as f:
        assert '\tDEBUG' in f.read()

    driver.quit()


def test_log_truncation(log_path):
    service = webdriver.firefox.service.Service(service_args=['--log-no-truncate', '--log', 'debug'], log_path=log_path)

    driver = webdriver.Firefox(service=service)

    with open(log_path, 'r') as f:
        assert ' ... ' not in f.read()

    driver.quit()


def test_profile_location(temp_dir):
    os.mkdir('temp')
    service = webdriver.firefox.service.Service(service_args=['--profile-root', temp_dir])

    driver = webdriver.Firefox(service=service)
    profile_name = driver.capabilities.get('moz:profile').split('/')[-1]

    assert profile_name in os.listdir(temp_dir)

    driver.quit()


def test_install_addon(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example.xpi")
    driver.install_addon(path)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"


def test_uninstall_addon(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example.xpi")
    id = driver.install_addon(path)
    driver.uninstall_addon(id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert len(driver.find_elements(By.ID, "webextensions-selenium-example")) == 0


def test_install_unsigned_addon_directory(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example/")
    driver.install_addon(path, temporary=True)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"
