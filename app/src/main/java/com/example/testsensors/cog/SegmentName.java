package com.example.testsensors.cog;

public enum SegmentName {
    HEAD_AND_NECK(10.75, 8.26, 0.59),
    TRUNK(30.0, 46.84, 0.44),
    UPPER_ARM(17.20, 3.25, 0.57),
    FORE_ARM(15.70, 1.87, 0.45),
    HAND(5.75, 0.65, 0.79),
    THIGH(23.20, 10.50, 0.40),
    LOWER_LEG(24.70, 4.75, 0.45),
    FOOT(4.25, 1.43, 0.44);

    private final double pct; //percent of total height
    private final double segmentWeight; //percent of total body weight
    private final double cog; //percent of segment length

    SegmentName(double pct, double segmentWeight, double cog) {
        this.pct = pct;
        this.segmentWeight = segmentWeight;
        this.cog = cog;
    }

    public double getPct() {
        return pct;
    }

    public double getSegmentWeight() {
        return segmentWeight;
    }

    public double getCog() {
        return cog;
    }
}

