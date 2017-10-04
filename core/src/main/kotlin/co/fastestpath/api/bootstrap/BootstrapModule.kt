package co.fastestpath.api.bootstrap

import co.fastestpath.api.utils.DocumentFetcher
import co.fastestpath.api.utils.JsoupDocumentFetcher
import com.google.inject.AbstractModule

// TODO: install this
class BootstrapModule : AbstractModule() {

  override fun configure() {
    bind(DocumentFetcher::class.java).to(JsoupDocumentFetcher())
  }
}