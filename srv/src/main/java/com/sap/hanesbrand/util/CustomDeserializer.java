package com.sap.hanesbrand.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final Pattern DATE_PATTERN = Pattern.compile("/Date\\((\\d+)\\)/");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String jsonValue = p.getValueAsString();
        Matcher matcher = DATE_PATTERN.matcher(jsonValue);
        if (matcher.matches()) {
            long timestamp = Long.parseLong(matcher.group(1));
            return LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(timestamp),
                    ZoneId.systemDefault()
            );
        } else {
            throw new IllegalArgumentException("Invalid date format: " + jsonValue);
        }
    }
}