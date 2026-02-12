package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;


// javadoc FileUtils.java -encoding UTF-8 -charset UTF-8

/**
 * 文件工具类
 */
public class FileUtils {

    public static void main(String[] args) {
        for (int i = 48; i <= 82; i++) {
            String str = "https://www.sehuatang.org/book-149-7" + i + ".html";
            download(str, String.valueOf(i));
        }
    }

    private static void download(String url, String fileName) {
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:102.0) Gecko/20100101 Firefox/102.0");
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.connect();
            try (
                    final InputStream inputStream = connection.getInputStream();
                    final OutputStream outputStream =
                            new FileOutputStream(fileName)
            ) {
                byte[] bytes = new byte[10240];
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String FILE_PATH = "C:\\Users\\lpruoyu\\Desktop\\98-设计模式之美";

    private static int count = 0;
    private static final StringBuilder SB = new StringBuilder();
    private static final Set<String> SET = new HashSet<>();

    private static ExecutorService pool
            = null;
//            = Executors.newFixedThreadPool(5);

    public static void main4(String[] args) {
        main1();
//        main2();
//        pool.shutdown();
//        File file = new File(FILE_PATH);
//        main3(file);
//        System.out.println(SET.size());
    }

    private static void main3(File file) {
        if (null == file || !file.exists()) return;

        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (File listFile : files) {
                main3(listFile);
            }
        } else {
            final String name = file.getName();
            if (name.endsWith(".flac")) {
                SET.add(name);
            }
        }
    }

    /**
     * main2
     */
    public static void main2() {
        File file = new File(FILE_PATH);
        try {
            final File[] files = file.listFiles();
            for (File f : files) {
                final File[] mp4 = f.listFiles();
//                String name = mp4[0].getName();
//                name = name.substring(0, name.indexOf("."));
//                name = name.substring(name.indexOf("_") + 1, name.lastIndexOf("_"));
//                name += ".mp4";
                String newName = f.getName();
//                String newName = (Integer.parseInt(f.getName()) - 14) + "";
                copyFileToFile(mp4[0], new File(file, newName + ".mp4"));
            }
        } catch (Exception e) {
        }
    }

    /**
     * 将一个文件拷贝到某处
     *
     * @param src  源文件路径
     * @param dest 目标文件路径
     */
    public static void copyFileToFile(File src, File dest) {
        pool.execute(() -> {
            try (InputStream is = new FileInputStream(src);
                 OutputStream os = new FileOutputStream(dest)) {
                byte[] buf = new byte[1024 * 1024];
                int len;
                while ((len = (is.read(buf))) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                System.out.println(dest.getAbsolutePath() + " copy finished!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void main1() {
        File file = new File(FILE_PATH);
        fileR(file);
        System.out.println(SB);
        System.out.println(count);
    }

    public static void fileR(File file) {
        if (null == file || !file.exists()) return;

        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (File listFile : files) {
                fileR(listFile);
            }
        } else {
            final String name = file.getName();
            if (name.endsWith(".m4a")) {
                file.delete();
                SB.append(file.getAbsolutePath()).append('\n');
                count++;
            }
//            System.out.println(name);
        }
    }

    /**
     * 方法的作用、功能
     *
     * @param name 传入一个name
     * @return 返回一个name + name
     */
    public static String xx(String name) {
        return name + "name";
    }

}
