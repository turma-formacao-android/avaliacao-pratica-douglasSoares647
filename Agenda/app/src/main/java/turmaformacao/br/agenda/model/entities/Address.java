package turmaformacao.br.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Address implements Parcelable {

    private String zipCode;
    private String type;
    private String street;
    private String neighborhood;
    private String city;
    private String state;


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.zipCode = this.zipCode == null?"" : this.zipCode);
        dest.writeString(this.type = this.type == null?"" : this.type);
        dest.writeString(this.street = this.street == null?"" : this.street);
        dest.writeString(this.neighborhood = this.neighborhood == null?"" : this.neighborhood);
        dest.writeString(this.city = this.city == null?"" : this.city);
        dest.writeString(this.state = this.state == null?"" : this.state);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.zipCode = in.readString();
        this.type = in.readString();
        this.street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
