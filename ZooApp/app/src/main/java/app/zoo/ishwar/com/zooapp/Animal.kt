package app.zoo.ishwar.com.zooapp

/**
 * Created by Admin on 7/21/2017.
 */
//animal contains animal value
class Animal{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var isKiller:Boolean? = null
    constructor(name:String ,des:String ,image:Int,isKiller:Boolean){
        this.name=name
        this.des=des
        this.image=image
        this.isKiller=isKiller
    }
}
