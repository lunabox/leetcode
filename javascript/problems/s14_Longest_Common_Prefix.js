// Write a function to find the longest common prefix string amongst an array of strings.

/**
 * @param {string[]} strs
 * @return {string}
 */
var longestCommonPrefix = function(strs) {
    if (strs.length == 0) {
        return "";
    }
    var pre = strs[0]
    for (var i = 1; i < strs.length; i++) {
        var j = 0;
        for (j = 0; j < pre.length && j < strs[i].length; j++) {
            if (pre[j] != strs[i][j]) {
                break;
            }
        }
        pre = pre.substr(0, j)
    }
    return pre;
};


// Test Case
console.log(longestCommonPrefix(["aba", "abc", "abbbbbb"]))