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



    @Test (priority = 0, groups ={"events"} ,description="Test 1: Preview upcoming events")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Preview upcoming events")
    @Story("Verify upcoming events")
    public void numberOfCardsEqualsToCounter (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyEvents(Objects.upcomingEventsCounter, Objects.upcomingEvents, Objects.numberOfUpcomingEvents);
    }

    @Test (priority = 0, groups ={"events"} , description="Test 2: Verify order of displayed blocks")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verify order of displayed blocks")
    @Story("Verify upcoming events")
    public void orderOfDisplayedBlocks (){
        homePage
                .goToEpam()
                .goToEventsPage()
                .verifyOrderOfInformation();
    }


}
