// Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
//
// Example:
//     For num = 5 you should return [0,1,1,2,1,2].
//

/**
 * @param {number} num
 * @return {number[]}
 */
var countBits = function(num) {
    var result = Array()
    result.push(0)
    var n = 1;
    for (var i = 1; i <= num; i++) {
        if (n == i) {
            result.push(1)
            n *= 2
        } else if (i < n) {
            var m = result[n / 2] + result[i - n / 2]
            result.push(m)
        }
    }
    return result
};

// Test case
var count_one = function (n) {
    var count = 0;
    for (var i = 0; i < 32; i++) {
        count += (n & 1)
        n >>= 1
    }
    return count;
};

var arr = countBits(100)
for (var i in arr) {
    var ones = count_one(i)
    console.log(i, arr[i], ones, arr[i] == ones)
}
