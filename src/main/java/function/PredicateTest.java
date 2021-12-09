package function;

import java.util.function.Predicate;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/30 16:49
 * @history:
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String>  predicate = (str) -> {return str.isBlank();};

        System.out.println("predicate.test(str) = " + predicate.test("3333"));
    }
}
