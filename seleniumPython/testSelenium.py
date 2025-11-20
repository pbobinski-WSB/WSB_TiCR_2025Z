from selenium import webdriver
from selenium.webdriver.common.by import By


def main():
    driver = webdriver.Chrome()

    driver.get("https://www.python.org/events/python-events/")

    title = driver.title

    print(driver.title)

    assert title == 'Our Events | Python.org'

    driver.implicitly_wait(0.5)

#    print(driver.page_source)

    element = driver.find_element(By.CLASS_NAME, "list-recent-events")
    elements = element.find_elements(By.TAG_NAME, 'h3')
    for e in elements:
        print(e.text);


if __name__ == '__main__':
    main()
