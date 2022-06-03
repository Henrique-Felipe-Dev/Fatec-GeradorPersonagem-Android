package com.example.sql.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class Classe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tipo: String,
    val arma: String,
    val bonusHp: Double,
    val bonusDano: Double
)