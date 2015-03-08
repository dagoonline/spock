package spock.learning

import spock.Range

/** Distribution estimator that learns with each observations */
trait DistroEstimator {
  def learn(observation: Range.NonEmpty): Unit
  def distro: Distro
}