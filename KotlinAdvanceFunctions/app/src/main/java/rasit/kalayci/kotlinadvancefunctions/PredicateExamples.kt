package rasit.kalayci.kotlinadvancefunctions

fun main(){
    val myNumlist = listOf(1,2,3,4,5,6,7,8,9)


    val allCheck = myNumlist.all { num -> num > 5 }
    println(allCheck)

    val anycheck = myNumlist.any { num -> num > 5 }
    println(anycheck)

val totalcount = myNumlist.count { it > 5 }
    println(totalcount)
 val findnum = myNumlist.find { it > 5 }
    println(findnum)
val findlastnum = myNumlist.findLast { it > 5 }
    println(findlastnum)

    // predicate böyle veriyoruz
val myPredicate = {num:Int -> num > 5}
    val allcheck = myNumlist.all(myPredicate)


}