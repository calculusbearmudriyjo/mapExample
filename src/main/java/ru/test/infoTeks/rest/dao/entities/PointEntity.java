package ru.test.infoTeks.rest.dao.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="points")
public class PointEntity implements Serializable {

    private static final long serialVersionUID = -4862926644813433707L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="longitude")
    private double longitude;

    @Column(name="latitude")
    private double latitude;

    public PointEntity(){}

    public PointEntity(String title, double longitude, double latitude) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
