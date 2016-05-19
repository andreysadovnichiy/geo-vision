package com.solveast.muleteer.model;

import com.google.gson.annotations.Expose;
import com.solveast.muleteer.model.commons.State;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by TEST on 16.05.2016.
 */
@Entity
public class TrackingMode {
//    @Id
//    @GeneratedValue
//    private long id;

    @Id
    @GeneratedValue
    private int id;

//    @OneToOne(mappedBy = "currentTrackingMode", fetch = FetchType.LAZY)
//    @Expose private Muletrack mule;

    @Expose
    private Muletrack mule;


    @Enumerated(EnumType.ORDINAL)
    @Expose
    private State state;

    private Time fixedStart;

    private Time fixedEnd;

    @Expose
    private Date switchTime;

    public TrackingMode() {
    }

    public TrackingMode(final State state, final Time fixedStart, final Time fixedEnd) {
        this.state = state;
        this.fixedStart = fixedStart;
        this.fixedEnd = fixedEnd;
    }

    public static TrackingMode setDefaultMode(Muletrack mule) {
        TrackingMode trackingMode = new TrackingMode(State.FIXED, Time.valueOf("7:30:00"), Time.valueOf("20:00:00"));
        trackingMode.setMule(mule);
        return trackingMode;
    }

    public static TrackingMode setDefaultMode() {
        TrackingMode trackingMode = new TrackingMode(State.FIXED, Time.valueOf("7:30:00"), Time.valueOf("20:00:00"));
        return trackingMode;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public Date getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(final Date switchTime) {
        this.switchTime = switchTime;
    }

    public Muletrack getMule() {
        return mule;
    }

    public void setMule(final Muletrack mule) {
        this.mule = mule;
    }

    @Override
    public String toString() {
        return "TrackingMode{" +
                "id=" + id +
                ", state=" + state +
                ", fixedStart=" + fixedStart +
                ", fixedEnd=" + fixedEnd +
                ", switchTime=" + switchTime +
                '}';
    }
}
