package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    public static int globalCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home_page);




        TextView btnSeeComplaint = findViewById(R.id.btnSeeComplaint);

        btnSeeComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(AdminActivity.this, AdminSeeComplaintListActivity.class);
                startActivity(i);
            }
        });



        TextView btnUpdateComplaintStatus = findViewById(R.id.btnUpdateComplaintStatus);

        btnUpdateComplaintStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(AdminActivity.this, AdminUpdateComplaintStatusActivity.class);
                startActivity(i);
            }
        });

        TextView btnCheckFeedbacks = findViewById(R.id.btnCheckFeedbacks);

        btnCheckFeedbacks.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {



                Intent i = new Intent(AdminActivity.this, AdminSeeFeedbacklist.class);
                startActivity(i);
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






    @Override
    protected void onStart() {
        super.onStart();






    }


//    onResume(), onPause(), onStop(), onRestart(), onDestroy()

    @Override
    protected void onResume() {
        super.onResume();




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