package com.zpcs.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Observer: reacts to generation events without coupling to orchestrator.
 * Future: analytics, notifications, usage tracking.
 */
@Component
@Slf4j
public class GalleryEventListener {

    @EventListener
    @Async
    public void onImageGenerated(ImageGeneratedEvent event) {
        log.info("Image generated: id={}, timeMs={}",
                event.getRecord().getId(),
                event.getRecord().getGenerationTimeMs());
    }
}
