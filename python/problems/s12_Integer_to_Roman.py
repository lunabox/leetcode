class Solution:
    # @return a string
    def intToRoman(self, num):
        result = ""
        intArray = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        romanArray = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
        intArrayLength = len(intArray)
        for i in range(intArrayLength):
            while num >= intArray[i]:
                result += romanArray[i]
                num -= intArray[i]
        return result
