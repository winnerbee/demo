package com.jay;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class SequenceFileDriver {
	public static void main(String[] args) throws Exception {
		
		args = new String[] {"D:\\hadoop_input_whole", "D:\\hadoop_output_whole"};
		
		// 1 ��ȡjob����
		Configuration conf = new Configuration();
		// �����и��
		conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");
		
		Job job = Job.getInstance(conf);
		
		// 2 ����jar·��
		job.setJarByClass(SequenceFileDriver.class);
		
		// 3 ����mapper��reduce
		job.setMapperClass(SequenceFileMapper.class);
		job.setReducerClass(SequenceFileReducer.class);
		
		// 8 ���������ʽ
		job.setInputFormatClass(WholeFileInputformat.class);
		// 9 ���������ʽ
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		// 4 ����mapper�����key��value����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BytesWritable.class);
		
		// 5 �������������key��value����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);
		
				
		// 6 �����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 7 �ύjob
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}
}
