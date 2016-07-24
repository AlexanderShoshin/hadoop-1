package shoshin.alex.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class FindLongestReducer extends Reducer<NullWritable, Text, NullWritable, Text> {
    @Override
    public void reduce(NullWritable key, Iterable<Text> values, FindLongestReducer.Context context)
            throws IOException, InterruptedException {
        String longestWord = "";
        int maxLength = 0;
        for (Text word : values) {
            if (word.toString().length() > maxLength) {
                longestWord = word.toString();
                maxLength = longestWord.length();
            }
        }
        context.write(NullWritable.get(), new Text(longestWord));
    }
}