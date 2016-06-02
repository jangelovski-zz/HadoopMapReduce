import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class TheReducer extends MapReduceBase implements Reducer<Text, FloatWritable, Text, FloatWritable> {

	public void reduce(Text imeTim, Iterator<FloatWritable> proseci,
			OutputCollector<Text, FloatWritable> output,
			Reporter reporter) throws IOException {
		// replace KeyType with the real type of your key

		float sezonaProsekGolovi = 0;
		int brSezoni = 0;
		
		while(proseci.hasNext()){
			
			FloatWritable prosekGolovi = (FloatWritable)proseci.next();
			
			sezonaProsekGolovi += prosekGolovi.get();
			
			brSezoni++;
			
		}
		
		sezonaProsekGolovi /= (float)brSezoni;
		
		output.collect(imeTim, new FloatWritable(sezonaProsekGolovi));
	}

}
