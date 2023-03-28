package pl.pollub.s95408.lab2

import android.content.res.Configuration
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sredniaTextView = findViewById<TextView>(R.id.srednia)
        sredniaTextView.text = intent.extras?.getString("average")
        sredniaTextView.visibility = View.VISIBLE

        addListeners()
    }
/*
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksTextField = findViewById<EditText>(R.id.marksTextField)
        val marksButton = findViewById<Button>(R.id.marksButton)
        outState.putString("name", nameTextField.text.toString())
        outState.putString("surname", surnameTextField.text.toString())
        outState.putString("marks", marksTextField.text.toString())
        outState.putInt("marksButtonVisibility", marksButton.visibility)
        outState.putString("marksButtonError", marksButton.error?.toString())
        outState.putString("nameTextFieldError", nameTextField.error?.toString())
        outState.putString("surnameTextFieldError", surnameTextField.error?.toString())
        outState.putString("marksTextFieldError", marksTextField.error?.toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksTextField = findViewById<EditText>(R.id.marksTextField)
        val marksButton = findViewById<Button>(R.id.marksButton)
        nameTextField.setText(savedInstanceState?.getString("name"))
        surnameTextField.setText(savedInstanceState?.getString("surname"))
        marksTextField.setText(savedInstanceState?.getString("marks"))
        marksButton.visibility = savedInstanceState?.getInt("marksButtonVisibility") ?: View.VISIBLE
        marksButton.error = savedInstanceState?.getString("marksButtonError")
        nameTextField.error = savedInstanceState?.getString("nameTextFieldError")
        surnameTextField.error = savedInstanceState?.getString("surnameTextFieldError")
        marksTextField.error = savedInstanceState?.getString("marksTextFieldError")
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }
*/
    private fun addListeners()
    {
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksTextField = findViewById<EditText>(R.id.marksTextField)
        val marksButton = findViewById<Button>(R.id.marksButton)
        val fields = listOf(nameTextField, surnameTextField, marksTextField)
        marksButton.setOnClickListener {
            val intent = android.content.Intent(this, MarksActivity::class.java)
            intent.putExtra("marks", marksTextField.text.toString().toInt())
            intent.putExtra("name", nameTextField.text.toString())
            intent.putExtra("surname", surnameTextField.text.toString())
            startActivity(intent)
        }
        for(field in fields)
        {
            field.addTextChangedListener { text ->
                if(text.toString().isNotEmpty())
                {
                    if(field == marksTextField)
                    {
                        val value = field.text.toString().toIntOrNull() ?: 0
                        if(value < 5 || value > 15)
                        {
                            field.error = "Liczba ocen jest spoza zakresu"
                            Toast.makeText(applicationContext, "Liczba ocen jest spoza zakresu", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    if(field == nameTextField)
                    {
                        nameTextField.error = "Imię nie może być puste"
                        Toast.makeText(applicationContext, "Imię nie może być puste", Toast.LENGTH_SHORT).show()
                    }
                    if(field == surnameTextField)
                    {
                        surnameTextField.error = "Nazwisko nie może być puste"
                        Toast.makeText(applicationContext, "Nazwisko nie może być puste", Toast.LENGTH_SHORT).show()
                    }
                }
                for(field2 in fields)
                {
                    if(field2.text.toString().isNotEmpty())
                    {
                        if(field2 == marksTextField)
                        {
                            val value = field2.text.toString().toIntOrNull() ?: 0
                            if(value < 5 || value > 15)
                            {
                                marksButton.visibility = View.GONE
                                break
                            }
                        }
                        marksButton.visibility = View.VISIBLE
                    }
                    else
                    {
                        marksButton.visibility = View.GONE
                        break
                    }
                }
            }
        }

    }
}