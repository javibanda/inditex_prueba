package com.inditex.zarachallenge.config;

import com.inditex.zarachallenge.infrastructure.KafkaListener;
import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockserver.netty.MockServer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ZaraInitialConfigTest {
    @InjectMocks
    private ZaraInitialConfig zaraInitialConfig;
    @Mock
    private KafkaListener kafkaListener;
    @Mock
    private MockServer mockServer;

    @Test
    public void testRun() throws Exception {
        Path tempFile = Files.createTempFile("events", ".csv");
        Files.write(tempFile, List.of("1,true,2022-01-01T00:00:00.000Z"));

        zaraInitialConfig.run(tempFile.toString());

        verify(kafkaListener).KafkaConsumer();

    }

    @Test
    public void should_make_on_destroy(){
        zaraInitialConfig.onDestroy();
        verify(mockServer, times(1)).stop();
    }

    @Test
    public void should_convert_stock() {
        List<String> stock = List.of("1", "true", "2022-01-01T00:00:00.000Z");
        ProductAvailabilityEvent result = zaraInitialConfig.convertStock(stock);
        assertTrue(result.isAvailability());
        assertEquals(Timestamp.valueOf(LocalDateTime.parse("2022-01-01T00:00:00.000Z",
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"))), result.getUpdate());
    }

}