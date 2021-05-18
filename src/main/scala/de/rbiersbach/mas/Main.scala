package de.rbiersbach.mas
import scala.collection.JavaConverters._
import de.rbiersbach.mas.adapters.files.NioFileStreamAdapter
import de.rbiersbach.mas.adapters.files.Path
import de.rbiersbach.mas.adapters.files.visualize
@main def hello: Unit =
  val fileStreamAdapter = NioFileStreamAdapter
  fileStreamAdapter.streamResourcesDepthSearch(Path(List(""))).map(_.visualize).foreach(println)

