//Original data set to be converted to XML
val languagesMap = mapOf("JavaScript" to "Eich", "Java" to "Gosling", "Ruby" to "Matz")

//DslMarker to prevent calling of implicit receivers more than one level up
@DslMarker
annotation class XMLMarker

//The code to convert the above map into an XML
val xmlValue = xml{
    root("languages"){
        languagesMap.forEach { name, author ->
                element("language", "name" to name) {
                element("author") { text(author) }
                //root("oops"){}                    //This fails compilation because of DslMarker annotation
                this@xml.root("oops") {}      //This is an explicit call. This works. Though it is a senseless addition here.
            }
        }
    }
}

println(xmlValue)

//First xml method that takes one lambda as parameter
//Creates an XMLBuilder element, and runs the lambda; returns its response, which is a Node
fun xml(block: XMLBuilder.() -> Node):Node = XMLBuilder().run(block)

//Use of XML marker makes sure nested levels cannot call on parent methods with implicit receivers
@XMLMarker
//XML Builder class; Only method root runs a lambda. The result is Node
class XMLBuilder(){
    fun root(name: String, block: Node.() -> Unit):Node = Node(name).apply(block)
}

@XMLMarker
//Node class, specifying each node in XML
class Node(val name: String){
    var attributes: Map<String, String> = mapOf()           //The attributes of a particular node in a map
    var children: List<Node> = listOf()                     //The children of a particular node; These are all nodes themselves
    var textValue = ""                                      //Text value of node, if it is a non-parent node

    //text() method used in the above XML-generating snippet. Just takes a text and maps it to textValue in Node
    fun text(value: String){ textValue = value}

    //element() method from above; Takes a vararg parameter with all the attributes for the node
    //This actually creates a child node on the present node. Using apply at the end and keeping it open for a lambda expression
    //enables recursive calling of element() (through lambda) to create children
    fun element(name: String, vararg attributesList: Pair<String, String>, block: Node.() -> Unit): Node{
        val child = Node(name)
        attributesList.forEach { child.attributes += it }
        children += child
        return child.apply(block)
    }

    //Convering a node into a string; indentation specifies the length of space prefix for a single node
    fun toString(indentation: Int): String{
        val depth = 2
        val indent = " ".repeat(indentation)            //spaces repeated based on the indentation

        //attributes concatenated together with spaces in between, and a space at the start, for this particular node
        val attributesValue = if(attributes.isEmpty()) "" else
            attributes.map{"${it.key}='${it.value}'"}.joinToString(" ", " ")

        return if(!textValue.isEmpty())
            "$indent<$name$attributesValue>$textValue</$name>"          //if 'textValue' is not empty, it's just a matter of keeping text between tags
        else
            """$indent<$name$attributesValue>${children.joinToString("\n", "\n"){ it. toString(indentation + depth)}}
                |$indent</$name>
            """.trimMargin()
        //if the 'textValue' is empty, it signifies a parent node, so it will have children instead
        //Apart from tags and attributes, children nodes are made to call on their own toString, with indentation increased by 2 count for every level
        //Thus the whole xml is constructed
    }

    override fun toString() = toString(0)
}