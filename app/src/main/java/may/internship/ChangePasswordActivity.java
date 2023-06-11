package may.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText oldPwd, newPwd, confirmPwd;
    Button changePassword;

    SQLiteDatabase db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPwd = findViewById(R.id.old_password);
        newPwd = findViewById(R.id.new_password);
        confirmPwd = findViewById(R.id.confirm_password);
        changePassword=findViewById(R.id.change_password);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldPwd.getText().toString().trim().equals("")){
                    oldPwd.setError("Old Password required");
                }
                else if(oldPwd.getText().toString().trim().length()<6){
                    oldPwd.setError("Min 6 character password required");
                }
                else if(newPwd.getText().toString().trim().equals("")){
                    newPwd.setError("New Password required.");
                }
                else if(newPwd.getText().toString().trim().length()<6){
                    newPwd.setError("Min 6 character password required");
                }
                else if(newPwd.getText().toString().matches(oldPwd.getText().toString())){
                    newPwd.setError("Same as old Password not allowed");
                }
                else if(confirmPwd.getText().toString().trim().equals("")){
                    confirmPwd.setError("Confirm Password required.");
                }
                else if(confirmPwd.getText().toString().trim().length()<6){
                    confirmPwd.setError("Min 6 character password required");
                }
                else if(!confirmPwd.getText().toString().matches(newPwd.getText().toString())){
                    newPwd.setError("Confirm password does not match.");
                }
                else {
                    String selectQuery = "SELECT * FROM RECORD WHERE CONTACT = '" + sp.getString(ConstantData.CONTACT, "") + "' AND PASSWORD='" + oldPwd.getText().toString() + "' ";
                    Cursor cursor = db.rawQuery(selectQuery, null);
                    if (cursor.getCount() > 0) {
                        String updateQuery = "UPDATE RECORD SET PASSWORD='" + newPwd.getText().toString() + "' WHERE CONTACT='" + sp.getString(ConstantData.CONTACT, "") + "'";
                        db.execSQL(updateQuery);
                        new CommonMethod(ChangePasswordActivity.this, "Password Change Successfully");
                        onBackPressed();
                    }
                    else{
                        new CommonMethod(ChangePasswordActivity.this,"Old Pwd Does Not Match");
                    }
                }

            }
        });
    }
}