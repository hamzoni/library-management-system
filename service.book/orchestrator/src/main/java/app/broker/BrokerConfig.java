package app.broker;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import saga.core.services.BrokerService;

public class BrokerConfig {
	@Bean
	public DirectExchange direct() {
		return new DirectExchange("sub-services-router");
	}

	@Bean
	public BrokerService sender() {
		return new BrokerService();
	}

	@Bean
	public Jackson2JsonMessageConverter jsonConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	Queue replyQueue() {
		return QueueBuilder.durable("services-replies-queue").build();
	}

	@Bean
	AsyncRabbitTemplate template(ConnectionFactory connectionFactory, RabbitTemplate template) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(replyQueue().getName());
		return new AsyncRabbitTemplate(template, container);
	}
}
