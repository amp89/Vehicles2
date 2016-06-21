package entities;

public class EpaData {
	private double cityMpg;
	private boolean hasGasTax;
	private double emissions;
	private double highwayMpg;
	private double averageMpg;
	
	public EpaData(){
		
		
		
	}
	
	
	
	public EpaData(double cityMpg, boolean hasGasTax, double emissions, double highwayMpg, double averageMpg) {
		super();
		this.cityMpg = cityMpg;
		this.hasGasTax = hasGasTax;
		this.emissions = emissions;
		this.highwayMpg = highwayMpg;
	}



	public double getCityMpg() {
		return cityMpg;
	}
	public void setCityMpg(double cityMpg) {
		this.cityMpg = cityMpg;
	}
	public boolean isHasGasTax() {
		return hasGasTax;
	}
	public void setHasGasTax(boolean hasGasTax) {
		this.hasGasTax = hasGasTax;
	}
	public double getEmissions() {
		return emissions;
	}
	public void setEmissions(double emissions) {
		this.emissions = emissions;
	}
	public double getHighwayMpg() {
		return highwayMpg;
	}
	public void setHighwayMpg(double highwayMpg) {
		this.highwayMpg = highwayMpg;
	}



	public double getAverageMpg() {
		return averageMpg;
	}



	public void setAverageMpg(double averageMpg) {
		this.averageMpg = averageMpg;
	}
	
	
	
}
