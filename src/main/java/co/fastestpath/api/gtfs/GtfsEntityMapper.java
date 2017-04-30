package co.fastestpath.api.gtfs;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Singleton
public class GtfsEntityMapper {

  private final Path resourcePath;
  private final CsvMapper mapper;

  @Inject
  public GtfsEntityMapper(@Named("resources") Path resourcePath, CsvMapper mapper) {
    this.resourcePath = resourcePath;
    this.mapper = mapper;
  }

  public <T> List<T> map(GtfsEntity<T> entity) throws GtfsEntityMappingException {
    try {
      Path file = Paths.get(resourcePath + "/" + GtfsArchive.NAME);
      return (List<T>) mapper.readerFor(entity.getClazz())
          .with(GtfsArchive.SCHEMA)
          .readValues(file.toFile())
          .readAll();
    } catch (IOException e) {
      throw new GtfsEntityMappingException("Unable to map csv to class.", e);
    }
  }
}
