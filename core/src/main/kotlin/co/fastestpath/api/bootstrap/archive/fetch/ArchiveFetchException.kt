package co.fastestpath.api.bootstrap.archive.fetch

internal class ArchiveFetchException : Exception {
  constructor(message: String?) : super(message)
  constructor(message: String?, cause: Throwable?) : super(message, cause)
}
