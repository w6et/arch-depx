package com.myapp.new_feat;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

//作者：spring
//链接：https://www.zhihu.com/question/497838660/answer/1961548041803794327
//来源：知乎
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

public class NewConcurrentService {
    
//    public Map<String, Object> getPageData(String userId) throws InterruptedException, ExecutionException {
//        // ✅ 使用 try-with-resources 确保 Scope 自动关闭
//    	StructuredTaskScope<Object, Void> open = StructuredTaskScope.open();
//        try (var scope = StructuredTaskScope.open()) {
//            // 1. fork() 启动虚拟线程执行任务
//            Supplier<String> userSupplier = scope.fork(() -> fetchUserInfo(userId));
//            Supplier<String> productSupplier = scope.fork(() -> fetchProductList());
//
//            // 2. join() 等待所有任务完成，如果任何一个失败则抛出异常
//            scope.join();
//            scope.throwIfFailed(); // 如果有失败，将第一个异常抛出
//
//            // 3. 获取结果，类型安全
//            return Map.of("user", userSupplier.get(), "products", productSupplier.get());
//        }
//        // ✅ 无需手动管理线程池！
//    }
//    // 模拟API调用...
	
	public Integer add() {
		return 0;
	}
	
	public Void voidtest() {
		return null;
	}
}