package com.zpcs.storage;

import com.zpcs.model.ImageRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Repository interface for image records (DIP).
 */
public interface ImageRecordRepository {
    ImageRecord save(ImageRecord record);

    Optional<ImageRecord> findById(String id);

    Page<ImageRecord> findAll(Pageable pageable);

    void deleteById(String id);
}
