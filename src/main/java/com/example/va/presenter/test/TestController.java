package com.example.va.presenter.test;

import com.example.va.RoofCalculation;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.commons.jexl3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class TestController {
    public class Veranda {
        public Double width;
        public Double depth;
        public Double roofMaterialQuantity;

        public Veranda(Double width, Double depth, Double roofMaterialQuantity) {
            this.width = width;
            this.depth = depth;
            this.roofMaterialQuantity = roofMaterialQuantity;
        }
    }

    @GetMapping("/test/test-jx-scripts")
    public ResponseEntity<String> testJxScripts() {
        Map<String, String> formulas = new HashMap<>();
        formulas.put("roofMaterialQuantity", "math.ceil(width / 105)");
        formulas.put("roofMaterialWidth", "sierjlistWidth + 3.2");
        formulas.put("sierjlistQuantity", "roofMaterialQuantity * 2");
        formulas.put("sierjlistWidth", "(width - (roofMaterialQuantity + 1) * 5.5) / roofMaterialQuantity");

        RoofCalculation rC = new RoofCalculation(500.0, 300.0, formulas);

        rC.calculateAndUpdate(formulas);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/test/test-scripts")
    public ResponseEntity<String> test() {

        Map<String, String> formulas = new HashMap<>();
        formulas.put("roofMaterialQuantity", "");

        RoofCalculation rC = new RoofCalculation(500.0, 300.0, formulas);

        Veranda veranda = new Veranda(500.0, 300.0, 5.0);
        String formula = "(V1 - ((V2 + C1) * C2)) / V2";

        HashMap<String, String> variableNames = new HashMap<String, String>();
        variableNames.put("V1", "width");
        variableNames.put("V2", "roofMaterialQuantity");

        HashMap<String, Double> constants = new HashMap<String, Double>();
        constants.put("C1", 1.0);
        constants.put("C2", 5.5);

        HashMap<String, Double> variables = new HashMap<String, Double>(constants);

        Arrays.stream(veranda.getClass().getFields()).forEach(field -> {
            Iterator<Map.Entry<String, String>> iterator = variableNames.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (field.getName().equals(entry.getValue())) {
                    try {
                        variables.put(entry.getKey(), (Double) field.get(veranda));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        try {
            double result = evaluateExpression(formula, variables);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Error evaluating formula: " + e.getMessage());
        }

        return ResponseEntity.ok("OK");
    }

    private static double evaluateExpression(String formula, HashMap<String, Double> variables) {
        // Build the expression
        Expression expression = new ExpressionBuilder(formula)
                .variables(variables.keySet()) // Add variables
                .build()
                .setVariables(variables); // Set variable values

        // Evaluate the expression
        return expression.evaluate();
    }
}
