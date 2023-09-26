package com.sap.hanesbrand.client;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;

import com.sap.cloud.sdk.cloudplatform.connectivity.*;
import com.sap.cloud.sdk.cloudplatform.tenant.Tenant;
import com.sap.cloud.sdk.cloudplatform.tenant.TenantAccessor;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.hanesbrand.util.JsonUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Slf4j
@Component
public abstract class HttpDestinationClient {

    private static final String CONNECTION_ABORTED = "Connection was aborted {}";


    protected abstract String destinationName();

    public <T> T get(String path, Class<T> targetObj) {
        String response = getResponseAsString(path);

        return JsonUtils.from(response, targetObj);
    }

    public String getResponseAsString(String path) {
        HttpDestination httpDestination = getDestination(destinationName()).asHttp()
                .decorate(DefaultErpHttpDestination::new);
        HttpClient client = getHttpClient(httpDestination);
        try {
            String requestUrl = httpDestination.getUri() + path;
            log.info("GET response as a string: {} ", requestUrl);

            return client.execute(new HttpGet(requestUrl), getResponseHandler());
        } catch (IOException e) {
            log.error(CONNECTION_ABORTED, e);
        }

        return EMPTY;
    }


    private ResponseHandler<String> getResponseHandler() {
        return new BasicResponseHandler();
    }


}
