import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {


    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/vmastenkov/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "There is no skip button on screen",
                5
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }




    @Test
    public void saveTwotArticlesToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "There is no search field on the screen",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "There is no search field on the screen",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
                "There is no search results on the screen"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'programming language')]"),
                "There is no 'Java' search results on the screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='GOT IT']"),
                "Cannot find button 'GOT IT'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Create new']"),
                "Cannot find button to create a reading list",
                5
        );
        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text= 'OK']"),
                "Cannot press OK button",
                5

        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Navigate up button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find Navigate up button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "There is no search field on the screen",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "There is no search field on the screen",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "There is no 'Appium' search results on the screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_actions_tab_layout']"),
                "Cannot skip quick access",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Learning programming')]"),
                "There is no 'Learning programming' folder on the screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Navigate up button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find Navigate up button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists button",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder +"']"),
                "Cannot find 'Learning programming' list",
                10
        );
        swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find 'Appium'"
        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find anything by the request",
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath("//android.widget.FrameLayout")
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find anything by the request",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='pagelib_edit_section_title_description']"),
                "Wrong title",
                5

        );


    }



    private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_msg) {
        return waitForElementPresent(by, error_msg, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_msg);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int lower_y = element.getLocation().getY();
        int upper_y = lower_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    }


