package app.zoo.ishwar.com.zooapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animal_info.*

/**
 * Created by Admin on 7/21/2017.
 */
//when image is clicked in view, this activity is called
class AnimalInfo : AppCompatActivity() {

    //override onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        //add value to properties
        val bundle:Bundle = intent.extras
        val name =bundle.getString("name")
        val des = bundle.getString("des")
        val image=bundle.getInt("image")
        ivAnimaImage.setImageResource(image)
        tvName.text=name.toString()
        tvDes.text=des
    }
}