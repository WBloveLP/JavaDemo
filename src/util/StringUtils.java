package util;

public class StringUtils {

    public static void main(String[] args) {
        generateHTMLWithColor(
                "一路摸爬滚打，在实践中不断成长"
        );
    }

    public static final String[] colors = {
            "red",
            "orange",
            "green",
            "blue",
            "purple"
    };

    /**
     * 写HTML时生成字体颜色
     * BLOG
     *
     * @param str 源字符串
     */
    public static void generateHTMLWithColor(String str) {
        StringBuilder sb = new StringBuilder();
        int colorI = 0;
        for (int i = 0, length = str.length(); i < length; i++) {
            sb.append("<font color=\"");
            sb.append(colors[colorI]);
            sb.append("\">");
            sb.append(str.substring(i, i + 1));
            sb.append("</font>");
            colorI++;
            colorI = colorI % colors.length;
        }
        System.out.println(sb);
    }

}
