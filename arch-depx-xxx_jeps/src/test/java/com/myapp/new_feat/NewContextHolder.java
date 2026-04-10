package com.myapp.new_feat;

//作者：spring
//链接：https://www.zhihu.com/question/497838660/answer/1961548041803794327
//来源：知乎
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

public class NewContextHolder {
    // ✅ final 实例，天然不可变，线程安全
    private final static ScopedValue<String> USER_CONTEXT = ScopedValue.newInstance();

    public void processRequest(String userId) {
        System.out.println("开始处理请求，绑定用户上下文: " + userId);
        
        // ✅ where 方法绑定值，run/call 执行代码块，结束后自动恢复
        ScopedValue.where(USER_CONTEXT, userId)
                   .run(() -> new UserService().getUserInfo());

        System.out.println("请求处理完毕，上下文自动清理。");
        // USER_CONTEXT.get() 在这里会抛出异常，因为它已经不在作用域内
    }

    // 在业务层深处获取
    public static String getCurrentUser() {
        // ✅ .get() 获取值，如果不在作用域内会抛出 NoSuchElementException
        return USER_CONTEXT.get();
    }
}

class UserService{

	public Object getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}