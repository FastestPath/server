package co.fastestpath.api.gtfs;

public class GtfsEntityMappingException extends RuntimeException {

  public GtfsEntityMappingException(String message) {
    super(message);
  }

  public GtfsEntityMappingException(String message, Exception e) {
    super(message, e);
  }
}
