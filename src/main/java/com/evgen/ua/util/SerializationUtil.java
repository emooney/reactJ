package com.evgen.ua.util;

import org.json.JSONObject;

public class SerializationUtil {

  private SerializationUtil() {
    throw new UnsupportedOperationException();
  }

  public static JSONObject buildEmptyRespose(boolean error) {
    return new JSONObject().put("error", error);
  }

  public static JSONObject buildErrorResponse(String message) {
    return buildEmptyRespose(true).put("message", message);
  }
}

