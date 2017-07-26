package notes.my.ishwar.com.mynotes


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //list notes
    var listNotes=ArrayList<Note>()

    //on create when app first launch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        LoadQuery("%")


    }

    override  fun onResume() {
        super.onResume()
        //on resume
        LoadQuery("%")
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show()
    }


    //load query
    fun LoadQuery(title:String){


        //get db manager
        var dbManager=DbManager(this)
        val projections= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(title)

        //sort on title
        val cursor=dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        listNotes.clear()

        //cursor detect till notes
        if(cursor.moveToFirst()){

            do{
                //get data to add into notes
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note(ID,Title,Description))

            }while (cursor.moveToNext())
        }

        //create note adapter to add data from model to view
        var myNotesAdapter= MyNotesAdpater(this, listNotes)

        //list view add data from adapter
        lvNotes.adapter=myNotesAdapter


    }


    @SuppressLint("NewApi")

    //add menu options
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

         //menu view main_menu
          menuInflater.inflate(R.menu.main_menu, menu)

         //create item search item
          val sv:SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

          //from context get sys service search
          val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
          sv.setSearchableInfo(sm.getSearchableInfo(componentName))
          sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
              //search query
              override fun onQueryTextSubmit(query: String): Boolean {
                  Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                  LoadQuery("%"+ query +"%")
                  return false
              }

              override fun onQueryTextChange(newText: String): Boolean {
                  return false
              }
          })


        return super.onCreateOptionsMenu(menu)
    }

    // when item selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.addNote->{
                    //Got to add paage
                    var intent= Intent(this,AddNotes::class.java)
                    startActivity(intent)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

     //implement base adapter to pass data from db to view
    inner class  MyNotesAdpater:BaseAdapter{
        //create arraylist of note type
        var listNotesAdpater=ArrayList<Note>()

         //create context
        var context:Context?=null
        constructor(context:Context, listNotesAdpater:ArrayList<Note>):super(){
            this.listNotesAdpater=listNotesAdpater
            this.context=context
        }

         //add elements to get view
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

             //get view for each note as ticket
            var myView=layoutInflater.inflate(R.layout.ticket,null)
             //index p0 get myNote
            var myNote=listNotesAdpater[p0]
             //add data to view from note
            myView.tvTitle.text=myNote.nodeName
            myView.tvDes.text=myNote.nodeDes
             //set delete button onClick listener
            myView.ivDelete.setOnClickListener( View.OnClickListener {
                //if delete dbManager instance
                var dbManager=DbManager(this.context!!)
                val selectionArgs= arrayOf(myNote.nodeID.toString())
                dbManager.Delete("ID=?",selectionArgs)
                LoadQuery("%")
            })
             //for edit set Onclick
            myView.ivEdit.setOnClickListener( View.OnClickListener{

                //update note
                GoToUpdate(myNote)

            })
            return myView
        }

         //get item form note
        override fun getItem(p0: Int): Any {
        return listNotesAdpater[p0]
         }

        override fun getItemId(p0: Int): Long {
           return p0.toLong()
         }

        override fun getCount(): Int {

            return listNotesAdpater.size

        }



    }

    //update note, intent used to pass data between components
   fun GoToUpdate(note:Note){
       var intent=  Intent(this,AddNotes::class.java)
       intent.putExtra("ID",note.nodeID)
       intent.putExtra("name",note.nodeName)
       intent.putExtra("des",note.nodeDes)
       startActivity(intent)
   }


}
