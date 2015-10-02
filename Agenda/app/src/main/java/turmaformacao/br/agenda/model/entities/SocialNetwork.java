package turmaformacao.br.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhb_s on 01/10/2015.
 */
public class SocialNetwork implements Parcelable {

    private Long id;
    private Long id_contact;
    private String socialNetwork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(Long id_contact) {
        this.id_contact = id_contact;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id==null?-1:this.id);
        dest.writeValue(this.id_contact==null?-1:this.id_contact);
        dest.writeString(this.socialNetwork==null?"":this.socialNetwork);
    }

    public SocialNetwork() {
    }

    protected SocialNetwork(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.id_contact = (Long) in.readValue(Long.class.getClassLoader());
        this.socialNetwork = in.readString();
    }

    public static final Parcelable.Creator<SocialNetwork> CREATOR = new Parcelable.Creator<SocialNetwork>() {
        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };
}
