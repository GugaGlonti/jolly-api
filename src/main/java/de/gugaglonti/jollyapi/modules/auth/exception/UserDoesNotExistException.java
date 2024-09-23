package de.gugaglonti.jollyapi.modules.auth.exception;

public class UserDoesNotExistException extends RuntimeException {

  public UserDoesNotExistException(String message) {
    super(message);
  }
}
