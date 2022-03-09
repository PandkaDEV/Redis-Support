package org.pieszku.redis.packet.serializer;

import io.lettuce.core.codec.RedisCodec;
import org.pieszku.redis.RedisServer;
import org.pieszku.redis.packet.Packet;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PacketSerializerCodec implements RedisCodec<String, Packet> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public String decodeKey(ByteBuffer byteBuffer) {
       return charset.decode(byteBuffer).toString();
    }

    @Override
    public Packet decodeValue(ByteBuffer byteBuffer) {
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return (Packet) RedisServer.getInstance().getFstConfiguration().asObject(bytes);
    }

    @Override
    public ByteBuffer encodeKey(String key) {
        return charset.encode(key);
    }

    @Override
    public ByteBuffer encodeValue(Packet value) {
        return ByteBuffer.wrap(RedisServer.getInstance().getFstConfiguration().asByteArray(value));
    }
}
