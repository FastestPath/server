package co.fastestpath.api.scheduler

import co.fastestpath.api.bootstrap.archive.Archive

interface ScheduleFetchCallback {

  fun onSuccess(archive: Archive)
  fun onFailure()

}
