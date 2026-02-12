package programmer.lp;

import redis.clients.jedis.Jedis;

public class TestMasterSlaves {
    // 主从复制—读写分离
    public static void main(String[] args) throws InterruptedException {
        // 别忘了在redis.conf中注销bind和protected-mode no
        Jedis jedis_M = new Jedis("192.168.152.134", 6380);
        Jedis jedis_S = new Jedis("192.168.152.134", 6381);
        jedis_S.slaveof("192.168.152.134", 6380);
        jedis_M.set("k6", "v6");
        Thread.sleep(500);
        System.out.println(jedis_S.get("k6"));
    }
}
