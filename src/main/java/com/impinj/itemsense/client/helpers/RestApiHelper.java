package com.impinj.itemsense.client.helpers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jcombopi on 1/25/16.
 */
public class RestApiHelper<T> {
    private Class<T> type;

    public RestApiHelper(Class<T> type) {
        this.type = type;
    }

    public Response post(T request, String path, WebTarget target) {
        return target.path(path)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request));
    }

    public Response put(T request, String path, WebTarget target) {
        return target.path(path)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(request));
    }

    public Response put(String path, WebTarget target) {
        return target.path(path)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .put(Entity.json(""));
    }

    public Response delete(String id, String path, WebTarget target) {
        return target.path(path + "/" + id)
                .request(MediaType.APPLICATION_JSON_TYPE).delete();

    }

    public Response get(String path, WebTarget target) {
        return target.path(path).request(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response get(String id, String path, WebTarget target) {
        return target.path(path + "/" + id).request(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response get(Map<String, Object> queryParams, String path, WebTarget target) {

        target = target.path(path);
        if (queryParams != null) {
            for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
                target = target.queryParam(queryParam.getKey(), queryParam.getValue());
            }
        }
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }

    public T readObjectFromString(String string) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        try {
            return mapper.readValue(string, type);
        } catch (IOException ioe ) {
            System.err.println("Could not read class from string");
            System.err.println(ioe);
            return null;
        }

    }

    public List<T> readObjectsFromString(String string) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        JavaType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, type) ;

        try {
            return mapper.readValue(string, listType);
        } catch (IOException ioe ) {
            System.err.println("Could not read class from string");
            System.err.println(ioe);
            return null;
        }

    }
}


