package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)
        val editText = findViewById<EditText>(R.id.edit_text)
        val slider = findViewById<Slider>(R.id.slider)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val tipRatio = slider.value
                if (s.isNullOrEmpty()) {
                    textView.text = ""
                } else {
                    val tipAmount = DecimalFormat("0.00").format(s.toString().toDouble() * tipRatio / 100.0)
                    textView.text = "Tip amount: $tipAmount"
                }
            }
        })
        slider.addOnChangeListener(object : Slider.OnChangeListener {
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                val billValue = editText.text.toString().toDoubleOrNull()
                if (billValue == null) {
                    textView.text = ""
                } else {
                    val tipAmount = DecimalFormat("0.00").format(billValue * value / 100.0)
                    textView.text = "Tip amount: $tipAmount"
                }
            }
        })

    }
}