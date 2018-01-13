package co.fastestpath.api.schedule

import com.google.inject.AbstractModule

class ScheduleModule : AbstractModule() {

  private var schedule: Schedule? = null

  override fun configure() {
    // TODO
  }

  fun update(schedule: Schedule) {
    this.schedule = schedule
  }
}
