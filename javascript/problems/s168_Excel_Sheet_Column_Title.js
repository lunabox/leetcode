// Given a positive integer, return its corresponding column title as appear in an Excel sheet.
//
// For example:
//
// 1 -> A
// 2 -> B
// 3 -> C
//     ...
// 26 -> Z
// 27 -> AA
// 28 -> AB

/**
 * @param {number} n
 * @return {string}
 */
var convertToTitle = function(n) {
    var result = '';
    while (n > 0) {
        var code = (n - 1) % 26 + 1;
        result = String.fromCharCode(64 + code) + result;
        n = Math.floor((n - 1) / 26);
    }
    return result;
};


// Test Case
for (var i = 1; i < 100; i++) {
    console.log(convertToTitle(i))
}
