package com.zpcs.storage;

import org.springframework.core.io.Resource;

/**
 * Interface Segregation: only CRUD operations, no business logic.
 */
public interface StorageService {
    String save(byte[] data, String id);

    Resource load(String id);

    boolean delete(String id);

    boolean exists(String id);
}
