// Implement strStr().
//
// Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
// Subscribe to see which companies asked this question.

/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function(haystack, needle) {
    return haystack.indexOf(needle)
};


// Test case
console.log(strStr('asdfasfas', 'ss'))
console.log(strStr("", ""))