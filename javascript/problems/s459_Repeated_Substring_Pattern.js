// Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
//
// Example 1:
// Input: "abab"
//
// Output: True
//
// Explanation: It's the substring "ab" twice.

/**
 * @param {string} s
 * @return {boolean}
 */
var repeatedSubstringPattern = function (s) {
    for (var i = Math.floor(s.length / 2); i >= 1; i--) {
        if (s.length % i == 0) {
            var m = Math.floor(s.length / i)
            var subStr = s.substr(0, i)
            var ss = ''
            for (var j = 0; j < m; j++) {
                ss += subStr
            }
            if (ss == s) {
                return true
            }
        }
    }
    return false
};


// Test Case
console.log(repeatedSubstringPattern('abab'))
console.log(repeatedSubstringPattern('aba'))
console.log(repeatedSubstringPattern('abcabcabcabc'))
console.log(repeatedSubstringPattern('aaaaaaaaaaaaaaaaaaaaa'))