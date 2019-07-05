package com.yp.designpatterns.help;

public class Topic {
    public static final Topic NO_HELP_TOPIC = new Topic(-1);

    private final int topic;
    public Topic(int val) {
        this.topic = val;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Topic)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        return ((Topic)obj).topic == topic;
    }
}
