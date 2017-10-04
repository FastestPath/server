package co.fastestpath.api.utils

import org.jsoup.nodes.Document
import java.io.IOException

interface DocumentFetcher {
  @Throws(IOException::class)
  fun fetch(url: String): Document
}