package turmaformacao.br.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {

    private Long id;
    private String name;
    private Address adress;
    private List<String> telephones;
    private List<String> socialNetworks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public List<String> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<String> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id = this.id==null?-1 : this.id);
        dest.writeString(this.name = this.name == null ? "" : this.name);
        dest.writeParcelable(this.adress, flags);
        dest.writeStringList(this.telephones = this.telephones == null? new ArrayList<String>(): this.telephones);
        dest.writeStringList(this.socialNetworks = this.socialNetworks == null? new ArrayList<String>(): this.socialNetworks);

    }

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.id = in.readLong();
        id = id == -1? null : id;
        this.name = in.readString();
        this.adress = in.readParcelable(Address.class.getClassLoader());
        this.telephones = in.createStringArrayList();
        this.socialNetworks = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
