package co.fastestpath.api.gtfs;

class GtfsArchiveFetchingException extends Exception {

  public GtfsArchiveFetchingException(String message) {
    super(message);
  }

  public GtfsArchiveFetchingException(String message, Exception e) {
    super(message, e);
  }
}
