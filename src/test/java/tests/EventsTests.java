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



    @Test (priority = 0, description="Test 1: Preview upcoming events")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Preview upcoming events")
    @Story("Verify upcoming events")
    public void numberOfCardsEqualsToCounter (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyEvents(Objects.upcomingEventsCounter, Objects.upcomingEvents, Objects.numberOfUpcomingEvents);
    }

    @Test (priority = 1, description="Test 2: Verify order of displayed blocks")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verify order of displayed blocks")
    @Story("Verify upcoming events")
    public void orderOfDisplayedBlocks (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyOrderOfInformation();
    }

    @Test (priority = 0, description="Test 3: Preview upcoming events")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Verify This week date of upcoming events")
    @Story("Verify upcoming events")
    public void verifyThisWeekDate () throws ParseException {
        homePage
                .goToEpam()
                .goToEventsPage()
                .getEventsDate(Objects.allUpcomingSections, Objects.allCards)
                .thisWeekEvents();

    }

    @Test (priority = 1, description="Test 4: Verify Past Events in Canada")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Verify date of past events")
    @Story("Verify past events")
    public void pastEventsCanada () throws InterruptedException, ParseException {
        homePage
                .goToEpam()
                .goToEventsPage()
                .pastEventsCanada()
                .verifyEvents(Objects.pastEventsCounter, Objects.pastEvents, Objects.numberOfPastEvents)
                .getEventsDate(Objects.allPastSection, Objects.allCards)
                .thisWeekEvents();

    }

    @Test (priority = 1, description="Test 5: Preview event's details")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Preview detailed information about upcoming event")
    @Story("Verify upcoming events")
    public void previewEventDetails () {
        homePage
                .goToEpam()
                .goToEventsPage()
                .eventDetails();
    }

    @Test (priority = 1, description="Test 6: Verify videos by criteria")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Open videos and verify filters")
    @Story("Verify criteria for videos")
    public void verifyVideosByCriteria () throws InterruptedException {
        homePage
                .goToEpam()
                .goToVideoPage()
                .openVideosByCriteria()
                .verifyVideosShownByCriteria(Objects.allVideos, Objects.videoCardHref);
    }

    @Test (priority = 1, description="Test 7: Verify search for videos by word 'QA'")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Open videos and search by word QA")
    @Story("Verify criteria for videos")
    public void VerifySearchByQA () throws InterruptedException {
        homePage
                .goToEpam()
                .goToVideoPage()
                .videoSearch(Objects.allVideos, Objects.CardTitle);
    }

}
