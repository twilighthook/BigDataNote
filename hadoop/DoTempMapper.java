package com.test.hadoop;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DoTempMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private static final int MISSINGDATA = -9999;

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		String year = line.substring(68, 73);

		String aitTemperatureStr = line.substring(78, 83).trim();
		int airTemperature = MISSINGDATA;

		if (isInteger(aitTemperatureStr))
			airTemperature = Integer.parseInt(aitTemperatureStr);

		if (airTemperature != MISSINGDATA) {
			context.write(new Text(year), new IntWritable(airTemperature));
		}

	}

	public static boolean isInteger(String value) {
		Pattern pattern = Pattern.compile("^[-+]?\\d+$");
		return pattern.matcher(value).matches();
	}

}
