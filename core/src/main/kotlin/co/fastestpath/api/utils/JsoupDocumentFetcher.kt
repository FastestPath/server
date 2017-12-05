package co.fastestpath.api.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.net.URL

class JsoupDocumentFetcher : DocumentFetcher{
  @Throws(IOException::class)
  override fun fetch(url: URL): Document {
    return Jsoup.connect(url.toString()).get()
  }
}