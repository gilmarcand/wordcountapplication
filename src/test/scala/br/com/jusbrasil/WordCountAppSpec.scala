package br.com.jusbrasil

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.scalatest.FreeSpec


class WordCountAppSpec extends FreeSpec  with DataFrameComparer with SparkSessionTestWrapper{

  "Validate dataframe" in {
    import spark.implicits._

    val dataFrame = WordCountApp.filterAndCount(spark,"teste.txt")
    val expectedDF = Seq(("Gilmar", 1)).toDF("word","num")
    assertSmallDataFrameEquality(dataFrame,expectedDF)
  }

}
