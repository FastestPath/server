package co.fastestpath.api.schedule;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Singleton
public class ScheduleCsvParser {

  private static final CsvSchema SCHEMA = CsvSchema.emptySchema()
      .withHeader()
      .withColumnSeparator(',');

  private final CsvMapper mapper;

  @Inject
  public ScheduleCsvParser(CsvMapper mapper) {
    this.mapper = mapper;
  }

  public <T> List<T> parseCsv(Path csv, Class<T> clazz) throws ScheduleParseException {
    try {
      MappingIterator<T> iterator = mapper.reader(clazz)
          .with(SCHEMA)
          .readValues(csv.toFile());
      return toList(iterator);
    } catch (IOException e) {
      throw new ScheduleParseException("Unable to map csv to class.", e);
    }
  }

  private static <T> List<T> toList(Iterator<T> iterator) {
    List<T> values = new ArrayList<>();
    iterator.forEachRemaining(values::add);
    return values;
  }
}
