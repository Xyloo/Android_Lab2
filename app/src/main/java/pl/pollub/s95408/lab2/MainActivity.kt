package pl.pollub.s95408.lab2

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sredniaTextView = findViewById<TextView>(R.id.srednia)
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksTextField = findViewById<EditText>(R.id.marksTextField)
        val resultButton = findViewById<Button>(R.id.resultButton)
        val average = intent.extras?.getString("average")
        sredniaTextView.text = getString(R.string.srednia, average)
        if(intent.extras?.getString("name") != null) {
            nameTextField.text =
                Editable.Factory.getInstance().newEditable(intent.extras?.getString("name"))
            surnameTextField.text =
                Editable.Factory.getInstance().newEditable(intent.extras?.getString("surname"))
            marksTextField.text =
                Editable.Factory.getInstance().newEditable(intent.extras?.getString("marksAmount"))
        }
        if(average != null)
        {
            sredniaTextView.visibility = View.VISIBLE
            findViewById<Button>(R.id.marksButton).visibility = View.VISIBLE
            resultButton.visibility = View.VISIBLE
            if(BigDecimal(average) < BigDecimal(3.0))
            {
                resultButton.text = getString(R.string.buttonNiePoszlo)
            }
            else
            {
                resultButton.text = getString(R.string.buttonSuper)
            }
        }

        addListeners()
    }

    private fun addListeners()
    {
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksTextField = findViewById<EditText>(R.id.marksTextField)
        val marksButton = findViewById<Button>(R.id.marksButton)
        val resultButton = findViewById<Button>(R.id.resultButton)
        val fields = listOf(nameTextField, surnameTextField, marksTextField)
        marksButton.setOnClickListener {
            val intent = android.content.Intent(this, MarksActivity::class.java)
            intent.putExtra("marks", marksTextField.text.toString().toInt())
            intent.putExtra("name", nameTextField.text.toString())
            intent.putExtra("surname", surnameTextField.text.toString())
            startActivity(intent)
        }
        resultButton.setOnClickListener {
            if(resultButton.text == getString(R.string.buttonNiePoszlo))
            {
                Toast.makeText(applicationContext, "Wysyłam podanie o zaliczenie warunkowe", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show()
            }
            this.finishAffinity()
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