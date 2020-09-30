package io.github.javaasasecondlanguage.lecture03.practice4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {
    LocationService locationService;
    Location userLocation;

    @BeforeEach
    void setUp() {
        locationService = new LocationService(List.of(
                new Place("Soho",
                        new Location(51.5140777, -0.1369451),
                        List.of("Restaurant", "Pub", "Club")
                ),
                new Place("Baker Street",
                        new Location(51.520199, -0.1557377),
                        List.of("Museum", "Park", "Tourist", "Pub")
                ),
                new Place("Chelsea",
                        new Location(51.4877006, -0.1866126),
                        List.of("Posh", "Riverside", "Museum", "Restaurant")
                ),
                new Place("Wembley",
                        new Location(51.5475409, -0.2697519),
                        List.of("Stadium", "Football", "Freddie")
                ),
                new Place("City",
                        new Location(51.515069, -0.1107947),
                        List.of("Finance", "Riverside", "Pub"))
        ));

        userLocation = new Location(51.5, 0);
    }

    @Test
    void noBarsFound() {
        assertTrue(locationService.findClosestByTag(userLocation, "Bar").isEmpty());
    }

    @Test
    void closestMuseum() {
        assertEquals("Baker Street", locationService.findClosestByTag(userLocation, "Museum").get().name());
    }

    @Test
    void closestRestaurant() {
        assertEquals("Soho", locationService.findClosestByTag(userLocation, "Restaurant").get().name());
    }

    @Test
    void mostCommonTagForSure() {
        assertEquals("Pub", locationService.mostCommonTag());
    }

}