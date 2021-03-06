package shoshin.alex.hadoop;

import org.apache.hadoop.io.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import shoshin.alex.utils.Args;

public class LongestWordJob extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        Args.checkInput(args, "Input file path", "Output directory path");
        int res = ToolRunner.run(new Configuration(), new LongestWordJob(), args);
        System.exit(res);
    }
    
    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJobName("findLongestWord");
        job.setJarByClass(LongestWordJob.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(ExtractRussianWordsMapper.class);
        job.setReducerClass(FindLongestWordReducer.class);
        job.setCombinerClass(FindLongestWordReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setNumReduceTasks(1);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }
}