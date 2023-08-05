package com.clashj.model.common

/**
 * Represents the *Paging* model of the Clash of Clans API.
 *
 * @property cursors The cursors for pagination, containing references to the `before` and `after` elements in a list.
 * It provides navigation to the previous and next pages of results.
 */
data class Paging(
    val cursors: Cursors
)

/**
 * Represents the *Cursors* model of the Clash of Clans API.
 * Cursors are used for pagination, providing references to the "before" and "after" elements in a list.
 *
 * @property before The cursor pointing to the element before the current page in the list.
 * It is null for the first page of results.
 * @property after The cursor pointing to the element after the current page in the list.
 * It is null for the last page of results.
 */
data class Cursors(
    val before: String?,
    val after: String?
)