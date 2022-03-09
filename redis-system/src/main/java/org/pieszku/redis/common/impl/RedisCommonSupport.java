package org.pieszku.redis.common.impl;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.pieszku.redis.common.RedisCommonSupportInterface;
import org.pieszku.redis.handler.RedisHandler;
import org.pieszku.redis.packet.Packet;
import org.pieszku.redis.packet.serializer.PacketSerializerCodec;

public class RedisCommonSupport implements RedisCommonSupportInterface {


    private RedisClient redisClient;
    private final PacketSerializerCodec packetSerializerCodec =  new PacketSerializerCodec();
    private StatefulRedisPubSubConnection<String, Packet> pubSubConnection;
    private StatefulRedisConnection<String, Packet> connection;

    @Override
    public void connect(String host, String password) {
        long time = System.currentTimeMillis();
        this.redisClient = RedisClient.create(RedisURI.builder()
                .withHost(host)
                .withPort(6379)
                .withPassword(password)
                .build());

        this.pubSubConnection = this.redisClient.connectPubSub(this.packetSerializerCodec);
        this.connection = this.redisClient.connect(this.packetSerializerCodec);
        System.out.println("[REDIS-SERVER] Connect to redis-client: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Override
    public void publishPacket(String channel, Packet packet) {
        this.connection.sync().publish(channel, packet);
    }

    @Override
    public void subscribeHandler(String channel, RedisHandler handler) {
        this.pubSubConnection.sync().subscribe(channel);
        this.pubSubConnection.addListener(handler);
    }
    @Override
    public void disconnect() {
        this.redisClient.shutdown();
    }

    @Override
    public boolean isConnected() {
        return this.redisClient.getOptions().isPingBeforeActivateConnection();
    }

    public StatefulRedisPubSubConnection<String, Packet> getPubSubConnection() {
        return pubSubConnection;
    }

    public StatefulRedisConnection<String, Packet> getConnection() {
        return connection;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public PacketSerializerCodec getPacketSerializerCodec() {
        return packetSerializerCodec;
    }
}
