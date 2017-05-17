package co.fastestpath.api.schedule;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResource {

  private final ScheduleManager scheduleManager;

  @Inject
  public ScheduleResource(ScheduleManager scheduleManager) {
    this.scheduleManager = scheduleManager;
  }

  @GET
  @Path("/stops")
  public Response getStops() {
    Schedule schedule = scheduleManager.getSchedule();
    Set<StopId> parentStopIds = schedule.getStops()
        .getParentStopIds();

    return Response.ok()
        .entity(parentStopIds)
        .build();
  }
}