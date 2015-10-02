package turmaformacao.br.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhb_s on 01/10/2015.
 */
public class Email implements Parcelable {

    private Long id;
    private Long id_contact;
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id==null?-1:this.id);
        dest.writeValue(this.id_contact==null?-1:this.id_contact);
        dest.writeString(this.email==null?"":this.email);
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.id_contact = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
