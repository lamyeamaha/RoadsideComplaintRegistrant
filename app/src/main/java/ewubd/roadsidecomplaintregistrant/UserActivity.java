package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    public static int globalCounter = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_page);



        TextView btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint);

        btnSubmitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(UserActivity.this, SubmitComplaintActivity.class);
                startActivity(i);
            }
        });

        TextView btnFeedback = findViewById(R.id.btnFeedback);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(UserActivity.this, UserFeedbackActivity.class);
                startActivity(i);
            }
        });


        TextView btnCheckArea = findViewById(R.id.btnCheckArea);

        btnCheckArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(UserActivity.this, CheckAreaActivity.class);
                startActivity(i);
            }
        });


        TextView btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(UserActivity.this, MainHomeActivity.class);
                startActivity(i);

            }
        });



    }






    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("@UserActivity- onStart");





    }


//    onResume(), onPause(), onStop(), onRestart(), onDestroy()

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("@UserActivity- onResume");




    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("@UserActivity- onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("@UserActivity- onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("@UserActivity- onRestart");



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(" @UserActivity-onDestroy()");

    }





}