package co.fastestpath.api.schedule;

class ScheduleParseException extends Exception {

  public ScheduleParseException(String message) {
    super(message);
  }

  public ScheduleParseException(String message, Exception e) {
    super(message, e);
  }
}
