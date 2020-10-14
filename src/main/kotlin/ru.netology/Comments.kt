package ru.netology

data class Comments(
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true,
    val comments: List<Comment> = emptyList()
){
    val count: Int = comments.size
}