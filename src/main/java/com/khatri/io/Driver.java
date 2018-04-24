package com.khatri.io;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws ScriptException {
        //printClassPath();

        ScriptEngineManager engineManager = new ScriptEngineManager();
        printEngineFactories();
        //Need to add StandAlone Jython jar to classpath, otherwise engineManager won't be able to find
        //factory for Jython, jar size: ~37MB
        ScriptEngine engine = engineManager.getEngineByName("python");

        //setting variable in environment
        engine.eval("import sys");
        engine.eval("x=2");
        engine.eval("y=4");

        //extracting variables from environment
        System.out.println(engine.eval("x"));

        System.out.println(engine.eval("x+y"));
    }

    private static void printClassPath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        for(URL url : urls) {
            System.out.println(url.getFile());
        }
    }

    private static void printEngineFactories() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factoryList = manager.getEngineFactories();
        System.out.println("Following " + factoryList.size() + " script engines were found");
        for(ScriptEngineFactory factory : factoryList) {
            System.out.println("Engine name: " + factory.getEngineName());
            System.out.println("Version name: " + factory.getEngineVersion());
        }
    }
}
