package spock

import spock.util.LineOrientedIO

object Main {
  def main(args: Array[String]): Unit = args match {
    case Array("pick") => LineOrientedIO(new PickerRunner(Strategy.Picker.Bayesian))
    case Array("guess") => LineOrientedIO(new GuesserRunner(Strategy.Guesser.ExpectedValueBayesian))
    case unexpected =>
      println("Unexpected arguments: " + unexpected.mkString(" "))
      println("usage: java -jar <this jar> (pick | guess)")
  }
}
