package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.text
import androidx.core.text.HtmlCompat
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val htmlInput: EditText = findViewById(R.id.htmlInput)
        val sendButton: Button = findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val htmlString = htmlInput.text.toString()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/html" // Set MIME type to HTML
                putExtra(Intent.EXTRA_SUBJECT, "HTML Test Email")
                putExtra(Intent.EXTRA_TEXT, HtmlCompat.fromHtml(htmlString,
                    HtmlCompat.FROM_HTML_MODE_LEGACY))
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Send email..."))
            } else {
                Toast.makeText(this, "No email app found", Toast.LENGTH_LONG).show()
            }
        }
    }
}