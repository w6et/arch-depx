package base_jdk_new_jep.var_handle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import org.junit.jupiter.api.Test;

public class VarHandleTest {

	@Test
	public void testSetPublicField() throws NoSuchFieldException, IllegalAccessException {
		Demo instance = new Demo();
		VarHandle countHandle = MethodHandles.lookup().in(Demo.class).findVarHandle(Demo.class, "count", int.class);
		countHandle.set(instance, 99);
		System.out.println(instance.count);
	}

	@Test
	public void testSetProtectedField() throws NoSuchFieldException, IllegalAccessException {
		Demo instance = new Demo();
		VarHandle countHandle = MethodHandles.lookup().in(Demo.class).findVarHandle(Demo.class, "sum", long.class);
		countHandle.set(instance, 99999);
		System.out.println(instance);
	}

	@Test
	public void testSetPrivateField() throws NoSuchFieldException, IllegalAccessException {
		Demo instance = new Demo();
		VarHandle countHandle = MethodHandles.privateLookupIn(Demo.class, MethodHandles.lookup())
				.findVarHandle(Demo.class, "name", String.class);
		countHandle.set(instance, "hello world");
		System.out.println(instance);
	}
	
    @Test
    public void testSetArray(){
        Demo instance = new Demo();
        VarHandle arrayVarHandle = MethodHandles.arrayElementVarHandle(int[].class);
        int size = arrayVarHandle.coordinateTypes().size();
        System.out.println("size="+size);//size=2
        arrayVarHandle.compareAndSet(instance.arrayData1,0,3,100);
       // arrayVarHandle.compareAndSet(instance.arrayData2,1,5,300);
        System.out.println(instance);
    }

}
