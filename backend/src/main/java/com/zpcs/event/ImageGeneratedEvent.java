package com.zpcs.event;

import com.zpcs.model.ImageRecord;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ImageGeneratedEvent extends ApplicationEvent {

    private final ImageRecord record;

    public ImageGeneratedEvent(Object source, ImageRecord record) {
        super(source);
        this.record = record;
    }
}
