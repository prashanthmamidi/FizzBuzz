import java.util.Map;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.rangeClosed;

public class RealFizzBuzz {

    public String retrieveFizzBuzz(int start, int end) {
        validateRange(start, end);
        return rangeClosed(start, end)
                .mapToObj(this::parse)
                .reduce("", (accumulator, element) -> accumulator + " " + element)
                .trim();
    }

    public Map<String, Long> generateReport(String fizzBuzzString) {
        return asList(fizzBuzzString.split(" ")).stream()
                .map(this::mapToWordOrInteger)
                .collect(groupingBy(identity(), counting()));
    }

    private String mapToWordOrInteger(String str) {
        return isWord(str) ? str : "integer";
    }

    private String parse(int number) {
        if (isNumberContainsDigitThree(number)) {
            return "lucky";
        }
        if (isNumberDivisibleBy(number, 15)) {
            return "fizzbuzz";
        }
        if (isNumberDivisibleBy(number, 3)) {
            return "fizz";
        }
        if (isNumberDivisibleBy(number, 5)) {
            return "buzz";
        }
        return valueOf(number);
    }

    private boolean isNumberContainsDigitThree(int number) {
        return valueOf(number).contains("3");
    }

    private boolean isNumberDivisibleBy(int number, int divider) {
        return number % divider == 0;
    }

    private boolean isWord(String item) {
        try {
            Integer.parseInt(item);
        } catch (NumberFormatException ex) {
            return true;
        }
        return false;
    }

    private void validateRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Invalid Range : Start should be less than end");
        }
    }

}
