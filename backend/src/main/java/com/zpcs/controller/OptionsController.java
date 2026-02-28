package com.zpcs.controller;

import com.zpcs.service.OptionsProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
@Tag(name = "Options")
public class OptionsController {

    private final OptionsProvider optionsProvider;

    @GetMapping
    @Operation(summary = "Get all dropdown options")
    public ResponseEntity<Map<String, List<Map<String, String>>>> getOptions() {
        return ResponseEntity.ok(optionsProvider.getAllOptions());
    }
}
