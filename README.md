# Selenium Java Automation Framework

A comprehensive Selenium WebDriver automation testing framework built with Java, featuring the Page Object Model (POM) pattern and TestNG.

## Features

- **Page Object Model**: Organized page structure for better maintainability
- **WebDriver Factory**: Support for multiple browsers (Chrome, Firefox)
- **Explicit Waits**: Smart wait mechanisms with WaitManager
- **Logging**: Detailed logging for debugging and monitoring
- **Configuration Management**: Centralized configuration via properties file
- **TestNG Integration**: Easy test execution and reporting
- **WebDriver Manager**: Automatic driver management
- **Maven Build**: Standard Maven project structure

## Project Structure

```
selenium-java-framework/
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── config/
│   │   │   │   └── Config.java              # Configuration management
│   │   │   ├── drivers/
│   │   │   │   └── DriverFactory.java       # WebDriver factory
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java            # Base page with common methods
│   │   │   │   └── ExamplePage.java         # Example page object
│   │   │   ├── utils/
│   │   │   │   └── Logger.java              # Logging utility
│   │   │   └── waits/
│   │   │       └── WaitManager.java         # Wait mechanisms
│   │   └── resources/
│   │       ├── config.properties            # Configuration file
│   │       └── log4j2.xml                   # Logging configuration
│   └── test/
│       └── java/com/automation/tests/
│           ├── BaseTest.java                # Base test class
│           └── ExampleTest.java             # Example test cases
├── pom.xml                                   # Maven configuration
├── testng.xml                                # TestNG configuration
└── README.md                                 # This file
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Git

## Installation

1. Clone the repository:
```bash
git clone https://github.com/deepanrajavlsi-wq/selenium-java-framework.git
cd selenium-java-framework
```

2. Install dependencies:
```bash
mvn clean install
```

3. Configure the framework:
- Edit `src/main/resources/config.properties` with your settings
- Update base URL, browser preferences, and timeouts

## Configuration

Edit `src/main/resources/config.properties` to customize:

```properties
# Browser Configuration
browser=chrome                    # chrome or firefox
headless=false                    # true for headless mode
window.size=1920,1080           # Window size

# URLs
base.url=https://www.example.com
test.url=https://test.example.com
prod.url=https://www.example.com

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=20

# Logging
log.level=INFO

# Test Data
valid.username=testuser
valid.password=testpass123
```

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test class:
```bash
mvn clean test -Dtest=ExampleTest
```

### Run specific test method:
```bash
mvn clean test -Dtest=ExampleTest#testPageTitle
```

### Run tests with TestNG XML:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run in headless mode:
```bash
mvn clean test -Dheadless=true
```

## Creating New Tests

### Step 1: Create a Page Object

Create a new class in `src/main/java/com/automation/pages/`:

```java
package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-btn");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String username, String password) {
        sendText(USERNAME_FIELD, username);
        sendText(PASSWORD_FIELD, password);
        clickElement(LOGIN_BUTTON);
    }
}
```

### Step 2: Create Test Class

Create a new test class in `src/test/java/com/automation/tests/`:

```java
package com.automation.tests;

import com.automation.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(description = "Test successful login")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://example.com/login");
        loginPage.login("user@example.com", "password123");
    }
}
```

### Step 3: Update TestNG XML

Add your test to `testng.xml`:

```xml
<test name="Login Tests">
    <classes>
        <class name="com.automation.tests.LoginTest"/>
    </classes>
</test>
```

## Best Practices

- Use Page Object Model for page interactions
- Implement explicit waits for better reliability
- Use meaningful test names and descriptions
- Keep tests independent and idempotent
- Use fixtures (TestNG @BeforeMethod/@AfterMethod) for setup/teardown
- Organize tests by feature/module
- Use assertions for validation
- Keep sensitive data in configuration files
- Use descriptive locators and comments

## Available Utilities

### WaitManager
```java
WaitManager waitManager = new WaitManager(driver);
WebElement element = waitManager.waitForElementToBeClickable(locator);
WebElement element = waitManager.waitForElementToBeVisible(locator);
WebElement element = waitManager.waitForElementToBePresent(locator);
boolean isInvisible = waitManager.waitForElementToBeInvisible(locator);
```

### BasePage Methods
```java
navigator.navigateTo(url);
navigator.clickElement(locator);
navigator.sendText(locator, text);
String text = navigator.getText(locator);
boolean isDisplayed = navigator.isElementDisplayed(locator);
navigator.doubleClickElement(locator);
navigator.rightClickElement(locator);
navigator.hoverElement(locator);
```

## Troubleshooting

### WebDriver Not Found
- Ensure webdriver-manager dependency is installed
- Check browser is installed on the system

### Timeout Errors
- Increase wait times in `config.properties`
- Verify element locators are correct

### Element Not Found
- Verify locators in page objects
- Use developer tools to inspect elements
- Check page load completely before accessing elements

### Permission Issues
- Run tests with appropriate permissions
- Check logs directory is writable

## Test Reports

Test reports are generated in the `test-output/` directory after test execution.

## CI/CD Integration

Example for GitHub Actions:

```yaml
name: Selenium Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn clean test
```

## Contributing

1. Create a feature branch
2. Make your changes
3. Commit with clear messages
4. Push to your branch
5. Create a Pull Request

## License

MIT License

## Support

For issues and questions, please create an issue in the repository.

## Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Log4j Documentation](https://logging.apache.org/log4j/2.x/)