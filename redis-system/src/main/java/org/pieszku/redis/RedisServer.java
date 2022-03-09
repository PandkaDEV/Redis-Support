package org.pieszku.redis;

import org.nustaq.serialization.FSTConfiguration;
import org.pieszku.redis.common.impl.RedisCommonSupport;
import org.pieszku.redis.packet.PacketExampleRequest;
import org.pieszku.redis.service.RedisHandlerService;

import java.util.concurrent.locks.LockSupport;

public class RedisServer {

    private static RedisServer instance;
    private final RedisCommonSupport redisCommonSupport;
    private final RedisHandlerService redisHandlerService;
    private final FSTConfiguration fstConfiguration = FSTConfiguration.createDefaultConfiguration();

    public static RedisServer getInstance() {
        return instance;
    }

    public RedisServer(){
        instance = this;
        this.redisCommonSupport = new RedisCommonSupport();
        this.redisHandlerService = new RedisHandlerService();
        this.start();
    }
    public void start(){
        this.redisCommonSupport.connect("localhost", "");
        this.redisHandlerService.enable("org.pieszku.redis.handler.packet");

        this.redisCommonSupport.publishPacket("MASTER", new PacketExampleRequest(1, "elo"));
    }
    public static void main(String[] args){
        new RedisServer();
        LockSupport.park();
    }

    public FSTConfiguration getFstConfiguration() {
        return fstConfiguration;
    }

    public RedisCommonSupport getRedisCommonSupport() {
        return redisCommonSupport;
    }

    public RedisHandlerService getRedisHandlerService() {
        return redisHandlerService;
    }
}
