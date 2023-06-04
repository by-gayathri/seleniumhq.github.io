import os

import pytest
from selenium import webdriver


@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()

    yield driver

    driver.quit()


@pytest.fixture(scope='function')
def log_path():
    log_path = 'file.log'

    yield log_path

    os.remove(log_path)


@pytest.fixture(scope='function')
def temp_dir():
    temp_dir = './temp'

    yield temp_dir

    os.rmdir(temp_dir)


@pytest.fixture(scope='function')
def firefox_driver():
    driver = webdriver.Firefox()
    driver.implicitly_wait(1)

    yield driver

    driver.quit()
