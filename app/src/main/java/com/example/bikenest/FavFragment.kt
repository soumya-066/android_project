package com.example.bikenest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavFragment : Fragment() {
    lateinit var FavouriteRecycler:RecyclerView
    lateinit var favouritetab:androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_fav, container, false)
        // Inflate the layout for this fragment
        FavouriteRecycler=view.findViewById(R.id.FavouriteRecycler)
        favouritetab=view.findViewById(R.id.favouritetab)


        (activity as AppCompatActivity).setSupportActionBar(favouritetab)
        (activity as AppCompatActivity).supportActionBar?.title="Favourites"
        lateinit var layoutManager: RecyclerView.LayoutManager
        lateinit var bikerecycleradapter: MyAdapter
        layoutManager = LinearLayoutManager(activity)
        val db = Firebase.firestore
        val dataList = arrayListOf<BikeModel>()
        ////////////////////////////////////////////////


        db.collection("User").document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("Favourites")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("Name") ?: ""
                    val Brand = document.getString("Brand") ?: ""
                    val price = document.getString("Price") ?: ""
                    val engine = document.getString("Engine") ?: ""
                    val description = document.getString("Details") ?: ""
                    val mileage = document.getString("Mileage") ?: ""
                    val Power = document.getString("Power") ?: ""
                    val imageUrl = document.getString("Imageurl") ?: ""
                    val myData = BikeModel(name,Brand,price,engine,description,mileage,Power,imageUrl)
                    dataList.add(myData)
                }

                bikerecycleradapter = MyAdapter(activity as Context ,dataList)
                FavouriteRecycler.adapter=bikerecycleradapter
                FavouriteRecycler.layoutManager=layoutManager

//                Bikerecycle.addItemDecoration(
//                    DividerItemDecoration(Bikerecycle.context,
//                        (layoutManager as LinearLayoutManager).orientation
//                    )
//                )
            }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse: BikeModel =
                    dataList.get(viewHolder.adapterPosition)

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                dataList.removeAt(viewHolder.adapterPosition)
                ////Below line is to remove Item from Database  Favourite////
                db.collection("User").document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("Favourites").document(deletedCourse.name).delete()


                // below line is to notify our item is removed from adapter.
                bikerecycleradapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                Snackbar.make(FavouriteRecycler, "Deleted " + deletedCourse.name,Snackbar.LENGTH_SHORT)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            dataList.add(position, deletedCourse)

                            // below line is to notify item is
                            // added to our adapter class.
                            bikerecycleradapter.notifyItemInserted(position)
                        }).show()
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(FavouriteRecycler)

        /////////////////////////////////////////////////

        return view
    }


}