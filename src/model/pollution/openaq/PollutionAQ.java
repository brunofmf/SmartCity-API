package model.pollution.openaq;

import java.util.Date;

import model.pollution.AbstractPollution;

public class PollutionAQ extends AbstractPollution{

	protected PollutionType parameter;
	protected double value;
	protected String sourceName;
	protected String lastUpdated;
	
	public enum PollutionType {
		PM25{
	       	public String toString() {
	       		return "Particulate Matter 2.5";
	       	}
	    },
		PM10{
    		public String toString() {
	       		return "Particulate Matter 10";
	       	}
    	},
		SO2	{
			public String toString() {
	       		return "Sulfur Dioxide";
	       	}
		},
		NO2	{
			public String toString() {
	       		return "Nitrogen Dioxide";
	       	}
		},
		O3	{
			public String toString() {
	       		return "Ozone";
	       	}
		},
		CO	{
			public String toString() {
	       		return "Carbon Monoxide";
	       	}
		},
		BC	{
			public String toString() {
	       		return "Black Carbon";
	       	}
		},
		NA	{
			public String toString() {
	       		return "Non Available";
	       	}
		};
	}
	
	public PollutionAQ(double lat, double lon, String lastUpdated, String unit, String parameter, double value, String sourceName) {
		super(lat, lon, System.currentTimeMillis(), unit);
		switch (parameter) {
		    case "pm25": 
				this.parameter = PollutionType.PM25;
		    	break;
		    case "pm10": 
				this.parameter = PollutionType.PM10;
		    	break;
		    case "co": 
				this.parameter = PollutionType.CO;
		    	break;
		    case "bc": 
				this.parameter = PollutionType.BC;
		    	break;
		    case "so2": 
				this.parameter = PollutionType.SO2;
		    	break;
		    case "no2": 
				this.parameter = PollutionType.NO2;
		    	break;
		    case "o3": 
				this.parameter = PollutionType.O3;
		    	break;
		    default:
				this.parameter = PollutionType.NA;
		    ;
		}
		this.value = value;
		this.sourceName = sourceName;
		this.lastUpdated = lastUpdated;
	}
	
	public PollutionType getParameter() {
		return parameter;
	}

	public double getValue() {
		return value;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setParameter(PollutionType parameter) {
		this.parameter = parameter;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("******** Pollution Measurement - ").append(this.parameter.toString()).append(" - ").append(this.parameter.name()).append(" ********").append(System.getProperty("line.separator"));
		sb.append("Latitude: ").append(this.lat).append(System.getProperty("line.separator"));
		sb.append("Longitude: ").append(this.lon).append(System.getProperty("line.separator"));	
		sb.append("Date: ").append(new Date(this.date)).append(System.getProperty("line.separator"));	
		sb.append("Last Update Date: ").append(this.lastUpdated).append(System.getProperty("line.separator"));		
		sb.append("Parameter: ").append(this.parameter.toString()).append(System.getProperty("line.separator"));	
		sb.append("Value: ").append(this.value).append(" ").append(this.unit).append(System.getProperty("line.separator"));	
		sb.append("Data Source: ").append(this.sourceName).append(System.getProperty("line.separator"));	
		return sb.toString();
	}
	
}
