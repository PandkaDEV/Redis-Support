package org.pieszku.redis.packet;

import org.pieszku.redis.type.RedisPacketType;

public class PacketExampleRequest extends Packet{

    private final int id;
    private final String message;

    public PacketExampleRequest(int id, String message) {
        super(RedisPacketType.REQUEST);
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
