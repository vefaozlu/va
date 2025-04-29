package com.example.va.core.domain.producttemplateengine;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import com.example.va.jexl.FormulaEvaluator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.Getter;

@Getter
public class CommonProductTemplateEngine implements ProductTemplateEngine {

  private final HashMap<String, ProductTemplateAttribute> attributes;
  private final HashMap<String, Object> nonCalculatedAttributes;
  private final Map<String, Object> variables;

  public CommonProductTemplateEngine(HashMap<String, ProductTemplateAttribute> attributes,
      HashMap<String, Object> nonCalculatedAttributes) {
    this.attributes = attributes;
    this.nonCalculatedAttributes = nonCalculatedAttributes;
    this.variables = new HashMap<>();

    this.attributes.forEach((key, value) -> {
      if (!value.isCalculated()) {
        this.variables.put(key, nonCalculatedAttributes.get(key));
      }

      if (value.isConstant()) {
        this.variables.put(key, value.getDefaultValue());
      }
    });
  }

  @Override
  public Object getValue(String attributeName) {
    if (this.variables.containsKey(attributeName)) {
      return this.variables.get(attributeName);
    }

    if (this.attributes.get(attributeName).isConstant()) {
      return this.attributes.get(attributeName).getDefaultValue();
    }

    Object result = evaluateFormula(this.attributes.get(attributeName).getFormula(),
        this.createVariables(this.attributes.get(attributeName)));

    this.variables.putIfAbsent(attributeName, result);

    return result;
  }

  @Override
  public HashMap<String, Object> calculate() {
    DynamicCalculationOrder dynamicCalculationOrder = new DynamicCalculationOrder(
        getVariableListMap());
    HashMap<String, Object> results = new HashMap<>();
    dynamicCalculationOrder.getCalculationOrder()
        .forEach(name -> results.putIfAbsent(name, getValue(name)));

    return results;
  }

  private Map<String, List<String>> getVariableListMap() {
    Map<String, List<String>> variables = new HashMap<>();
    attributes.forEach((key, value) -> {
      if (value.getVariables() == null) {
        variables.putIfAbsent(key, new ArrayList<>());
        return;
      }
      variables.putIfAbsent(key, new ArrayList<>(value.getVariables().values()));
    });
    return variables;
  }

  private Object evaluateFormula(String formula, Map<String, Object> variables) {
    return FormulaEvaluator.evaluate(formula, variables);
  }

  private HashMap<String, ProductTemplateAttribute> filterAttributes() {
    return attributes;
  }

  private Map<String, Object> createVariables(ProductTemplateAttribute attribute) {
    HashMap<String, Object> variables = new HashMap<>();
    attribute.getVariables().forEach((key, value) -> variables.put(key, getValue(value)));

    return variables;
  }

  private boolean conditionCheck(String conditions, Map<String, Object> variables) {
    return (boolean) FormulaEvaluator.evaluate(conditions, variables);
  }


  public static class DynamicCalculationOrder {

    @Getter
    private final List<String> calculationOrder = new ArrayList<>();
    private final Map<String, List<String>> variableMap;
    private final Map<String, List<String>> graph = new HashMap<>();
    private final Map<String, Integer> inDegree = new HashMap<>();

    public DynamicCalculationOrder(Map<String, List<String>> variableMap) {
      this.variableMap = variableMap;
      buildDependencyGraph();
      determineCalculationOrder();
    }

    // Step 1: Build dependency graph from formulas
    private void buildDependencyGraph() {
      for (Map.Entry<String, List<String>> entry : variableMap.entrySet()) {
        String field = entry.getKey();
        List<String> dependencies = entry.getValue();

        graph.putIfAbsent(field, new ArrayList<>());
        inDegree.putIfAbsent(field, 0);

        for (String dependency : dependencies) {
          if (!dependency.equals(field)) { // Avoid self-referencing
            graph.putIfAbsent(dependency, new ArrayList<>());
            inDegree.putIfAbsent(dependency, 0);
            graph.get(dependency).add(field);
            inDegree.put(field, inDegree.get(field) + 1);
          }
        }
      }
    }

    // Check if a string is a numeric constant
    private boolean isNumeric(String str) {
      return str.matches("-?\\d+(\\.\\d+)?");
    }

    // Step 3: Perform Topological Sorting
    private void determineCalculationOrder() {
      Queue<String> queue = new LinkedList<>();

      for (String node : inDegree.keySet()) {
        if (inDegree.get(node) == 0) {
          queue.offer(node);
        }
      }

      while (!queue.isEmpty()) {
        String current = queue.poll();
        calculationOrder.add(current);

        for (String dependent : graph.get(current)) {
          inDegree.put(dependent, inDegree.get(dependent) - 1);
          if (inDegree.get(dependent) == 0) {
            queue.offer(dependent);
          }
        }
      }
    }
  }
}
