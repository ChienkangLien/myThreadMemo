import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class a03CreateThread3 {
	/*
	 * 定義一個類MyCallable實現Callable介面
	 * 在MyCallable類中重寫call()方法
	 * 創建MyCallable類的物件
	 * 創建Future的實現類FutureTask物件，把MyCallable物件作為構造方法的參數
	 * 創建Thread類的物件，把FutureTask物件作為構造方法的參數
	 * 啟動執行緒
	 * 再調用get方法，就可以獲取執行緒結束之後的結果。
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyCallable mc = new MyCallable();

		FutureTask<Integer> ft = new FutureTask<Integer>(mc);

		Thread t1 = new Thread(ft);
		t1.start();

		System.out.println(ft.get());
	}
}

class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// 求1~100之和
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum = sum + i;
		}
		return sum;
	}
}
