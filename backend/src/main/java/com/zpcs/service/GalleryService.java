package com.zpcs.service;

import com.zpcs.model.ImageRecord;
import com.zpcs.storage.ImageRecordRepository;
import com.zpcs.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final ImageRecordRepository repository;
    private final StorageService storageService;

    public Page<ImageRecord> listImages(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public boolean deleteImage(String id) {
        repository.deleteById(id);
        return storageService.delete(id);
    }
}
