# Getting Started with Selenium Java Framework

## Quick Start Guide

### 1. Clone and Setup
```bash
git clone https://github.com/deepanrajavlsi-wq/selenium-java-framework.git
cd selenium-java-framework
mvn clean install
```

### 2. Configure Your Environment

Edit `src/main/resources/config.properties`:
```properties
base.url=https://your-test-url.com
browser=chrome
headless=false
```

### 3. Create Your First Test

**Step 1:** Create a page object class `src/main/java/com/automation/pages/HomePage.java`:
```java
package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By SEARCH_BOX = By.id("search");
    private static final By SEARCH_BUTTON = By.id("search-btn");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void searchFor(String query) {
        sendText(SEARCH_BOX, query);
        clickElement(SEARCH_BUTTON);
    }
}
```

**Step 2:** Create a test class `src/test/java/com/automation/tests/HomePageTest.java`:
```java
package com.automation.tests;

import com.automation.config.Config;
import com.automation.pages.HomePage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    
    @Test(description = "Test search functionality")
    public void testSearch() {
        Logger.info("Starting search test");
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Config.getBaseUrl());
        homePage.searchFor("Selenium");
        
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Logger.info("Search test completed successfully");
    }
}
```

**Step 3:** Update `testng.xml`:
```xml
<test name="Home Page Tests">
    <classes>
        <class name="com.automation.tests.HomePageTest"/>
    </classes>
</test>
```

### 4. Run Your Test
```bash
mvn clean test
```

## Common Patterns

### Using Page Object Methods
```java
// Clicking an element
pagePage.clickElement(locator);

// Sending text
pagePage.sendText(locator, "text");

// Getting text
String text = pagePage.getText(locator);

// Checking visibility
boolean visible = pagePage.isElementDisplayed(locator);

// Hovering
pagePage.hoverElement(locator);

// Double click
pagePage.doubleClickElement(locator);

// Right click
pagePage.rightClickElement(locator);
```

### Using Wait Manager
```java
WaitManager waitManager = new WaitManager(driver);
WebElement element = waitManager.waitForElementToBeClickable(locator);
```

### Using Configuration
```java
String baseUrl = Config.getBaseUrl();
int timeout = Config.getExplicitWait();
String username = Config.getValidUsername();
```

### Using Logger
```java
Logger.info("Test started");
Logger.debug("Debug message");
Logger.warn("Warning message");
Logger.error("Error message");
```

## Next Steps

1. Explore the example test in `src/test/java/com/automation/tests/ExampleTest.java`
2. Check the BasePage methods in `src/main/java/com/automation/pages/BasePage.java`
3. Review configuration options in `src/main/resources/config.properties`
4. Add more page objects and tests as needed

## Tips and Tricks

### Organize Tests by Feature
```
tests/
  ├── login/
  │   ├── LoginTest.java
  │   └── LogoutTest.java
  ├── search/
  │   └── SearchTest.java
  └── checkout/
      └── CheckoutTest.java
```

### Use Test Groups (TestNG)
```java
@Test(groups = {"smoke"})
public void testCriticalFeature() { ... }

@Test(groups = {"regression"})
public void testFeature() { ... }
```

Run specific group:
```bash
mvn test -DsuiteXmlFile=testng.xml -Dgroups=smoke
```

### Use Data Providers
```java
@DataProvider(name = "loginData")
public Object[][] getLoginData() {
    return new Object[][] {
        {"user1@email.com", "password1"},
        {"user2@email.com", "password2"}
    };
}

@Test(dataProvider = "loginData")
public void testLoginWithMultipleUsers(String username, String password) {
    // Test code
}
```

## Troubleshooting

### NoSuchElementException
- Verify the locator is correct
- Check if element is present in DOM
- Use wait manager to ensure element is loaded

### TimeoutException
- Increase timeout in config.properties
- Check if element actually loads
- Verify locator stability

### StaleElementReferenceException
- Don't store WebElement references
- Re-find elements each time you use them
- Use locators instead of storing elements

## Need Help?

Check the README.md for more detailed information and API documentation.