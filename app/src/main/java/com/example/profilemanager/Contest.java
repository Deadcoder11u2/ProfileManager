package com.example.profilemanager;

public class Contest {
    private int oldRating;
    private int newRating;
    private String contestName;

    public Contest(int oldRating, int newRating, String contestName) {
        this.oldRating = oldRating;
        this.newRating = newRating;
        this.contestName = contestName;
    }

    public int getOldRating() {
        return oldRating;
    }

    public void setOldRating(int oldRating) {
        this.oldRating = oldRating;
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    @Override
    public String toString() {
        return "Contest [oldRating=" + oldRating + ", newRating=" + newRating + ", contestName=" + contestName + "]";
    }
}
