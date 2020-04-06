package prog.com.ua;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}

class TestClass {
    @Test(a = 3, b = 5)
    public static void test(int a, int b) {
        System.out.println( "p:"+a + "are" +b ) ;
    }
}


public class Annotation {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        try {
            Class<?> ss = TestClass.class;

            Method[] methods = ss.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test test = method.getAnnotation(Test.class);
                    method.invoke(null, test.a(), test.b());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



