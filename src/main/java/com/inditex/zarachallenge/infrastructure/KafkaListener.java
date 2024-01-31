package com.inditex.zarachallenge.infrastructure;

import java.util.function.Consumer;

import com.inditex.zarachallenge.mapper.SizeProductMapper;
import com.inditex.zarachallenge.service.SizeProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;

@Component
@RequiredArgsConstructor
public class KafkaListener {

	private final SizeProductService sizeProductService;

	@Bean
	public Consumer<Message<ProductAvailabilityEvent>> KafkaConsumer() {
		return message -> {
			val productAvailabilityEvent = message.getPayload();
			val sizeProduct = sizeProductService.getSizeProduct(productAvailabilityEvent.getSizeId());

			if(sizeProduct != null){
				sizeProductService.update(SizeProductMapper.updateEntity(productAvailabilityEvent, sizeProduct));
			}
		};
	}
}
