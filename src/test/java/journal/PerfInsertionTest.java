package journal;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.joda.time.Duration;

import com.codahale.metrics.MetricRegistry;
import com.github.joschi.jadconfig.util.Size;
import com.google.common.collect.Lists;


public class PerfInsertionTest extends AbstractPerf {
	private static final long ITERATIONS = 100_0000;
	private static  String raw_msg = "<6>Jan 12 10:41:48 hadoop-04 kernel: hadoop-04:IN=eth0 OUT= MAC=00:1c:c0:a7:0f:8a:00:23:89:8d:29:f1:08:00 SRC=192.168.35.30 DST=192.168.27.254 LEN=60 TOS=0x00 PREC=0x00 TTL=63 ID=37377 DF PROTO=TCP SPT=747 DPT=111 WINDOW=65535 RES=0x00 SYN URGP=0";
	//private static final String RAW_MSG = "<178>DBAppWAF: 发生时间/2011-09-29 16:19:49,威胁/高,事件/SQL盲注攻击,URL地址/192.168.25.116/web/product.asp?tp=67%20anD%20%28cast%28cast%28substring%28cast%28suser%5Fsname%28%29%20as%20varchar%2864%29%29%2C1%2C1%29%20as%20varbinary%29%20as%20int%29%29%3E32768,POST数据/,服务器IP/59.111.12.230,主机名/59.111.12.230,服务器端口/80,客户端IP/202.107.193.236,客户端端口/57123,客户端环境/Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30),标签/SQL盲注攻击,动作/告警,HTTP/S响应码/200,攻击特征串/substring,触发规则/12020045,访问唯一编号/ToQqJX8AAAEAAGAuFiUAACsQ ";
	private static final int BATCH_INSERT = 20000;
	
	private KafkaJournal journal;
	
	{
		
		File journalDirectory = new File("kafka-data");
		if(!journalDirectory.exists())journalDirectory.mkdir();
		
		ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
		scheduler.prestartCoreThread();
		
		journal = new KafkaJournal(journalDirectory, 
												scheduler,Size.megabytes(200), 
												Duration.standardDays(1), 
												Size.terabytes(1), 
												Duration.standardDays(7), 
												100_00000, 
												Duration.standardMinutes(1), 
												new MetricRegistry());
		
//		StringBuilder sb = new StringBuilder(raw_msg);
//		
//		for(int i=0;i<8;i++){
//			sb.append(raw_msg);
//		}
//		
//		raw_msg = sb.toString();
		
		try {
			journal.startUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public long doTest() {
		
		try {
			
			
			List<Journal.Entry> entries = Lists.newArrayList();
			
			long start = System.currentTimeMillis();
			for(long i = 0;i < ITERATIONS;i++){
				byte[] content = (i+raw_msg).getBytes(UTF_8);
				byte[] id = (""+i).getBytes(UTF_8);
				entries.add(new Journal.Entry(id, content));
				
				if(i % BATCH_INSERT == 0){
					journal.write(entries);
					entries.clear();
				}
				
			}
			
			journal.write(entries);
			//journal.shutDown();
			return ITERATIONS * 1000 / (System.currentTimeMillis() - start);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void prePrint() {
		System.out.println("content length "+raw_msg.getBytes(UTF_8).length+" in bytes");
	}
	
	public static void main(String[] args) {
		PerfInsertionTest perfTest = new PerfInsertionTest();
		perfTest.testImplemention();
	}

}
