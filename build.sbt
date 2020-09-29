name := "WordCountApplication"
version := "0.1"
val scala = "2.12.2"
val sparkVersion = "2.4.7"
scalaVersion := scala
resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"
assemblyJarName in assembly :=  "WordCountApplication.jar"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scala
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-mllib" % sparkVersion

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "MrPowers" % "spark-fast-tests" % "0.20.0-s_2.12"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}