package com.example.backend.exceptionhand;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public interface Scraper {
    Map<String, JSONObject> scrape() throws IOException;
}