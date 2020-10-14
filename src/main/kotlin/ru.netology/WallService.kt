package ru.netology

object WallService {
    private val posts = mutableListOf<Post>()
    private val comments = mutableListOf<Comment>()

    fun clear() {
        posts.clear()
        comments.clear()
    }

    fun createComment(comment: Comment, postId: Int): Boolean {
        val post: Post = findPostById(postId)

        val postComments = (post.comments?.comments ?: mutableListOf()) as MutableList<Comment>
        val newComment = comment.copy(id = comments.lastIndex + 1)

        comments += newComment
        postComments += newComment
        update(post.copy(comments = Comments(comments = postComments)))
        return true
    }

    private fun findPostById(postId: Int): Post{
        for(post:Post in posts){
            if (post.id == postId) return post
        }
        throw PostNotFoundException()
    }

    fun add(post: Post): Post {
        val newPost = post.copy(id = posts.lastIndex + 1)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, updatingPost) in posts.withIndex()) {
            if (index == post.id) {
                posts[index] = post.copy(
                    ownerId = updatingPost.ownerId,
                    date = updatingPost.date
                )
                return true
            }
        }
        return false
    }
}