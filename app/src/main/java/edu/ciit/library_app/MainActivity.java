package edu.ciit.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity Error";
    private static GoogleSignInOptions gso;
    public GoogleSignInClient mGoogleSignInClient;
    public boolean session = false;
    private int RC_SIGN_IN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);

        SignInButton signInButton = (SignInButton) findViewById(R.id.signInBtn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //This checks if a previous account is login during startup of the app
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null && session == true)
        {
            startMainMenu(account);
        }
        else
        {
            signOut();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        String email;
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            email = account.getEmail();
            if (email.contains("@ciit.edu.ph")) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                startMainMenu(account);
            } else {
                Toast.makeText(this, "Invalid Email (Please Use CIIT email only)", Toast.LENGTH_SHORT).show();
                signOut();
            }
        } catch (ApiException ex) {
            Log.w(TAG, "signInResult: Failed code=" + ex.getStatusCode());

        }
    }

    private void startMainMenu(GoogleSignInAccount googleSignInAccount) {
        Intent startMain_Menu = new Intent(this, MainMenu.class);
        session = false;
        startMain_Menu.putExtra(MainMenu.GOOGLE_ACCOUNT, googleSignInAccount);
        startActivity(startMain_Menu);
        finish();

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}
