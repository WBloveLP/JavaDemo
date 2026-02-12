package com.example.sampledemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MdImageRefUpdater {

    private static int count = 0;

    public static void main(String[] args) {
        String folderPath = "D:\\lp\\gitcode\\my_private_repo\\资料\\MySQL是怎样运行的\\book-how-mysql-runs-main\\book-how-mysql-runs-main\\docs\\mysql"; // 或者使用绝对路径如 "D:/docs"

        try {
            countImages("D:\\lp\\gitcode\\my_private_repo\\资料\\MySQL是怎样运行的\\book-how-mysql-runs-main\\book-how-mysql-runs-main\\docs\\mysql\\images");
            updateMdFiles(folderPath);
            System.out.println("所有md文件处理完成！");
            System.out.println("图片总计：" + LISTS.size());
            System.out.println("处理的图片总计：" + count);
            MAPS.keySet().forEach(fileName->{
                LISTS.remove(fileName);
            });
            LISTS.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("处理文件时发生错误: " + e.getMessage());
        }
    }

    private static List<String> LISTS = new ArrayList<>();

    public static void countImages(String folderPath) throws IOException {
        Path dirPath = Paths.get(folderPath);

        try (Stream<Path> paths = Files.walk(dirPath)) {
            paths.filter(path -> path.toString().endsWith(".png")).forEach(path -> {
                LISTS.add(path.getFileName().toString());
            });
        }
    }

    public static void updateMdFiles(String folderPath) throws IOException {
        Path dirPath = Paths.get(folderPath);

        try (Stream<Path> paths = Files.walk(dirPath)) {
            paths.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".md")).forEach(MdImageRefUpdater::processMdFile);
        }
    }

    private static void processMdFile(Path filePath) {
        try {
            System.out.println("正在处理文件: " + filePath);

            String content = readFileToString(filePath);

            // 使用正则表达式替换图片引用
            String updatedContent = updateImageReferencesWithPattern(content);

            if (!content.equals(updatedContent)) {
                writeStringToFile(filePath, updatedContent);
                System.out.println("  文件已更新: " + filePath);
            } else {
                System.out.println("  文件无需更新: " + filePath);
            }

        } catch (IOException e) {
            System.err.println("处理文件 " + filePath + " 时出错: " + e.getMessage());
        }
    }

    private static Map<String, String> MAPS = new HashMap<>();

    /**
     * 使用Pattern和Matcher的更精确版本
     */
    private static String updateImageReferencesWithPattern(String content) {
        // 正则表达式：
        // !\\[     匹配 "!["
        // ([^\\]]*) 捕获组1：匹配0个或多个非"]"字符（图片描述）
        // \\]      匹配 "]"
        // \\[      匹配 "["
        // ([^\\]]+) 捕获组2：匹配1个或多个非"]"字符（图片引用名称）
        // \\]      匹配 "]"
        Pattern pattern = Pattern.compile("!\\[([^\\]]*)\\]\\[([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(content);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String altText = matcher.group(1);    // 图片描述（可能为空）
            String imageRef = matcher.group(2);   // 图片引用名称

            // 构建新的图片引用
            // 格式: ![图片描述][../images/引用名称]

            String replacement = "<img src=\"images/" + imageRef + ".png\" alt=\"" + altText + "\" style=\"zoom:67%;\" />";
//            String replacement = "![" + altText + "][../images/" + imageRef + "]";
            count++;
            MAPS.put(imageRef + ".png", "");
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Java 8兼容的文件读取方法
     */
    private static String readFileToString(Path filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(filePath);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Java 8兼容的文件写入方法
     */
    private static void writeStringToFile(Path filePath, String content) throws IOException {
        Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
    }
}