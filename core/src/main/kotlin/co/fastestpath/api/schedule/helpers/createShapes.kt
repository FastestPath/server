package co.fastestpath.api.schedule.helpers

import co.fastestpath.api.schedule.Shape
import co.fastestpath.gtfs.GtfsShape

fun createShapes(shapeEntities: List<GtfsShape>): List<Shape> {
  return shapeEntities.map {
    Shape(
      id = it.id,
      latitude = it.latitude,
      longitude = it.longitude,
      sequence = it.sequence,
      distanceTraveled = it.distanceTraveled
    )
  }
}
