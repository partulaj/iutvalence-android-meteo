package classes;

/**
 * Created by QtoR2 on 06/02/2016.
 */

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Station {

    private String id;
    private String libellé;
    private String latitude;
    private String longitude;
    private String altitude;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Station(String id, String libellé, String latitude, String longitude, String altitude) {
        this.id = id;
        this.libellé = libellé;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The libellé
     */
    public String getLibellé() {
        return libellé;
    }

    /**
     * @param libellé The libellé
     */
    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The altitude
     */
    public String getAltitude() {
        return altitude;
    }

    /**
     * @param altitude The altitude
     */
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
