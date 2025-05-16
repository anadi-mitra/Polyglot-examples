def merge_trees(node1, node2):
    if not node1 and not node2:
        return None

    val1 = node1.val if node1 else 0
    val2 = node2.val if node2 else 0

    new_node = nodeFactory.newProxyNode(val1 + val2)

    new_node.left = merge_trees(
        node1.left if node1 else None,
        node2.left if node2 else None
    )
    new_node.mid = merge_trees(
        node1.mid if node1 else None,
        node2.mid if node2 else None
    )
    new_node.right = merge_trees(
        node1.right if node1 else None,
        node2.right if node2 else None
    )

    return new_node


def print_tree(node, indent=""):
    if node is None:
        return
    print(f"value= {indent}{node.val}")
    print_tree(node.left, indent + "  L-")
    print_tree(node.mid, indent + "  M-")
    print_tree(node.right, indent + "  R-")
