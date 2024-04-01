package android.mmtdev.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPw extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pw);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        EditText etEmailForgot = findViewById(R.id.etEmailForgot);
        Button btnEmailForgot = findViewById(R.id.btnEmailForgot);
        btnEmailForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isNetworkConnected(ForgotPw.this)) {

                    String EmailForgot = etEmailForgot.getText().toString();
//                firebaseAuth.sendPasswordResetEmail(EmailForgot).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(ForgotPw.this,"Success",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(ForgotPw.this,Login.class));
//                        } else {
//                            Toast.makeText(ForgotPw.this,"Fail",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                    firebaseAuth.sendPasswordResetEmail(EmailForgot).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ForgotPw.this,"Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPw.this,Login.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ForgotPw.this,"Fail",Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ForgotPw.this,"No Internet Access",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
