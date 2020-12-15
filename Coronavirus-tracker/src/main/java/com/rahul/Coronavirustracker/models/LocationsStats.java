package com.rahul.Coronavirustracker.models;

public class LocationsStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public String getState() {
        return state;
    }
    public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

	@Override
    public String toString() {
        return "LocationsStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }

    public void setState(String state) {
        this.state = state;
    }

}
