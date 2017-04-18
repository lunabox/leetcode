/**
 * @param {number} n - a positive integer
 * @return {number} - a positive integer
 */
var reverseBits = function(n) {
    n = (n & 0xFFFFFFFF) >>> 0;
    for (var i = 0; i < 16; i++) {
        var low = (n & (1 << i)) != 0
        var high = (n & (1 << 31 - i)) != 0 // 有可能结果是负数，不为0，那一位则是1
        if (low ^ high) {
            // 异或，说明不相同
            if (low) {
                n = n & ~(1 << i)
            } else {
                n = n | (1 << i)
            }
            if (high) {
                n = n & ~(1 << 31 - i)
            } else {
                n = n | (1 << 31 - i)
            }
        }
    }
    return n >>> 0;
};


// test case
console.log(reverseBits(43261596))
console.log(reverseBits(1))
console.log(reverseBits(2147483648))