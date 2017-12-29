package co.fastestpath.api.bootstrap.archive.fetch

import co.fastestpath.api.bootstrap.archive.Archive
import co.fastestpath.api.bootstrap.archive.ArchiveEntity
import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.exception.ZipException
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import javax.net.ssl.HttpsURLConnection

class ArchiveFetcher {

  companion object {
    private val LOG = LoggerFactory.getLogger(ArchiveFetcher::class.java)
  }

  @Throws(ArchiveFetchException::class)
  fun fetch(archiveUrl: URL, savePath: Path): Archive {
    LOG.info("Creating empty directory at {}...", savePath)
    createEmptyDirectory(savePath)
    LOG.info("Created empty directory.")

    LOG.info("Fetching schedule from {}...", archiveUrl)
    downloadArchive(archiveUrl, savePath)
    LOG.info("Fetched schedule.")

    LOG.info("Extracting files from archive to {}...", savePath)
    extractAllFilesFromArchive(savePath)
    LOG.info("Extracted all files.")

    val entityMap = ArchiveEntity.getAll()
      .filter { Files.exists(createEntityPath(savePath, it.filename)) }
      .associateBy({ it }, { createEntityPath(savePath, it.filename).toFile() })

    return Archive(entityMap)
  }

  private fun createEntityPath(savePath: Path, filename: String): Path {
    return Paths.get(savePath.toString(), filename)
  }

  @Throws(ArchiveFetchException::class)
  private fun extractAllFilesFromArchive(savePath: Path) {
    try {
      val zipFile = ZipFile(savePath.toString())
      zipFile.extractAll(savePath.toString())
    } catch (e: ZipException) {
      throw ArchiveFetchException("Failed to unzip archive.", e)
    }
  }

  @Throws(ArchiveFetchException::class)
  private fun downloadArchive(archiveUrl: URL, savePath: Path) {
    try {
      openInputStream(archiveUrl).use { inputStream -> {
          Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING)
        }
      }
    } catch (e: IOException) {
      throw ArchiveFetchException("Failed to download archive from " + archiveUrl, e)
    }
  }

  @Throws(ArchiveFetchException::class)
  private fun openInputStream(archiveUrl: URL): InputStream {
    try {
      val connection = archiveUrl.openConnection() as HttpURLConnection
      val responseCode = connection.responseCode
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        throw ArchiveFetchException("Bad response code when fetching zip, " + responseCode)
      }
      return connection.inputStream
    } catch (e: IOException) {
      throw ArchiveFetchException("Unable to open a connection to " + archiveUrl, e)
    }
  }

  @Throws(ArchiveFetchException::class)
  private fun createEmptyDirectory(resourcesPath: Path) {
    if (Files.isDirectory(resourcesPath)) {
      try {
        FileUtils.cleanDirectory(resourcesPath.toFile())
      } catch (e: IOException) {
        throw ArchiveFetchException("Failed to empty directory.", e)
      }
      return
    }

    try {
      Files.createDirectory(resourcesPath)
    } catch (e: IOException) {
      throw ArchiveFetchException("Failed to create directory.", e)
    }
  }
}
