package com.inditex.zarachallenge.infrastructure;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;
import com.inditex.zarachallenge.mapper.SizeProductMapper;
import com.inditex.zarachallenge.model.entity.SizeProduct;
import com.inditex.zarachallenge.service.SizeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class KafkaListener {

	private final SizeProductService sizeProductService;

	@Bean
	public Consumer<Message<ProductAvailabilityEvent>> KafkaConsumer() {
		return message -> {
			ProductAvailabilityEvent productAvailabilityEvent = message.getPayload();
			SizeProduct sizeProduct = sizeProductService.getSizeProduct(productAvailabilityEvent.getSizeId());

			if(sizeProduct != null){
				sizeProductService.update(SizeProductMapper.updateEntity(productAvailabilityEvent, sizeProduct));
			}
		};
	}
}
