package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import static java.lang.Thread.*;
import static org.testng.Assert.assertTrue;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    private Logger logger = LogManager.getLogger(HomePage.class);

    String baseURL = "https://events.epam.com/";
    By locationCanada =  By.xpath("//label[@data-value='Canada']");

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Step("Open Epam page")
    public HomePage goToEpam() {
        driver.get(baseURL);
        return this;
    }

    @Step("Go to Events page")
    public HomePage goToEventsPage(){
        pageLoaded(driver, baseURL, 60);
        //except cookies
        if (driver.findElements(By.cssSelector("#onetrust-accept-btn-handler")).size()!=0)
        { driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();}
        //sometimes buttons from the top menu are not shown
        if (driver.findElements(By.cssSelector("li.nav-item:nth-child(2) > a:nth-child(1)")).size()!=0)
        {Objects.events(driver).click();}
        //if buttons do not appear, click on the upcoming events from the bottom section
         else {
            WebElement element = driver.findElement(By.xpath("//a[@href='/all-events']"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
        }
        waitVisibility(Objects.upcomingEventsCounter);
        return this;
    }

    @Step("Verify number of events")
    public HomePage verifyEvents(By counter, By events, By numberEvents) {
        int counterNumber = Integer.parseInt(driver.findElement(counter).getText());
        WebElement  allEvents= driver.findElement(events);
        List<WebElement> numberOfEvents = allEvents.findElements(numberEvents);
        Assert.assertEquals(counterNumber, numberOfEvents.size());
        return this;
    }

    @Step("Verify information position")
    public HomePage verifyOrderOfInformation() {
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[1]/div/div[1][contains(@class, 'online-cell')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[1]/div/div[2][contains(@class, 'language-cell')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[1]/div/div[3][contains(@class, 'calendar-cell')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[2]/div/div[1][@class, 'evnt-event-name']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[2]/div/div[2][@class, 'evnt-event-dates']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='evnt-card-wrapper']/div[3]/div/div[1][contains(@class, 'evnt-people-cell')]")).isDisplayed());
        return this;
    }

    @Step ("Find out the date of event")
    public HomePage getEventsDate(By allsections, By allcards) throws ParseException {
        WebElement allSections = driver.findElement(allsections);
        List<WebElement> allCards = allSections.findElements(allcards);
        for (WebElement card : allCards) {
            String eventDate = card.getText();
            String eventEndDate = eventDate.split("- ")[1];
            String selectMonth = eventEndDate.split(" ")[1];
            String year = eventDate.substring(eventDate.lastIndexOf(" ") + 1);
            String eventStartDateWithoutYear = eventDate.substring(0, eventDate.indexOf(" - "));
            String eventStartDate = null;
            String[] splittedStartDate = eventStartDateWithoutYear.split(" ");
            if (splittedStartDate.length == 2)
            {eventStartDate = eventStartDateWithoutYear + " " + year;}
            else
            {eventStartDate = eventStartDateWithoutYear + " " + selectMonth + " " + year;}

            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            DateFormat df = new SimpleDateFormat("dd MMM yyyy");
            Date startDateTimestamp = df.parse(eventStartDate);
            Date endDateTimestamp = df.parse(eventEndDate);
            WebElement element = card;
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            if (currentTimestamp.after(startDateTimestamp) && currentTimestamp.before(endDateTimestamp))
            { Objects.duringThisWeek.add(eventDate); }
            else
            { Objects.duringNotThisWeek.add(eventDate);}
        }
        return this;
    }

    @Step("Verify date of event")
    public HomePage thisWeekEvents(){
        logger.info(Objects.duringThisWeek + " - are during current week" + " and " + Objects.duringNotThisWeek + " - are during not current week");
        System.out.println(Objects.duringThisWeek + " - are during current week" + " and " + Objects.duringNotThisWeek + " - are during not current week");
        return this;
    }

    @Step("Verify date of event")
    public HomePage pastEvents(){
        saveTextLog("This is sparta!!!");
        return this;
    }

    @Step("Verify dates of past events in Canada")
    public HomePage pastEventsCanada() throws InterruptedException {
        Objects.pastEvents(driver).click();
        Objects.locations(driver).click();
        waitVisibility(locationCanada);
        driver.findElement(locationCanada).click();
        sleep(500);
        return this;
    }
}