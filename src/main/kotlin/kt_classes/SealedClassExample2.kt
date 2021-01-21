package kt_classes

fun process(card: Card) = when(card){
    is Ace -> "${card.javaClass.name} of ${card.suit}"
    is King, is Queen, is Jack -> "$card"
    is Pip -> "${card.number} of ${card.suit}"
}

//Extending the subclass 'Ace' of Sealed class 'Card'
class Game(suit: String) : Ace(suit)

fun main(){
    println(process(Ace("Spade")))
    println(process(Queen("Clubs")))
    println(process(Jack("Diamond")))

    //Accessing sealed class 'Card' variable through extension outside the file
    val game: Ace = Game("Cards")
    println(game.material)
}