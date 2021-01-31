package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Objects;
import utils.Listeners.TestListener;

import java.text.ParseException;

@Listeners({ TestListener.class })
@Epic("Regression Tests")
@Feature("Event Tests")
public class EventsTests extends BaseTest {



    @Test (priority = 0, description="Preview upcoming events")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Preview upcoming events")
    @Story("The number of cards equals to the counter on the Upcoming Events button")
    public void numberOfCardsEqualsToCounter (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyEvents(Objects.upcomingEventsCounter, Objects.upcomingEvents, Objects.numberOfupcomingEvents);
    }

    @Test (priority = 1, description="Verify order of displayed blocks")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verify order of displayed blocks")
    @Story("Verify order of displayed blocks with information in the event card")
    public void orderOfDisplayedBlocks (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyOrderOfInformation();
    }

    @Test (priority = 0, description="Preview upcoming events")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Verify This week date of upcoming events")
    @Story("This week date is more or equals to current date, are in the current week range")
    public void verifyThisWeekDate () throws ParseException {
        homePage
                .goToEpam()
                .goToEventsPage()
                .getEventsDate(Objects.allUpcomingSections, Objects.allCards)
                .thisWeekEvents();

    }

    @Test (priority = 1, description="Verify Past Events in Canada")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Verify date of past events")
    @Story("Verify that past events in Canada have dates in the past")
    public void pastEventsCanada () throws InterruptedException, ParseException {
        homePage
                .goToEpam()
                .goToEventsPage()
                .pastEventsCanada()
                .verifyEvents(Objects.pastEventsCounter, Objects.pastEvents, Objects.numberOfPastEvents)
                .getEventsDate(Objects.allPastSection, Objects.allCards);

    }
}
