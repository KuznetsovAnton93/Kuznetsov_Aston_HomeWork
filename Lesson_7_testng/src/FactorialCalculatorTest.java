import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(FactorialCalculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(FactorialCalculator.factorial(5), 120);
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(FactorialCalculator.factorial(1), 1);
    }

    @Test
    public void testFactorialOfLargeNumber() {
        assertEquals(FactorialCalculator.factorial(10), 3628800);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialThrowsExceptionForNegativeNumber() {
        FactorialCalculator.factorial(-1);
    }
}