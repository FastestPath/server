package co.fastestpath.api.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class JsoupDocumentFetcher : DocumentFetcher{
  @Throws(IOException::class)
  override fun fetch(url: String): Document {
    return Jsoup.connect(url).get()
  }
}