package com.yp.zookeeper.curator.leader;

public class Participant {
    private final String id;
    private final boolean isLeader;

    public Participant(String id, boolean leader) {
        this.id = id;
        this.isLeader = leader;
    }

    public Participant() {
        this("", false);
    }

    public String getId() {
        return id;
    }

    public boolean isLeader() {
        return isLeader;
    }

    @Override
    public String toString() {
        return "Participant{id=" + id + ", isLeader=" + isLeader + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        Participant that = (Participant) obj;

        if(isLeader != that.isLeader) {
            return false;
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (isLeader ? 1 : 0);
        return result;
    }
}
