package android.mmtdev.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnSignUpMain = findViewById(R.id.btnSignUpMain);
        Button btnLoginMain = findViewById(R.id.btnLoginMain);
        btnLoginMain.setOnClickListener(this);
        btnSignUpMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoginMain) {
            startActivity(new Intent(Main.this,Login.class));
        } else if (v.getId() == R.id.btnSignUpMain) {
            startActivity(new Intent(Main.this,SignUp.class));
        }
    }
}
