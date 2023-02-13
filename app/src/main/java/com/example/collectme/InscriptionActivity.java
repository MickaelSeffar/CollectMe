package com.example.collectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InscriptionActivity extends AppCompatActivity {

    private EditText et_email, et_mdp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        init();
    }

    //Permet d'initialiser les composants en les récupérants de la vue
    public void init() {
        et_email = findViewById(R.id.et_inscription_email);
        et_mdp = findViewById(R.id.et_inscription_mot_de_passe);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickInscription(View view) {
//Afficher dans les log Bonjour, tu es dans onClickInscription
//        Log.d("testInscription", "Bonjour, tu es dans le onclickInscription");

        //Afficher dans les logs les variables saisies par l'utilisateur
        String email = et_email.getText().toString();
        String mdp = et_mdp.getText().toString();
        Log.d("testInscription",email +", "+ mdp);
        insertUtilisateurFirebase(email, mdp);
    }


    public void onClickGoToConnexion(View view) {
//Afficher à l'utilisateur Bonjour, tu es dans onClickConnexion
        Toast.makeText(this, "Bonjour, tu es dans onClickConnexion", Toast.LENGTH_SHORT).show();
    }

    public void insertUtilisateurFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("testInscription", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            majUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("testInscription", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            majUI(null);
                        }
                    }
                });
    }

    public void majUI(FirebaseUser user){
        if (user != null) {
            Toast.makeText(this,R.string.inscription_reussite, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this,R.string.inscription_echec, Toast.LENGTH_SHORT).show();

        }
    }
}