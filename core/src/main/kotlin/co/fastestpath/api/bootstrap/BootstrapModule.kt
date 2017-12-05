package co.fastestpath.api.bootstrap

import co.fastestpath.api.utils.DocumentFetcher
import co.fastestpath.api.utils.JsoupDocumentFetcher
import co.fastestpath.api.utils.configuredCsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.google.inject.AbstractModule

class BootstrapModule : AbstractModule() {

  override fun configure() {
    bind(DocumentFetcher::class.java).to(JsoupDocumentFetcher::class.java)
    bind(CsvMapper::class.java).toInstance(configuredCsvMapper())
  }
}