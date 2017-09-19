package co.fastestpath.api.bootstrap

import co.fastestpath.api.schedule.Schedule
import com.google.inject.AbstractModule

class ScheduleModule : AbstractModule() {

  private var schedule: Schedule? = null

  override fun configure() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun update(schedule: Schedule) {
    this.schedule = schedule
  }

}