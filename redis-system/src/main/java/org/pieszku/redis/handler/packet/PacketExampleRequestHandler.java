package org.pieszku.redis.handler.packet;

import org.pieszku.redis.RedisServer;
import org.pieszku.redis.handler.RedisHandler;
import org.pieszku.redis.handler.RedisHandlerInfo;
import org.pieszku.redis.packet.Packet;
import org.pieszku.redis.packet.PacketExampleInfo;
import org.pieszku.redis.packet.PacketExampleRequest;
import org.pieszku.redis.type.RedisHandlerType;

@RedisHandlerInfo(channel = "MASTER", handlerType = RedisHandlerType.REQUEST, packetClass = PacketExampleRequest.class)
public class PacketExampleRequestHandler  extends RedisHandler {



    @Override
    public void handleReceiving(Packet packetClass) {
        PacketExampleRequest packetExampleRequest = (PacketExampleRequest) packetClass;
        System.out.println(packetExampleRequest.getPacketType());
        System.out.println(packetExampleRequest.getId());
        System.out.println(packetExampleRequest.getMessage());
        RedisServer.getInstance().getRedisCommonSupport().publishPacket("MASTER", new PacketExampleInfo("SIemka działam"));
    }
}
