package com.android.test.mangareader.data.model

data class MangaItem(
    val id: Int? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val author: String? = null,
    val genre: String? = null,
    val type: Int? = null,
    val rating: String? = null,
    val chapters: MutableList<String> = mutableListOf(),
    val language: String? = null,
    val description: String? = null,
    val noOfChapters: Int = chapters.size,
    val characters: MutableList<String> = mutableListOf(),
    val createdAt: String? = null,
    val publisher: String? = null
)