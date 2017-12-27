package co.fastestpath.api.bootstrap

import co.fastestpath.api.utils.*
import co.fastestpath.scheduler.TaskScheduler
import co.fastestpath.scheduler.ThreadedTaskScheduler
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.google.inject.AbstractModule

class BootstrapModule : AbstractModule() {

  override fun configure() {
    bind(TaskScheduler::class.java).to(ThreadedTaskScheduler::class.java)
    bind(DocumentFetcher::class.java).to(JsoupDocumentFetcher::class.java)
    bind(CsvMapper::class.java).toInstance(configuredCsvMapper())
  }
}
