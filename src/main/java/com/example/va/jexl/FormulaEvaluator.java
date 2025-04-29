package com.example.va.jexl;

import com.example.va.helper.ExcelHelper;
import com.example.va.helper.MathHelper;
import org.apache.commons.jexl3.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FormulaEvaluator {
    private static final JexlEngine JEXL = new JexlBuilder()
            .cache(200)
            .strict(false)
            .silent(false)
            .create();

    private static final ConcurrentHashMap<String, JexlExpression> EXPRESSION_CACHE =
            new ConcurrentHashMap<>();

    public static Object evaluate(String formula, Map<String, Object> variables) {
        JexlExpression expr = EXPRESSION_CACHE.computeIfAbsent(formula,
                JEXL::createExpression);

        JexlContext context = new MapContext(variables);
        context.set("math", MathHelper.class);
        context.set("excel", ExcelHelper.class);

        return expr.evaluate(context);
    }
}
