package de.gugaglonti.jollyapi.common.errorhandling;

import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private static class ErrorResponseJsonBuilder {

    private final JsonObject jsonObject = new JsonObject();

    public ErrorResponseJsonBuilder addMessage(String message) {
      jsonObject.addProperty("message", message);
      return this;
    }

    public ErrorResponseJsonBuilder addStatus(HttpStatus status) {
      jsonObject.addProperty("status", status.value());
      jsonObject.addProperty("error", status.getReasonPhrase());
      return this;
    }

    public ErrorResponseJsonBuilder addTimestamp(long timestamp) {
      jsonObject.addProperty("timestamp", timestamp);
      return this;
    }

    @Override
    public String toString() {
      return jsonObject.toString();
    }
  }

  public static String toJson(Exception e, HttpStatus status) {
    return new ErrorResponseJsonBuilder()
        .addMessage(e.getMessage())
        .addStatus(status)
        .addTimestamp(System.currentTimeMillis())
        .toString();
  }
}