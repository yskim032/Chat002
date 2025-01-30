package com.example.test2



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import  java.text.SimpleDateFormat
import java.util.*

class MainActivity: AppCompatActivity(){
   fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}



//fun main() {

/*    val data1: Array<Int> = Array(3, { 0 })*/

/*    println( "${data1[0]}")
    println("${data1.size}")

    for(i in data1.indices){
        println("${data1[i]}")
    }*/

//    for (i in 0..2){
//        print("${data1[i]}")
//    }

//    val data1= arrayOf(Int, 10, 20, 30, 40, 50)
//    for(i in data1.indices){
//        println("${data1[i]}")
//    }
//
//    for(i in 0..3){
//        println("${data1[i]}")
//    }
//

//    var list= listOf(Int, 10, 20, 30, 40, 50)
//    var list2= mutableListOf(Int, 10, 20, 30, 40, 50)
//
//    for(i in list.indices){
//        println("${list[i]}")
//    }
//
//    for(j in list2.indices){
//        println("${list2[j]}")
//    }
//
//    list2.add(60)
//    list2.add(70)
//    list2.add(80)
//
//    for(j in list2.indices){
//        println("${list2[j]}")
//    }

//    var data=10
//
//    val result=if(data>10){
//        true
//    } else {
//        false
//    }
//
//    println("${result}")
//    var data = arrayOf(Int, 10, 20, 30, 40, 50)
//    for ((index, value) in data.withIndex()) {
//        println("${index} : ${value}")
//
//    }

//    class Person(val name: String, val age: Int){
//
//        fun introduce(){
//            println("${name} is ${age} years old")
//        }
//
//        fun introduce2(){
//            println("${name} is NOT ${age} years old")
//        }
//
//    }
//    val person1=Person("sss", 20)
//    person1.introduce()
//
//    val person2=Person("ddd", 30)
//    person2.introduce2()


// =================================================
//class Student(val name: String){
//    var age: Int = 0
//
//    constructor(name: String, age: Int): this(name){
//        this.age = age
//    }
//
//    fun info(){
//        println("name: ${name}, age: ${age}")
//
//    }
//}
//
//    val student1 = Student("sss")
//    val student2 = Student("ddd", 20)
//    student1.info()
//    student2.info()


// =================================================
//class User(name:String) {
//
//    init {
//        println("${name} constructor called")
//    }
//
//    constructor(name:String, age:Int):this(name){
//        println("constructor(name:String, age:Int) called")
//    }
//}
//
//    val user1=User("sss")
//    val user2=User("ddd", 20)

// =================================================
//    open class Super{
//        var superData=10
//        fun superFun(){
//            println("i am superFun : ${superData}")
//        }
//
//    }
//
//    class Sub22:Super()
//
//    val obj=Sub22()
//    obj.superData=20
//    obj.superFun()

    // =================================================
//open class Super{
//       open var someData=10
//        open fun someFun(){
//            println("i am super fun : ${someData}")
//        }
//}
//
//    class Sub2:Super() {
//        override var someData = 20
//        override fun someFun() {
//            println("i am sub2 fun : ${someData}")
//        }
//    }
//
//        val obj=Sub2()
//        obj.someFun()

    // =================================================
//class NonDataClass(val name:String, val email:String, val age:Int)
//    data class DataClass(val name:String, val email:String, val age:Int)
//
//    val non1=NonDataClass("sss", "a@a.com", 10)
//    val non2=NonDataClass("sss", "a@a.com", 10)
//
//    val data1=DataClass("sss", "a@a.com", 10)
//    val data2=DataClass("sss", "a@a.com", 10)
//
//    println(non1.equals(non2))
//    println(data1.equals(data2))
//



    // =================================================
//open class Super{
//    open var data=10
//        open fun some(){
//            println("i am super some() : ${data}")
//        }
//}
//    val obj=object: Super(){
//        override var data=20
//        override fun some(){
//            println("i am object some() : ${data}")
//        }
//    }
//
//    obj.data=30
//    obj.some()
    // =================================================


    // =================================================

    // =================================================
//}





