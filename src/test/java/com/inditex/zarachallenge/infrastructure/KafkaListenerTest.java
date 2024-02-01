package com.inditex.zarachallenge.infrastructure;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;
import com.inditex.zarachallenge.model.entity.SizeProduct;
import com.inditex.zarachallenge.service.SizeProductService;
import com.inditex.zarachallenge.util.UtilDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class KafkaListenerTest {

    @Mock
    private SizeProductService sizeProductService;

    @Mock
    private Message<ProductAvailabilityEvent> message;

    @InjectMocks
    private KafkaListener kafkaListener;

    @Test
    public void should_update_sizeProduct() {
        ProductAvailabilityEvent productAvailabilityEvent = productAvailabilityEvent();

        SizeProduct existingSizeProduct = new SizeProduct();
        existingSizeProduct.setSizeId(1L);

        when(message.getPayload()).thenReturn(productAvailabilityEvent);
        when(sizeProductService.getSizeProduct(1L)).thenReturn(existingSizeProduct);

        Consumer<Message<ProductAvailabilityEvent>> consumer = kafkaListener.KafkaConsumer();
        consumer.accept(message);

        verify(sizeProductService).update(any(SizeProduct.class));
    }

    @Test
    public void should_not_update_sizeProduct() {
        ProductAvailabilityEvent productAvailabilityEvent = productAvailabilityEvent();

        when(message.getPayload()).thenReturn(productAvailabilityEvent);

        Consumer<Message<ProductAvailabilityEvent>> consumer = kafkaListener.KafkaConsumer();
        consumer.accept(message);

        verify(sizeProductService, never()).update(any(SizeProduct.class));
    }

    private ProductAvailabilityEvent productAvailabilityEvent(){
            return ProductAvailabilityEvent.builder()
                        .sizeId(1L)
                    .availability(Boolean.TRUE)
                    .update(UtilDate.parseTimeStamp("2023-11-24T12:40:01.773Z"))
                        .build();
    }
}