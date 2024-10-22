package com.isadent.users.infrastructure.configuration;


import com.isadent.users.domain.model.UserCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, UserCredentials> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory
    ) {

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<UserCredentials> valueSerializer =
                new Jackson2JsonRedisSerializer<>(UserCredentials.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, UserCredentials> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);

        RedisSerializationContext<String, UserCredentials> context =
                builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
