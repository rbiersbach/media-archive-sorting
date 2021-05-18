package de.rbiersbach.mas
import scala.collection.JavaConverters._
import de.rbiersbach.mas.adapters.files.NioFileStreamAdapter
import de.rbiersbach.mas.adapters.files.Path

@main def hello: Unit =
  val fileStreamAdapter = NioFileStreamAdapter

  fileStreamAdapter.streamResourcesDepthSearch(Path(List(""))).foreach(println)

