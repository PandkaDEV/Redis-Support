package org.pieszku.redis.packet;

import org.pieszku.redis.type.RedisPacketType;

public class PacketExampleInfo extends Packet{


    private final String message;

    public PacketExampleInfo(String message) {
        super(RedisPacketType.INFORMATION);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
