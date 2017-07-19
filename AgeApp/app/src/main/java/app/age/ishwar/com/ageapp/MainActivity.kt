package app.age.ishwar.com.ageapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        buFindAge.setOnClickListener{
//            val yearBrith = yob.text.toString().toInt()
//            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
//            var yourAge = currentYear - yearBrith
//            dispAge.text = yourAge.toString()
//        }
    }

    fun getAgeOnClick(view: View){
        val yearBrith = yob.text.toString().toInt()
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        var yourAge = currentYear - yearBrith
        dispAge.text = yourAge.toString()
    }

}
