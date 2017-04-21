// Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
// Example:
// Given nums = [-2, 0, 3, -5, 2, -1]
//
// sumRange(0, 2) -> 1
// sumRange(2, 5) -> -1
// sumRange(0, 5) -> -3

/**
 * @param {number[]} nums
 */
var NumArray = function (nums) {
    this.nums = nums;
    this.indexSum = Array()
};

/**
 * @param {number} i
 * @param {number} j
 * @return {number}
 */
NumArray.prototype.sumRange = function (i, j) {
    if (this.indexSum.length != this.nums.length) {
        var sum = 0;
        for (var index = 0; index < this.nums.length; index++) {
            sum += this.nums[index];
            this.indexSum[index] = sum;
        }
    }
    if (j < this.indexSum.length) {
        return this.indexSum[j] - this.indexSum[i] + this.nums[i]
    }
    return 0;
};

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = Object.create(NumArray).createNew(nums)
 * var param_1 = obj.sumRange(i,j)
 */

var nums = [-2, -23, 23, 34, 43, 12, 1]
var obj = new NumArray(nums)
var param_1 = obj.sumRange(0, 1)
console.log(param_1)