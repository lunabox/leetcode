// Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
//
// Example 1:
// Input: "Let's take LeetCode contest"
// Output: "s'teL ekat edoCteeL tsetnoc"
// Note: In the string, each word is separated by single space and there will not be any extra space in the string.

/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function(s) {
    var words = s.split(' ')
    var result = ''
    var reverse = function (str) {
        return str.split("").reverse().join("")
    }
    for (var i in words) {
        if (i == words.length - 1) {
            result += reverse(words[i])
        } else {
            result += reverse(words[i]) + ' '
        }
    }
    return result
};

// Test Case
console.log(reverseWords("Let's take LeetCode contest"))