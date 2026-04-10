package base_jdk_new_jep.StructuredTaskScope;

//import java.util.concurrent.Joiners;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.function.Predicate;
import java.util.stream.Stream;

//Subtask<T>\
public class MyJoiner<T, R> implements Joiner<T, R> {

	// Ok\Err\Done\Succ\Fail\Fork\Join
	@Override
	public R result() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onFork(Subtask<T> subtask) {
		// TODO Auto-generated method stub
		return Joiner.super.onFork(subtask);
	}

	@Override
	public boolean onComplete(Subtask<T> subtask) {
		// TODO Auto-generated method stub
		return Joiner.super.onComplete(subtask);
	}

	@Override
	public void onTimeout() {
		// TODO Auto-generated method stub
		Joiner.super.onTimeout();
	}

}
