package com.example.practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.ActivityMainBinding
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val btn : Button = binding.btn1
    btn.setOnClickListener{
      val intent = Intent(this, MainActivity2::class.java)
      intent.putExtra("phone_number", binding.editText.text.toString())
      intent.putExtra("num", 123)
      startActivity(intent)
    }
    val xpp = resources.getXml(R.xml.user)
    var eventType = xpp.eventType
    var curValue = ""
    var result = "Добро пожаловать"
    while(eventType != XmlPullParser.END_DOCUMENT){
      val name = xpp.name
      when(eventType){
        XmlPullParser.TEXT -> curValue = xpp.text
        XmlPullParser.END_TAG ->
          if((name == "name") || (name == "surname")) result += " $curValue"
      }
      eventType = xpp.next()
    }
    result += '!'
    binding.welcome.text = result
  }
}