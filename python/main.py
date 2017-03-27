# coding:utf8
from problems.s405_Convert_a_Number_to_Hexadecimal import Solution


if __name__ == '__main__':
    s = Solution()
    num = list()
    num.extend([2, 3, 4, 15])
    #     print s.twoSum(num, 17)

    print s.toHex(1000) #([[0, 1, 0, 0], [1, 1, 1, 0], [0, 1, 0, 0], [1, 1, 0, 0]])
