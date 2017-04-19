// Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
//
//     Subscribe to see which companies asked this question.Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
//
//     Subscribe to see which companies asked this question.

/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
var containsNearbyDuplicate = function (nums, k) {
    var hash = {}
    for (var i in nums) {
        if (!!hash[nums[i]] && i - hash[nums[i]] <= k) {
            return true
        }
        hash[nums[i]] = i
    }
    return false
};


// Test case
console.log(containsNearbyDuplicate([11, 10, 11], 1))