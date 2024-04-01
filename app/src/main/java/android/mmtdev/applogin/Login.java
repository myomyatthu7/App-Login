package android.mmtdev.applogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.prefs.Preferences;

public class Login extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    EditText etEmailLogin;
    TextInputLayout tiPwLogin;
    Button btnLogin;
    TextView tvForgotPw;
    CheckBox cbRemember;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    String sharePreKey = "Remember";
    boolean remember;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        tiPwLogin = findViewById(R.id.tiPwLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPw = findViewById(R.id.tvForgotPw);
        cbRemember = findViewById(R.id.cbRemember);
        btnLogin.setOnClickListener(this);
        tvForgotPw.setOnClickListener(this);
        cbRemember.setOnCheckedChangeListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(sharePreKey,MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean(sharePreKey,false);
      //  Toast.makeText(this, String.valueOf("Result : "+result), Toast.LENGTH_SHORT).show();
        if (result) {
            startActivity(new Intent(Login.this, MyApi.class));
        }
       // Toast.makeText(this, "Checked : "+remember, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            String email = etEmailLogin.getText().toString();
            String pw = Objects.requireNonNull(tiPwLogin.getEditText()).getText().toString();
            //Toast.makeText(this, email +" : "+ pw, Toast.LENGTH_SHORT).show();
            firebaseAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (remember) {
                            sharedPreferences = getSharedPreferences(sharePreKey,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(sharePreKey,true);
                            editor.apply();
                        }
                        //Toast.makeText(Login.this, "Remember : "+remember, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MyApi.class));
                    } else {
                        Toast.makeText(Login.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (v.getId() == R.id.tvForgotPw) {
            //Toast.makeText(this, "Forgot", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this,ForgotPw.class));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //Toast.makeText(this, "Checked : "+isChecked, Toast.LENGTH_SHORT).show();
        remember = isChecked;
    }
}
