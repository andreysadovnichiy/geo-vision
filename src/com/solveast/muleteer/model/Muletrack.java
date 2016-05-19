package com.solveast.muleteer.model;

import com.google.gson.annotations.Expose;
import com.solveast.muleteer.model.commons.State;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Muletrack {
    @Id
    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private double lat;

    @Expose
    private double lng;

    @Expose
    private Date date;

    @Expose
    private int distance;

    private int speed;

    private State state;

    private Time fixedStart;

    private Time fixedEnd;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "currentTrackingMode_id") //pDetail_FK
//    private TrackingMode currentTrackingMode;

    @OneToOne(mappedBy="mule", cascade = CascadeType.ALL)
    private TrackingMode currentTrackingMode;


    private boolean active;

    public Muletrack() {
    }

    public Muletrack(final int id, final String name, final boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Muletrack(final Muletrack muletrack) {
        this.id = muletrack.getId();
        this.name = muletrack.getName();
        this.lat = muletrack.getLat();
        this.lng = muletrack.getLng();
        this.active = muletrack.isActive();
        this.date = muletrack.getDate();
        this.distance = muletrack.getDistance();
        this.currentTrackingMode = muletrack.getCurrentTrackingMode();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public boolean isActive() {
        return active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void setLng(final double lng) {
        this.lng = lng;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    public void copy(final Muletrack mule) {
        this.id = mule.getId();
        this.name = mule.getName();
        this.lng = mule.getLng();
        this.lat = mule.getLat();
        this.date = mule.getDate();
        this.active = mule.isActive();
        this.distance = mule.getDistance();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public boolean isTrackingTimeFixedNow() {
        LocalTime nowTime = (new Time((new Date()).getTime())).toLocalTime();
        return this.getCurrentTrackingMode().getState() == State.FIXED
                && nowTime.isAfter(this.getCurrentTrackingMode().getFixedStart().toLocalTime())
                && nowTime.isBefore(this.getCurrentTrackingMode().getFixedEnd().toLocalTime());
    }

    public boolean isTrackingOnNow() {
        return this.getCurrentTrackingMode().getState() == State.ON;
    }

    public boolean isTrackingActiveNow() {
        return isTrackingOnNow() || isTrackingTimeFixedNow();
    }

    public boolean isNotNull() {
        return this.lat != 0 && this.lng != 0;
    }

    public TrackingMode getCurrentTrackingMode() {
        return currentTrackingMode;
    }

    public void setCurrentTrackingMode(final TrackingMode currentTrackingMode) {
        this.currentTrackingMode = currentTrackingMode;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public Time getFixedStart() {
        return fixedStart;
    }

    public void setFixedStart(final Time fixedStart) {
        this.fixedStart = fixedStart;
    }

    public Time getFixedEnd() {
        return fixedEnd;
    }

    public void setFixedEnd(final Time fixedEnd) {
        this.fixedEnd = fixedEnd;
    }

    @Override
    public String toString() {
        return "Muletrack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", date=" + date +
                ", distance=" + distance +
                ", speed=" + speed +
                ", active=" + active +
                '}';
    }
}
