package co.fastestpath.api.gtfs;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Singleton
public class GtfsEntityMapper {

  private final CsvMapper mapper;

  @Inject
  public GtfsEntityMapper(CsvMapper mapper) {
    this.mapper = mapper;
  }

  public GtfsEntityMap map(Map<GtfsEntityType, File> fileMap) {
    GtfsEntityMap.Builder builder = GtfsEntityMap.builder();
    for (GtfsEntityType entityType : fileMap.keySet()) {
      File entityFile = fileMap.get(entityType);
      List<GtfsEntity> entities = createEntity(entityFile, entityType.getClazz());
      builder.put(entityType, entities);
    }
    return builder.build();
  }

  private <T extends GtfsEntity> List<T> createEntity(File entityFile, Class<T> entityClass)
      throws GtfsEntityMappingException {
    try {
      return (List<T>) mapper.readerFor(entityClass)
          .with(GtfsArchive.SCHEMA)
          .readValues(entityFile)
          .readAll();
    } catch (IOException e) {
      throw new GtfsEntityMappingException("Unable to map csv to class.", e);
    }
  }
}
