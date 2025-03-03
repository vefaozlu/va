package com.example.va;

import org.apache.commons.jexl3.*;
import org.apache.commons.jexl3.introspection.JexlMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoofCalculation {
    private double width;
    private double depth;
    private double roofMaterialQuantity;
    private double roofMaterialWidth;
    private double sierjlistQuantity;
    private double sierjlistWidth;
    private Map<String, String> formulas;

    public RoofCalculation(double width, double depth, Map<String, String> formulas) {
        this.width = width;
        this.depth = depth;
        this.formulas = formulas;
    }

    public void calculateFields(List<String> order) {
        Map<String, Object> values = new HashMap<>();
        values.put("width", width);
        values.put("depth", depth);

        for (String field : order) {
            String formula = formulas.get(field);
            if (formula != null) {
                JexlEngine jexl = new JexlBuilder().create();

                JexlExpression expression = jexl.createExpression(formula);
                JexlContext context = new MapContext(values);
                context.set("math", MathHelper.class);

                Object result = expression.evaluate(context);
                values.put(field, result);
                updateField(field, (Double) result);
            }
        }
    }

    private void updateField(String field, double value) {
        switch (field) {
            case "roofMaterialQuantity":
                this.roofMaterialQuantity = value;
                break;
            case "roofMaterialWidth":
                this.roofMaterialWidth = value;
                break;
            case "sierjlistQuantity":
                this.sierjlistQuantity = value;
                break;
            case "sierjlistWidth":
                this.sierjlistWidth = value;
                break;
        }
    }

    @Override
    public String toString() {
        return "Width: " + width + ", Depth: " + depth + ", RoofGlassQuantity: " + roofMaterialQuantity +
                ", RoofGlassWidth: " + roofMaterialWidth + ", SierjlistQuantity: " + sierjlistQuantity +
                ", SierjlistWidth: " + sierjlistWidth;
    }

    public void calculateAndUpdate(Map<String, String> formulas) {
        DynamicCalculationOrder calcOrder = new DynamicCalculationOrder(formulas);

        calculateFields(calcOrder.getCalculationOrder());
        System.out.println(this.toString());
    }
}
