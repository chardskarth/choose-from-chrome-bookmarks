//#!/usr/bin/env kotlin -Xplugin=/Users/chardskarth/.local/share/mise/installs/kotlin/2.1.21/kotlinc/lib/kotlinx-serialization-compiler-plugin.jar # setting up the kotlin environment
@file:DependsOn("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.30")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

// @file:CompilerOpts("")

// Kotlin code.
//
//

import kotlinx.serialization.*
import java.io.File
import kotlinx.serialization.json.*

val chromeBookmarksFilePath = args[0]

val chromeFileContents = File(chromeBookmarksFilePath)
    .inputStream()
    .readBytes()
    .toString(Charsets.UTF_8)

val browser = Json.decodeFromString<JsonObject>(chromeFileContents)
val bookmarkContents = browser["roots"]?.jsonObject?.get("bookmark_bar")?.jsonObject?.get("children").toString()
val json = Json { ignoreUnknownKeys = true
}
val bookmarkBarItems: List<BookmarkBarItem> = json.decodeFromString(bookmarkContents)

bookmarkBarItems.flatMap {
    flattenByFolder(it.name, it)
}.forEach {
    println("${it.name} >> ${it.url}")
}

//println(json.encodeToString(bookmarkBarItems))
//println(json.encodeToString(flattenedByFolder))

fun flattenByFolder(key: String, bookmarkBarItem: BookmarkBarItem): List<FlattenedByFolder> {
    return bookmarkBarItem.getBookmarks("${key.smartTrailing(".")}${bookmarkBarItem.name}") + bookmarkBarItem.getFolders().flatMap {
        flattenByFolder("$key.${it.name}", it)
    }
}

fun String.smartTrailing(toAppend: String) = if (this != "") "$this$toAppend" else this

@Serializable
data class BookmarkBarItem(
    val children: List<BookmarkBarItem>? = null,
    val url: String? = null,
    val name: String,
    val type: String,

) {
    private val isFolder = type == "folder"
    fun getBookmarks(key: String) = children?.filter {
        it.isFolder.not()
    }?.map {
      FlattenedByFolder("$key.${it.name}", it.url!!)
    } ?: listOf()

    fun getFolders() = children?.filter {
        it.isFolder
    } ?: listOf()

}

@Serializable
data class FlattenedByFolder(
    val name: String,
    val url: String,
)


