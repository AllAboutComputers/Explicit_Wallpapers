package com.sdk.explicitwallpapers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import android.content.Intent; // Ensure this import is included

public class LoginActivity extends AppCompatActivity {

    // Declare variables for the UI components
    EditText username, email, password, confirmPassword;
    Button registerButton;
    ImageButton btnShowHidePassword, btnShowHideConfirmPassword;

    // Track password visibility state
    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);  // Set the content to the login_page layout

        // Link the XML components to Java variables using findViewById
        username = findViewById(R.id.inputusername);
        email = findViewById(R.id.inputemail);
        password = findViewById(R.id.inputpassword);
        confirmPassword = findViewById(R.id.inputconfirmpassword);
        registerButton = findViewById(R.id.btnregister);
        btnShowHidePassword = findViewById(R.id.btnShowHidePassword);
        btnShowHideConfirmPassword = findViewById(R.id.btnShowHideConfirmPassword);

        // Set an onClickListener on the register button to handle registration
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from EditTexts
                String usernameInput = username.getText().toString().trim();
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                String confirmPasswordInput = confirmPassword.getText().toString().trim();

                // Validate the inputs
                if (validateInputs(usernameInput, emailInput, passwordInput, confirmPasswordInput)) {
                    // If validation passes, proceed with registration logic
                    registerUser(usernameInput, emailInput, passwordInput);
                }
            }
        });

        // Toggle password visibility on button click
        btnShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                updatePasswordVisibility(password, btnShowHidePassword, isPasswordVisible);
            }
        });

        // Toggle confirm password visibility on button click
        btnShowHideConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                updatePasswordVisibility(confirmPassword, btnShowHideConfirmPassword, isConfirmPasswordVisible);
            }
        });
    }

    // Method to validate user input
    private boolean validateInputs(String username, String email, String password, String confirmPassword) {
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Method to handle user registration (You can replace this with your backend logic)
    private void registerUser(String username, String email, String password) {
        // Placeholder for actual registration logic
        Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show();

        // Navigate to the login activity
        Intent intent = new Intent(LoginActivity.this, ActualLogin.class);
        startActivity(intent);
        finish(); // Optional: finish the current activity
    }

    // Method to update the visibility of the password
    private void updatePasswordVisibility(EditText passwordField, ImageButton toggleButton, boolean isVisible) {
        if (isVisible) {
            passwordField.setInputType(1); // 1 for visible password
            toggleButton.setImageResource(R.drawable.ic_visibility); // Change to visible icon
        } else {
            passwordField.setInputType(129); // 129 for hidden password
            toggleButton.setImageResource(R.drawable.ic_visibility_off); // Change to hidden icon
        }
        passwordField.setSelection(passwordField.length()); // Move cursor to the end
    }
}
