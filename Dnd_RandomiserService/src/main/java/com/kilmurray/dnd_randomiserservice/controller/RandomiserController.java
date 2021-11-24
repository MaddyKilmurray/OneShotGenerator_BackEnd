package com.kilmurray.dnd_randomiserservice.controller;

import com.kilmurray.dnd_randomiserservice.service.RandomiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomiserController {

    final
    RandomiserService randomiserService;

    public RandomiserController(RandomiserService randomiserService) {
        this.randomiserService = randomiserService;
    }


}
