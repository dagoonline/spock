package spock.learning.guesser

import spock.Range.NonEmpty
import spock._
import spock.learning.guesser.distro.{PickerDistro, DistroEstimator}

class FrequencyDistroEstimator(var frequencies: Map[Int, Double]) extends DistroEstimator {

  override def distro = PickerDistro.normalize(frequencies)

  override def learn(observation: NonEmpty): Unit = {
    val weight = 1d / observation.size
    observation.iterable.foreach { observation =>
      frequencies += observation -> (frequencies(observation) + weight)
    }
  }

  override def toString = "frequentist"
}

object FrequencyDistroEstimator {
  def uniformPrior(weight: Double) =
    new FrequencyDistroEstimator((MinValue to MaxValue).zip(Stream.continually(weight)).toMap)
}
