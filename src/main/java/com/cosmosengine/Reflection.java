package com.cosmosengine;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Reflection {

    public static final List<Class> LEVELS = getClasses("com.cosmosengine.levels");

    /*
     * Load all the classes inside the level package using simple Java Reflection.
     */

    private static List<Class> getClasses(String packageName) {
        ArrayList<Class> classes = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String fileName = resource.getFile();
                String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
                dirs.add(new File(fileNameDecoded));
            }
            classes = new ArrayList<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (file.isDirectory()) {
                    assert !fileName.contains(".");
                    classes.addAll(findClasses(file, packageName + "." + fileName));
                } else if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    Class aClass;
                    try {
                        aClass = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
                    } catch (ExceptionInInitializerError e) {
                        aClass = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6), false, Thread.currentThread().getContextClassLoader());
                    }
                    classes.add(aClass);
                }
            }
        }
        return classes;
    }
}
