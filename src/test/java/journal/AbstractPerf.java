package journal;

public abstract class AbstractPerf {

	private static final int RUN = 7;
	
	
	public void testImplemention(){
		prePrint();
		for(int i = 0; i < RUN;i++){
			System.out.format("run %d eps=%,d\n", i,doTest());
		}
	}
	
	public abstract void prePrint();
	
	public abstract long doTest();
	
}
