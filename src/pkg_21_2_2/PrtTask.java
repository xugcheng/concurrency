package pkg_21_2_2;

public class PrtTask implements Runnable {

	private int count=0;
	private static int taskCount=0;
	private final int id = taskCount++;
	
	public PrtTask()
	{
		System.out.println(String.format("PrtTask : %s Start", id));
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			count++;
			System.out.println(status());
			Thread.yield();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("PrtTask : %s Stop", id));
	}
	
	public String status()
	{
		return "#"+id+"("+count+")";
	}

}
