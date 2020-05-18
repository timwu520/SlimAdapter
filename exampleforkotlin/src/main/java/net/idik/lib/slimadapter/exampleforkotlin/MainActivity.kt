package net.idik.lib.slimadapter.exampleforkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.idik.lib.slimadapter.SlimAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy<RecyclerView> {
        (findViewById(R.id.recyler_view) as RecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private val adapter by lazy {
        SlimAdapter.create()
                .register<String>(R.layout.item_string) { data, injector, position ->
                    Log.i(MainActivity::class.java.simpleName, "position : $position")
                    injector.text(R.id.text, data)
                }
                .register<User>(R.layout.item_user) { data, injector, position ->
                    Log.i(MainActivity::class.java.simpleName, "position : $position")
                    injector.text(R.id.name, data.name)
                            .text(R.id.age, data.age.toString())
                            .clicked(R.id.name) {
                                Toast.makeText(this@MainActivity, "click user name", Toast.LENGTH_LONG).show()
                            }
                }
                .register<Int>(R.layout.item_interger) { data, injector, position ->
                    Log.i(MainActivity::class.java.simpleName, "position : $position")
                    injector.text(R.id.text, data.toString())
                            .longClicked(R.id.text) {
                                Toast.makeText(this@MainActivity, "longclick int", Toast.LENGTH_LONG).show()
                                true
                            }
                }
                .registerDefault(R.layout.item_string) { data, injector, position ->
                    Log.i(MainActivity::class.java.simpleName, "position : $position")
                    injector.text(R.id.text, data.toString())
                            .clicked(R.id.text) {
                                Toast.makeText(this@MainActivity, "click default", Toast.LENGTH_LONG).show()
                            }

                }
                .attachTo(recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter.updateData(data).notifyDataSetChanged()
    }

    companion object {

        private val data = ArrayList<Any>()

        init {
            data.add(666666)
            data.add("hello")
            data.add(",")
            data.add(User("iDIK", 27))
            data.add("world")
            data.add("with")
            data.add("kotlin")
            data.add("!")
            data.add(34234)
            data.add(666669L)
        }
    }
}
