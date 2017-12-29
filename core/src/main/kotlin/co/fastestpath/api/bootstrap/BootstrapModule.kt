package co.fastestpath.api.bootstrap

import co.fastestpath.api.mappers.configuredCsvMapper
import co.fastestpath.utils.documentfetcher.DocumentFetcher
import co.fastestpath.utils.documentfetcher.JsoupDocumentFetcher
import co.fastestpath.utils.scheduler.SingleTaskScheduler
import co.fastestpath.utils.scheduler.TaskScheduler
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.google.inject.AbstractModule

class BootstrapModule : AbstractModule() {

  override fun configure() {
    bind(TaskScheduler::class.java).to(SingleTaskScheduler::class.java)
    bind(DocumentFetcher::class.java).to(JsoupDocumentFetcher::class.java)
    bind(CsvMapper::class.java).toInstance(configuredCsvMapper())
  }
}
