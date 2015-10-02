package turmaformacao.br.agenda.controller.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.controller.adatper.ContactListAdapter;
import turmaformacao.br.agenda.controller.asynctasks.AsyncFindAllDB;
import turmaformacao.br.agenda.controller.asynctasks.AsyncInterfaceFindAll;
import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.services.ContactBusinessService;


public class ContactListActivity extends AppCompatActivity implements AsyncInterfaceFindAll {

    ListView listViewContacts;
    EditText editTextFilter;
    ContactListAdapter adapter;
    Contact selectedContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        bindListViewContacts();
        bindEditTextFilter();
    }





    private void bindEditTextFilter() {
        editTextFilter = (EditText) findViewById(R.id.editTextFilter);

        editTextFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void bindListViewContacts() {
        listViewContacts = (ListView) findViewById(R.id.listView_contacts);
        registerForContextMenu(listViewContacts);
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_item_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        goToContactForm.putExtra("Contact",selectedContact);
        startActivity(goToContactForm);

    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(getString(R.string.lbl_confirm))
                .setMessage(getString(R.string.msg_delete_confirm))
                .setPositiveButton(getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactBusinessService.delete(ContactListActivity.this,selectedContact.getId());
                        selectedContact = null;
                        Toast.makeText(ContactListActivity.this, getString(R.string.msg_deleted_sucessfully), Toast.LENGTH_SHORT).show();
                        ContactBusinessService.findAll(ContactListActivity.this,ContactListActivity.this);
                    }
                })
                .setNeutralButton(getString(R.string.lbl_no),null)
                .create()
                .show();
    }


    @Override
    protected void onResume() {
        AsyncFindAllDB async = new AsyncFindAllDB(this,this);
        async.execute();
        super.onResume();
    }

    private void refresh(List<Contact> contacts) {

        adapter = new ContactListAdapter(this,contacts);
        listViewContacts.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add_contact) {
            onMenuAddContactClick();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddContactClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this,ContactFormActivity.class);
        startActivity(goToContactForm);
    }

    @Override
    public void listReceivedFromDatabase(List<Contact> contacts) {
        refresh(contacts);
    }
}
