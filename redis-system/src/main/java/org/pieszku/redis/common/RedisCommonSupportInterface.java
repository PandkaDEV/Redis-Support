package org.pieszku.redis.common;

import org.pieszku.redis.handler.RedisHandler;
import org.pieszku.redis.packet.Packet;

public interface RedisCommonSupportInterface {

    void connect(String host, String password);
    void publishPacket(String channel, Packet packet);
    void subscribeHandler(String channel, RedisHandler handler);
    void disconnect();
    boolean isConnected();
}
