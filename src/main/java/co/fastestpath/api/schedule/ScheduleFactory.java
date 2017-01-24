package co.fastestpath.api.schedule;

import co.fastestpath.api.schedule.models.*;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Singleton
class ScheduleFactory {

  private static final CsvSchema SCHEMA = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');

  private final CsvMapper mapper;

  private final StationGrouper stationGrouper;

  private final SequenceJoiner sequenceJoiner;

  @Inject
  public ScheduleFactory(CsvMapper mapper, StationGrouper stationGrouper, SequenceJoiner sequenceJoiner) {
    this.mapper = mapper;
    this.stationGrouper = stationGrouper;
    this.sequenceJoiner = sequenceJoiner;
  }

  public Schedule createFromCSV(Path stopIds, Path stopTimes, Instant modifiedOn) throws IOException {
    List<Stop> stops = readStops(stopIds);
    List<Arrival> arrivals = readTrips(stopTimes);

    List<Station> stations = stationGrouper.groupByStation(stops);
    List<Sequence> sequences = sequenceJoiner.joinArrivals(arrivals, stations);

    Multimap<String, Sequence> departureMap = createDepartureMap(sequences);

    return new Schedule(departureMap, modifiedOn);
  }

  /**
   * Creates a map of station names and sequences.
   */
  private Multimap<String, Sequence> createDepartureMap(List<Sequence> sequences) {
    Multimap<String, Sequence> departureMap = LinkedListMultimap.create();
    sequences.forEach((sequence) -> {

      // For each sequence, adds the following entries:
      // [ Hob ]  - Hob, 9th, 14th, 23rd, 33rd
      // [ 9th ]  - 9th, 14th, 23rd, 33rd
      // [ 14th ] - 14th, 23rd, 33rd
      // [ 23rd ] - 23rd, 33rd

      LinkedList<Arrival> sequenceArrivals = sequence.getArrivals();
      while (sequenceArrivals.size() > 1) {
        Arrival arrival = sequenceArrivals.peek();
        LinkedList<Arrival> arrivalsClone = new LinkedList<>(sequenceArrivals);
        departureMap.put(arrival.getStation().getName(), new Sequence(arrivalsClone));
        sequenceArrivals.pop();
      }
    });
    return departureMap;
  }

  private List<Stop> readStops(Path stopIds) throws IOException {
    MappingIterator<Stop> iterator = mapper.reader(Stop.class).with(SCHEMA).readValues(stopIds.toFile());
    return Lists.newArrayList(iterator);
  }

  private List<Arrival> readTrips(Path stopTimes) throws IOException {
    MappingIterator<Arrival> iterator = mapper.reader(Arrival.class).with(SCHEMA).readValues(stopTimes.toFile());
    return Lists.newArrayList(iterator);
  }
}
