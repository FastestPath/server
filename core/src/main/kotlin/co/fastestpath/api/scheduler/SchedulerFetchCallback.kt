package co.fastestpath.api.scheduler

import co.fastestpath.api.bootstrap.gtfs.archive.Archive

interface ScheduleFetchCallback {

  fun onSuccess(archive: Archive)
  fun onFailure()

}