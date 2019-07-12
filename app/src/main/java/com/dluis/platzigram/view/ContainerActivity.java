package com.dluis.platzigram.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.dluis.platzigram.R;
import com.dluis.platzigram.login.view.LoginActivity;
import com.dluis.platzigram.post.view.HomeFragment;
import com.dluis.platzigram.view.fragment.ProfileFragment;
import com.dluis.platzigram.view.fragment.SearchFragment;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;


public class ContainerActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FirebaseCrash.log("Inicializando variables ContainerActivity");

        BottomNavigationView bottomBar = findViewById(R.id.bottombar);
        bottomBar.setSelectedItemId(R.id.bottombar);
        crearFragmentHome();

        firebaseInitialize();


        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {
                    case R.id.tab_home:

                        crearFragmentHome();

                        break;
                    case R.id.tab_profile:

                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case R.id.tab_search:

                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null)
                                .commit();
                        break;
                }
                return true;
            }
        });


    }

    private void firebaseInitialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    //  goHome();
                    System.out.println("Usuario Logeado " + firebaseUser.getEmail());
                } else {
                    System.out.println("Usuario No Logeado ");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mSignOut:

                firebaseAuth.signOut();

                if (LoginManager.getInstance() != null) {
                    LoginManager.getInstance().logOut();
                }

                Intent i = new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(i);

                break;

            case R.id.mAbout:
                Toast.makeText(this, "Platzigram by platzi", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void crearFragmentHome() {

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

}

