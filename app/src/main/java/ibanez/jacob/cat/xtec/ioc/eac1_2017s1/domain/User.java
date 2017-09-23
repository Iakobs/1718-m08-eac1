package ibanez.jacob.cat.xtec.ioc.eac1_2017s1.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * User type containing a single user's data
 *
 * @author <a href="mailto:jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3151897174913720049L;

    private String name;
    private String street;
    private String zipCode;
    private String town;
    private String phoneNumber;
    private String webSite;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User with its {@link User#name}.
     *
     * @param name the name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new User with all its attributes.
     *
     * @param name        the name
     * @param street      the street
     * @param zipCode     the zip code
     * @param town        the town
     * @param phoneNumber the phone number
     * @param webSite     the web site
     */
    public User(String name, String street, String zipCode, String town, String phoneNumber, String webSite) {
        this.name = name;
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets town.
     *
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets town.
     *
     * @param town the town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets web site.
     *
     * @return the web site
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Sets web site.
     *
     * @param webSite the web site
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(street, user.street) &&
                Objects.equals(zipCode, user.zipCode) &&
                Objects.equals(town, user.town) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(webSite, user.webSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, zipCode, town, phoneNumber, webSite);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town='" + town + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", webSite='" + webSite + '\'' +
                '}';
    }
}
