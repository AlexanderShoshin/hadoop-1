package shoshin.alex.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import shoshin.alex.utils.Words;

/**
 *
 * @author Alexander_Shoshin
 */
public class ExtractWordsMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
    @Override
    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String word = Words.removePunctuationMarks(tokenizer.nextToken());
            if (Words.isRussianWord(word)) {
                context.write(NullWritable.get(), new Text(word.toLowerCase()));
            }
        }
    }
}