package co.fastestpath.api.schedule;

import co.fastestpath.api.gtfs.GtfsArchiveManager;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Optional;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResource {

  private final GtfsArchiveManager scheduleManager;

  @Inject
  public ScheduleResource(GtfsArchiveManager scheduleManager) {
    this.scheduleManager = scheduleManager;
  }

  @GET
  public Response getSchedule(@QueryParam("from") StationName from, @QueryParam("to") StationName to,
      @QueryParam("departAt") String departAt) {

    Optional<Departure> departure = scheduleManager.getDeparture(from, to, Instant.parse(departAt));

    if (!departure.isPresent()) {
      return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
          .build();
    }

    return Response.ok()
        .entity(scheduleManager.getDeparture(from, to, Instant.parse(departAt)))
        .build();
  }
}