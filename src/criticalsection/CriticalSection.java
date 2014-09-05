package criticalsection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pair{
	private int x;
	private int y;
	
	public Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public Pair(){
		this(0, 0);
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void incrementX(){
		x++;
	}
	
	public void incrementY(){
		y++;
	}
	
	public String toString(){
		return "x:"+x+",y:"+y;
	}
	
	public class PairValuesNotEqualException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public PairValuesNotEqualException(){
			super("Pair values not equal."+Pair.this);
		}
	}
	
	public void checkState(){
		if(x!=y){
			throw new PairValuesNotEqualException();
		}
	}
}

abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	public synchronized Pair getPair(){
		return new Pair(p.getX(),p.getY());
	}
	protected void store(Pair pair){
		storage.add(pair);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public abstract void increment();
}

/**
 * 同步整个方法
 * @author xugc
 *
 */
class PairManager1 extends PairManager{

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

/**
 * 使用临界区控制同步
 * @author xugc
 *
 */
class PairManager2 extends PairManager{

	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
	
}

/**
 * 使用显式的锁
 * @author xugc
 *
 */
class PairManager3 extends PairManager{

	@Override
	public void increment() {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		}finally{
			lock.unlock();
		}
	}
	
}

class PairManipulator implements Runnable{

	private PairManager pairManager;
	
	public PairManipulator(PairManager pairManager){
		this.pairManager = pairManager;
	}
	
	@Override
	public void run() {
		while(true){
			pairManager.increment();
		}
	}
	
	public String toString(){
		return "Pair:"+pairManager.getPair()+",checkCounter:"+pairManager.checkCounter.get();
	}
	
}

class PairChecker implements Runnable{

	private PairManager pm;
	
	public PairChecker(PairManager pm){
		this.pm = pm;
	}
	
	@Override
	public void run() {
		while(true){
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState(); //不能使用pm.p.checkState()
		}
	}
	
}

public class CriticalSection {
	
	public static void main(String[] args){
		PairManager p1 = new PairManager1();
		PairManager p2 = new PairManager2();
		
		testApproache(p1, p2);		
	}
	
	public static void testApproache(PairManager pman1,PairManager pman2){
		PairManipulator pm1 = new PairManipulator(pman1);
		PairManipulator pm2 = new PairManipulator(pman2);
		
		PairChecker pc1 = new PairChecker(pman1);
		PairChecker pc2 = new PairChecker(pman2);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pc1);
		exec.execute(pc2);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("pm1:"+pm1+"\npm2:"+pm2);
		System.exit(0);
		
	}
	
}
