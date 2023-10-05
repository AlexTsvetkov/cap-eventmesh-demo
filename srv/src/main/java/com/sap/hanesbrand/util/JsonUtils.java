package com.sap.hanesbrand.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
    public static ObjectMapper outboundDeliveryMapper = new ObjectMapper();
    private static final ObjectMapper DTO_MAPPER = new ObjectMapper();

    static {
        outboundDeliveryMapper.registerModule(new JavaTimeModule());
        outboundDeliveryMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DTO_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DTO_MAPPER.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    }

    public static <T> T from(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return DTO_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("Cannot parse string '{}' to object {}", jsonString, clazz, e);
        }
        return null;
    }
}
