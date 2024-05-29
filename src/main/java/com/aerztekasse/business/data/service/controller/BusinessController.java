package com.aerztekasse.business.data.service.controller;

import com.aerztekasse.business.data.service.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/place/{id}")
    public ResponseEntity getPlaceInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(businessService.getPlaceInfo(id));
    }

}
