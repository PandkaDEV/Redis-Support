package org.pieszku.redis.handler;

import org.pieszku.redis.type.RedisHandlerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisHandlerInfo {

    String channel();
    Class<?> packetClass();
    RedisHandlerType handlerType();


}
