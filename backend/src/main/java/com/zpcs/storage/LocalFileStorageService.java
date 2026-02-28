package com.zpcs.storage;

import com.zpcs.config.StorageProperties;
import com.zpcs.exception.ImageNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalFileStorageService implements StorageService {

    private final StorageProperties properties;
    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(properties.getBasePath()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(rootLocation);
            log.info("Storage initialized at: {}", rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public String save(byte[] data, String id) {
        try {
            Path target = rootLocation.resolve(id + ".png");
            Files.write(target, data);
            log.info("Saved image: {}", target);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + id, e);
        }
    }

    @Override
    public Resource load(String id) {
        try {
            Path file = rootLocation.resolve(id + ".png");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            throw new ImageNotFoundException(id);
        } catch (MalformedURLException e) {
            throw new ImageNotFoundException(id);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Path file = rootLocation.resolve(id + ".png");
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            log.error("Failed to delete image: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean exists(String id) {
        Path file = rootLocation.resolve(id + ".png");
        return Files.exists(file);
    }
}
