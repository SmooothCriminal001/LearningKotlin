interface Remote{
    fun up ()
    fun down()
    fun upTimes(times: Int){
        for(i in 1..times){ up() }
    }
}

class TV(){
    //private variable; this is accessible by inner class
    private var volume: Int = 0

    //get method of this creates a new remote every time 'remote' variable is called
    val remote: Remote
    get() = TVRemote()

    //Using anonymous inner class using object to create a class where it is needed
    //No references, no names
    val fasterRemote: Remote
    get() = object: Remote{
        override fun up() {
            volume += 5
        }
        override fun down() {
            volume -= 5
        }

        override fun toString(): String = "Faster Remote : ${this@TV.toString()}"
    }

    override fun toString(): String = "Volume is $volume"

    inner class TVRemote(): Remote{
        override fun up() {
            volume ++
        }

        override fun down() {
            volume --
        }

        //using a method 'toString()' which is defined in TV class also
        //To call on the TV toString() method, this@TV notation isused
        override fun toString(): String = "Remote : ${this@TV.toString()}"
    }
}


val newTV = TV()
val newTVRemote = newTV.remote      //called only once to create a new TVRemote; 'newTVRemote' instance to be used later
newTVRemote.upTimes(5)
println(newTV.toString())           //calling TV's toString()

newTVRemote.down()
println(newTVRemote.toString())     //calling TVRemote's toString()

val fasterRemote = newTV.fasterRemote   //creating a 'faster remote' with anonymous inner class
fasterRemote.upTimes(5)           //call to upTimes() changes the volume of original newTV itself
println(fasterRemote.toString())