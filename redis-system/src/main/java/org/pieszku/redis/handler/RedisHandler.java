package org.pieszku.redis.handler;

import io.lettuce.core.pubsub.RedisPubSubListener;
import org.pieszku.redis.packet.Packet;

public abstract class RedisHandler implements RedisPubSubListener<String, Packet> {


    public RedisHandlerInfo handlerInfo(){
        return this.getClass().isAnnotationPresent(RedisHandlerInfo.class) ? this.getClass().getDeclaredAnnotation(RedisHandlerInfo.class) : null;
    }

    public abstract void handleReceiving(Packet packetClass);

    @Override
    public void message(String channel, Packet packet) {
        if(this.handlerInfo().channel().equalsIgnoreCase(channel) && packet.getClass().isAssignableFrom(this.handlerInfo().packetClass())){
            if(!packet.getPacketType().name().equals(this.handlerInfo().handlerType().name())){
                System.out.println("[REDIS-SERVER-HANDLER] HandlerType packet: " + packet.getClass().getSimpleName() + " must the same packetType");
                return;
            }
            this.handleReceiving(packet);
        }
    }

    @Override
    public void message(String s, String k1, Packet packet) {

    }

    @Override
    public void subscribed(String s, long l) {

    }

    @Override
    public void psubscribed(String s, long l) {

    }

    @Override
    public void punsubscribed(String s, long l) {

    }

    @Override
    public void unsubscribed(String s, long l) {

    }
}
