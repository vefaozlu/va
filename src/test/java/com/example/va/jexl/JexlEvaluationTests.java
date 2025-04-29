package com.example.va.jexl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JexlEvaluationTests {

    @Test
    void testForEvaluation() {
        Map<String, Object> variables = new HashMap<>();
        String arrayString1 = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
        String arrayString2 = "[10, 20, 30, 40, 50, 60, 70, 80, 90]";

        String[] stringValues1 = arrayString1
                .replace("[", "")
                .replace("]", "")
                .split(",\\s*"); // Split by comma followed by optional whitespace

        int[] intArray1 = Arrays.stream(stringValues1)
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] stringValues2 = arrayString2
                .replace("[", "")
                .replace("]", "")
                .split(",\\s*"); // Split by comma followed by optional whitespace

        int[] intArray2 = Arrays.stream(stringValues2)
                .mapToInt(Integer::parseInt)
                .toArray();

        variables.put("v1", intArray1);
        variables.put("v2", intArray2);
        Object res = FormulaEvaluator.evaluate("excel.lookup(2, v1 ,v2)", variables);

        assert res.equals(20);
    }
}
