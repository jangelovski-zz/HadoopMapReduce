import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class TheMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, FloatWritable> {
	private static final FloatWritable NullWritable = null;
	public void map(
			LongWritable key, 
			Text value, 
			OutputCollector<Text, FloatWritable> output,
			Reporter reporter ) throws IOException {
		
		String [] matchInfo = value.toString().split("\\s+");
		
		final Text imeNaLiga = new Text(matchInfo[0] + " " + matchInfo[1]);
		
		int vkupnoGoloviSezona 		= Integer.parseInt(matchInfo[2]);
		int vkupnoIzigraniNatprevari	= Integer.parseInt(matchInfo[3]);
		
		float prosek = (float)vkupnoGoloviSezona / (float)vkupnoIzigraniNatprevari;
		
		FloatWritable prosekGoloviNaNatprevari = new FloatWritable(prosek);
		
		output.collect(imeNaLiga, prosekGoloviNaNatprevari);
		
		
	}

}
