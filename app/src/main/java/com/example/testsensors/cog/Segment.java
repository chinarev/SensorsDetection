package com.example.testsensors.cog;

import com.example.testsensors.Constants;

public class Segment {
    private SegmentName name;
    private double length;
    private double mass;
    private double cog;

    public Segment(SegmentName segmentName) {
        this.name = segmentName;
        length = Constants.HEIGHT * name.getPct() / 100;
        mass = Constants.WEIGHT * name.getSegmentWeight() / 100;
        cog = length * name.getCog();
    }

    public double getLength() {
        return length;
    }

    public double getMass() {
        return mass;
    }

    public double getCog() {
        return cog;
    }
}
