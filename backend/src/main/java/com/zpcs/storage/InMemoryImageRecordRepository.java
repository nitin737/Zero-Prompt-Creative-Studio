package com.zpcs.storage;

import com.zpcs.model.ImageRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryImageRecordRepository implements ImageRecordRepository {

    private final ConcurrentMap<String, ImageRecord> store = new ConcurrentHashMap<>();

    @Override
    public ImageRecord save(ImageRecord record) {
        store.put(record.getId(), record);
        return record;
    }

    @Override
    public Optional<ImageRecord> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Page<ImageRecord> findAll(Pageable pageable) {
        List<ImageRecord> all = new ArrayList<>(store.values());
        // Sort by createdAt descending
        all.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), all.size());
        List<ImageRecord> pageContent = start < all.size() ? all.subList(start, end) : List.of();

        return new PageImpl<>(pageContent, pageable, all.size());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
