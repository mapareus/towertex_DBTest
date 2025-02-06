package com.towertex.dbtest.test

fun main() {
    println("Hello, World!")
}

class MyStructure1 private constructor(
    val str: String
) {
    companion object {
        fun String.toMyStructure(): MyStructure1 = MyStructure1(this)
    }
}

class MyStructure2: MutableList<Data> by mutableListOf() {
    fun addNumber(number: Int) {
        add(Data.DNumber(number))
    }

    fun addOperator(operator: MyOperator2) {
        add(Data.DOperator(operator))
    }

    fun count(): Int = zipWithNext().foldIndexed((first() as Data.DNumber).data) { ind, acc, pair ->
        when {
            ind % 2 == 0 -> acc
            else -> {
                val operator = pair.first as Data.DOperator
                val number = pair.second as Data.DNumber
                operator.data.doOperation(acc, number.data)
            }
        }
    }
}

sealed class Data{
    data class DNumber(val data: Int): Data()
    data class DOperator(val data: MyOperator2): Data()
}

sealed class MyOperator2(delegate: Operation): Operation by delegate {
    object PLUS: MyOperator2(Operation { a, b -> a + b })
    object MINUS: MyOperator2(Operation { a, b -> a - b })
    object MULTIPLY: MyOperator2(Operation { a, b -> a * b })
}

fun interface Operation {
    fun doOperation(a: Int, b: Int): Int
}