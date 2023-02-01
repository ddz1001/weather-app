package com.github.dantezitello.weatherapp.controller;

import com.github.dantezitello.weatherapp.charting.RenderedChartEntity;
import com.github.dantezitello.weatherapp.charting.RenderedChartRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
