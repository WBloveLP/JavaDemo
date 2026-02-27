package com.example.sampledemo.test;

import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeSubtract {
    public static void main(String[] args) {
        // 定义两个时间点
        LocalDateTime time1 = LocalDateTime.of(2025, 11, 27, 10, 0, 0); // 2025-11-27 10:00:00
        LocalDateTime time2 = LocalDateTime.of(2025, 11, 28, 12, 30, 15); // 2025-11-28 12:30:15

        // 计算两个时间的差值（Duration对象）
        Duration duration = Duration.between(time1, time2);

        // 提取各单位的差值
        long days = duration.toDays(); // 总天数：1
        long hours = duration.toHours() % 24; // 剩余小时：2
        long minutes = duration.toMinutes() % 60; // 剩余分钟：30
        long seconds = duration.getSeconds() % 60; // 剩余秒：15
        long nanos = duration.getNano(); // 纳秒：0

        System.out.printf("差值：%d天 %d小时 %d分钟 %d秒%n", days, hours, minutes, seconds);
        // 输出：差值：1天 2小时 30分钟 15秒
    }
}