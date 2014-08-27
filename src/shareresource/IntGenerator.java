package shareresource;

/**
 * 生产者---生产偶数
 * @author xugc
 *
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel(){canceled=true;}
	public boolean isCanceled(){return canceled;}
}
