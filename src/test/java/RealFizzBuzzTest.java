import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class RealFizzBuzzTest {
    private final RealFizzBuzz realFizzBuzz = new RealFizzBuzz();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void retrieveFizzBuzz_throwIllegalArgumentException_givenInvalidRange() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid Range : Start should be less than end");
        realFizzBuzz.retrieveFizzBuzz(5, 3);
    }

    @Test
    public void retrieveFizzBuzz_returnFizz_givenRangeHasOnlyDivisibleByThree() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(6, 6);
        assertThat(actualElements, is("fizz"));
    }

    @Test
    public void retrieveFizzBuzz_returnBuzz_givenRangeHasOnlyDivisibleByFive() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(5, 5);
        assertThat(actualElements, is("buzz"));
    }

    @Test
    public void retrieveFizzBuzz_returnFizzBuzz_givenRangeHasOnlyDivisibleByFifteen() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(15, 15);
        assertThat(actualElements, is("fizzbuzz"));
    }

    @Test
    public void retrieveFizzBuzz_returnFizzBuzz_givenRangeHasOnlyDivisibleByZero() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(0, 0);
        assertThat(actualElements, is("fizzbuzz"));
    }

    @Test
    public void retrieveFizzBuzz_givenRangeFrom1to20() throws Exception {
        String expectedElements = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        String actualElements = realFizzBuzz.retrieveFizzBuzz(1, 20);

        assertThat(actualElements, is(expectedElements));
    }

    @Test
    public void retrieveFizzBuzz_givenNegativeRange() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(-20, 1);
        assertThat(actualElements, is("buzz -19 fizz -17 -16 fizzbuzz -14 lucky fizz -11 buzz fizz -8 -7 fizz buzz -4 lucky -2 -1 fizzbuzz 1"));
    }

    @Test
    public void retrieveFizzBuzz_returnLucky_givenRangeHasOnlyThree() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(31, 31);
        assertThat(actualElements, is("lucky"));
    }

    @Test
    public void retrieveFizzBuzz_shouldIncludeLucky_givenRangeIncludesMultipleThrees() throws Exception {
        String actualElements = realFizzBuzz.retrieveFizzBuzz(23, 35);
        assertThat(actualElements, is("lucky fizz buzz 26 fizz 28 29 lucky lucky lucky lucky lucky lucky"));
    }

    @Test
    public void generateReport_shouldGenerateReportWithCountOfWordsAndIntegers_givenAValidRange() throws Exception {
        String parsedFizzBuzzString = realFizzBuzz.retrieveFizzBuzz(1, 20);
        Map<String, Long> actualGeneratedReport = realFizzBuzz.generateReport(parsedFizzBuzzString);

        assertThat(actualGeneratedReport.get("fizz").intValue(), is(4));
        assertThat(actualGeneratedReport.get("buzz").intValue(), is(3));
        assertThat(actualGeneratedReport.get("fizzbuzz").intValue(), is(1));
        assertThat(actualGeneratedReport.get("lucky").intValue(), is(2));
        assertThat(actualGeneratedReport.get("integer").intValue(), is(10));
    }

    @Test
    public void generateReport_shouldGenerateReportWithCountOfWordsAndNoIntegers_givenAValidRange() throws Exception {
        String parsedFizzBuzzString = realFizzBuzz.retrieveFizzBuzz(5, 5);
        Map<String, Long> actualGeneratedReport = realFizzBuzz.generateReport(parsedFizzBuzzString);
        assertThat(actualGeneratedReport.get("buzz").intValue(), is(1));
        assertNull(actualGeneratedReport.get("integer"));
    }

    @Test
    public void generateReport_shouldGenerateReportWithCountOfIntegersAndNoWords_givenAValidRange() throws Exception {
        String parsedFizzBuzzString = realFizzBuzz.retrieveFizzBuzz(7, 8);
        Map<String, Long> actualGeneratedReport = realFizzBuzz.generateReport(parsedFizzBuzzString);
        assertThat(actualGeneratedReport.get("integer").intValue(), is(2));
        assertNull(actualGeneratedReport.get("fizz"));
        assertNull(actualGeneratedReport.get("buzz"));
        assertNull(actualGeneratedReport.get("fizzbuzz"));
        assertNull(actualGeneratedReport.get("lucky"));
    }
}
