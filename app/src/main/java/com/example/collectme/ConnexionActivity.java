package com.example.collectme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionActivity extends AppCompatActivity {

    private EditText et_mail, et_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        init();
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
        finish();
    }

    public void init() {
        et_mail = findViewById(R.id.et_form_connexion_mail);
        et_password = findViewById(R.id.et_form_connexion_password);
        mAuth = FirebaseAuth.getInstance();

    }

    public void onClickConnexion(View view) {
        String email = et_mail.getText().toString();
        String mdp = et_password.getText().toString();

        Toast.makeText(this, email + " " + mdp, Toast.LENGTH_SHORT).show();
        connexionFireBase(email,mdp);
    }

    public void onClickAllerAInscription(View view) {
        Intent inscriptionIntent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
        startActivity(inscriptionIntent);
        finish();
    }

    public void connexionFireBase(String email, String mdp) {
        mAuth.signInWithEmailAndPassword(email, mdp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's
                    Log.d("connexionTest", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
// If sign in fails, display a message to the user.
                    Log.w("connexionTest", "signInWithEmail:failure", task.getException());
                    Toast.makeText(ConnexionActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }

            private void updateUI(FirebaseUser user) {
                if (user !=null){
                    Toast.makeText(ConnexionActivity.this, R.string.connexion_reussite, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ConnexionActivity.this, R.string.connexion_echec, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}