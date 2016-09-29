package com.spardha.ritesh.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.ContactAdapter;
import com.spardha.ritesh.models.ContactListItem;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 06-Aug-15.
 */
public class ContactActivity extends AppCompatActivity {

    private ArrayList<ContactListItem> contacts;
    private Context context;
    private final int CALL_REQUEST_CODE = 2;
    private Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ListView contactsList = (ListView) findViewById(R.id.lvContactList);
        initContacts();
        context = getBaseContext();
        ContactAdapter adapter = new ContactAdapter(context, contacts);
        contactsList.setAdapter(adapter);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListItem clickedContact = contacts.get(position);
                String number = clickedContact.getPhoneNumber();
                if (number != null) {
                    makeCall(number);
                }
            }
        });
    }


    private void initContacts() {
        contacts = new ArrayList<>();

        contacts.add(new ContactListItem("CORE TEAM"));
        contacts.add(new ContactListItem("Satish Kumar", "satish.kumar.cer13@itbhu.ac.in", "+917754941947", "http://spardha.co.in/2016/en/img/team/core/satish.jpg"));
        contacts.add(new ContactListItem("Neeraj Singh Sarwan", "nsingh.sarwan.min13@itbhu.ac.in", "+918960421891", "http://spardha.co.in/2016/en/img/team/core/neeraj.jpg"));
        contacts.add(new ContactListItem("Sunith Singh", "sunith.singh.cer13@itbhu.ac.in", "+917869800997", "http://spardha.co.in/2016/en/img/team/core/sunith.jpg"));


        contacts.add(new ContactListItem("EVENT MANAGEMENT TEAM"));
        contacts.add(new ContactListItem("Anand Khatri", "anand.skhatri@iitbhu.ac.in", "+918604780975", "http://spardha.co.in/2016/en/img/team/event/anand.jpg"));
        contacts.add(new ContactListItem("Ankit Pal", "ankit.pal.cer14@iitbhu.ac.in", "+918923995636", "http://spardha.co.in/2016/en/img/team/event/ankit.jpg"));
        contacts.add(new ContactListItem("Anshul Srivastava", "anshul.srivastava.che14@iitbhu.ac.in", "+918874665410", "http://spardha.co.in/2016/en/img/team/event/anshul.jpg"));
        contacts.add(new ContactListItem("Divyanshu Gupta", "divyanshu.gupta.che14@iitbhu.ac.in", "+919413590246", "http://spardha.co.in/2016/en/img/team/event/divyanshu.jpg"));
        contacts.add(new ContactListItem("Ritesh Gupta", null, null, "http://spardha.co.in/2016/en/img/team/event/ritesh.jpg"));
        contacts.add(new ContactListItem("Surendra Godara", "surendra.godara.phe14@iitbhu.ac.in", "+917275683405", "http://spardha.co.in/2016/en/img/team/event/surendra.jpg"));
        contacts.add(new ContactListItem("Yash Sharma", "yash.sharma.civ14@iitbhu.ac.in", "+917565816969", "http://spardha.co.in/2016/en/img/team/event/yash.jpg"));
        contacts.add(new ContactListItem("Yugal Agarwal", "yugal.agarwal.civ14@iitbhu.ac.in", "+919456623801", "http://spardha.co.in/2016/en/img/team/event/yugal.jpg"));

        contacts.add(new ContactListItem("PUBLICITY TEAM"));
        contacts.add(new ContactListItem("Mahesh Tejavath", "tmahesh.naik.cer14@itbhu.ac.in", "+918009151299", "http://spardha.co.in/2016/en/img/team/publicity/mahesh.jpg"));
        contacts.add(new ContactListItem("Sahil Patil", "sahil.patil.cer14@iitbhu.ac.in", "+91887498849", "http://spardha.co.in/2016/en/img/team/publicity/sahil.jpg"));
        contacts.add(new ContactListItem("Navya Yadav", "navya.yadav.cer14@itbhu.ac.in", "+919616274102", "http://spardha.co.in/2016/en/img/team/publicity/navya.jpg"));
        contacts.add(new ContactListItem("Ayush Jain", "ayush.jain.cer14@itbhu.ac.in", "+919761947044", "http://spardha.co.in/2016/en/img/team/publicity/ayush.jpg"));

        contacts.add(new ContactListItem("MARKETING TEAM"));
        contacts.add(new ContactListItem("Mayank Prasoon", "marketing.spardha@iitbhu.ac.in", "+919955810010", "http://spardha.co.in/2016/en/img/team/marketing/mayank.jpg"));
        contacts.add(new ContactListItem("Sarthak Vijay", "sarthak.vijay.met13@itbhu.ac.in", "+917080792681", "http://spardha.co.in/2016/en/img/team/marketing/sarthak.jpg"));
        contacts.add(new ContactListItem("Vaibhav Raj", "marketing.spardha@iitbhu.ac.in", "+918874345464", "http://spardha.co.in/2016/en/img/team/marketing/vaibhav.jpg"));

        contacts.add(new ContactListItem("HOSPITALITY TEAM"));
        contacts.add(new ContactListItem("Hrishikesh Krishna", "hrishikesh.krishna.cer14@iitbhu.ac.in", "+917388325585", "http://spardha.co.in/2016/en/img/team/hospitality/hrishikesh.jpg"));
        contacts.add(new ContactListItem("Deepak Dhull", "deepak.dhull.cer14@iitbhu.ac.in", "+918090919718", "http://spardha.co.in/2016/en/img/team/hospitality/deepak.jpg"));
        contacts.add(new ContactListItem("Daniel Aggarwal", "daniel.aggarwal.cer14@iitbhu.ac.in", null, "http://spardha.co.in/2016/en/img/team/hospitality/daneil.jpg"));

        contacts.add(new ContactListItem("TECH TEAM"));
        contacts.add(new ContactListItem("Shubham Jain", "shubham.jain.cer14@itbhu.ac.in", "+918233916701", "http://spardha.co.in/2016/en/img/team/tech/shubham_jain_iit_bhu.jpg"));
        contacts.add(new ContactListItem("Ritesh Kumar", "ritesh.kumar.ece13@iitbhu.ac.in", "+918953839075", R.drawable.face_rit));


        contacts.add(new ContactListItem("CREATIVE TEAM"));
        contacts.add(new ContactListItem("Suraj Panigrahi", null, "+91090919942", "http://spardha.co.in/2016/en/img/team/design/suraj.jpg"));
        contacts.add(new ContactListItem("Shubham Shekhar Jha", null, null, "http://spardha.co.in/2016/en/img/team/design/sekhar.jpg"));

    }

    private void makeCall(String number) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(String.format("tel:%s", number)));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
        } else {
            context.startActivity(callIntent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(callIntent);
                } else {
                    Toast.makeText(this, "Please permit the app to make the call!", Toast.LENGTH_LONG).show();
                }
        }
    }
}
