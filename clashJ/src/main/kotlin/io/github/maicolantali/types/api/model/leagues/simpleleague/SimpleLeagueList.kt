package io.github.maicolantali.types.api.model.leagues.simpleleague

import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *SimpleLeagueList* model of the Clash of Clans API.
 * The SimpleLeagueList data class stores information about a list of simple leagues.
 *
 * @property items A list of [SimpleLeague] objects representing individual simple leagues.
 * @property paging Information about the paging of the simple league list.
 */
data class SimpleLeagueList(
    val items: List<SimpleLeague>,
    val paging: Paging?,
)
