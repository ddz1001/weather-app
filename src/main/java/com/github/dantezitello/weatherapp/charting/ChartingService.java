

/*
 * Weather Charting Project
 * Copyright (C) 2023 Dante Zitello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.dantezitello.weatherapp.charting;

import com.github.dantezitello.weatherapp.graphics.ChartData;
import com.github.dantezitello.weatherapp.graphics.ChartOptions;
import com.github.dantezitello.weatherapp.graphics.ChartRenderer;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
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

        String key = generateResourceKey(options.getContentType().getSuffix());
        RenderedChartEntity entity = new RenderedChartEntity();
        entity.setCreatedTime( LocalDateTime.now() );
        entity.setFormat(options.getContentType());
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
