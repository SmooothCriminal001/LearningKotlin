package kt_classes

sealed class Card(val suit: String){
    val material = "Plastic"
}

open class Ace(suit: String): Card(suit)

class King(suit: String): Card(suit){
    override fun toString(): String = "King of $suit"
}

class Queen(suit: String): Card(suit){
    override fun toString(): String = "Queen of $suit"
}

class Jack(suit: String): Card(suit){
    override fun toString(): String = "Jack of $suit"
}

class Pip(val number:Int, suit: String): Card(suit){
    init{
        if(number < 2 || number > 10){
            throw RuntimeException("Pip has to be between 2 and 10")
        }
    }
}