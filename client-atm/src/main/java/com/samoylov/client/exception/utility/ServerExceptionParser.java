package com.samoylov.client.exception.utility;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ServerExceptionParser {
    public static JsonNode removeDetails(String responseBody) {
        try {
            ObjectNode node = (ObjectNode) new ObjectMapper().readTree(responseBody);
            node.remove("details");
            return node;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
