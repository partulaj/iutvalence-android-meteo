package classes;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Created by QtoR2 on 06/02/2016.
 */

@Generated("org.jsonschema2pojo")
public class Releve {

    private String station;
    private String quand;
    private String temp1;
    private String temp2;
    private String pressure;
    private String lux;
    private String hygro;
    private String windSpeed;
    private String windDir;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Releve(String station, String quand, String temp1, String temp2, String pressure, String lux, String hygro, String windSpeed, String windDir) {
        this.station = station;
        this.quand = quand;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.pressure = pressure;
        this.lux = lux;
        this.hygro = hygro;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }

    /**
     * @return The station
     */
    public String getStation() {
        return station;
    }

    /**
     * @param station The station
     */
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * @return The quand
     */
    public String getQuand() {
        return quand;
    }

    /**
     * @param quand The quand
     */
    public void setQuand(String quand) {
        this.quand = quand;
    }

    /**
     * @return The temp1
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * @param temp1 The temp1
     */
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * @return The temp2
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * @param temp2 The temp2
     */
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    /**
     * @return The pressure
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The lux
     */
    public String getLux() {
        return lux;
    }

    /**
     * @param lux The lux
     */
    public void setLux(String lux) {
        this.lux = lux;
    }

    /**
     * @return The hygro
     */
    public String getHygro() {
        return hygro;
    }

    /**
     * @param hygro The hygro
     */
    public void setHygro(String hygro) {
        this.hygro = hygro;
    }

    /**
     * @return The windSpeed
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed The windSpeed
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return The windDir
     */
    public String getWindDir() {
        return windDir;
    }

    /**
     * @param windDir The windDir
     */
    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
