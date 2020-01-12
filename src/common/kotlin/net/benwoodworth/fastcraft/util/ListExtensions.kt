package net.benwoodworth.fastcraft.util

fun <T> List<List<T>>.forEachPermutation(action: (permutation: List<T>) -> Unit) {
    val options = this
    if (options.isEmpty() || options.any { it.isEmpty() }) {
        return
    }

    val curPerm = MutableList(options.size) { i -> options[i].first() }
    val curPermIndices = IntArray(options.size)

    action(curPerm)
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
                        return
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

        action(curPerm)
    }
}
