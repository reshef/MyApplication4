package com.example.owner.myapplication;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Massage> massages = new ArrayList<Massage>();
    ListView listmassges;
    MassageAdapter massageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listmassges = (ListView)findViewById(R.id.listMassges);

        massageAdapter = new MassageAdapter(this, massages);

        listmassges.setLongClickable(true);
        listmassges.setAdapter(massageAdapter);


        listmassges.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View view, final int index, long arg3) {
                showDeleteMassagesPopUp(view, index);
                return false;
            }

        });


        FloatingActionButton f_a_b = (FloatingActionButton) findViewById(R.id.fab);
        f_a_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewMsageasPopUp(view);
            }
        });
    }




    public void showNewMsageasPopUp(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new Mssages and username");
        final View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_new_massage, (ViewGroup) findViewById(android.R.id.content), false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        final EditText input2 = (EditText) viewInflated.findViewById(R.id.username);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String currentMessage = input.getText().toString();
                String currentMessage2 = input2.getText().toString();
                String inputText = currentMessage2+ " : massage" + " " + currentMessage  ;


                if (!inputText.trim().equals("")) {
                    String time =  DateFormat.getDateInstance().format(new Date());

                    massageAdapter.add(new Massage(inputText,time)

                    );

                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void showDeleteMassagesPopUp(View view, int index) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        String time =  DateFormat.getDateInstance().format(new Date());
        String time = massages.get(index).getDate();

        builder.setTitle("Delete this massage? at"+" "+time);

        final int indexToDelete = index;

        // del
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                massages.remove(indexToDelete);
                massageAdapter.notifyDataSetChanged();

            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}