import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, FactorialCalculator.factorial(0));
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(120, FactorialCalculator.factorial(5));
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(1, FactorialCalculator.factorial(1));
    }

    @Test
    public void testFactorialOfLargeNumber() {
        assertEquals(3628800, FactorialCalculator.factorial(10));
    }

    @Test
    public void testFactorialThrowsExceptionForNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.factorial(-1));
    }
}
