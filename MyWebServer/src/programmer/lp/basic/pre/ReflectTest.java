package programmer.lp.basic.pre;

public class ReflectTest {

    public static void main(String[] args) throws Exception {
        final Class<?> clz = Class.forName("programmer.lp.basic.pre.XMLTest");
        // 反射调用静态方法时，不需要给invoke传递对象
        clz.getMethod("main").invoke(null);
    }

}
