package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    EditText tv_email,tv_password;
    TextView logout_header;
    Button login;
    TextView username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        login = root.findViewById(R.id.login);
        tv_email = root.findViewById(R.id.email);
        tv_password = root.findViewById(R.id.password);
        username = root.findViewById(R.id.username);
        logout_header = root.findViewById(R.id.text_slideshow);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = ((FirebaseUser) user).getEmail();
            tv_email.setVisibility(View.INVISIBLE);
            tv_password.setVisibility(View.INVISIBLE);
            logout_header.setText("Logout");
            username.setText(email);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            login.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             CharSequence text = "El usuario ya está logueado";
                                             int duration = Toast.LENGTH_SHORT;
                                             Toast toast = Toast.makeText(root.getContext(), text, duration);
                                             toast.show();
                                         }
                                     }
            );

            login.setText("logout");
            login.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             CharSequence text = "Cerrando Sesión";
                                             int duration = Toast.LENGTH_SHORT;
                                             Toast toast = Toast.makeText(root.getContext(), text, duration);
                                             toast.show();

                                             desloguearUsuario();

                                         }
                                     }
            );

        }
        else{
            //si no está logueado
            login.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             String email = tv_email.getText().toString().trim();
                                             String password = tv_password.getText().toString().trim();

                                             if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                                                 CharSequence text = "ningun campo puede estar vacio";
                                                 int duration = Toast.LENGTH_SHORT;
                                                 Toast toast = Toast.makeText(root.getContext(), text, duration);
                                                 toast.show();
                                             }
                                             else {
                                                 loguearUsuario(email, password);
                                                 CharSequence text = "logueando...";
                                                 int duration = Toast.LENGTH_SHORT;
                                                 Toast toast = Toast.makeText(root.getContext(), text, duration);
                                                 toast.show();
                                             }
                                         }
                                     }
            );


        }





        return root;
    }

    private void desloguearUsuario() {
        FirebaseAuth.getInstance().signOut();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            username.setText("");
            login.setText("Login");


            // Reload current fragment
            Intent intent = getActivity().getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getActivity().overridePendingTransition(0, 0);
            getActivity().finish();

            getActivity().overridePendingTransition(0, 0);
            startActivity(intent);

        }

    }

    private void loguearUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {

                        // Reload current fragment
                        Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        getActivity().overridePendingTransition(0, 0);
                        getActivity().finish();

                        getActivity().overridePendingTransition(0, 0);
                        startActivity(intent);


                    }

                }
            }
        });

    }
}