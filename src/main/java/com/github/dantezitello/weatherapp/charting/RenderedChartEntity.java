package com.github.dantezitello.weatherapp.charting;

import com.github.dantezitello.weatherapp.common.ContentGenerationType;
import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
public class RenderedChartEntity {

    @Id @GeneratedValue int id;
    @Column String resourceKey;
    @Column ContentGenerationType format;
    @Column LocalDateTime createdTime;
    @Column @Lob Blob data;


    public RenderedChartEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public ContentGenerationType getFormat() {
        return format;
    }

    public void setFormat(ContentGenerationType format) {
        this.format = format;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }
}
