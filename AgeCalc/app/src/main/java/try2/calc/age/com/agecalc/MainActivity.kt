package try2.calc.age.com.agecalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvAge.setText("")
    }

    private fun calculateAge(view: View){
        val db: String = etBirth.text.toString()
        val cYear = Calendar.getInstance().get(Calendar.YEAR)
        val age  = cYear - db.toInt()
        tvAge.setText("Your age is $age")
    }
}
