package co.fastestpath.api.gtfs;

class GtfsArchiveDateFetchingException extends Exception {

  public GtfsArchiveDateFetchingException(String message) {
    super(message);
  }

  public GtfsArchiveDateFetchingException(String message, Exception e) {
    super(message, e);
  }
}
