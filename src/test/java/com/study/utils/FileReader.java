package com.study.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileReader {

    private static final String ROOT_PATH = "src/test/resources/";
    public static final String REQUEST_FILE_PATH = ROOT_PATH + "json/request/";
    private static final String RESPONSE_FILE_PATH = ROOT_PATH + "json/response/";

    public static JsonNode readRequestFile(String caminho) throws IOException {
        return readFile(REQUEST_FILE_PATH + caminho);
    }

    public static JsonNode readExpectedResponseFile(String caminho) throws IOException {
        return readFile(RESPONSE_FILE_PATH + caminho);
    }

    private static JsonNode readFile(String caminho) throws IOException {
        return new ObjectMapper().readTree(new File(caminho));
    }

}
