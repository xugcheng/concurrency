package daemon;

import java.util.concurrent.ThreadFactory;

/**
 * 守护(daemon)线程---工厂
 * @author xugc
 *
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
	
}
