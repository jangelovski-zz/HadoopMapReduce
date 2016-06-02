import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class GoalsByMatchAverage {

	public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(GoalsByMatchAverage.class);

		// TODO: specify output types
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(FloatWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		
		FileInputFormat.addInputPath(conf, new Path("SeasonGoals"));
		FileOutputFormat.setOutputPath(conf, new Path("AverageGoalsTeams"));

		// TODO: specify a mapper
		conf.setMapperClass(TheMapper.class);

		// TODO: specify a reducer
		conf.setReducerClass(TheReducer.class);

		client.setConf(conf);
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
