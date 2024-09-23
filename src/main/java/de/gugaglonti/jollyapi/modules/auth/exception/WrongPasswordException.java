package de.gugaglonti.jollyapi.modules.auth.exception;

public class WrongPasswordException extends RuntimeException {

  public WrongPasswordException(String message) {
    super(message);
  }
}
