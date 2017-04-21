// You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
// Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
// You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

/**
 * Definition for isBadVersion()
 *
 * @param {integer} version number
 * @return {boolean} whether the version is bad
 */
var isBadVersion = function (version) {
    return version >= 18;
};


/**
 * @param {function} isBadVersion()
 * @return {function}
 */
var solution = function (isBadVersion) {
    /**
     * @param {integer} n Total versions
     * @return {integer} The first bad version
     */
    return function (n) {
        var left = 1;
        var right = n;
        while (left <= right) {
            var mid = Math.floor((left + right) / 2)
            var first;
            if (mid > 1) {
                first = isBadVersion(mid - 1);
            } else {
                first = false
            }
            var second = isBadVersion(mid);
            if (!first && second) {
                return mid;
            }
            if (first) { // 如果第一个就是badnumber，second也一定是
                right = mid - 1;
            } else if (!second) { // 第二个不是badnumber，第一个也一定不是
                left = mid + 1
            }
        }
        if (isBadVersion(1)) {
            return n;
        }
        return 0;
    };
};


// Test Case
var s = new solution(isBadVersion)
console.log(s(15)) // 5