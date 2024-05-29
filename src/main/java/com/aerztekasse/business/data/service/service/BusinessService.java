package com.aerztekasse.business.data.service.service;

import com.aerztekasse.business.data.service.data.DataStore;
import com.aerztekasse.business.data.service.exception.PlaceNotFoundException;
import com.aerztekasse.business.data.service.model.Place;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessService {

    public Place getPlaceInfo(long placeId) {
        if (DataStore.places == null) {
            log.error("Data was not imported correctly");
            throw new RuntimeException();
        }
        return DataStore.places.stream()
                .filter(placeObj -> placeObj.getId() == placeId)
                .findFirst()
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
    }

}
