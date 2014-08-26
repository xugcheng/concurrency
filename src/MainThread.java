
public class MainThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		LiftOff liftOff = new LiftOff();
		liftOff.run(); //1.直接在main线程中启动任务
	}

}
