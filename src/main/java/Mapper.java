import java.io.IOException;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper {
	@Override
	protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
		super.map(key, value, context);
	}
}
