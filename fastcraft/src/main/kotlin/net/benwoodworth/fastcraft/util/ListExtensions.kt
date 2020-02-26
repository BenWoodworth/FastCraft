package net.benwoodworth.fastcraft.util

fun <T> List<List<T>>.getPermutations(): Sequence<List<T>> = sequence {
    val options = this@getPermutations
    if (options.isEmpty() || options.any { it.isEmpty() }) {
        return@sequence
    }

    val curPerm = MutableList(options.size) { i -> options[i].first() }
    val curPermIndices = IntArray(options.size)

    yield(curPerm)
    var rollingOver: Boolean
    while (true) {
        // Increment curPermutationIndices
        rollingOver = true
        options.forEachIndexed { i, option ->
            if (rollingOver) {
                // Increment current index
                if (curPermIndices[i] >= option.lastIndex) {
                    if (i == options.lastIndex) {
                        // Finished iterating permutations
                        return@sequence
                    } else {
                        // Continue rollover
                        curPermIndices[i] = 0
                        curPerm[i] = option.first()
                    }
                } else {
                    // Finish rollover
                    curPerm[i] = option[++curPermIndices[i]]
                    rollingOver = false
                }
            }
        }

        yield(curPerm)
    }
}
