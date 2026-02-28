package com.zpcs.controller;

import com.zpcs.model.ImageRecord;
import com.zpcs.service.GalleryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gallery")
@RequiredArgsConstructor
@Tag(name = "Gallery")
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping
    @Operation(summary = "List generation history (paginated)")
    public ResponseEntity<Page<ImageRecord>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(galleryService.listImages(page, size));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a generated image")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        galleryService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
