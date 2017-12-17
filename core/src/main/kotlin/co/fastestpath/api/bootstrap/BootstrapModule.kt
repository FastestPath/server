package co.fastestpath.api.bootstrap

import co.fastestpath.api.utils.DocumentFetcher
import co.fastestpath.api.utils.JsoupDocumentFetcher
import co.fastestpath.api.utils.ThreadedTaskScheduler
import co.fastestpath.api.utils.configuredCsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.google.inject.AbstractModule
import com.google.inject.Scopes

class BootstrapModule : AbstractModule() {

  override fun configure() {
    bind(TaskScheduler::class.java).`in`(Scopes.SINGLETON).to(ThreadedTaskScheduler::class.java)
    bind(DocumentFetcher::class.java).`in`(Scopes.SINGLETON).to(JsoupDocumentFetcher::class.java)
    bind(CsvMapper::class.java).toInstance(configuredCsvMapper())
  }
}