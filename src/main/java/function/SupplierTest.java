package function;

import java.util.function.Supplier;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/30 16:43
 * @history:
 */
public class SupplierTest {

    public static void main(String[] args) {

        Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("get");
                return 1;
            }
        };

        Supplier supplier1 = () ->{ System.out.println("get"); return "11";};
        System.out.println("supplier1.get() = " + supplier1.get());


    }

}
