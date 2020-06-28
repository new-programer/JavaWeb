package site.ericblog.annotation;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 22:13 2020/6/19
 */

@SuppressWarnings("all") //正压警告，放在类上面表示正压了全部警告
public class AnnotationDIY01 extends Object{
    @Override
    public String toString(){
        return super.toString();
    }

    @Deprecated //表示该方法已过时，不建议使用，但可以使用
    public static void test(){
        System.out.println("Deprecated");
    }

    public static void main(String[] args) {
        test();
    }
}
