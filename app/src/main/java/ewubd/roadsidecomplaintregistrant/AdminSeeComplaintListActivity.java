package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminSeeComplaintListActivity extends AppCompatActivity {

    public static int globalCounter = 0;
    private ListView complaintLists;   // for complaint_list


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_list);




        complaintLists = findViewById(R.id.listComplaints);









        TextView btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });







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


        ComplaintTable db = new ComplaintTable(this);
        Cursor rows = db.execute("SELECT * FROM complaint_value_pairs");


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


            items.add(new Complaint(complaint_id, issue, address, problem_faced, urgency, review));
        }

        ComplaintListAdapter cla = new ComplaintListAdapter(this, R.layout.layout_row_complaint, items);
        complaintLists.setAdapter(cla);// sets rows in the layout: complaint_list

        //httpRequest(new String[] {}, new String[]{});






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
        UserActivity.globalCounter++;
    }




}