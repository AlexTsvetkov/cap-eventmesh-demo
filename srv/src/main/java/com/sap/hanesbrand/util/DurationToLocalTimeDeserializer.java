package com.sap.hanesbrand.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationToLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String duration = p.getText(); // Get the duration string

        // Define a regular expression pattern to match the duration format
        Pattern pattern = Pattern.compile("PT(\\d{2})H(\\d{2})M(\\d{2})S");
        Matcher matcher = pattern.matcher(duration);

        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (matcher.matches()) {
            // Extract hours, minutes, and seconds
            hours = Integer.parseInt(matcher.group(1));
            minutes = Integer.parseInt(matcher.group(2));
            seconds = Integer.parseInt(matcher.group(3));
        }

        // Create a LocalTime object based on the extracted values
        return LocalTime.of(hours, minutes, seconds);
    }
}
