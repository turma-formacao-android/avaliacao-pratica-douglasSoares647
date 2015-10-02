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
    private List<Telephone> telephones;
    private List<SocialNetwork> socialNetworks;
    private List<Email> emails;


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

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
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
        dest.writeValue(this.id==null?-1:this.id);
        dest.writeString(this.name==null?"":this.name);
        dest.writeParcelable(this.adress, 0);
        dest.writeTypedList(telephones);
        dest.writeTypedList(socialNetworks);
        dest.writeTypedList(emails);
    }

    public Contact() {
        adress = new Address();
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.adress = in.readParcelable(Address.class.getClassLoader());
        this.telephones = in.createTypedArrayList(Telephone.CREATOR);
        this.socialNetworks = in.createTypedArrayList(SocialNetwork.CREATOR);
        this.emails = in.createTypedArrayList(Email.CREATOR);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
