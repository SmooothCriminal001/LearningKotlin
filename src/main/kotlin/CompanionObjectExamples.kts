class Motorbike private constructor(val make: String, val year: Int){

    fun overDrive(){mileage -= 0.25}

    fun secureDrive(){
        if(mileage < (originalMileage - 1)){
            mileage += 0.05
        }
    }

    fun yearsOnMileage(year: Int){
        mileage -= (year * 0.5)
    }

    companion object{
        val originalMileage = 50.0
        //companion object variable that is updated from instance methods
        var mileage: Double = originalMileage

        //factory method to generate instance of class
        fun createMotorBike(make: String, year: Int): Motorbike{
            return Motorbike(make, year)
        }

        fun createMotorBike(make: String): Motorbike{
            return Motorbike(make, 2020)
        }
    }
}

//val heroHonda = Motorbike("Hero Honda", 2000)
val heroHonda = Motorbike.createMotorBike("Hero Honda", 2000)
println("${heroHonda.make}'s mileage is actually ${Motorbike.mileage}")

heroHonda.yearsOnMileage(5)
println("After 5 years, the milage is ${Motorbike.mileage}")

heroHonda.overDrive()
println("With some overdrive, mileage becomes ${Motorbike.mileage}")

heroHonda.secureDrive()
println("Driving secure, milage turns into ${Motorbike.mileage}")

val unicorn = Motorbike.createMotorBike("Honda Unicorn")
println("The year of manufacture of ${unicorn.make} is ${unicorn.year}")