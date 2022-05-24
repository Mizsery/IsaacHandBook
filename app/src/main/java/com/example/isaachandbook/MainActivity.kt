package com.example.isaachandbook

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
        var adapter: MyAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener (this)

        var list=ArrayList<ListItem>()

        list.addAll(fillArras(resources.getStringArray(R.array.characters),
        resources.getStringArray(R.array.characters_content),
        getImageId(R.array.characters_img)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter= MyAdapter(list, this)
        rcView.adapter=adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){

           R.id.id_characters ->
           {
               Toast.makeText(this, "Категория: персонажи", Toast.LENGTH_SHORT).show()
               adapter?.updateAdapter(
                   fillArras(resources.getStringArray(R.array.characters),
                       resources.getStringArray(R.array.characters_content),
                       getImageId(R.array.characters_img))as ArrayList<ListItem>)
           }

           R.id.id_monsters ->
           {
               Toast.makeText(this, "Категория: монстры", Toast.LENGTH_SHORT).show()
               adapter?.updateAdapter(
                   fillArras(resources.getStringArray(R.array.monsters),
                       resources.getStringArray(R.array.monsters_content),
                       getImageId(R.array.monsters_img))as ArrayList<ListItem>)
           }

           R.id.id_bosses ->
           {
               Toast.makeText(this, "Категория: боссы", Toast.LENGTH_SHORT).show()
               adapter?.updateAdapter(
                   fillArras(resources.getStringArray(R.array.bosses),
                       resources.getStringArray(R.array.bosses_content),
                       getImageId(R.array.bosses_img))as ArrayList<ListItem>)
           }
           R.id.id_trinkets ->
           {
               Toast.makeText(this, "Категория: брелки", Toast.LENGTH_SHORT).show()
               adapter?.updateAdapter(
                   fillArras(resources.getStringArray(R.array.trinkets),
                       resources.getStringArray(R.array.trinkets_content),
                       getImageId(R.array.trinkets_img))as ArrayList<ListItem>)
           }
       }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun fillArras(titleArray:Array<String>,contentArray: Array<String>,imageArray: IntArray):List<ListItem>
    {
        var listItemArray = ArrayList<ListItem>()
        for(n in 0..titleArray.size-1)
        {
            var listItem = ListItem(imageArray[n],titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }


    fun getImageId(imageArrayId:Int):IntArray
    {
        var tArray:TypedArray=resources.obtainTypedArray(imageArrayId)
        var count = tArray.length()
        val ids=IntArray(count)
        for(i in ids.indices){
            ids[i]=tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }
}
