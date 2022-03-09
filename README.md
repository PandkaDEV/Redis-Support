# Redis-Support
Redis pub/sub handler message with custom object 


Connection and send example packet
```     
        this.redisCommonSupport.connect("localhost", ""); // connection
        this.redisHandlerService.enable("org.pieszku.redis.handler.packet"); //listen packets
        this.redisCommonSupport.publishPacket("MASTER", new PacketExampleRequest(1, "elo")); // send packet
```
Example packet: 
```
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
```
Listen class example packet:
```
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
```
EASY :D
