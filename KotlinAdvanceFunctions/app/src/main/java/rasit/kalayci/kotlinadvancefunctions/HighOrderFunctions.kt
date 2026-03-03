package rasit.kalayci.kotlinadvancefunctions

fun main (){
   val myNumlist = listOf(1,2,3,4,5,6,7,8,9)
    myNumlist.filter { num -> num < 5 }
  val squarlist = myNumlist.map { num -> num * num }
    println(squarlist)

    val filtermapcombined = myNumlist.filter { num -> num < 5 }
        .map { num -> num * num }
    println(filtermapcombined)
    val musicians = listOf<musicians>(
        musicians("James",60,"male"),
        musicians("Lars",55,"male"),
        musicians("Kirk",50,"male"),
        musicians("Rob",40,"male"),
        musicians("Josh",30,"male") )
    val filteredexample = musicians.filter { musicians -> musicians.age < 60 && musicians.gender == "male" }
        .map { musicians -> musicians.name }
   for (musician in filteredexample){
       println(musician)
   }



}
data class musicians(val name:String,val age:Int,val gender:String){

}