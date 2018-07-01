name := "grpc-tryout"

version := "0.1"

scalaVersion := "2.12.6"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)


// compiles protobuf definitions into scala code
PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
