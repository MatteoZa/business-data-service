package com.aerztekasse.business.data.service.controller;

import com.aerztekasse.business.data.service.controller.model.Error;
import com.aerztekasse.business.data.service.model.Place;
import com.aerztekasse.business.data.service.util.DataImport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
class BusinessControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp() {
        DataImport.importPlacesData();
    }

    @Test
    void placeIdNotFound() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/business/place/3"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
        Error error = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Error.class);

        assertEquals(404, error.getStatus());
        assertEquals("Place with id 3 not found", error.getMessage());
    }

    @Test
    void placeIdNotLong() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/business/place/abc"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andReturn();
        Error error = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Error.class);

        assertEquals(500, error.getStatus());
        assertEquals("Something went wrong", error.getMessage());
    }

    @Test
    void returnsValidPlace() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/business/place/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
        Place place = new ObjectMapper().readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Place.class);

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
