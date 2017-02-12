package co.fastestpath.api.schedule;

public class StationNotPresentException extends RuntimeException {

  public StationNotPresentException(String message) {
    super(message);
  }

  public StationNotPresentException(String message, Exception e) {
    super(message, e);
  }
}
