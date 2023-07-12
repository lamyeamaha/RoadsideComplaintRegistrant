package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubmitComplaintActivity extends AppCompatActivity {

    public static int globalCounter = 0;
    private EditText etAddress, etProblemFaced;
    private RadioGroup radioGroupIssue, radioGroupUrgency;
    private String selectedId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_complaint);





        radioGroupIssue = (RadioGroup)findViewById(R.id.radioGroupIssue);
        etAddress = findViewById(R.id.etAddress);
        etProblemFaced = findViewById(R.id.etProblemFaced);
        radioGroupUrgency = (RadioGroup)findViewById(R.id.radioGroupUrgency);








        TextView btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                EditText name = findViewById(R.id.etName);
//                EditText place = findViewById(R.id.etPlace);
//                EditText dateTime = findViewById(R.id.etDateTime);
//                EditText capacity = findViewById(R.id.etCapacity);
//                EditText budget = findViewById(R.id.etBudget);
//                EditText email = findViewById(R.id.etEmail);
//                EditText phone= findViewById(R.id.etPhone);
//                EditText description = findViewById(R.id.etDescription);



                int selectedRadioIssueId = radioGroupIssue.getCheckedRadioButtonId();
                RadioButton radioIssueButton = (RadioButton)findViewById(selectedRadioIssueId);

                int selectedRadioUrgencyId = radioGroupUrgency.getCheckedRadioButtonId();
                RadioButton radioUrgencyButton = (RadioButton)findViewById(selectedRadioUrgencyId);



                System.out.println("issue: "+radioIssueButton.getText().toString());
                System.out.println("Address: "+etAddress.getText().toString());
                System.out.println("Problem faced: "+etProblemFaced.getText().toString());
                System.out.println("urgency: "+radioUrgencyButton.getText().toString());



                String issue_str = radioIssueButton.getText().toString();
                String address_str = etAddress.getText().toString();
                String problem_faced_str = etProblemFaced.getText().toString();
                String urgency = radioUrgencyButton.getText().toString();
                String review = "Not Reviewed Yet";

                String complaintId = address_str + System.currentTimeMillis();
                String eventValue = complaintId + "-----"+ issue_str + "-----" + address_str + "-----" + problem_faced_str + "-----" + urgency + "-----" + review;


                ComplaintUtil.getInstance().setComplaintKeyValue(SubmitComplaintActivity.this, complaintId, eventValue);   // context can be activity or service. using only this as context will get the nearest
                UnreviewedComplaintUtil.getInstance().setComplaintKeyValue(SubmitComplaintActivity.this, complaintId, eventValue);   // context can be activity or service. using only this as context will get the nearest

                Intent i = new Intent(SubmitComplaintActivity.this, UserActivity.class);
                startActivity(i);
                //List<NameValuePair> params= new ArrayList<>()  ;
                //params.add(new BasicNameValuePair("key", complaintId));
               // params.add(new BasicNameValuePair("value",eventValue));

               // JSONParser.getInstance().makeHttpRequest("http://10.0.2.2:80/CSE489/index.php",params);



                String[] keys = new String[]{"key", "event"};
                String[] values = new String[]{complaintId, eventValue};

                httpRequest(keys, values);





            }
        });


        TextView btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(SubmitComplaintActivity.this, UserActivity.class);
                startActivity(i);

            }
        });

























    }






    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("@SubmitComplaintActivity- onStart");







    }


//    onResume(), onPause(), onStop(), onRestart(), onDestroy()

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("@SubmitComplaintActivity- onResume");



    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("@SubmitComplaintActivity- onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("@SubmitComplaintActivity- onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("@SubmitComplaintActivity- onRestart");



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("@SubmitComplaintActivity- onDestroy");

    }


    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String keys[], final String values[]) {

        new AsyncTask<Void, Void, JSONObject>() {
            @Override
            protected void onPreExecute() {    // run before the background task
                super.onPreExecute();
//                System.out.println("onPreExecute called");
            }
            @Override
            protected JSONObject doInBackground(Void... param) {   //  run after onPreExecute
                try {
                    List<NameValuePair> params = new ArrayList<>();
                    for(int i=0; i<keys.length; i++) {
                        params.add(new BasicNameValuePair(keys[i], values[i]));
                    }
                    JSONObject jObj = JSONParser.getInstance().makeHttpRequest("http://10.0.2.2:80/roadsidecomplaintregistrant/index.php", params);

                    return jObj;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(JSONObject jObj) {
                String msg = "Failed to send request";
                if(jObj != null){
                    try{
                        msg = jObj.getString("msg");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println(msg);




            }
        }.execute();
    }

}