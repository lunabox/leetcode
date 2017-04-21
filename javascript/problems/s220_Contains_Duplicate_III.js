// Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
//
//     Subscribe to see which companies asked this question.


/**
 * @param {number[]} nums
 * @param {number} k
 * @param {number} t
 * @return {boolean}
 */
var containsNearbyAlmostDuplicate = function (nums, k, t) {
    var save = {}
    var i, j = 0
    for (var n in nums) {
        console.log(nums[n])
    }
};

// Test case
console.log(containsNearbyAlmostDuplicate([1, 2, 3, 4], 2, 1))