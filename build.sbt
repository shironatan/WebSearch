name := "spark-simple-app6"
version := "0.1"
scalaVersion := "2.10.4"
val sparkVersion = "1.5.0"
val sparkDependencyScope = "provided"
libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "1.5.0" % "provided",
  "joda-time" % "joda-time" % "2.8.2",
  "org.apache.spark" %% "spark-core" % sparkVersion % sparkDependencyScope,
  "org.apache.spark" %% "spark-streaming" % sparkVersion % sparkDependencyScope,
  "org.apache.spark" %% "spark-streaming-kafka" % "1.5.0")

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}
