package org.pieszku.redis.packet;

import org.pieszku.redis.type.RedisPacketType;

import java.io.Serializable;

public class Packet implements Serializable {

    private final RedisPacketType packetType;

    public Packet(RedisPacketType packetType){
        this.packetType = packetType;
    }
    public RedisPacketType getPacketType() {
        return packetType;
    }
}
