package com.example.va;

import java.util.*;
import java.util.regex.*;

public class DynamicCalculationOrder {
    private Map<String, String> formulas;
    private Map<String, List<String>> graph = new HashMap<>();
    private Map<String, Integer> inDegree = new HashMap<>();
    private List<String> calculationOrder = new ArrayList<>();

    public DynamicCalculationOrder(Map<String, String> formulas) {
        this.formulas = formulas;
        buildDependencyGraph();
        determineCalculationOrder();
    }

    // Step 1: Build dependency graph from formulas
    private void buildDependencyGraph() {
        for (Map.Entry<String, String> entry : formulas.entrySet()) {
            String field = entry.getKey();
            String formula = entry.getValue();

            List<String> dependencies = extractDependencies(formula);
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

    // Step 2: Extract variable names from formula
    private List<String> extractDependencies(String formula) {
        Set<String> dependencies = new HashSet<>();
        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*"); // Match variable names
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            String variable = matcher.group();
            if (!isNumeric(variable)) {
                dependencies.add(variable);
            }
        }
        return new ArrayList<>(dependencies);
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

    public List<String> getCalculationOrder() {
        return calculationOrder;
    }

}

