package com.kafka.kafkakotlinproducer.infrastructure.configs

import com.kafka.kafkakotlinproducer.domain.entities.Person
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfiguration {

    @Bean
    fun producerFactory(): ProducerFactory<String, Person>{
        var config: HashMap<String, Any> = HashMap()

        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return DefaultKafkaProducerFactory<String, Person>(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Person> {
        return KafkaTemplate(producerFactory())
    }
}