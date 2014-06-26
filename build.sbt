name := "scala-yo"

version := "0.1"

scalaVersion := "2.11.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test"
)
