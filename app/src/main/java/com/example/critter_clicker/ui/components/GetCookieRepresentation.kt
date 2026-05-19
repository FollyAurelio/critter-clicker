package com.example.critter_clicker.ui.components

fun getCookieRepresentation(
    cookies: Long
): Pair<String, String> {
    if (cookies >= 1_000_000_000_000_000_000) {
        return Pair(
            "Quin",
            "%.3f".format(cookies / 1_000_000_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000_000_000) {
        return Pair(
            "Q",
            "%.3f".format(cookies / 1_000_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000_000) {
        return Pair(
            "T",
            "%.3f".format(cookies / 1_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000) {
        return Pair(
            "B",
            "%.3f".format(cookies / 1_000_000_000f)
        )
    } else if (cookies >= 1_000_000) {
        return Pair(
            "M",
            "%.3f".format(cookies / 1_000_000f)
        )
    } else if (cookies >= 1_000) {
        return Pair(
            "K",
            "%.3f".format(cookies / 1_000f)
        )
    }

    return Pair(
        "",
        cookies.toString()
    )
}