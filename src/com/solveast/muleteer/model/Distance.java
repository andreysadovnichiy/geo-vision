package com.solveast.muleteer.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by TEST on 12.05.2016.
 */
@Entity
public class Distance {
    @Id
    @GeneratedValue
    private int id;

    @Expose private int distance;

    @Expose private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mule_id")
    @Expose private Muletrack mule;

    public Distance() {

    }

    public Distance(final int distance, final Date date, final Muletrack mule) {
        this.distance = distance;
        this.date = date;
        this.mule = mule;
    }

    public Distance(final Muletrack mule) {
        this.distance = mule.getDistance();
        this.date = mule.getDate();
        this.mule = mule;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Muletrack getMule() {
        return mule;
    }

    public void setMule(final Muletrack mule) {
        this.mule = mule;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", distance=" + distance +
                ", date=" + date +
                '}';
    }
}
