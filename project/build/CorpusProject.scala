import sbt._

class CorpusProject(info: ProjectInfo) extends DefaultProject(info) {

  lazy val akka = project("akka", "akka", new DefaultProject(_) { /* git 57d0e85a9adeb088fbe4b2cb7140647cdbc1c432 */
    lazy val akka_actor = project("akka-actor", "akka-actor", new DefaultProject(_))
    lazy val akka_stm = project("akka-stm", "akka-stm", new DefaultProject(_), akka_actor)
    lazy val akka_typed_actor = project("akka-typed-actor", "akka-typed-actor", new DefaultProject(_), akka_stm)
    lazy val akka_remote = project("akka-remote", "akka-remote", new DefaultProject(_), akka_typed_actor)
    lazy val akka_http = project("akka-http", "akka-http", new DefaultProject(_), akka_remote)
    lazy val akka_samples = project("akka-samples", "akka-samples", new DefaultProject(_) {
      lazy val akka_sample_ants = project("akka-sample-ants", "akka-sample-ants", new DefaultProject(_), akka_stm)
      lazy val akka_sample_fsm = project("akka-sample-fsm", "akka-sample-fsm", new DefaultProject(_), akka_actor)
      lazy val akka_sample_remote = project("akka-sample-remote", "akka-sample-remote", new DefaultProject(_), akka_remote)
    })
  })
  lazy val casbah = project("casbah", "casbah", new DefaultProject(_) { /* git 4c4e62442ba4bdf55388b139acb27e2d871dbe11 */
    lazy val commons = project("casbah-commons", "casbah-commons", new DefaultProject(_))
    lazy val core = project("casbah-core", "casbah-core", new DefaultProject(_), commons, query)
    lazy val query = project("casbah-query", "casbah-query", new DefaultProject(_), commons)
    lazy val gridfs = project("casbah-gridfs","casbah-gridfs", new DefaultProject(_), core)
  })
  lazy val ensime = project("ensime", "ensime", new DefaultProject(_)) /* git d3a4de5805e2b98fd0cb15783af6a6d4b83d535c */
  lazy val flashup = project("flashup", "flashup", new DefaultProject(_)) /* git 61aaf4159ef739068295efa03dd08ccbde76b600 */
  lazy val scalaz = project("scalaz", "scalaz", new DefaultProject(_) with ScalazBoilerplate { /* git 32757b4ed1f59dfbd121c9e483f06dada46ff792 */
    override def mainSourceRoots = super.mainSourceRoots +++ srcManagedScala##
    override def compileAction = super.compileAction dependsOn(generateTupleW)
  })
  lazy val scalaswing = project("scala-swing", "scala-swing", new DefaultProject(_)) /* git 5b45ba65a6afa15b6083bc4c0654d551a379e9a3 */
  lazy val scalaquery = project("scala-query", "scala-query", new DefaultProject(_)) /* git 0b9f9adfa15716ba4e5a659324000706ee1e42f7 */
  lazy val smile = project("smile", "smile", new com.twitter.sbt.StandardProject(_)) /* git ee41070f87e2a99b2556ebde6b505c3dd54e7b88 */
  lazy val squeryl = project("squeryl", "squeryl", new DefaultProject(_)) /* git 0286ac918fd2e74f8f65e1cafe693e4d8f15f4ec */
}
