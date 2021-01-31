package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Objects {

    public static WebElement element = null;
    public static By upcomingEventsCounter = By.cssSelector("a.active:nth-child(1) > span:nth-child(3)");
    public static By upcomingEvents = By.xpath("//div[contains(@class, 'evnt-upcoming-events')]");
    public static By numberOfupcomingEvents = By.xpath("//div[contains(@class, 'evnt-event-card')]");
    public static By pastEventsCounter = By.cssSelector("a.active:nth-child(1) > span:nth-child(3)");
    public static By pastEvents = By.xpath("//div[@class='evnt-cards-container']");
    public static By numberOfPastEvents = By.xpath("//div[contains(@class, 'evnt-event-card')]");
    public static By allUpcomingSections = By.xpath("//div[contains(@class, 'evnt-upcoming-events')]");
    public static By allCards = By.xpath("//div[@class='evnt-event-dates']/div/div/p/span[@class='date']");
    public static By allPastSection = By.xpath("//div[@class='evnt-cards-container']");
    public static ArrayList<String> duringThisWeek = new ArrayList<>();
    public static ArrayList<String> duringNotThisWeek = new ArrayList<>();

    // main page-------------------------------------------------------------------

    public static WebElement events(WebDriver driver) {
        element = driver.findElement(By.cssSelector("li.nav-item:nth-child(2) > a:nth-child(1)"));
        return element;
    }

    public static WebElement pastEvents(WebDriver driver) {
        element = driver.findElement(By.cssSelector("li.evnt-tab-item:nth-child(2) > a:nth-child(1) > span:nth-child(1)"));
        return element;
    }

    public static WebElement locations(WebDriver driver) {
        element = driver.findElement(By.cssSelector("#filter_location > span:nth-child(1)"));
        return element;
    }

}
