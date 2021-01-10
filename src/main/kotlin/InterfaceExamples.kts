interface Vehicle{

    //abstract properties in interface
    var type: String
    var mileage: Double

    //abstract methods in interface
    fun start()
    fun drive()

    //default method 1 in interface
    fun getGoing(){
        start()
        drive()
    }

    //default method 2 in interface
    fun getMileage(grossFuel: Double, totalDistance: Double): Double{
        mileage = totalDistance / grossFuel
        return mileage
    }

    //static methods in interface via companion object
    companion object{

        //A static method that works on two instance of Vehicle interface
        fun getEfficientVehicle(first: Vehicle, second: Vehicle): Vehicle{
            if(first.mileage >= second.mileage){
                return first
            }
            else{
                return second
            }
        }

        //A static method that results in creating a new object of type Vehicle, with redefined methods
        fun combinedVehicle(first: Vehicle, second:Vehicle) = object : Vehicle{

            //abstract methods and properties HAVE TO BE defined in this rendition of object which is more like a single copy class

            //Giving dummy values to abstract properties as they are not really needed here
            override var type: String = "Combined Vehicle"
                get() = field
                set(value) {field = value}

            override var mileage: Double = 50.0
                get() = field
                set(value) {field = value}

            //overriding interface method with the methods defined in each of the instances, each in turn a Vehicle implementation
            override fun start(){
                first.start()
                second.start()
            }

            override fun drive(){
                first.drive()
                second.drive()
            }

            //overriding default method
            override fun getGoing(){
                first.getGoing()
                second.getGoing()
            }
        }
    }
}

//Interface implementation
class Car(): Vehicle{

    //Properties defined inside class
    val totalFuelIntake = 100.00
    val totalDistanceTravelled = 5000.00

    //Giving values to abstract properties of interface. Not there are no inbuilt backing fields for these properties.
    //Getter and setters have to be defined here
    override var type: String = "Car"
        get() = field
        set(value) {field = value}

    //Calling on default interface method, getMileage
    override var mileage: Double = getMileage(totalFuelIntake, totalDistanceTravelled)
        get() = field
        set(value) { field = value }

    override fun start() {
        println("Car is started!")
    }

    override fun drive() {
        println("Car is being driven!")
    }
}

//Interface implementation
class Bike(val grossFuel: Double, val totalDistance: Double): Vehicle{

    override var type: String = "Bike"
        get() = field
        set(value) {field = value}

    //Calling on the overridden 'default?' method of interface. The method does not do what is defined for it in interface
    //but the actual definition provided within Bike class
    override var mileage: Double = getMileage(grossFuel, totalDistance)
        get() = field
        set(value) {field = value}

    override fun start() {
        println("Bike is started!")
    }

    override fun drive() {
        println("Bike is being driven!!")
    }

    //Overriding the default method of interface
    override fun getMileage(grossFuel: Double, totalDistance: Double): Double {
         if(totalDistance < 1000.00){
             return 70.0
         }
        else{
            return super.getMileage(grossFuel, totalDistance)
         }
    }
}

//Creating a vehicle: a car
val newCar:Vehicle = Car()
newCar.getGoing()
println(newCar.mileage)

//Creating a vehicle: a bike
println("\nBike details")
val newBike:Vehicle = Bike(5.0, 500.0)
newBike.getGoing()
println(newBike.mileage)

//Calling on static method in interface, getEfficientVehicle, and passing two vehicle instances for it
//The result is a reference to a Vehicle interface
val betterVehicle:Vehicle = Vehicle.getEfficientVehicle(newCar, newBike)
println("\n${betterVehicle.type} is the better of the two vehicles with a mileage of ${betterVehicle.mileage}")

//Calling on static method in interface, combinedVehicle
//Results in an object, which is a rendition of Vehicle
val combinedVehicle:Vehicle = Vehicle.combinedVehicle(newCar, newBike)
println("\nCombined vehicle start")
combinedVehicle.start()
println("Combined vehicle drive")
combinedVehicle.drive()
println("Combined vehicle is going!")
combinedVehicle.getGoing()