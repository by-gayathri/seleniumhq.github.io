import os

from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService

DRIVER_PATH = os.getenv('CHROMEWEBDRIVER') + 'chromedriver'

def test_basic_service():
    service = ChromeService()
    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_driver_location():
    service = ChromeService(executable_path=DRIVER_PATH)

    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_driver_port():
    service = ChromeService(port=1234)

    driver = webdriver.Chrome(service=service)

    driver.quit()
