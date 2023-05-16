package com.example.myapplication.model

data class AllData(
    val industry : MutableList<Industry>,
    val expertise : MutableList<Expertise>,
    val countries : MutableList<Countries>
)
