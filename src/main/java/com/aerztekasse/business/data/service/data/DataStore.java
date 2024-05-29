package com.aerztekasse.business.data.service.data;

import com.aerztekasse.business.data.service.model.Place;
import lombok.Data;

import java.util.List;

@Data
public class DataStore {
    public static List<Place> places;
}
