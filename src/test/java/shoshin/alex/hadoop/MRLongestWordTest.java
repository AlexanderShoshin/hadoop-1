package shoshin.alex.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MRLongestWordTest {
    private MapDriver<LongWritable, Text, NullWritable, Text> mapDriver;
    private ReduceDriver<NullWritable, Text, NullWritable, Text> reduceDriver;
    private MapReduceDriver<LongWritable, Text, NullWritable, Text, NullWritable, Text> mapCombineReduceDriver;

    @Before
    public void setUp() throws URISyntaxException, FileNotFoundException {
        Mapper mapper = new ExtractWordsMapper();
        Reducer reducer = new FindLongestReducer();
        Reducer combiner = new FindLongestReducer();

        mapDriver = new MapDriver<>();
        mapDriver.setMapper(mapper);
        reduceDriver = new ReduceDriver<>();
        reduceDriver.setReducer(reducer);
        mapCombineReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer, combiner);
    }

    @Test
    public void mapper_should_extract_russian_words_from_text() throws IOException {
        mapDriver.withInput(new LongWritable(1), new Text("Пролог: 9000 лет назад"));
        mapDriver.withOutput(NullWritable.get(), new Text("пролог"));
        mapDriver.withOutput(NullWritable.get(), new Text("лет"));
        mapDriver.withOutput(NullWritable.get(), new Text("назад"));
        mapDriver.runTest();
    }

    @Test
    public void reducer_should_find_longest_word() throws IOException {
        List<Text> words = new ArrayList<>();
        words.add(new Text("пролог"));
        words.add(new Text("лет"));
        words.add(new Text("назад"));
        reduceDriver.withInput(NullWritable.get(), words);
        reduceDriver.withOutput(NullWritable.get(), new Text("пролог"));
        reduceDriver.runTest();
    }

    @Test
    public void job_should_find_longest_word() {
        mapCombineReduceDriver.withInput(new LongWritable(1), new Text("Пролог: 9000 лет назад"));
        mapCombineReduceDriver.withOutput(NullWritable.get(), new Text("пролог"));
    }
}