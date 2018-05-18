# SmartCity-API
A set of APIs fostering Ambient Intelligence within a Smart City

## Main Achievements
- Software collecting data about the <b>current weather</b> and its <b>forecast</b> in a city:
  - weather description, temperature, atmospheric pressure, humidity, wind speed, cloudiness, rain, sunrise and sunset.
- Software collecting data about <b>pollution</b> in a city, if available:
  - ultraviolet index, ozone layer thickness (DU), carbon monoxide, sulfur dioxide and nitrogen dioxide volume mixing ratio, particulate matter 2.5, particulate matter 10 and black carbon.
- Software collecting data about <b>traffic flow</b> and <b>traffic incidents</b> in a city:
  - incident latitude and longitude, intersection or location where the traffic due to the incident starts and ends, length of the incident in meters, delay caused by the incident in seconds, the road/s affected by the incident, cause of the incident, description of the incident, free flow speed, current speed and the magnitude of delay, among many others.
- Function to roughly model <b>luminosity along the day</b>;
- Integration with <b>Open Weather Maps</b>, <b>Open Air Quality</b> and <b>Tomtom</b> APIs.

## Setup
1. Go to Consts class and define your Open Weather Maps (OPEN_WEATHER_API_KEY) and Tomtom (TOMTOM_API_KEY) API keys;
2. Go to the Main class and just comment/uncomment the services that you want to execute;
3. Run!

## Notes
- By default, data is gathered for the city of Braga, Portugal;
- For now data will be printed in the console. In the future data will be integrated within a database such as Firebase (or other);
- CallableServices class still to be developed.
