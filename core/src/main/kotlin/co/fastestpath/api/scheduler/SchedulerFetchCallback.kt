package co.fastestpath.api.scheduler

interface ScheduleFetchCallback {

  fun onSuccess(archive: ScheduleArchive)
  fun onFailure()

}