package co.fastestpath.api.schedule;

class ScheduleFetcherException extends Exception {

  public ScheduleFetcherException(String message) {
    super(message);
  }

  public ScheduleFetcherException(String message, Exception e) {
    super(message, e);
  }
}
