package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminSeeFeedbacklist extends AppCompatActivity {

    public static int globalCounter = 0;
    private ListView feedbackLists;   // for feedback_list


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_list);




        feedbackLists = findViewById(R.id.listFeedbacks);






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





        ArrayList<Feedback> items = new ArrayList<>();

        UserFeedbackTable db = new UserFeedbackTable(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");

        while(rows.moveToNext()){
            String Feedback = rows.getString(0);     // returns the first column out of the 2 columns
            String value = rows.getString(1);

            String[] subStrings = value.split("-----");

            String Feedback_id = subStrings[0];
            String fdbk = subStrings[1];
            String comment = subStrings[2];



            items.add(new Feedback(Feedback_id,fdbk,comment));
        }

        FeedbackListAdapter k = new FeedbackListAdapter(this, R.layout.layout_row_feedback, items);
        feedbackLists.setAdapter(k);    // sets rows in the layout: feedback_list




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