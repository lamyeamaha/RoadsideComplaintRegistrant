package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class UserFeedbackActivity extends AppCompatActivity {

    public static int globalCounter = 0;
    private EditText etR8cmntus;
    private RadioGroup radioGroupFeedback;
    private String selectedId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        System.out.println(UserFeedbackActivity.globalCounter+" @UserFeedbackActivity-oCreate()");




        radioGroupFeedback = (RadioGroup)findViewById(R.id.radioGroupFeedback);
        etR8cmntus = findViewById(R.id.etR8cmntus);



        TextView btnFbSubmit  = findViewById(R.id.btnFbSubmit);
        btnFbSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(UserActivity.globalCounter+ "@UserFeedbackActivity- button Submit was pressed");


                int selectedRadioFeedbackId = radioGroupFeedback.getCheckedRadioButtonId();
                RadioButton radioGroupFeedback = (RadioButton)findViewById(selectedRadioFeedbackId);


                System.out.println("fdbk: "+radioGroupFeedback.getText().toString());
                System.out.println("comment: "+etR8cmntus.getText().toString());

                String fdbk = radioGroupFeedback.getText().toString();
                String comment = etR8cmntus.getText().toString();


                String feedbackId = comment + System.currentTimeMillis();
                String Value = feedbackId + "-----"+ fdbk+ "-----" + comment ;

                UserFeedbackUtil.getInstance().setKeyValue(UserFeedbackActivity.this, feedbackId, Value);   // context can be activity or service. using only this as context will get the nearest

                Intent i = new Intent(UserFeedbackActivity.this, UserActivity.class);
                startActivity(i);

            }
        });






        TextView btnFbHome  = findViewById(R.id.btnFbHome);

        btnFbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(UserActivity.globalCounter+ "@UserFeedbackActivity- button Home was pressed");

                Intent i = new Intent(UserFeedbackActivity.this, UserActivity.class);
                startActivity(i);

            }
        });








    }

    @Override
    protected void onStart() {
        super.onStart();


    }


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