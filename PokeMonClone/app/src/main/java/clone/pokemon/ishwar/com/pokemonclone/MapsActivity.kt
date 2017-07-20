package clone.pokemon.ishwar.com.pokemonclone

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback  {
    private var mMap: GoogleMap? = null

    @RequiresApi(Build.VERSION_CODES.M) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        checkPermmison()
        LoadPockemon()
    }

    var ACCESSLOCATION=123
    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermmison(){

        if(Build.VERSION.SDK_INT>=23){

            if(ActivityCompat.
                    checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),ACCESSLOCATION)
                return
            }
        }

        GetUserLocation()
    }

    @SuppressLint("MissingPermission")
    fun GetUserLocation(){
        Toast.makeText(this,"User location access on",Toast.LENGTH_LONG).show()

        var myLocation= MylocationListener()

        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)

        var t_mythread = myThread()
        t_mythread.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){

            ACCESSLOCATION->{

                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    GetUserLocation()
                }else{
                    Toast.makeText(this,"We cannot access to your location",Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val mario = LatLng(18.562622, 73.808723)
        val bulbasaur = LatLng(18.762624, 73.908723)
        val charmander = LatLng(18.862632, 73.808723)
        val squirtle = LatLng(18.962622, 73.708723)

        mMap!!.addMarker(MarkerOptions()
                .position(mario)
                .title("Me")
                .snippet(" here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))

        mMap!!.addMarker(MarkerOptions()
                .position(bulbasaur)
                .title("Me")
                .snippet(" here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bulbasaur)))

        mMap!!.addMarker(MarkerOptions()
                .position(charmander)
                .title("Me")
                .snippet(" here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.charmander)))


        mMap!!.addMarker(MarkerOptions()
                .position(squirtle)
                .title("Me")
                .snippet(" here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.squirtle)))




        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(mario, 14f))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(bulbasaur, 14f))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(charmander, 14f))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(squirtle, 14f))

    }

    var location:Location?=null

    //Get user location

    inner class MylocationListener:LocationListener{


        constructor(){
            location= Location("Start")
            location!!.longitude=0.0
            location!!.longitude=0.0
        }
        override fun onLocationChanged(p0: Location?) {
            location=p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


    var oldLocation:Location?=null
    inner class myThread:Thread{

        constructor():super(){
            oldLocation= Location("Start")
            oldLocation!!.longitude=0.0
            oldLocation!!.longitude=0.0
        }

        override fun run(){

            while (true){

                try {

                    if(oldLocation!!.distanceTo(location)==0f){
                        continue
                    }

                    oldLocation=location


                    runOnUiThread {


                        mMap!!.clear()


                        // show me
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet("Location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))

                        // show pockemons

                        for(i in 0..listPockemons.size-1){

                            var newPockemon=listPockemons[i]

                            if(newPockemon.IsCatch==false){

                                val pockemonLoc = LatLng(newPockemon.location!!.latitude, newPockemon.location!!.longitude)
                                mMap!!.addMarker(MarkerOptions()
                                        .position(pockemonLoc)
                                        .title(newPockemon.name!!)
                                        .snippet(newPockemon.des!! +", power:"+ newPockemon!!.power)
                                        .icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!)))


                                if (location!!.distanceTo(newPockemon.location)<2){
                                    newPockemon.IsCatch=true
                                    listPockemons[i]=newPockemon
                                    playerPower+=newPockemon.power!!
                                    Toast.makeText(applicationContext,
                                            "You catch new pokemon your new power is " + playerPower,
                                            Toast.LENGTH_LONG).show()

                                }

                            }
                        }





                    }

                    Thread.sleep(1000)

                }catch (ex:Exception){}


            }

        }

    }


    var playerPower=0.0
    var listPockemons=ArrayList<Pockemon>()

    fun  LoadPockemon(){

        listPockemons.add(Pockemon(R.drawable.charmander,
                "Charmander", "Charmander living in japan", 55.0, 18.516726, 73.856255))
        listPockemons.add(Pockemon(R.drawable.bulbasaur,
                "Bulbasaur", "Bulbasaur living in usa", 90.5, 37.7949568502667, -122.410494089127))
        listPockemons.add(Pockemon(R.drawable.squirtle,
                "Squirtle", "Squirtle living in iraq", 33.5, 37.7816621152613, -122.41225361824))


    }

}
