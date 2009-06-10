package br.com.caelum.recorder.modelo;

/**
 * @author leonardobessa
 * 
 */
public class Restaurant {

    private String name;
    private String speciality;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    private City city;


}
