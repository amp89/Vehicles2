package entities;

public class SearchFormVehicleData {

//	private String id;
	private Integer yearLow;
	private Integer yearHigh;
	private String model;
	private String make;
//	private String mechData;
	private String driveType;
	private Double displacementLow;
	private Double displacementhigh;
	private String transmissionType;
	private String fuelType;
//	private EpaData epaData;
	private Double cityMpgLow;
	private Double cityMpgHigh;
	private Boolean hasGasTax;
	private Double emissionsLow;
	private Double emissionsHigh;
	private Double highwayMpgLow;
	private Double highwayMpgHigh;
	private Double averageMpgHigh;
	
	public SearchFormVehicleData(){}
	
	public SearchFormVehicleData(Integer yearLow, Integer yearHigh, String model, String make, String driveType,
			Double displacementLow, Double displacementhigh, String transmissionType, String fuelType,
			Double cityMpgLow, Double cityMpgHigh, Boolean hasGasTax, Double emissionsLow, Double emissionsHigh,
			Double highwayMpgLow, Double highwayMpgHigh, Double averageMpgHigh) {
		super();
		this.yearLow = yearLow;
		this.yearHigh = yearHigh;
		this.model = model;
		this.make = make;
		this.driveType = driveType;
		this.displacementLow = displacementLow;
		this.displacementhigh = displacementhigh;
		this.transmissionType = transmissionType;
		this.fuelType = fuelType;
		this.cityMpgLow = cityMpgLow;
		this.cityMpgHigh = cityMpgHigh;
		this.hasGasTax = hasGasTax;
		this.emissionsLow = emissionsLow;
		this.emissionsHigh = emissionsHigh;
		this.highwayMpgLow = highwayMpgLow;
		this.highwayMpgHigh = highwayMpgHigh;
		this.averageMpgHigh = averageMpgHigh;
	}

	public Integer getYearLow() {
		return yearLow;
	}

	public void setYearLow(Integer yearLow) {
		this.yearLow = yearLow;
	}

	public Integer getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(Integer yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public Double getDisplacementLow() {
		return displacementLow;
	}

	public void setDisplacementLow(Double displacementLow) {
		this.displacementLow = displacementLow;
	}

	public Double getDisplacementhigh() {
		return displacementhigh;
	}

	public void setDisplacementhigh(Double displacementhigh) {
		this.displacementhigh = displacementhigh;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Double getCityMpgLow() {
		return cityMpgLow;
	}

	public void setCityMpgLow(Double cityMpgLow) {
		this.cityMpgLow = cityMpgLow;
	}

	public Double getCityMpgHigh() {
		return cityMpgHigh;
	}

	public void setCityMpgHigh(Double cityMpgHigh) {
		this.cityMpgHigh = cityMpgHigh;
	}

	public Boolean getHasGasTax() {
		return hasGasTax;
	}

	public void setHasGasTax(Boolean hasGasTax) {
		this.hasGasTax = hasGasTax;
	}

	public Double getEmissionsLow() {
		return emissionsLow;
	}

	public void setEmissionsLow(Double emissionsLow) {
		this.emissionsLow = emissionsLow;
	}

	public Double getEmissionsHigh() {
		return emissionsHigh;
	}

	public void setEmissionsHigh(Double emissionsHigh) {
		this.emissionsHigh = emissionsHigh;
	}

	public Double getHighwayMpgLow() {
		return highwayMpgLow;
	}

	public void setHighwayMpgLow(Double highwayMpgLow) {
		this.highwayMpgLow = highwayMpgLow;
	}

	public Double getHighwayMpgHigh() {
		return highwayMpgHigh;
	}

	public void setHighwayMpgHigh(Double highwayMpgHigh) {
		this.highwayMpgHigh = highwayMpgHigh;
	}

	public Double getAverageMpgHigh() {
		return averageMpgHigh;
	}

	public void setAverageMpgHigh(Double averageMpgHigh) {
		this.averageMpgHigh = averageMpgHigh;
	}
	
	
	
	
	
	
	
}
