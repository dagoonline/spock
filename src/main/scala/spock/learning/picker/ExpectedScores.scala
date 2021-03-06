package spock.learning.picker

import spock._
import spock.util.Choose

/** Expected score of a guesser depending on the value to guess */
case class ExpectedScores(averageScores: Vector[Double]) {
  require(averageScores.size == MaxValue, s"Invalid vector size: $averageScores")
  require(averageScores.forall(_ >= 0), s"Negative expected scores: $averageScores")

  def asMap: Map[Int, Double] = (MinValue to MaxValue).zip(averageScores).toMap

  def chooseAmongBest(percentile: Double): Int = {
    val threshold = averageScores.max * percentile
    val eligible = for {
      (score, index) <- averageScores.zipWithIndex if score >= threshold
    } yield index + 1
    Choose.randomly(eligible)
  }
}

object ExpectedScores {
  def linearCombination(elems: Seq[(Double, ExpectedScores)]): ExpectedScores = {
    require(elems.nonEmpty)
    val accum = Array.fill(MaxValue)(0d)
    for {
      (factor, ExpectedScores(vector)) <- elems
      (score, index) <- vector.zipWithIndex
    } {
      accum(index) += factor * score
    }
    ExpectedScores(accum.toVector)
  }
}
