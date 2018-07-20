package model.pollution.owm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pollution.AbstractPollution;

public class PollutionOWM extends AbstractPollution{

	//Class Variables
	protected PollutionType parameter; 	
	//Variable only to be used in case of OZONE or UV pollution
	protected double value;
	//Lists of Value and Precision to be used
	protected List<List<Double>> data;
	
	public enum PollutionType {
		O3 {
	       	public String toString() {
	       		return "Ozone";
	       	}
	    },
		UV {
	       	public String toString() {
	       		return "Ultraviolet";
	       	}
	    },
		CO {
	       	public String toString() {
	       		return "Carbon Monoxide";
	       	}
	    },
		SO2 {
	       	public String toString() {
	       		return "Sulfur Dioxide";
	       	}
	    },
		NO2 {
	       	public String toString() {
	       		return "Nitrogen Dioxide";
	       	}
	    },
	    NA {
			public String toString() {
	       		return "Non Available";
	       	}
		};
	}
	
	public PollutionOWM(double lat, double lon, long date, String unit, String parameter, double value, List<List<Double>> data) {
		super(lat, lon, date*1000, unit);
		switch (parameter) {
		    case "O3":
				this.parameter = PollutionType.O3;
				this.value = value;
				this.data = null;
		    	break;
		    case "UV":
				this.parameter = PollutionType.UV;
				this.value = value;
				this.data = null;
		    	break;
		    case "CO":
				this.parameter = PollutionType.CO;
				this.value = -99.0;
				this.data = clone(data);
		    	break;
		    case "SO2":
				this.parameter = PollutionType.SO2;
				this.value = -99.0;
				this.data = clone(data);
		    	break;
		    case "NO2":
				this.parameter = PollutionType.NO2;
				this.value = -99.0;
				this.data = clone(data);
		    	break;
		    default:
				this.parameter = PollutionType.NA;
				this.value = -99.0;
				this.data = null;
		    ;
		}
	}
	
	public PollutionType getParameter() {
		return parameter;
	}
	
	public void setParameter(PollutionType parameter) {
		this.parameter = parameter;
	}
	
	public double getValue() {
        if(!Double.isNaN(value))
            return value;
        else
            return -99;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double[] getMeanValuePrecision() {
	    double mean[] = new double[]{0.0, 0.0}; //mean of Value/Precision
	    int validElements = 0;
	    if(value == -99.0) {
	        for(List<Double> values : data) {
	            if(!Double.isNaN(values.get(0)) && !Double.isNaN(values.get(1))) {
    	            mean[0]+=values.get(0); //Value
                    mean[1]+=values.get(1); //Precision
                    validElements++;
	            }
	        }
	        mean[0] = mean[0]/validElements;
            mean[1] = mean[1]/validElements;
	    }
	    return mean;
	}
	
	private List<List<Double>> clone(List<List<Double>> data) {
		List<List<Double>> cloned = new ArrayList<>();
		
		for(List<Double> d : data) {
			List<Double> inner = new ArrayList<>();
			for(int i=0; i < d.size(); i++)
				inner.add(d.get(i));
			cloned.add(inner);
		}
		
		return cloned;
	}

	public String toString() {		
		StringBuilder sb = new StringBuilder();
		sb.append("********  Pollution Measurement - ").append(this.parameter.toString()).append(" - ").append(this.parameter.name()).append(" ********").append(System.getProperty("line.separator"));
		sb.append("Latitude: ").append(this.lat).append(System.getProperty("line.separator"));
		sb.append("Longitude: ").append(this.lon).append(System.getProperty("line.separator"));	
		sb.append("Date: ").append(new Date(this.date)).append(System.getProperty("line.separator"));
		
		if(this.parameter == PollutionType.NA) {
			return sb.toString();					
		}
		else if(this.parameter == PollutionType.O3 || this.parameter == PollutionType.UV) {
			sb.append("Value: ").append(this.value).append(" ").append(this.unit).append(System.getProperty("line.separator"));				
		} else {
			for(List<Double> d : this.data) {
				sb.append("Value: ").append(d.get(0)).append(" ").append(this.unit).append(System.getProperty("line.separator"));	
				sb.append("Precision: ").append(d.get(1)).append(System.getProperty("line.separator"));
				sb.append("---").append(System.getProperty("line.separator"));
			}			
		}
		
		return sb.toString();
	}

}
