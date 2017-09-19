package co.fastestpath.api.manager;

public interface FastestPathManaged {

  void start() throws Exception;

  void stop() throws Exception;

  int getPriority();

}
