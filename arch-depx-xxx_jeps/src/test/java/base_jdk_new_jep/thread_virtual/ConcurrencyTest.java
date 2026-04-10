package base_jdk_new_jep.thread_virtual;

import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

public class ConcurrencyTest {
	Semaphore sem = new Semaphore(10);
	
//	@Test
//	void test_concurrency() throws Exception {
//	    sem.acquire();
//	    try {
//	        return callLimitedService();
//	    } finally {
//	        sem.release();
//	    }
//	}

	private Object callLimitedService() {
		// TODO Auto-generated method stub
		return null;
	}

}
