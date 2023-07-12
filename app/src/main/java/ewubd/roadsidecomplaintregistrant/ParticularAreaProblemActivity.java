package ewubd.roadsidecomplaintregistrant;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ParticularAreaProblemActivity extends AppCompatActivity {

    private EditText etIssue, etProblemDescription, etUrgency, etReviewed;
    private String selected_address;
    //    private RadioGroup radioEventGroup;
    private String selectedId = "";

    private ListView areaLists;   // for complaint_list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_list);

        areaLists = findViewById(R.id.listAreas);







        TextView btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });




        Intent i = getIntent();


        if(i.hasExtra("address")) {


            selected_address = i.getStringExtra("address");




        }








    }



























    @Override
    protected void onStart() {
        super.onStart();




    }


//    onResume(), onPause(), onStop(), onRestart(), onDestroy()

    @Override
    protected void onResume() {
        super.onResume();



        ArrayList<Complaint> items = new ArrayList<>();


        UnreviewedComplaintTable db = new UnreviewedComplaintTable(this);
        Cursor rows = db.execute("SELECT * FROM unreviewed_complaint_value_pairs");


        while(rows.moveToNext()){
            String complaint = rows.getString(0);     // returns the first column out of the 2 columns
            String value = rows.getString(1);

            String[] subStrings = value.split("-----");

            String complaint_id = subStrings[0];
            String issue = subStrings[1];
            String address = subStrings[2];
            String problem_faced = subStrings[3];
            String urgency = subStrings[4];
            String review = subStrings[5];

            if (address.equals(selected_address)){
                items.add(new Complaint(complaint_id, issue, address, problem_faced, urgency, review));
            }



        }

        AreaDescriptionListAdapter ala = new AreaDescriptionListAdapter(this, R.layout.layout_row_area_description, items);
        areaLists.setAdapter(ala);    // sets rows in the layout: listAreas




    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }





}