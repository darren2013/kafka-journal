package journal;

import java.io.File;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import journal.Journal.JournalReadEntry;

import org.joda.time.Duration;

import com.codahale.metrics.MetricRegistry;
import com.github.joschi.jadconfig.util.Size;


public class PerfReadingTest extends AbstractPerf{

	private static final long ITERATIONS = 100_0000;
	private static final int BATCH_READING = 2000;
	
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
		
		try {
			journal.startUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void prePrint() {
		
	}

	@Override
	public long doTest() {
		
		long start = System.currentTimeMillis();
		long offset = Long.MIN_VALUE;
		
		for(int i = 0; i < ITERATIONS;){
			List<Journal.JournalReadEntry> entries = journal.read(BATCH_READING);
			
			if(entries.size() > 0){
				JournalReadEntry readEntry = entries.get(entries.size() - 1);
				offset = readEntry.getOffset();
			}
//			JournalReadEntry readEntry = entries.get(0);
//			System.out.println(new String(readEntry.getPayload(), UTF_8));
			i += BATCH_READING;
		}
		
		journal.markJournalOffsetCommitted(offset);
		
		long duration = System.currentTimeMillis() - start;
		
		if(duration == 0)return 0;
		
		return ITERATIONS * 1000 / (System.currentTimeMillis() - start);
	}
	
	public static void main(String[] args) {
		PerfReadingTest perfReadingTest = new PerfReadingTest();
		perfReadingTest.testImplemention();
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
