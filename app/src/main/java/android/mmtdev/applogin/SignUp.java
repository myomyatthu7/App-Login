package android.mmtdev.applogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etEmail;
    TextInputLayout tiPw, tiConfirmPw;
    TextInputEditText etPw, etConfirmPw;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;
    RadioGroup rGp;
    RadioButton rbMale, rbFemale;
    String gender = "";
    Animation shakeAnimation;
    FirebaseApp firebaseApp;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPw = findViewById(R.id.etPw);
        etConfirmPw = findViewById(R.id.etConfirmPw);
        btnSignUp = findViewById(R.id.btnSignUp);
        rGp = findViewById(R.id.rGp);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        tiPw = findViewById(R.id.tiPw);
        tiConfirmPw = findViewById(R.id.tiConfirmPw);
        btnSignUp.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        // Define the duration and offset for the vibration effect
        long duration = 100;
        int offset = 10;

        FirebaseApp.initializeApp(SignUp.this);

        // Define the animation for vibrating the view horizontally
        shakeAnimation = new TranslateAnimation(0, offset, 0, 0);
        shakeAnimation.setDuration(duration);
        shakeAnimation.setRepeatCount(3); // Number of times to repeat the animation
        shakeAnimation.setInterpolator(new CycleInterpolator(1)); // Create a cyclic interpolation
        rGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                check = checkedId;
            }
        });
    }

    @Override
    public void onClick(View v) {
        String email = etEmail.getText().toString();
        String pw = etPw.getEditableText().toString();
        String confirmPw = etConfirmPw.getEditableText().toString();
        String name = etName.getText().toString();
        if (name.isEmpty()) {
            etName.setError("enter your name");
            etName.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError("enter your email");
            etEmail.requestFocus();
        } else if (TextUtils.isEmpty(pw) || pw.length() < 6) {
            tiPw.setError("password must be at least 6 characters in length.");
            etPw.requestFocus();
        } else {
            tiPw.setError(null);

            if (!pw.contentEquals(confirmPw)) {
                tiConfirmPw.setError("Passwords don't match, try again.");
                etConfirmPw.requestFocus();
                } else if (pw.contentEquals(confirmPw)) {
                tiConfirmPw.setError(null);

                if (!(check == R.id.rbMale || check== R.id.rbFemale)) {
                    rGp.startAnimation(shakeAnimation);
                    } else {
                        firebaseAuth.createUserWithEmailAndPassword(email,pw)
                                .addOnCompleteListener(task -> {
                                    Toast.makeText(SignUp.this,task.toString(),Toast.LENGTH_SHORT).show();
                                    if (task.isSuccessful()) {
                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        if (firebaseUser != null) {
                                            firebaseUser.sendEmailVerification()
                                                    .addOnSuccessListener(unused -> Toast.makeText(SignUp.this,"Register successful",Toast.LENGTH_SHORT).show())
                                                    .addOnFailureListener(e -> Toast.makeText(SignUp.this, "Register Failed", Toast.LENGTH_SHORT).show());
                                        }
                                    } else {
                                        Toast.makeText(SignUp.this, "Already Exit Email", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }
        }




//        if (name.isEmpty()) {
//            etName.setError("enter your name");
//            etName.requestFocus();
//        } else if (TextUtils.isEmpty(email)) {
//            etEmail.setError("enter your email");
//            etEmail.requestFocus();
//        } else if (TextUtils.isEmpty(pw)) {
//            tiPw.setError("enter your passwords");
//            etPw.requestFocus();
//        } else if (!TextUtils.isEmpty(pw)) {
//            tiPw.setError(null);
//
//            if (TextUtils.isEmpty(confirmPw)) {
//                tiConfirmPw.setError("enter your confirm passwords");
//                etConfirmPw.requestFocus();
//            } else if (!TextUtils.isEmpty(confirmPw)) {
//                tiConfirmPw.setError(null);
//
//                 if (!pw.contentEquals(confirmPw)) {
//                    tiConfirmPw.setError("Password isn't same");
//                    etConfirmPw.requestFocus();
//                } else if (pw.contentEquals(confirmPw)) {
//                     tiConfirmPw.setError(null);
//                     if (gender.contentEquals("")) {
//                         // Apply the animation to the view
//                         rGp.startAnimation(shakeAnimation);
//                     } else {
//                         firebaseAuth.createUserWithEmailAndPassword(email,pw)
//                                 .addOnCompleteListener(task -> {
//                                     if (!task.isSuccessful()) {
//                                         Toast.makeText(this, "Already Exit Email", Toast.LENGTH_SHORT).show();
//                                     } else {
//                                         FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                                         if (firebaseUser != null) {
//                                             firebaseUser.sendEmailVerification()
//                                                     .addOnSuccessListener(unused -> Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show())
//                                                     .addOnFailureListener(e -> Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show());
//                                         }
//                                     }
//                                 });
//                     }
//                 }
//            }
//        }
//    }
    }
}