package com.nicholas.session7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText txtUsr, txtPwd;
    Button btnInsert, btnUpdate, btnDelete, btnGetAll;
    String user, pass;

    private void init() {
        dbHelper = new DBHelper(this);

        txtUsr = findViewById(R.id.etxUsername);
        txtPwd = findViewById(R.id.etxPassword);

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnGetAll = findViewById(R.id.btnRead);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        insert();
        update();
        delete();
        getall();
    }

    private void insert() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = txtUsr.getText().toString();
                pass = txtPwd.getText().toString();
                if (dbHelper.InsertData(user, pass)) {
                    Toast.makeText(MainActivity.this, "Insert success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void update() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = txtUsr.getText().toString();
                pass = txtPwd.getText().toString();
                if (dbHelper.UpdateData(user, pass)) {
                    Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void delete() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = txtUsr.getText().toString();
                pass = txtPwd.getText().toString();
                if (dbHelper.DeleteData(user)) {
                    Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getall() {
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = dbHelper.getAll();
                if (c.getCount() == 0) {
                    // Message dialog error
                }
                // Berguna untuk menampung sebuah data ke array dr string
                StringBuffer sb = new StringBuffer();
                if (c != null) {
                    while (c.moveToNext()) {
                        sb.append("ID: " + c.getInt(0));
                        sb.append("Name: " + c.getString(1));
                        sb.append("Password: " + c.getString(2));
                    }
                }
                messageDialog("All data", sb.toString());
            }
        });
    }

    private void messageDialog (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}