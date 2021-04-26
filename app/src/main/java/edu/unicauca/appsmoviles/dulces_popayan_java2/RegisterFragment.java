package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    EditText tv_email,tv_password,tv_password2;
    Button register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        register = root.findViewById(R.id.register);
        tv_email = root.findViewById(R.id.email);
        tv_password = root.findViewById(R.id.password);
        tv_password2 = root.findViewById(R.id.password2);

        register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String email = tv_email.getText().toString().trim();
                                            String password = tv_password.getText().toString().trim();
                                            String password2 = tv_password2.getText().toString().trim();
                                            Boolean check;
                                            if (password.equals(password2)){
                                                check = true;
                                            }
                                            else{
                                                check = false;
                                            }
                                            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)){
                                                CharSequence text = "ningun campo puede estar vacio";
                                                int duration = Toast.LENGTH_SHORT;
                                                Toast toast = Toast.makeText(root.getContext(), text, duration);
                                                toast.show();
                                            }
                                            else if(check){

                                                System.out.println("Las contraseñas de porqueria son:");
                                                System.out.println("---" + password + "---" + password2 + "---");
                                                System.out.println(check);
                                                registrarUsuario(email, password);
                                                CharSequence text = "Registrando...";
                                                int duration = Toast.LENGTH_SHORT;
                                                Toast toast = Toast.makeText(root.getContext(), text, duration);
                                                toast.show();
                                            }
                                            else{

                                                System.out.println("Las contraseñas de porqueria son:");
                                                System.out.println("---" + password + "---" + password2 + "---");

                                                CharSequence text = "Las contraseñas deben coicidir";
                                                int duration = Toast.LENGTH_SHORT;
                                                Toast toast = Toast.makeText(root.getContext(), text, duration);
                                                toast.show();
                                            }
                                        }
                                    }
        );

        return root;
    }

    private void registrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password);

    }

    private void finalizarregistro(Task<AuthResult> task) {
        if (task.isSuccessful()){
            System.out.println("registro exitoso");
        }
        else {
            System.out.println("error al registrar");
        }
    }


}