package ewubd.roadsidecomplaintregistrant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etUserId, etPass1, etPass2;
    private TextView txtConfirmPass;
    private CheckBox chkRememberUserId, chkRememberLogin;
    private boolean isUserExist = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    private String existingUserId = "";
    private boolean wasOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        isUserExist = sharedPreferences.contains("RM_LOGIN");

        System.out.println("isUserExist value is: "+isUserExist);

        boolean isUserIdRemembered = false;
        if (isUserExist) {
            if (sharedPreferences.getBoolean("RM_LOGIN", false)) {
                Intent i = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(i);
                return;
            }
            isUserIdRemembered = sharedPreferences.getBoolean("RM_USER_ID", false);
            if (isUserIdRemembered) {
                existingUserId = sharedPreferences.getString("USER_ID", "");
                System.out.println("existingUserId is: "+existingUserId);
            }
        }

        setContentView(R.layout.activity_admin_login);
        etUserId = findViewById(R.id.etAdminId);
        etUserId.setText(existingUserId);
        etPass1 = findViewById(R.id.etPassword1);
        etPass2 = findViewById(R.id.etPassword2);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);

        if(isUserExist){
            etPass2.setVisibility(View.GONE);
            txtConfirmPass.setVisibility(View.GONE);
        }

        chkRememberUserId = findViewById(R.id.chkRememberUser);
        chkRememberLogin = findViewById(R.id.chkRememberLogin);
        chkRememberUserId.setChecked(isUserIdRemembered);

        findViewById(R.id.btnExit).setOnClickListener(r -> finish());


        findViewById(R.id.btnGo).setOnClickListener(r -> {
            String userId = etUserId.getText().toString();
            String pass1 = etPass1.getText().toString();
            String pass2 = etPass2.getText().toString();
            boolean isUserRemembered = chkRememberUserId.isChecked();
            boolean isLoginRemembered = chkRememberLogin.isChecked();

            StringBuilder str = new StringBuilder();
            if (TextUtils.isEmpty(userId) || userId.length() < 4 || userId.length() > 8) {
                str.append("Invalid user id, ");
            }

            if (TextUtils.isEmpty(pass1) || pass1.length() < 4 || pass1.length() > 6) {
                str.append("Invalid password, ");
            }

            if (!isUserExist && (TextUtils.isEmpty(pass2) || pass2.length() < 4 || pass2.length() > 6)) {
                str.append("Invalid password re-entered, ");
            }

            if (isUserExist) {

                if(existingUserId.equals("")){
                    if(!userId.equals(sharedPreferences.getString("USER_ID",""))) {
                        str.append("UserId didn't match, ");
                    }
                }
                if (!existingUserId.equals("") && !userId.equals(existingUserId)) {
                    System.out.println("existingUserId is2: "+existingUserId);
                    str.append("UserId didn't match2, ");
                }
                String existingPassword = sharedPreferences.getString("PASSWORD", "");
                if (!pass1.equals(existingPassword)) {
                    str.append("Password didn't match, ");
                }
                if (str.toString().length() != 0) {
                    ((TextView) findViewById(R.id.tvErrorMsg)).setText(str.toString());
                    return;
                }

                // To store newly RM_USER_ID and newly RM_LOGIN if any changes made
                prefsEditor = sharedPreferences.edit();
                prefsEditor.putBoolean("RM_USER_ID", isUserRemembered);
                prefsEditor.putBoolean("RM_LOGIN", isLoginRemembered);
                prefsEditor.apply();   // to commit


                Intent i = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(i);

            } else {
                if (!pass1.equals(pass2)) {
                    str.append("Passwords didn't match, ");
                }

                if (str.toString().length() != 0) {
                    ((TextView) findViewById(R.id.tvErrorMsg)).setText(str.toString());
                    return;
                }
                prefsEditor = sharedPreferences.edit();
                prefsEditor.putString("USER_ID", userId);
                prefsEditor.putString("PASSWORD", pass1);
                prefsEditor.putBoolean("RM_USER_ID", isUserRemembered);
                prefsEditor.putBoolean("RM_LOGIN", isLoginRemembered);
                prefsEditor.apply();   // to commit
                Intent i = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(i);
            }

        });
    }

    @Override
    public void onPause(){
        super.onPause();
        wasOpened = true;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if(wasOpened){
            finish();
        }
    }


}
