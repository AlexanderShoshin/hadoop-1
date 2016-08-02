package shoshin.alex.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class FindLongestWordReducer extends Reducer<NullWritable, Text, NullWritable, Text> {
    @Override
    public void reduce(NullWritable key, Iterable<Text> values, FindLongestWordReducer.Context context)
            throws IOException, InterruptedException {
        String longestWord = "";
        int maxLength = 0;
        String word;
        for (Text text : values) {
            word = text.toString();
            if (word.length() > maxLength) {
                longestWord = word;
                maxLength = longestWord.length();
            }
        }
        context.write(NullWritable.get(), new Text(longestWord));
    }
}