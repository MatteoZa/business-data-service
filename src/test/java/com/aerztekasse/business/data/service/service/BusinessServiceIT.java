package com.aerztekasse.business.data.service.service;

import com.aerztekasse.business.data.service.exception.PlaceNotFoundException;
import com.aerztekasse.business.data.service.model.Place;
import com.aerztekasse.business.data.service.util.DataImport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessServiceIT {

    @Autowired
    private BusinessService businessService;

    @BeforeAll
    public static void setUp() {
        DataImport.importPlacesData();
    }

    @Test
    void placeIdNotFound() {
        assertThrows(PlaceNotFoundException.class, () -> businessService.getPlaceInfo(3));
    }

    @Test
    void returnsValidPlace() {
        Place place = businessService.getPlaceInfo(1);

        assertEquals(1, place.getId());
        assertEquals("Le Café du Marché", place.getName());
        assertEquals("Rue de Conthey 17, 1950 Sion", place.getAddress());
        assertEquals(6, place.getOpeningHours().size());
        assertNull(place.getOpeningHours().get(DayOfWeek.MONDAY));
        assertEquals(2, place.getOpeningHours().get(DayOfWeek.TUESDAY).size());
        assertEquals(2, place.getOpeningHours().get(DayOfWeek.WEDNESDAY).size());
        assertEquals(2, place.getOpeningHours().get(DayOfWeek.THURSDAY).size());
        assertEquals(2, place.getOpeningHours().get(DayOfWeek.FRIDAY).size());
        assertEquals(1, place.getOpeningHours().get(DayOfWeek.SATURDAY).size());
        assertEquals(1, place.getOpeningHours().get(DayOfWeek.SUNDAY).size());
	}

}
