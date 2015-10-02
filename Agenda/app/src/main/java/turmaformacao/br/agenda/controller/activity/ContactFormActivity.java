package turmaformacao.br.agenda.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.controller.asynctasks.AsyncInterfaceAddress;
import turmaformacao.br.agenda.model.entities.Address;
import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.entities.Email;
import turmaformacao.br.agenda.model.entities.SocialNetwork;
import turmaformacao.br.agenda.model.entities.Telephone;
import turmaformacao.br.agenda.model.persistence.ContactContract;
import turmaformacao.br.agenda.model.persistence.EmailRepository;
import turmaformacao.br.agenda.model.persistence.SocialNetworksRepository;
import turmaformacao.br.agenda.model.persistence.TelephoneRepository;
import turmaformacao.br.agenda.model.services.ContactBusinessService;
import turmaformacao.br.agenda.model.services.EmailBusinessService;
import turmaformacao.br.agenda.model.services.SocialNetworkBusinessService;
import turmaformacao.br.agenda.model.services.TelephoneBusinessService;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactFormActivity extends AppCompatActivity implements AsyncInterfaceAddress {

    EditText editTextName;
    EditText editTextZipCode;
    EditText editTextType;
    EditText editTextStreet;
    EditText editTextNeighborhood;
    EditText editTextCity;
    EditText editTextState;
    EditText editTextTelephone;
    EditText editTextEmail;
    EditText editTextSocialNetwork;
    ListView listViewTelephone;
    ListView listViewEmail;
    ListView listViewSocialNetwork;
    Button btnAddTelephone;
    Button btnAddEmail;
    Button btnAddSocialNetwork;
    Button btnFillAddress;

    ArrayAdapter<String> adapterListViewEmail;
    ArrayAdapter<String> adapterListViewSocialNetwork;
    ArrayAdapter<String> adapterListViewTelephone;

    //ARRAYS DE CLASSES PARA SALVAR NO CONTACT
    ArrayList<Telephone> telephones = new ArrayList<>();
    ArrayList<Email> emails = new ArrayList<>();
    ArrayList<SocialNetwork> socialNetworks = new ArrayList<>();

    //ARRAYS DE STRING PARA MOSTRAR NA TELA
    ArrayList<String>telephoneList = new ArrayList<>();
    ArrayList<String> emailList = new ArrayList<>();
    ArrayList<String> socialNetworkList = new ArrayList<>();



    Contact contact;

    public static final String PARAM_CONTACT = "Contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact);

        bindListViewEmail();
        bindListViewSocialNetwork();
        bindListViewTelephone();

        initContact();

        bindEditTextName();
        bindEditTextZipCode();
        bindEditTextType();
        bindEditTextStreet();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();
        bindBtnFillAddress();
        bindEditTextSocialNetWork();
        bindEditTextEmail();
        bindEditTextTelephone();

        bindBtnAddTelephone();
        bindBtnAddEmail();
        bindBtnAddSocialNetwork();


    }

    private void bindListViewTelephone() {
        listViewTelephone = (ListView) findViewById(R.id.listViewTelephones);
        listViewTelephone.setAdapter(adapterListViewTelephone);
        listViewTelephone.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindListViewSocialNetwork() {
        listViewSocialNetwork = (ListView) findViewById(R.id.listViewSocialNetworks);
        listViewSocialNetwork.setAdapter(adapterListViewSocialNetwork);
        listViewSocialNetwork.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindListViewEmail() {
        listViewEmail = (ListView) findViewById(R.id.listViewEmails);
        listViewEmail.setAdapter(adapterListViewEmail);

        listViewEmail.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindBtnAddSocialNetwork() {
        btnAddSocialNetwork = (Button) findViewById(R.id.btnAddSocialNetwork);

        btnAddSocialNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialNetwork socialNetwork = new SocialNetwork();
                if (!editTextSocialNetwork.getText().toString().equals("")) {
                    socialNetwork.setSocialNetwork(editTextSocialNetwork.getText().toString());
                    socialNetworkList.add(socialNetwork.getSocialNetwork());
                    socialNetworks.add(socialNetwork);
                }

                if (adapterListViewSocialNetwork == null) {
                    setAdapters();  
                    editTextSocialNetwork.setText("");
                } else {
                    listViewSocialNetwork.setAdapter(adapterListViewSocialNetwork);
                    editTextSocialNetwork.setText("");
                }
            }
        });

    }


    private void bindBtnAddEmail() {
        btnAddEmail = (Button) findViewById(R.id.btnAddEmail);

        btnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email email = new Email();
                if (!editTextEmail.getText().toString().equals("")) {
                    email.setEmail(editTextEmail.getText().toString());
                    emailList.add(email.getEmail());
                    emails.add(email);
                }

               if(adapterListViewEmail==null) {
                   setAdapters();
                   editTextEmail.setText("");
               }else {
                   listViewEmail.setAdapter(adapterListViewEmail);
                   editTextEmail.setText("");
               }
            }
        });

    }

    private void bindBtnAddTelephone() {
        btnAddTelephone = (Button) findViewById(R.id.btnAddTelephone);

        btnAddTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telephone telephone = new Telephone();
                if (!editTextTelephone.getText().toString().equals("")) {
                    telephone.setTelephone(editTextTelephone.getText().toString());
                    telephoneList.add(telephone.getTelephone());
                    telephones.add(telephone);
                }

                if(adapterListViewTelephone==null) {
                    setAdapters();
                    editTextTelephone.setText("");
                }
                else{
                    listViewTelephone.setAdapter(adapterListViewTelephone);
                    editTextTelephone.setText("");
                }
            }
        });

    }

    private void bindEditTextTelephone() {
        editTextTelephone = (EditText) findViewById(R.id.editTextTelephone);
    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    private void bindEditTextSocialNetWork() {
        editTextSocialNetwork = (EditText) findViewById(R.id.editTextSocialNetwork);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_form, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_create_contact){
            onMenuCreateContactClick();
        }
        if(id == R.id.menu_cancel){
            onMenuCancelClick();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuCancelClick() {
        finish();
    }

    private void onMenuCreateContactClick() {
        bindContact();
        ContactBusinessService.save(contact, this);

        bindListsWithContactId();

        //FORA DO PADRÃO- FALTA ASYNC
        for(Email e : emails)
        EmailBusinessService.save(e);

        for(Telephone t : telephones)
            TelephoneBusinessService.save(t);


        for(SocialNetwork sn : socialNetworks)
            SocialNetworkBusinessService.save(sn);
        finish();

    }

    //FORA DO PADRÃO
    private void bindListsWithContactId() {
        Long id = ContactBusinessService.findIdFromLastPosition();

        for(Telephone t : telephones){
            t.setId_contact(id);
        }
        for(Email e : emails){
            e.setId_contact(id);
        }
        for(SocialNetwork sn : socialNetworks){
            sn.setId_contact(id);
        }
    }


    private void bindContact() {
        contact.setName(editTextName.getText().toString());

        Address address = new Address();
        address.setZipCode(editTextZipCode.getText().toString());
        address.setType(editTextType.getText().toString());
        address.setStreet(editTextStreet.getText().toString());
        address.setNeighborhood(editTextNeighborhood.getText().toString());
        address.setCity(editTextCity.getText().toString());
        address.setState(editTextState.getText().toString());
        contact.setAdress(address);
    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = extras.getParcelable(PARAM_CONTACT);
        }
        this.contact = this.contact == null ? new Contact() : this.contact;

        if(contact.getId()!=null){
            List<Email> emailsTemporariosDoBanco = EmailBusinessService.findAll();
            List<SocialNetwork> socialNetworksTemporarios = SocialNetworkBusinessService.findAll();
            List<Telephone> telephonesTemporarios = TelephoneBusinessService.findAll();


            for(Email e : emailsTemporariosDoBanco){
                if(e.getId_contact()==contact.getId()){
                    emails.add(e);
                    emailList.add(e.getEmail().toString());
                }

            }

            for(Telephone t : telephonesTemporarios) {
                if(t.getId_contact()==contact.getId()){
                    telephones.add(t);
                    telephoneList.add(t.getTelephone());
                }

            }

            for(SocialNetwork sn : socialNetworksTemporarios){
                if(sn.getId_contact()==contact.getId()){
                    socialNetworks.add(sn);
                    socialNetworkList.add(sn.getSocialNetwork());
                }

            }

            setAdapters();
        }


    }

    private void setAdapters() {
        adapterListViewTelephone = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, telephoneList);
        listViewTelephone.setAdapter(adapterListViewTelephone);
        adapterListViewEmail = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, emailList);
        listViewEmail.setAdapter(adapterListViewEmail);
        adapterListViewSocialNetwork = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, socialNetworkList);
        listViewSocialNetwork.setAdapter(adapterListViewSocialNetwork);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextFormName);
        editTextName.setText(contact.getName()==null?"" : contact.getName());
    }

    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextZipCode.setText(contact.getAdress().getZipCode()==null?"" : contact.getAdress().getZipCode());
    }

    private void bindEditTextType() {
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextType.setText(contact.getAdress().getType()==null?"" : contact.getAdress().getType());
    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextStreet.setText(contact.getAdress().getStreet()==null?"" : contact.getAdress().getStreet());
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextNeighborhood.setText(contact.getAdress().getNeighborhood()==null?"" : contact.getAdress().getNeighborhood());
    }

    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextCity.setText(contact.getAdress().getCity()==null?"" : contact.getAdress().getCity());
    }

    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.editTextState);
        editTextState.setText(contact.getAdress().getState() == null ? "" : contact.getAdress().getState());
    }

    private void bindBtnFillAddress() {
        btnFillAddress = (Button) findViewById(R.id.btnIdFillAdress);

        btnFillAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextZipCode.getText().toString().equals(""))
                ContactBusinessService.getAddressByZipCode(ContactFormActivity.this,ContactFormActivity.this,editTextZipCode.getText().toString());

            }
        });
    }


    @Override
    public void fillAddress(Address address) {
        editTextState.setText(address.getState());
        editTextCity.setText(address.getCity());
        editTextNeighborhood.setText(address.getNeighborhood());
        editTextStreet.setText(address.getStreet());
        editTextType.setText(address.getType());
    }
}
