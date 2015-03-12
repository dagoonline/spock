package spock.arbiter

import spock.Strategy

object BenchmarkMain extends App {
  new Tournament(Strategy.Pickers, Strategy.Guessers).run(100)
}
