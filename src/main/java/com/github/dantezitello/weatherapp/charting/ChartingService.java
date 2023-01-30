package com.github.dantezitello.weatherapp.charting;

import com.github.dantezitello.weatherapp.graphics.ChartData;
import com.github.dantezitello.weatherapp.graphics.ChartOptions;
import com.github.dantezitello.weatherapp.graphics.ChartRenderer;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class ChartingService {
    RenderedChartRepository repository;

    @Autowired
    public ChartingService(RenderedChartRepository repository) {
        this.repository = repository;
    }

    public String createChart(ChartData data, ChartOptions options) {

        String format = options.getContentType().name().toLowerCase();
        String key = generateResourceKey(format);
        RenderedChartEntity entity = new RenderedChartEntity();
        entity.setCreatedTime( LocalDateTime.now() );
        entity.setFormat(format);
        entity.setResourceKey( key );

        Blob blob = BlobProxy.generateProxy( ChartRenderer.renderChart(data, options) );
        entity.setData(blob);

        repository.save(entity);

        return key;
    }

    public RenderedChartEntity fetchFromKey(String resourceKey) {
        return repository.findByResourceKey(resourceKey).get();
    }



    private String generateResourceKey(String format) {
        UUID uuid = UUID.randomUUID();
        String keyPrefix = Base64.getEncoder().encodeToString(uuid.toString().getBytes(StandardCharsets.UTF_8));
        return String.format("%s.%s", keyPrefix, format);
    }



}
