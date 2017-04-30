package co.fastestpath.api.managed;

public interface FastestPathManaged {

  void start() throws Exception;

  void stop() throws Exception;

  int getPriority();

}
