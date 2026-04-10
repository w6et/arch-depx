package base_jdk_new_jep.StructuredTaskScope;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class StructuredConcurrency_01_Jdk24_Demo {

	
	@Test
	void testName() throws Exception {

	}
	
//	Response handle() throws ExecutionException, InterruptedException {
//	    Future<String>  user  = esvc.submit(() -> findUser());
//	    Future<Integer> order = esvc.submit(() -> fetchOrder());
//	    String theUser  = user.get();   // Join findUser
//	    int    theOrder = order.get();  // Join fetchOrder
//	    return new Response(theUser, theOrder);
//	}
	
//	<T> List<T> runAll(List<Callable<T>> tasks) 
//	        throws InterruptedException, ExecutionException {
//	    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//	        List<? extends Supplier<T>> suppliers = tasks.stream().map(scope::fork).toList();
//	        scope.join()
//	             .throwIfFailed();  // Propagate exception if any subtask fails
//	        // Here, all tasks have succeeded, so compose their results
//	        return suppliers.stream().map(Supplier::get).toList();
//	    }
//	}
//	
//	<T> T race(List<Callable<T>> tasks, Instant deadline) 
//	        throws InterruptedException, ExecutionException, TimeoutException {
//	    try (var scope = new StructuredTaskScope.ShutdownOnSuccess<T>()) {
//	        for (var task : tasks) {
//	            scope.fork(task);
//	        }
//	        return scope.joinUntil(deadline)
//	                    .result();  // Throws if none of the subtasks completed successfully
//	    }
//	}
}
