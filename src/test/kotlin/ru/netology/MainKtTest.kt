package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun main_add() {
        val wall = WallService
        val post = Post()

        wall.add(post)
        wall.add(post.copy(date = 1))
        val lastPost = wall.add(post.copy(date = 2))
        wall.clear()

        assertNotEquals(0, lastPost.id)
    }

    @Test
    fun main_update_existing() {
        val wall = WallService
        val post = Post()

        wall.add(post)
        wall.add(post.copy(date = 1))
        wall.add(post.copy(date = 2))

        val updated = wall.update(post.copy(id = 1, text = "new text"))
        wall.clear()

        assertTrue(updated)
    }

    @Test
    fun main_update_notExisting() {
        val wall = WallService
        val post = Post()

        wall.add(post)
        wall.add(post.copy(date = 1))
        wall.add(post.copy(date = 2))

        val updated = wall.update(post.copy(id = 3, text = "new text"))
        wall.clear()

        assertFalse(updated)
    }

    @Test
    fun main_equalPosts() {
        val post = Post()
        val clonePost = post.copy()

        assertEquals(post, clonePost)
    }

    @Test
    fun main_hashPost() {
        val hash = Post().hashCode()

        assertNotEquals(0, hash)
    }

    @Test(expected = PostNotFoundException::class)
    fun main_createCommentThrowsException() {
        WallService.createComment(Comment(), Int.MIN_VALUE)
    }

    @Test
    fun main_createComment() {
        val wall = WallService
        val post = wall.add(Post())

        val result = wall.createComment(Comment(), post.id)

        wall.clear()

        assertTrue(result)
    }
}