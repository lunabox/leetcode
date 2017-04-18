// We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
//
// Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
// Example:
// Input: 28
// Output: True
// Explanation: 28 = 1 + 2 + 4 + 7 + 14
// Note: The input number n will not exceed 100,000,000. (1e8)

/**
 * @param {number} num
 * @return {boolean}
 */
var checkPerfectNumber = function(num) {
    var sum = 1;
    for (var i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0 && num / i != i) {
            sum = sum + i + num / i;
        }
    }
    if (num > 1 && sum == num) {
        return true
    }
    return false
};

// test case
console.log(checkPerfectNumber(1))