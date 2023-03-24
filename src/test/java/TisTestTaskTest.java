import org.example.TisTestTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TisTestTaskTest {

    @Test
    public void testMultithreadedArrayFilling() {
        List<Integer> list1 = TisTestTask.multithreadedArrayFilling(100);
        Assertions.assertEquals(25, list1.size());
        Assertions.assertEquals(97, list1.get(list1.size() - 1));

        List<Integer> list2 = TisTestTask.multithreadedArrayFilling(500);
        Assertions.assertEquals(95, list2.size());
        Assertions.assertEquals(499, list2.get(list2.size() - 1));

        List<Integer> list3 = TisTestTask.multithreadedArrayFilling(1000);
        Assertions.assertEquals(168, list3.size());
        Assertions.assertEquals(997, list3.get(list3.size() - 1));

        List<Integer> list4 = TisTestTask.multithreadedArrayFilling(10000);
        Assertions.assertEquals(1229, list4.size());
        Assertions.assertEquals(9973, list4.get(list4.size() - 1));
    }

}
