package co.fastestpath.utils.documentfetcher

import org.jsoup.nodes.Document
import java.io.IOException
import java.net.URL

interface DocumentFetcher {
  @Throws(IOException::class)
  fun fetch(url: URL): Document
}
