// You are given a string representing an attendance record for a student. The record only contains the following three characters:
//
// 'A' : Absent.
// 'L' : Late.
// 'P' : Present.
// A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
//
// You need to return whether the student could be rewarded according to his attendance record.
//
// Example 1:
// Input: "PPALLP"
// Output: True
// Example 2:
// Input: "PPALLL"
// Output: False

/**
 * @param {string} s
 * @return {boolean}
 */
var checkRecord = function(s) {
    var ACount = 0;
    var LCount = 0;
    for (var i in s) {
        if (s[i] == 'A') {
            ACount++;
            LCount = 0;
        } else if (s[i] == 'L') {
            LCount++;
        } else {
            LCount = 0;
        }
        if (ACount > 1 || LCount > 2) {
            return false
        }
    }
    return true
};


// Test Case
console.log(checkRecord("LALL"))
