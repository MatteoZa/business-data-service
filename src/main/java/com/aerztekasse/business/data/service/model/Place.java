package com.aerztekasse.business.data.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    Long id;
    String name;
    String address;
    Map<DayOfWeek, List<Time>> openingHours;

}
