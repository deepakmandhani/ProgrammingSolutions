package com.droidrank.sample;

/**
 * Created by deepak.mandhani on 14/02/18.
 */


class Node {
    int num;
    Node left;
    Node right;
}

public class DistanceCompleteBinary {

    /**
     *    Given a binary tree and two numbers (which represent two nodes in a tree) as input and are at the same level
     * @param node-     binary tree root
     * @param n1-       number1 of tree
     * @param n2-       number2 of tree
     * @return          distance between n1 & n2
     */
    int countAdjascentDistant(Node node, int n1, int n2) {
        int nodeCount1 = findNodeDistance(node, n1, 0);
        int nodeCount2 = findNodeDistance(node, n2, 0);
        return nodeCount2 > nodeCount1 ? nodeCount2 - nodeCount1 : nodeCount1 - nodeCount2;
    }

    /**
     *
     * @param node-     binary tree node
     * @param n-        number to be searsh
     * @param index-    index of node to be processed
     * @return          index of the number searched
     */
    public int findNodeDistance(Node node, int n, int index) {

        if (node == null)
            return 0;

        if (node.num == n)
            return index;

        int left = 0;
        left = findNodeDistance(node.left, n, 2 * index + 1);
        if (left > 0) {
            return left;
        }

        int right = 0;
        right = findNodeDistance(node.right, n, 2 * index + 2);
        if (right > 0) {
            return right;
        }

        return 0;
    }
}
