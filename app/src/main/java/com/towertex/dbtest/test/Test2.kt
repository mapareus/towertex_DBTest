package com.towertex.dbtest.test

import android.util.Rational

fun main() {
    println("Hello, World!")
}

/*
Record(recordId: Int, recordName: String, price: Int)
RecordOrder(recordId: Int, orderId: Int)
Order(userId: Int, orderId: Int)
User(userId: Int, firstName: String, lastName: String)

SELECT User.firstName, User.lastName, count(*) as COUNT_OF_RECORDS FROM User
INNER JOIN Order ON User.userId = Order.userId
INNER JOIN RecordOrder ON Order.orderId = RecordOrder.orderId
GROUP BY User.userId ORDER BY COUNT_OF_RECORDS DESC LIMIT 1


SELECT count(*)
(SELECT count(*) FROM RecordOrder GROUP BY orderId)

SELECT User.firstName, User.lastName FROM User
WHERE (select count(*) from Order GROUP BY userId) = (select max(*) from Order GROUP BY userId)
 */