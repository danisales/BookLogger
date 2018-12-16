package br.ufpe.cin.dso.booklogger.model

data class Book (val id: String = "",
            val title: String = "",
            val author: String = "",
            val publisher: String = "",
            val thumbnail: String? = null,
            val borrowed: Boolean = false,
            val status: String? = null)