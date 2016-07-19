package shoshin.alex.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

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
            String word = removePunctuationMarks(tokenizer.nextToken());
            if (isRussianWord(word)) {
                context.write(NullWritable.get(), new Text(word));
            }
        }
    }
    private String removePunctuationMarks(String word) {
        return word.replaceAll("[^а-яёА-ЯЁ]*$", "").replaceAll("^[^а-яёА-ЯЁ]*", "");
    }
    private boolean isRussianWord(String word) {
        return word.matches("[а-яёА-ЯЁ]+-?[а-яёА-ЯЁ]+");
    }
}