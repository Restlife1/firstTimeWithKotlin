package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnList = findViewById(R.id.buttonList) as Button

        btnList.setOnClickListener{

            list()

        }

        var btnNear = findViewById(R.id.buttonNear) as Button

        btnNear.setOnClickListener{

            near()

        }


    }

    private fun list(){

        var pnt = findViewById(R.id.TaxisView) as TextView

        pnt.text = ""

        var queue = Volley.newRequestQueue(this)

        var jsonRequest = JsonArrayRequest(Request.Method.GET, "http://192.168.103.29:40000/find", null,
            Response.Listener<JSONArray> { response ->

                var text = ""

                var taxisJSON = response

                for(i in 0..(taxisJSON).length()-1){

                    var taxi = taxisJSON.getJSONObject(i).getString("taxi")
                    var time = taxisJSON.getJSONObject(i).getString("time")

                    text += "Taxi: " + taxi + " Llegada en: " + time + "\n"

                }

                pnt.text = text

            },
            Response.ErrorListener {
                Log.e("fallo", "fallito")
            })

        queue.add(jsonRequest)

    }

    private fun near(){

        var pnt = findViewById(R.id.TaxisView) as TextView

        var queue = Volley.newRequestQueue(this)

        var jsonRequest = JsonObjectRequest(Request.Method.GET, "http://192.168.103.29:40000/near", null,
            Response.Listener<JSONObject> { response ->

                var taxi = response.getString("taxi")
                var time = response.getString("time")

                pnt.text = "Taxi número: " + taxi + "\n" + "Llegada en: " + time

            },
            Response.ErrorListener {
                Log.e("fallo", "fallito")
            })

        queue.add(jsonRequest)

    }

}
