import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class a15CustomizedThreadPool {
	/*
	 * ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
	 * 參數1：核心線程數量 (不能小於0)
	 * 參數2：線程池最大線程的數量 (最大數量>=核心線程數量)
	 * 參數3：空閒時間(值) (不能小於0)
	 * 參數4：空閒時間(單位) (用TimeUnit指定)
	 * 參數5：阻塞隊列 (不能為null)
	 * 參數6：創建線程的方式 (不能為null)
	 * 參數7：要執行的任務過多時的解決方案 (不能為null)
	 */
	public static void main(String[] args) throws InterruptedException {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				3, 8, 60, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3), 
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.AbortPolicy()
			);

		// 三個由核心線程處理、三個先排隊、兩個由臨時線程處理
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());
		threadPoolExecutor.submit(new MyRunnable3());

	}
}

class MyRunnable3 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "-" + i);
		}
	}
}
