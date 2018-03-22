class MyTest(val bool: Boolean) {
    override fun toString(): String {
        return "MyTest($bool)"
    }
}

fun main(args: Array<out String>) {
    val test = ArrayList<MyTest>()

    test.add(MyTest(false))
    test.add(MyTest(true))
    test.add(MyTest(false))
    test.add(MyTest(true))

    test.sortBy { !it.bool }
    println(test)
}