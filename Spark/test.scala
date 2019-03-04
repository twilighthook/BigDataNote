import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object test {
  def main(args: Array[String]) : Unit= {

    val inputFile = args(0)
    val outputFile = args(1)

    //create a SparkContext object
    val conf = new SparkConf().setMaster("local").setAppName("MyApp")
    val sc = new SparkContext(conf)

    //load the input data
    val input = sc.textFile(inputFile)

    // Split up into words.
    val words = input.flatMap(line => line.split(" "))
    
    // Transform into word and count.
    val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    
    // Save the word count back out to a text file, causing evaluation.
    counts.saveAsTextFile(outputFile)

  }
}