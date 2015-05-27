package volatilefeature;

import java.util.concurrent.TimeUnit;

/**
 * Volatile关键字 的语意解释
 * @author xugc
 *
 */
public class VolatileFeaturesExample {
	
	@Override
	public String toString() {
		return "VolatileFeaturesExample [vl=" + vl + "]";
	}

	private volatile long vl = 0l;	//使用volatile声明的64位long型变量
	
	/**
	 * 单个对volatile变量的写
	 * @param l
	 */
	public void setVl(long l){
		vl = l;
	}
	
	/**
	 * 单个对volatile变量的读
	 * @return
	 */
	public long getVl(){
		return vl;
	}
	
	/**
	 * 复合volatile变量的读写
	 */
	public void getAndIncrement(){
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vl++;
	}
	
	public static void main(String[] args)
	{
		final VolatileFeaturesExample vfe = new VolatileFeaturesExample();
		for(int i=0;i<1000;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					vfe.getAndIncrement();
				}
			}).start();
		}
		while(Thread.activeCount()>1){
			System.out.println("Thread.activeCount():"+Thread.activeCount());
			Thread.yield();
		}
		System.out.println(vfe);
	}
	
}

/**
 * 程序效果与VolatileFeaturesExample等价
 * @author xugc
 *
 */
class VolatileFeaturesExample2
{
	private long vl = 0l;		//64位的long型普通变量
	
	/**
	 * 对普通变量的同步写
	 * @param l
	 */
	public synchronized void setVl(long l)
	{
		vl = l;
	}
	
	/**
	 * 对普通变量的同步读
	 * @return
	 */
	public synchronized long getVl()
	{
		return vl;
	}
	
	/**
	 * 普通的方法调用
	 */
	public void getAndIncrement()
	{
		long temp = getVl();//同步读调用
		temp+=1l;			//普通写操作
		setVl(temp);		//同步写调用
	}
	
}