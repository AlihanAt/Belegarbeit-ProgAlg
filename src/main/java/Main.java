import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;

public class Main {
    public static void main(String[] args) {
        String logFile = "src/main/resources/data/German/Bis zum Nullpunkt des Seins - Kurd Lasswitz.txt"; // Should be some file on your system
        SparkSession session = SparkSession.builder().appName("Simple Test").config("spark.master", "local").getOrCreate();
        Dataset<String> logData = session.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        session.stop();
    }
}
