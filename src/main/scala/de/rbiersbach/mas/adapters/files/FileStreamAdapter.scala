package de.rbiersbach.mas.adapters.files

trait FileStreamAdapter:
  /**
   * recursively stream all resources under the specified path using depth search
   * @param path file system path as a starting point
   * @return a stream of resources that can be fetched lazily
   */
  def streamResourcesDepthSearch(path: Path): Iterator[Resource]
