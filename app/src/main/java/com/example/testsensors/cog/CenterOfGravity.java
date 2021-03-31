package com.example.testsensors.cog;

import com.example.testsensors.Constants;

import java.util.ArrayList;
import java.util.List;

public class CenterOfGravity {
    
    private double sumOfMoments = 0;
    private double moment;

    Segment headAndNeck = new Segment(SegmentName.HEAD_AND_NECK);
    Segment trunk = new Segment(SegmentName.TRUNK);
    Segment upperArm = new Segment(SegmentName.UPPER_ARM);
    Segment foreArm = new Segment(SegmentName.FORE_ARM);
    Segment hand = new Segment(SegmentName.HAND);
    Segment thigh = new Segment(SegmentName.THIGH);
    Segment lowerLeg = new Segment(SegmentName.LOWER_LEG);
    Segment foot = new Segment(SegmentName.FOOT);

    public double findMoment(double segmentCogLocation, double segmentCOG, double segmentMass, int segmentsCount) {
        moment = (segmentCogLocation - segmentCOG) * segmentMass * segmentsCount;
        return moment;
    }

    public double sumOfMoments() {
        List<Double> moments = new ArrayList<>();
        double headAndNeckMoment = findMoment(Constants.HEIGHT, headAndNeck.getCog(), headAndNeck.getMass(), 1);
        double trunkMoment = findMoment(Constants.HEIGHT-headAndNeck.getLength(), trunk.getCog(), trunk.getMass(), 1);
        double upperArmMoment = findMoment(Constants.HEIGHT-headAndNeck.getLength(), upperArm.getCog(), upperArm.getMass(), 2);
        double foreArmMoment = findMoment(Constants.HEIGHT-headAndNeck.getLength()-upperArm.getLength(), foreArm.getCog(), foreArm.getMass(), 2);
        double handMoment = findMoment(Constants.HEIGHT-headAndNeck.getLength()-upperArm.getLength()-foreArm.getLength(), hand.getCog(), hand.getMass(), 2);
        double thighMoment = findMoment(foot.getLength()+lowerLeg.getLength()+thigh.getLength(), thigh.getCog(), thigh.getMass(), 2);
        double lowerLegMoment = findMoment(foot.getLength()+lowerLeg.getLength(), lowerLeg.getCog(), lowerLeg.getMass(), 2);
        double footMoment = findMoment(foot.getLength(), foot.getCog(), foot.getMass(),2);

        moments.add(headAndNeckMoment);
        moments.add(trunkMoment);
        moments.add(upperArmMoment);
        moments.add(foreArmMoment);
        moments.add(handMoment);
        moments.add(thighMoment);
        moments.add(lowerLegMoment);
        moments.add(footMoment);

        for (double i: moments) {
            sumOfMoments += i;
        }

        return sumOfMoments;
    }

    public double findCOG(){
        double humanCOG = sumOfMoments() / Constants.WEIGHT;
        return humanCOG;
    }
}
