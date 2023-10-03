package com.sap.hanesbrand.config.vcap;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class VcapParser {

    private static final String VCAP_SERVICES = "VCAP_SERVICES";
    private static final String VCAP_APPLICATION = "VCAP_APPLICATION";
    private static final String DEFAULT_VALUE = "{}";

    private JsonNode vcapService;
    private JsonNode vcapApplication;

    VcapParser() {
        initializeEnvVariables();
    }

    private void initializeEnvVariables() {
        synchronized (this) {
            vcapService = getRootNode(getenv(VCAP_SERVICES, DEFAULT_VALUE));
            vcapApplication = getRootNode(getenv(VCAP_APPLICATION, DEFAULT_VALUE));
        }
    }

    public String getVcapApplication(String path) {
        synchronized (this) {
            return parse(this.vcapApplication, getPathArray(path));
        }
    }

    public String getVcapService(String path) {
        synchronized (this) {
            return parse(this.vcapService, getPathArray(path));
        }
    }

    private String[] getPathArray(String path) {
        path = (path.length() == 0) ? "." : path;
        path = path.replace("[", ".");
        path = path.replace("]", ".");
        path = path.replace("..", ".");
        return path.split("\\.");
    }

    private String parse(JsonNode rootNode, String... path) {
        JsonNode node = rootNode;
        for (String p : path) {
            if (node instanceof ArrayNode) {
                int i = Integer.parseInt(p);
                node = node.get(i);
            } else {
                node = node.get(p);
            }
        }
        return node.toString().replaceAll("^\"(.*)\"$", "$1"); // remove quotes "..."
    }

    public JsonNode getRootNode(String json) {
        try {
            return new ObjectMapper().readTree(json);
        } catch (IOException e) {
            log.error("Json Parsing Exception: {}", e.getMessage());
            return null;
        }
    }

    public String getenv(String name, String defaultValue) {
        String value = System.getenv(name);
        return (value != null) ? value : defaultValue;
    }
}