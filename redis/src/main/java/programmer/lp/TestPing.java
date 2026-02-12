package programmer.lp;

import redis.clients.jedis.Jedis;

public class TestPing {

    public static void main(String[] args) {
        // redis.conf：
        // # bind 127.0.0.1
        // protected-mode no
        Jedis jedis = new Jedis("192.168.152.134", 6379);
        System.out.println(jedis.ping());
    }

}
