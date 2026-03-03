package rasit.kalayci.kotlinadvancefunctions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rasit.kalayci.kotlinadvancefunctions.ui.theme.KotlinAdvanceFunctionsTheme

class MainActivity : ComponentActivity() {

    private var myInt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinAdvanceFunctionsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        myInt?.let {
            val num = it + 1
        }
        val mynum = myInt?.let {
            it + 1
        } ?: 0
// ALSO
        val rasit = Person("rasit", 23)
        val ceren = Person("ceren", 22)
        val omer = Person("omer", 24)
        val elvan = Person("elvan", 27)
        val people = listOf<Person>(rasit, ceren, omer, elvan)
        people.filter { it.age > 25 }.also {
            for (person in it) {
                println(person.name)
            }

            // APPLY
            val adam = Person("Adam", 32).apply {
                this.age + 10}

            // WİTH
            Person("nida",21).apply {
                this.name = "Nida"
            }.also {
                println(it.name)
            }
            val anotherPerson = with(Person("barley",4)){
                this.name = "Barley"
                println(anotherPerson.name)
            }


            }



    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinAdvanceFunctionsTheme {
        Greeting("Android")
    }
}
data class  Person(val name:String,val age:Int){

}