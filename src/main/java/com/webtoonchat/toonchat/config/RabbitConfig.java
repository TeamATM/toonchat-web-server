// package com.webtoonchat.stomp_rabbitmq.config;
//
// import org.springframework.amqp.rabbit.annotation.EnableRabbit;
// import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
// @Configuration
// @EnableRabbit
// public class RabbitConfig {
// 	@Bean
// 	public RabbitTemplate rabbitTemplate() {
// 		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
// 		rabbitTemplate.setMessageConverter(jsonMessageConverter());
// 		return rabbitTemplate;
// 	}
//
// 	@Bean
// 	public ConnectionFactory connectionFactory() {
// 		CachingConnectionFactory factory = new CachingConnectionFactory();
// 		factory.setHost("52.78.234.182");
// 		factory.setUsername("atm");
// 		factory.setPassword("soma14atm@!");
// 		return factory;
// 	}
//
// 	// @Bean
// 	// public SimpleMessageListenerContainer container(){
// 	// 	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
// 	// 	container.setConnectionFactory(connectionFactory());
// 	// 	container.setQueueNames(CHAT_QUEUE_NAME);
// 	// 	container.setMessageListener(null);
// 	// 	return container;
// 	// }
//
// 	@Bean
// 	public Jackson2JsonMessageConverter jsonMessageConverter() {
// 		//LocalDateTime serializable을 위해
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
// 		objectMapper.registerModule(dateTimeModule());
//
// 		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
//
// 		return converter;
// 	}
//
// 	@Bean
// 	public JavaTimeModule dateTimeModule() {
// 		return new JavaTimeModule();
// 	}
// }
