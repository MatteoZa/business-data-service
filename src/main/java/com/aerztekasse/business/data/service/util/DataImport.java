package com.aerztekasse.business.data.service.util;

import com.aerztekasse.business.data.service.data.DataStore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@Slf4j
@UtilityClass
public class DataImport {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void importPlacesData() {
        try {
            File placesFile = new ClassPathResource("data/places.json").getFile();
            DataStore.places = MAPPER.readValue(placesFile, new TypeReference<>(){});
        } catch (IOException e) {
            log.error("Error importing data");
            throw new RuntimeException();
        }
    }

}
