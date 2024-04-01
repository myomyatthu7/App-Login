package android.mmtdev.applogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddData extends AppCompatActivity implements View.OnClickListener {
    EditText etAddId,etAddName,etAddEmail,etAddAddress;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);
        etAddId = findViewById(R.id.etAddId);
        etAddName = findViewById(R.id.etAddName);
        etAddEmail = findViewById(R.id.etAddEmail);
        etAddAddress = findViewById(R.id.etAddAddress);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String stone = "Stone";
        String time = String.valueOf(System.currentTimeMillis());
        int id = Integer.parseInt(etAddId.getText().toString());
        String name = etAddName.getText().toString();
        String email = etAddEmail.getText().toString();
        String address = etAddAddress.getText().toString();
        //DatabaseReference dbReference= FirebaseDatabase.getInstance().getReference("Students");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("students");
        myRef.setValue("Hello, World!");

        Log.d(stone,String.valueOf(id));
        Log.d(stone,name);
        Log.d(stone,email);
        Log.d(stone,address);
        StudentData stuData = new StudentData(id,name,email,address);
        //myRef.child(name).setValue(stuData);
    }
}
