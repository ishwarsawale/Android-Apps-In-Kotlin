package notes.my.ishwar.com.mynotes

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {

    //table name
    val dbTable="Notes"
    var id=0



    //oncreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)




       try{
           //add intent
           var bundle:Bundle=intent.extras
           id=bundle.getInt("ID",0)
           if(id!=0) {
               etTitle.setText(bundle.getString("name") )
               etDes.setText(bundle.getString("des") )

           }
       }catch (ex:Exception){}


    }

    //add note
    fun  buAdd(view:View){
        var dbManager=DbManager(this)

        //values from content
        var values= ContentValues()

        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())

        //add data to sqlite db
        if(id==0) {
            //insert query
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }else{

            //update query
            var selectionArs= arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArs)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }

    }
}


