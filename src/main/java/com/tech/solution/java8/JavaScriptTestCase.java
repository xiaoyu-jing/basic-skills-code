package com.tech.solution.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription  Java嵌入JS代码
 */
public class JavaScriptTestCase {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        System.out.printf("result :%s",
                engine.eval("function f1() {return 3}; f1() + 5;"));

    }
}
