import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        SparkConf config = new SparkConf().
                setAppName("Belegaufgabe").
                setMaster("local[*]").
                set("spark.executor.cores", "1");

        // Spark Context
        JavaSparkContext context = new JavaSparkContext(config);

        LinkedList<LanguageResult> results = new LinkedList<>();

        for (Language lang: Language.values()) {
            results.add(getResult(context, lang));
        }

        printResult(results);
        context.stop();
    }

    public static LanguageResult getResult(JavaSparkContext con, Language lang){
        JavaRDD<String> inputFile = con.textFile(lang.getPath());

        JavaPairRDD<Integer, String> topWords = inputFile.flatMap(line -> Arrays.asList(line.split("\\P{L}+")).iterator()).
                mapToPair(word -> new Tuple2<>(word.length(),word)).
                reduceByKey((v1,v2) -> v1 ).
                sortByKey(false);

        LanguageResult result = new LanguageResult(lang, topWords.collect().get(0)._2);
        return result;
    }

    public static void printResult(LinkedList<LanguageResult> results){
        System.out.printf(" Language     |     Longest Word     |   Length %n");
        System.out.printf("-------------------------------------------------%n");
        results.forEach( result -> System.out.printf( "%s | %s | %d %n", result.getLanguage(), result.getTopWord(), result.getLength()));
    }
}
