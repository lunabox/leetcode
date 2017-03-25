import string

class Solution(object):
    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        i = 0
        j = len(numbers) - 1
        while(numbers[i] + numbers[j] != target):
            if numbers[i] + numbers[j] > target:
                j -= 1
            elif numbers[i] + numbers[j] < target:
                i += 1
        return list([i + 1, j + 1])

    def canConstruct(self, ransomNote, magazine):
        """
        :type ransomNote: str
        :type magazine: str
        :rtype: bool
        """
        letters = {}
        for l in magazine:
            if letters.has_key(l):
                letters[l] = letters[l] + 1
            else:
                letters[l] = 1
        for ra in ransomNote:
            if letters.has_key(ra):
                letters[ra] = letters[ra] - 1
            else:
                letters[ra] = -1
        for k in letters.keys():
            if letters[k] < 0:
                return False
        return True
        
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        result = list()
        uni_num = {}
        for n in nums1:
            uni_num[n] = 1
        for n in nums2:
            if uni_num.has_key(n):
                if result.count(n) == 0:
                    result.append(n)
        return result
    
    def firstUniqChar(self, s):
        """
        :type s: str
        :rtype: int
        """
        letters = dict.fromkeys(string.ascii_lowercase, 0)
        count = 0
        for ch in s:
            letters[ch] += 1
        for i in range(len(s)):
            if letters[s[i]] == 1:
                return i
        return -1
        
    def titleToNumber(self, s):
        """
        :type s: str
        :rtype: int
        """
        result = 0
        sq = 0
        for i in range(len(s) - 1, -1, -1):
            result += (ord(s[i]) - ord('A') + 1) * (26 ** sq)
            sq += 1
        return result
    
    def convertToBase7(self, num):
        """
        :type num: int
        :rtype: str
        """
        result = ''
        positive = 1
        if num < 0:
            positive = -1
            num = -num
        while (num >= 7):
            result = str(num % 7) + result
            num /= 7
        if num >= 0:
            result = str(num) + result
        if positive < 0:
            result = '-' + result
        return result
    
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        count = 1
        cur = nums[0]
        for n in nums[1:]:
            if n == cur:
                count += 1
            else:
                count -= 1
                if count == 0:
                    cur = n
                    count = 1
        return cur
    
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        hasMid = 0
        result = 0
        letters = dict.fromkeys(string.lowercase + string.uppercase, 0)
        for ch in s:
            letters[ch] += 1
        for _, v in letters.items():
            if v % 2 == 1:
                hasMid = 1
            if v > 1:
                result += (v / 2 * 2)
        result += hasMid
        return result
    
    def reverseStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        reverse = -1
        result = ''
        for i in range(0, len(s), k):
            if i + k < len(s):
                result += s[i : i + k][::reverse]
            else:
                result += s[i : len(s)][::reverse]
            reverse = -reverse
        return result
        
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        result = list()
        count_num = {}
        for n in nums1:
            if count_num.has_key(n):
                count_num[n] += 1
            else:
                count_num[n] = 1
        for n in nums2:
            if count_num.has_key(n) and count_num[n] > 0:
                count_num[n] -= 1
                result.append(n)
        return result
    
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        result = []
        result.append(1)
        result.append(2)
        for i in range(2, n):
            result.append(result[i - 1] + result[i - 2])
        return result.pop()
    
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        resultMax = 0
        curMax = 0
        for n in nums:
            curMax += n
            if curMax > 0:
                if resultMax < curMax:
                    resultMax = curMax
            else:
                curMax = 0
        if resultMax == 0:
            resultMax = -0xFFFFFFFF
            for n in nums:
                if n > resultMax:
                    resultMax = n
        return resultMax
    
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        resultMax = nums[0]
        curMax = nums[0]
        curMin = nums[0]
        for i in range(1, len(nums)):
            n = nums[i]
            temp = curMax
            curMax = max([max([temp * n, n]), curMin * n])
            curMin = min([min([temp * n, n]), curMin * n])
            resultMax = max([resultMax, curMax])
        return resultMax
    
    def isUgly(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num <= 0:
            return False
        while num >= 2:
            if num % 5 == 0:
                num /= 5
            elif num % 3 == 0:
                num /= 3
            elif num % 2 == 0:
                num /= 2
            else:
                return False
        if num == 1:
            return True
        
    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        n &= 0xFFFFFFFF
        count = 0
        while n > 0:
            count += (n & 0x1)
            n >>= 1
        return count
    
    def countSegments(self, s):
        """
        :type s: str
        :rtype: int
        """
        count = 0
        seqLen = 0
        for c in s:
            if c != ' ':
                seqLen += 1
                continue
            elif seqLen > 0:
                count += 1
                seqLen = 0
        if seqLen > 0:
            return count + 1
        return count
            
        
if __name__ == '__main__':
    s = Solution();
    num = list()
    num.extend([2, 3, 4, 15])
#     print s.twoSum(num, 17)
    
    print s.countSegments("love live! mu'sic forever")
    
    
    
