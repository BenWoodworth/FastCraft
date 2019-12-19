package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.events.HandlerSet
import kotlin.math.ceil
import kotlin.math.max

class PageCollection<T>(val pageSize: Int) {
    val onChange: HandlerSet<PageCollection<T>> = HandlerSet()

    var pageNumber: Int = 1
        private set

    var pageCount: Int = 1
        private set

    var content: List<T> = emptyList()
        private set

    private var pageStartIndex: Int = 0

    fun setContent(content: List<T>) {
        this.content = content
        pageNumber = 1
        pageCount = max(1, ceil(content.size / pageSize.toDouble()).toInt())

        onChange.notifyHandlers(this)
    }

    fun setPage(page: Int) {
        val newPage = page.coerceIn(1..pageCount)
        if (newPage != pageNumber) {
            pageNumber = newPage
            pageStartIndex = (newPage - 1) * pageSize

            onChange.notifyHandlers(this)
        }
    }

    fun getPageItem(index: Int): T? {
        return content.getOrNull(pageStartIndex + index)
    }
}
