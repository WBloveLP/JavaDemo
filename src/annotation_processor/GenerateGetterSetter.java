package annotation_processor;

import java.io.*;
import java.lang.reflect.Field;

public class GenerateGetterSetter {

    static String tplStr = null;

    public static void main(String[] args) throws Exception {
        tplStr = Ios.readFileToString("generateMethod.tpl");
        String beanPackage = "bean.aaa";
        String directory = "src/" + directory(beanPackage);
        traversal(beanPackage, new File(directory));
    }

    private static void traversal(String beanPackage, File file) {
        if (file == null || !file.exists()) return;
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (File f : files) {
                traversal(beanPackage, f);
            }
        } else {
            try {
                String fileName = file.getName();
                final Class<?> clazz = Class.forName(beanPackage + "." + fileName.substring(0, fileName.length() - 5));
                if (clazz.getAnnotation(Data.class) != null) {
                    generateOneClass(clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateOneClass(Class clazz) {
        String directory = "src/" + directory(clazz);
        String fileName = name(clazz) + ".java";
        String srcFileStr = Ios.readFileToString(directory, fileName);
        StringBuilder sb = new StringBuilder();
        sb.append(srcFileStr.substring(0, srcFileStr.lastIndexOf("}")));
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {

            System.out.println(field.getGenericType());

            String name = name(field);
            String type = type(field);
            sb.append(tplStr.replace("#name#", name).replace("#type#", type).replace("#field#", field.getName()));
            sb.append('\n');
        }
        sb.append("}");
        Ios.copyStringToFile(sb.toString(), new File(directory, fileName));
    }

    private static String type(Field field) {
		/*
			class java.lang.Integer -> Integer
		 */
        String type = field.getType().toString();
        return type.substring(type.lastIndexOf(".") + 1, type.length());
    }

    private static String name(Class clazz) {
        String type = clazz.getName();
        return type.substring(type.lastIndexOf(".") + 1, type.length());
    }

    private static String name(Field field) {
		/*
			age -> Age
		 */
        String str = field.getName();
        Character ch = str.charAt(0);
        return field.getName().replace(ch, Character.toUpperCase(str.charAt(0)));
    }

    private static String directory(Class clazz) {
        // com.xysf.Person
        // com/xysf
        String str = clazz.getName().replace('.', '/');
        return str.substring(0, str.lastIndexOf("/"));
    }

    private static String directory(String packageName) {
        // com.xysf.Person
        // com/xysf
        return packageName.replace('.', '/');
    }

}