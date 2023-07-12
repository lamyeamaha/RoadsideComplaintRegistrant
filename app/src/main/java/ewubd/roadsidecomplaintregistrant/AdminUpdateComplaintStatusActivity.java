package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminUpdateComplaintStatusActivity extends AppCompatActivity {

    public static int globalCounter = 0;
    private ListView complaintLists;   // for complaint_list
    private ArrayList<Complaint> items = new ArrayList<>();
    private ComplaintListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_list);




        complaintLists = findViewById(R.id.listComplaints);



        complaintLists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Complaint selectedComplaint = items.get(position);

                showDialog("Is this complaint reviewed? "+ selectedComplaint.issue, "Yes", selectedComplaint);


                return true;
            }
        });




        TextView btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });








    }

    private void showDialog(String message, String issue, Complaint complaint){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(issue);
        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("the address is: "+ complaint.address);
                        System.out.println("the complaint_id is: "+ complaint.complaint_id);

                        String value = ComplaintUtil.getInstance().getValueByComplaintKey(AdminUpdateComplaintStatusActivity.this, complaint.complaint_id);
                        String[] subStrings = value.split("-----");

                        String complaint_id = subStrings[0];
                        String issue = subStrings[1];
                        String address = subStrings[2];
                        String problem_faced = subStrings[3];
                        String urgency = subStrings[4];
                        String new_review = "Already Reviewed";

                        String new_complaint_value = complaint_id + "-----"+ issue + "-----" + address + "-----" + problem_faced + "-----" + urgency + "-----" + new_review;
                        ComplaintUtil.getInstance().setComplaintKeyValue(AdminUpdateComplaintStatusActivity.this, complaint_id, new_complaint_value);


                        UnreviewedComplaintUtil.getInstance().deleteByComplaintKey(AdminUpdateComplaintStatusActivity.this, complaint.complaint_id);
                        dialog.cancel();
                        //initializeCustomEventList();
                        items.remove(complaint);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }






    @Override
    protected void onStart() {
        super.onStart();






    }


//    onResume(), onPause(), onStop(), onRestart(), onDestroy()

    @Override
    protected void onResume() {
        super.onResume();





        items.clear();


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


            items.add(new Complaint(complaint_id, issue, address, problem_faced, urgency, review));

        }

        adapter = new ComplaintListAdapter(this, R.layout.layout_row_complaint, items);

//        ComplaintListAdapter cla = new ComplaintListAdapter(this, R.layout.layout_row_complaint, items);
        complaintLists.setAdapter(adapter);    // sets rows in the layout: complaint_list






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