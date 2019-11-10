package com.example.redisdemo

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.io.Serializable

@Configurable
@AutoConfigureAfter(value = [RedisAutoConfiguration::class])
class RedisConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: LettuceConnectionFactory): RedisTemplate<String, Serializable> {
        val t = RedisTemplate<String, Serializable>()
        t.keySerializer = StringRedisSerializer()
        t.valueSerializer = GenericJackson2JsonRedisSerializer()
        t.setConnectionFactory(redisConnectionFactory)
        return t
    }

}

// StringRedisSerializer: Simple {@link java.lang.String} to {@literal byte[]} (and back) serializer.
// GenericJackson2JsonRedisSerializer: Generic Jackson 2-based {@link RedisSerializer} that maps {@link Object objects} to JSON using dynamic typing.
// GenericJackson2JsonRedisSerializer implements RedisSerializer<Object>,
// RedisSerializer: Basic interface serialization and deserialization of Objects to byte arrays (binary data).
