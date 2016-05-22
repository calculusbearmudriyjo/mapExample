package ru.test.infoTeks.rest.resource;

import ru.test.infoTeks.rest.dao.entities.PointEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Point implements Serializable{

    private static final long serialVersionUID = -4862926644813433707L;

    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "longitude")
    private double longitude;

    @XmlElement(name = "latitude")
    private double latitude;

    public Point(){}

    public Point(String title, double longitude, double latitude) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Point(String title, String longitude, String latitude) {
        this.title = title;
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
    }

    public Point(PointEntity point) {
        this.id = point.getId();
        this.title = point.getTitle();
        this.longitude = point.getLongitude();
        this.latitude = point.getLatitude();
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
