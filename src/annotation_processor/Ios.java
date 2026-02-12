package annotation_processor;

import java.io.*;

public class Ios {

    public static String readFileToString(String fileName) {
        File file = new File(fileName);
        try (Reader reader = new InputStreamReader(new FileInputStream(file))) {
            char[] buff = new char[1024 * 10];
            int len;
            StringBuilder sb = new StringBuilder();
            while (-1 != (len = reader.read(buff))) {
                sb.append(buff, 0, len);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileToString(String directory, String name) {
        File file = new File(directory, name);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String str;
            StringBuilder sb = new StringBuilder();
            while (null != (str = reader.readLine())) {
                if (str.contains("@Data") || (str.startsWith("import") && str.endsWith("annotation_processor.Data;")))
                    continue;
                sb.append(str).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyStringToFile(String str, File file) {
        if (null == file || !file.exists()) return;
        try (Writer writer = new FileWriter(file)) {
            writer.write(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
