package app.zoo.ishwar.com.zooapp



import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*
import android.content.Intent



class MainActivity : AppCompatActivity() {

    //create array list of animal
    var listOfAnimals = ArrayList<Animal>()

    //creat adapter to add animal to view
    var adapter:AnimalsAdapter?=null

    //onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //load  animals with properties
        listOfAnimals.add(
                Animal("Baboon","Baboon live in  big place with tree",R.drawable.baboon,false))
        listOfAnimals.add(
                Animal("Bulldog","Bulldog live in  big place with tree",R.drawable.bulldog,false))

        listOfAnimals.add(
                Animal("Swallow","Swallow live in  big place with tree",R.drawable.swallow_bird,false))
        listOfAnimals.add(
                Animal("Zebra","Zebra live in  big place with tree",R.drawable.zebra,false))
        listOfAnimals.add(                                                                       
                Animal("Tiger","Tiger live in  big place with tree",R.drawable.white_tiger,true))
        listOfAnimals.add(
                Animal("Panda","Panda live in  big place with tree",R.drawable.panda,false))




        //create new animal adapter, with above list of animals
        adapter = AnimalsAdapter(this,listOfAnimals)

        //in main activity add above adapter to list adapter(activity_main.xml)
        tvListAnimal.adapter =  adapter


    }

    //remove object from array list 
    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        //call base adapter method update view
        adapter!!.notifyDataSetChanged()
    }

    //add object in view at index 
    fun  add(index:Int){
        //add after give index 
        listOfAnimals.add(index,listOfAnimals[index])
        //call base adapter method update view
        adapter!!.notifyDataSetChanged()
    }

    //adapter to call view and add data to it, implement BaseAdapter
    inner class  AnimalsAdapter:BaseAdapter {

        //list of animals 
        var  listOfAnimals= ArrayList<Animal>()

        //android context access component activity resources etc
        var context:Context?=null

        //invoke constructor add context, animals 
        constructor(context:Context, listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }

        //override default view in android
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            //get view object id(p0)
            val animal =  listOfAnimals[p0]

            //if not killer, property of an animal
            if( animal.isKiller ==true) {

              //get context layout                                                                        
              var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                                                                                          
              //create view and add values for view properties                                            
              var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
              myView.tvName.text = animal.name!!                                                          
              myView.tvDes.text = animal.des!!                                                            
              myView.ivAnimalImage.setImageResource(animal.image!!)                                       
                                                                                                          
              //after on click on ivAnimalImage property                                                  
              myView.ivAnimalImage.setOnClickListener {                                                   
                                                                                                          
                  //if not killer so duplicate animal, we need it                                         
//                  add(p0)
                                                                                                          
                  //add data to animal info                                                               
                   val intent = Intent(context,AnimalInfo::class.java)                                       
                   intent.putExtra("name",animal.name!!)                                                     
                   intent.putExtra("des",animal.des!!)
                   intent.putExtra("image",animal.image!!)

                   context!!.startActivity(intent)

                }
                //return view
                return myView

            }else {         //if not killer
                
                //get context layout
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                //create view and add values for view properties
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)

                //after on click on ivAnimalImage property
                myView.ivAnimalImage.setOnClickListener {

                    //if not killer so duplicate animal, we need it
                    add(p0)

                    //add data to animal info
                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView
            }
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return  listOfAnimals.size
        }

    }
}
