package org.pieszku.redis.handler.packet;

import org.pieszku.redis.handler.RedisHandler;
import org.pieszku.redis.handler.RedisHandlerInfo;
import org.pieszku.redis.packet.Packet;
import org.pieszku.redis.packet.PacketExampleInfo;
import org.pieszku.redis.type.RedisHandlerType;

@RedisHandlerInfo(channel = "MASTER", packetClass = PacketExampleInfo.class, handlerType = RedisHandlerType.INFORMATION)
public class PacketExampleInfoHandler extends RedisHandler {

    @Override
    public void handleReceiving(Packet packetClass) {
        PacketExampleInfo packetExampleInfo = (PacketExampleInfo) packetClass;
        System.out.println(packetExampleInfo.getMessage());
    }
}
