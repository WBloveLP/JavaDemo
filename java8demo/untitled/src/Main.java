import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String dirPath = "C:\\Users\\YAIC\\Downloads\\gulimall-dev\\gulimall-dev\\gmall文档";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                replaceAndCopy(file);
            }
        }
    }

    private static void replaceAndCopy(File file) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file.getParent(), file.getName().replace(".md","") + "_有图版.md"))))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.replace("](/", "](assert/")).append("\n");
            }
            bw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
