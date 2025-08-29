package com.example.mytestapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // используем XML layout

        val nameInput = findViewById<EditText>(R.id.name_input)
        val surenameInput = findViewById<EditText>(R.id.surename_input)
        val button = findViewById<Button>(R.id.click_button)
        val greetingOutput = findViewById<TextView>(R.id.greeting_output)
        val clickedOutput = findViewById<TextView>(R.id.clicked_output)

        button.setOnClickListener {
            clicks++
            val name = nameInput.text.toString().trim()
            val surename = surenameInput.text.toString().trim()

            greetingOutput.text = if (name.isNotEmpty() && surename.isNotEmpty()) {
                "Hello $name $surename!"
            } else {
                "Hello Guest!"
            }
            clickedOutput.text = "Button clicked $clicks times"
        }
    }
}