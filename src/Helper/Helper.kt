package Helper

import Models.*
import kotlin.math.pow
import kotlin.math.sqrt

fun newMatrix(count: Int): Array<DoubleArray> {
    return Array(count){ DoubleArray(count) }
}

fun initNewMatrix(count: Int): Array<DoubleArray> {
    var matrix: Array<DoubleArray> = Array(count) { DoubleArray(count) }

    for (i in 0..count-1) {
        matrix[i][i] = 0.0;
    }

    return matrix
}

fun getDistance(node1: Node, node2: Node): Double{
    return sqrt((node1.x-node2.x).pow(2)+ (node1.y-node2.y).pow(2))
}
