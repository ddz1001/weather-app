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

package com.github.dantezitello.weatherapp.controller;

import com.github.dantezitello.weatherapp.charting.RenderedChartEntity;
import com.github.dantezitello.weatherapp.charting.RenderedChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("charts")
public class ResourceAccessController {

    @Autowired
    RenderedChartRepository repository;

    @GetMapping("/{key}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("key") String key) throws SQLException, IOException {

        RenderedChartEntity entity = repository.findByResourceKey(key).get();

        return ResponseEntity.ok()
                .header("Content-Type",entity.getFormat().getMimeTypeString())
                .body(new InputStreamResource( entity.getData().getBinaryStream() ));
    }

}
