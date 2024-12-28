package com.campasklad.products.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/season")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeasonController {

}
