package list

private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var r1 = l1
    var r2 = l2
    var head: ListNode? = null
    var tail: ListNode? = null
    var carry = 0
    while (r1 != null || r2 != null) {
        val n1 = r1?.`val` ?: 0
        val n2 = r2?.`val` ?: 0
        val sum = n1 + n2 + carry
        if (head == null) {
            tail = ListNode(sum % 10)
            head = tail
        } else {
            tail!!.next = ListNode(sum % 10)
            tail = tail.next
        }
        carry = sum / 10 // 进位除以10即可，得到十位
        if (r1 != null) r1 = r1.next
        if (r2 != null) r2 = r2.next
    }
    if (carry > 0) {
        tail!!.next = ListNode(carry)
    }
    return head
}