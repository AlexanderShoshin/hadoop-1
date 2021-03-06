package shoshin.alex.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import shoshin.alex.utils.Words;

public class ExtractRussianWordsMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
    @Override
    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if (Words.isRussianWord(word)) {
                word = Words.removePunctuationMarks(word);
                context.write(NullWritable.get(), TextPool.getText(word.toLowerCase()));
            }
        }
    }
}