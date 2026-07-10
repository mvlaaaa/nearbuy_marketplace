package com.example.nearbuymarketplace.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class LocalItem(
    val title: String = "",
    val price: Double = 0.0,
    val desc: String = "",
    val sellerId: String = ""
)

class MarketplaceViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance().getReference("items")
    val items = mutableStateListOf<LocalItem>()

    var selectedItem by mutableStateOf<LocalItem?>(null)

    init {
        listenForItems()
    }

    fun addItem(title: String, price: Double, desc: String) {
        val itemId = database.push().key ?: return
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"
        val newItem = LocalItem(title, price, desc, currentUserId)
        database.child(itemId).setValue(newItem)
    }

    private fun listenForItems() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                items.clear()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(LocalItem::class.java)
                    if (item != null) {
                        items.add(item)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}