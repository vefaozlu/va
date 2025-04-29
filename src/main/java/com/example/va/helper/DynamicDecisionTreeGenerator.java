package com.example.va.helper;

import java.util.*;
import java.util.regex.*;

public class DynamicDecisionTreeGenerator {

    private final List<Rule> rules;
    private Node root;

    public DynamicDecisionTreeGenerator() {
        this.rules = new ArrayList<>();
    }

    public static void test() {
        // Example usage with the conditions from the prompt
        List<String> conditions = new ArrayList<>();
        conditions.add("For Polyc105 16mm Helder; type == \"Polyc\" && thickness == MM16 && materialWidth > 98");
        conditions.add("For Polyc105 16mm Opaal; type == \"Polyc\" && thickness == MM16 && materialWidth > 98");
        conditions.add("For Polyc105 16mm Getint; type == \"Polyc\" && thickness == MM16 && materialWidth > 98");
        conditions.add("For Polyc105 25mm; type == Polyc && thickness == MM25 && materialWidth > 98");
        conditions.add("For Polyc105 36mm; type == Polyc && thickness == MM36 && materialWidth > 98");
        conditions.add("For Polyc98 16mm; type == Polyc && thickness == MM16 && materialWidth <= 98");
        conditions.add("For Polyc98 25mm; type == Polyc && thickness == MM25 && materialWidth <= 98");
        conditions.add("For Polyc98 36mm; type == Polyc && thickness == MM36 && materialWidth <= 98");
        conditions.add("For Glass82 8mm; type == Glass && thickness == MM8");
        conditions.add("For Glass82 26mm; type == Glass && thickness == MM26");

        DynamicDecisionTreeGenerator generator = new DynamicDecisionTreeGenerator();
        generator.parseConditions(conditions);
        generator.buildTree();

        System.out.println("Generated Decision Tree:");
        generator.printTree();

        System.out.println("\nGenerated Classification Method:");
        generator.generateClassificationMethod();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("type", Type.Glass);
        attributes.put("thickness", Thickness.MM8);
        attributes.put("materialWidth", 77.2);

        // Get the result
        String result = generator.classify(attributes);
        System.out.println("The classified result is: " + result);
    }

    // Parse a condition string and extract rules
    public void parseConditions(List<String> conditionStrings) {
        Pattern pattern = Pattern.compile("For (.*?); (.*)");

        for (String conditionString : conditionStrings) {
            Matcher matcher = pattern.matcher(conditionString);
            if (matcher.find()) {
                String materialName = matcher.group(1).trim();
                String conditionsStr = matcher.group(2).trim();

                Rule rule = new Rule(materialName);
                String[] conditions = conditionsStr.split("&&");

                for (String condition : conditions) {
                    condition = condition.trim();

                    // Parse the condition to extract attribute, operator, and value
                    if (condition.contains("==")) {
                        String[] parts = condition.split("==");
                        String attribute = parts[0].trim();
                        String value = parts[1].trim();
                        rule.addCondition(new Condition(attribute, "==", value));
                    } else if (condition.contains(">")) {
                        String[] parts = condition.split(">");
                        String attribute = parts[0].trim();
                        String value = parts[1].trim();
                        rule.addCondition(new Condition(attribute, ">", value));
                    } else if (condition.contains("<=")) {
                        String[] parts = condition.split("<=");
                        String attribute = parts[0].trim();
                        String value = parts[1].trim();
                        rule.addCondition(new Condition(attribute, "<=", value));
                    } else if (condition.contains("<")) {
                        String[] parts = condition.split("<");
                        String attribute = parts[0].trim();
                        String value = parts[1].trim();
                        rule.addCondition(new Condition(attribute, "<", value));
                    } else if (condition.contains(">=")) {
                        String[] parts = condition.split(">=");
                        String attribute = parts[0].trim();
                        String value = parts[1].trim();
                        rule.addCondition(new Condition(attribute, ">=", value));
                    }
                }

                rules.add(rule);
            }
        }
    }

    // Build the decision tree from the parsed rules
    public void buildTree() {
        if (rules.isEmpty()) {
            return;
        }

        // Find the most common attribute to use as the root
        Map<String, Integer> attributeCounts = new HashMap<>();
        for (Rule rule : rules) {
            for (Condition condition : rule.conditions) {
                attributeCounts.put(condition.attribute,
                        attributeCounts.getOrDefault(condition.attribute, 0) + 1);
            }
        }

        String rootAttribute = attributeCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        // Build the tree recursively
        root = buildTreeRecursive(rules, new ArrayList<>(), rootAttribute);
    }

    private Node buildTreeRecursive(List<Rule> currentRules, List<Condition> previousConditions,
                                    String splitAttribute) {
        // If no rules match the current path, return null
        if (currentRules.isEmpty()) {
            return null;
        }

        // If only one rule remains, create a leaf node
        if (currentRules.size() == 1) {
            return new Node(currentRules.get(0).materialName);
        }

        // If no split attribute is provided, find the best one
        if (splitAttribute == null) {
            Map<String, Integer> attributeCounts = new HashMap<>();
            for (Rule rule : currentRules) {
                for (Condition condition : rule.conditions) {
                    // Skip attributes already used in previous conditions
                    boolean alreadyUsed = false;
                    for (Condition prevCond : previousConditions) {
                        if (prevCond.attribute.equals(condition.attribute)) {
                            alreadyUsed = true;
                            break;
                        }
                    }
                    if (!alreadyUsed) {
                        attributeCounts.put(condition.attribute,
                                attributeCounts.getOrDefault(condition.attribute, 0) + 1);
                    }
                }
            }

            splitAttribute = attributeCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);

            // If no attribute found, create a leaf with the first material
            if (splitAttribute == null) {
                return new Node(currentRules.get(0).materialName);
            }
        }

        // Find unique conditions for the split attribute
        Set<Condition> uniqueConditions = new HashSet<>();
        for (Rule rule : currentRules) {
            for (Condition condition : rule.conditions) {
                if (condition.attribute.equals(splitAttribute)) {
                    uniqueConditions.add(condition);
                }
            }
        }

        // If no conditions found for this attribute, try another attribute
        if (uniqueConditions.isEmpty()) {
            return buildTreeRecursive(currentRules, previousConditions, null);
        }

        // Create a node for each unique value of the split attribute
        Node node = new Node(splitAttribute, null, null);

        for (Condition uniqueCondition : uniqueConditions) {
            Node childNode = new Node(uniqueCondition.attribute, uniqueCondition.operator, uniqueCondition.value);
            node.addChild(childNode);

            // Filter rules that match this condition
            List<Rule> matchingRules = new ArrayList<>();
            for (Rule rule : currentRules) {
                boolean matches = false;
                for (Condition condition : rule.conditions) {
                    if (condition.equals(uniqueCondition)) {
                        matches = true;
                        break;
                    }
                }
                if (matches) {
                    matchingRules.add(rule);
                }
            }

            // Create a copy of previous conditions and add the current one
            List<Condition> newPreviousConditions = new ArrayList<>(previousConditions);
            newPreviousConditions.add(uniqueCondition);

            // Recursively build subtree
            Node subtree = buildTreeRecursive(matchingRules, newPreviousConditions, null);
            if (subtree != null) {
                childNode.addChild(subtree);
            }
        }

        return node;
    }

    // Print the decision tree
    public void printTree() {
        if (root == null) {
            System.out.println("Tree not built yet");
            return;
        }
        printNode(root, 0);
    }

    private void printNode(Node node, int depth) {

        System.out.println("  ".repeat(Math.max(0, depth)) + node);

        if (!node.isLeaf) {
            for (Node child : node.children) {
                printNode(child, depth + 1);
            }
        }
    }

    public String classify(Map<String, Object> attributes) {
        if (root == null) {
            return "Tree not built yet";
        }
        return classifyRecursive(root, attributes);
    }

    private String classifyRecursive(Node node, Map<String, Object> attributes) {
        if (node.isLeaf) {
            System.out.println("Leaf node reached: " + node.materialName);
            return node.materialName;
        }

        for (Node child : node.children) {
            if (evaluateCondition(child, attributes)) {
                System.out.println(
                        "Evaluating condition: " + child.attribute + " " + child.operator + " " + child.value);
                System.out.println(
                        "===============================================================================================================");

                if (!child.children.isEmpty()) {
                    return classifyRecursive(child.children.get(0), attributes);
                }
            }
        }

        return "No matching material found";
    }

    private boolean evaluateCondition(Node node, Map<String, Object> attributes) {
        if (node.attribute == null || !attributes.containsKey(node.attribute)) {
            return false;
        }

        Object attrValue = attributes.get(node.attribute);

        if ("==".equals(node.operator)) {
            return node.value.equals(String.valueOf(attrValue));
        } else if (">".equals(node.operator)) {
            if (attrValue instanceof Number && node.value != null) {
                return ((Number) attrValue).doubleValue() > Double.parseDouble(node.value);
            }
        } else if ("<=".equals(node.operator)) {
            if (attrValue instanceof Number && node.value != null) {
                return ((Number) attrValue).doubleValue() <= Double.parseDouble(node.value);
            }
        } else if ("<".equals(node.operator)) {
            if (attrValue instanceof Number && node.value != null) {
                return ((Number) attrValue).doubleValue() < Double.parseDouble(node.value);
            }
        } else if (">=".equals(node.operator)) {
            if (attrValue instanceof Number && node.value != null) {
                System.out.println("Instance of Number");
                return ((Number) attrValue).doubleValue() >= Double.parseDouble(node.value);
            }
        }

        return false;
    }

    // Generate Java code for the classification method
    public void generateClassificationMethod() {
        StringBuilder code = new StringBuilder();
        code.append("public static String classifyMaterial(Type type, Thickness thickness, int materialWidth) {\n");
        code.append("    // Auto-generated classification method\n");
        generateMethodBody(root, code, 1);
        code.append("    return \"Unknown material\";\n");
        code.append("}\n");

        System.out.println(code.toString());
    }

    private void generateMethodBody(Node node, StringBuilder code, int depth) {
        StringBuilder indent = new StringBuilder();
        indent.append("    ".repeat(Math.max(0, depth)));

        if (node.isLeaf) {
            code.append(indent).append("return \"").append(node.materialName).append("\";\n");
            return;
        }

        // Generate conditions for child nodes
        for (Node child : node.children) {
            code.append(indent).append("if (").append(child.attribute)
                    .append(" ").append(child.operator).append(" ")
                    .append(child.value).append(") {\n");

            for (Node grandchild : child.children) {
                generateMethodBody(grandchild, code, depth + 1);
            }

            code.append(indent).append("}\n");
        }
    }

    // Classes for representing properties and conditions
    public enum Type {
        Polyc, Glass
    }

    public enum Thickness {
        MM8, MM16, MM25, MM26, MM36
    }

    // Represents a condition like "type == Type.Polyc"
    static class Condition {
        String attribute; // The attribute name (e.g., "type", "thickness")
        String operator; // The operator (e.g., "==", ">", "<=")
        String value; // The value as a string

        public Condition(String attribute, String operator, String value) {
            this.attribute = attribute;
            this.operator = operator;
            this.value = value;
        }

        @Override
        public String toString() {
            return attribute + " " + operator + " " + value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Condition other))
                return false;
            return attribute.equals(other.attribute) &&
                    operator.equals(other.operator) &&
                    value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(attribute, operator, value);
        }
    }

    // Represents a rule (a material and its conditions)
    static class Rule {
        String materialName; // Name of the material
        List<Condition> conditions; // List of conditions for this material

        public Rule(String materialName) {
            this.materialName = materialName;
            this.conditions = new ArrayList<>();
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }
    }

    // Node class for the decision tree
    static class Node {
        String attribute; // The attribute to test
        String operator; // The operator for the test
        String value; // The value for the test
        boolean isLeaf; // Whether this is a leaf node
        String materialName; // The material if this is a leaf node
        List<Node> children; // Child nodes

        // Constructor for internal node
        public Node(String attribute, String operator, String value) {
            this.attribute = attribute;
            this.operator = operator;
            this.value = value;
            this.isLeaf = false;
            this.children = new ArrayList<>();
        }

        // Constructor for leaf node
        public Node(String materialName) {
            this.isLeaf = true;
            this.materialName = materialName;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }

        @Override
        public String toString() {
            if (isLeaf) {
                return "Material: " + materialName;
            } else {
                return attribute + " " + operator + " " + value;
            }
        }
    }
}