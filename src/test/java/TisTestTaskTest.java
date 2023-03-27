import org.example.TisTestTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TisTestTaskTest {
    @Test
    public void testFillingList() {
        List<Integer> result = TisTestTask.fillingList(100);
        Assertions.assertEquals(100, result.size());
        Assertions.assertEquals(541, result.get(result.size() - 1));

        result = TisTestTask.fillingList(500);
        Assertions.assertEquals(500, result.size());
        Assertions.assertEquals(3571, result.get(result.size() - 1));

        result = TisTestTask.fillingList(1000);
        Assertions.assertEquals(1000, result.size());
        Assertions.assertEquals(7919, result.get(result.size() - 1));

        result = TisTestTask.fillingList(10000);
        Assertions.assertEquals(10000, result.size());
        Assertions.assertEquals(104729, result.get(result.size() - 1));
    }
}
