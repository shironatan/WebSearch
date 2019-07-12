package com.test.spark
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.spark.streaming.{Milliseconds,StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.{Level,Logger}
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.kafka.KafkaUtils

object KafkaWorker{
	def main(args: Array[String]){
		if(args.length < 2){
			println("Please your zookeeper URL, Topic name")
			System.exit(1)
		}
		val Array(zkQuorum, topics) = args
		Logger.getRootLogger.setLevel(Level.WARN)
		val sparkConf = new SparkConf().setAppName("KafkaWorker")
		val sc = new SparkContext(sparkConf)
		val ssc = new StreamingContext(sc, Milliseconds(5000))
		ssc.checkpoint("/path/to/file/checkpoint")
		val kafkaStream = KafkaUtils.createStream(ssc,zkQuorum,"default",Map(topics -> 1))
		kafkaStream.print()

		ssc.start()
		ssc.awaitTermination()
	}
}
		
