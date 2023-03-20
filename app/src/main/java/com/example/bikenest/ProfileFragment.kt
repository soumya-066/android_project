package com.example.bikenest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var Username:TextView
    lateinit var email:TextView
    lateinit var Password:TextView

    var name: String? = ""
    var mail: String? = ""
    var Pass: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Username=view.findViewById(R.id.Username)
        email=view.findViewById(R.id.email)
        Password=view.findViewById(R.id.Password)

//        Username.isFocusable=false
//        email.isFocusable=false
//        Password.isFocusable=false

        val docRef = FirebaseFirestore.getInstance().collection("User")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
        docRef.get().addOnSuccessListener {
            //makeText(activity, "$it", Toast.LENGTH_LONG).show()
            Log.i("fetch", "${it.getString("Name")}")
            if (it.exists()) {
                name = it.getString("Name")
                Username.setText(" "+"$name")
            }
            Log.i("fetch", "${it.getString("Mail")}")
            if (it.exists()) {
               mail = it.getString("Mail").toString()
                email.setText(" "+"$mail")
            }
            Log.i("fetch", "${it.getString("Password")}")
            if (it.exists()) {
                Pass = it.getString("Password").toString()
                Password.setText(" "+"$Pass")
            }

        }


        Password.setText("$Pass")

    }


}