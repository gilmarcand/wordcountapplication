package br.com.jusbrasil

import org.apache.hadoop.conf.Configuration
import org.apache.spark.ml.feature.StopWordsRemover
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.desc


object WordCountApp {
  def main(args: Array[String]): Unit = {
    val master = if(System.getProperty("spark.master")!=null) System.getProperty("spark.master") else "local[*]"
    val inputFile = if(System.getProperty("inputFile")!=null) System.getProperty("inputFile") else "input.txt"
    val sparkSession = SparkSession
                .builder
                .master(master)
                .appName("WordCountApplication")
                .getOrCreate()
    val hadoopConfig: Configuration = sparkSession.sparkContext.hadoopConfiguration
    hadoopConfig.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName)
    hadoopConfig.set("fs.file.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName)

    val wordDataFrame =  filterAndCount(sparkSession, inputFile)

    //show information
    wordDataFrame.select("*").orderBy(desc("num")).show(wordDataFrame.count().intValue())

    sparkSession.stop()
  }

  def filterAndCount(sparkSession: SparkSession, inputFile:String) = {
    val stopWords = StopWordsRemover.loadDefaultStopWords("portuguese")
    val words = sparkSession.sparkContext.textFile(inputFile)
                      .flatMap(line => line.split(" "))
                        .filter(word => word.length >= 5 && !stopWords.contains(word.toLowerCase))
                        .map(word => (word, 1))
                        .reduceByKey(_ + _)
    sparkSession.createDataFrame(words).toDF("word", "num")
  }
}