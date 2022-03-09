package org.pieszku.redis.service;

import org.pieszku.redis.RedisServer;
import org.pieszku.redis.handler.RedisHandler;
import org.pieszku.redis.common.impl.RedisCommonSupport;
import org.reflections.Reflections;

public class RedisHandlerService {


    private final RedisCommonSupport redisCommonSupport = RedisServer.getInstance().getRedisCommonSupport();

    public void enable(String packageName) {
        long time = System.currentTimeMillis();
        for (Class<? extends RedisHandler> redisHandlerClass : new Reflections(packageName).getSubTypesOf(RedisHandler.class)) {
            try {
                RedisHandler redisHandler = redisHandlerClass.newInstance();
                this.redisCommonSupport.subscribeHandler(redisHandler.handlerInfo().channel(), redisHandler);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[REDIS-SERVER] Register handlers " + (System.currentTimeMillis() - time)  + " ms");
    }
}
