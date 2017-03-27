class Solution:
    # @return an integer
    def atoi(self, str):
        result = 0
        try:
            num = str.split()[0]
            length = len(num)
            for i in range(length):
                if not num[i].isdigit() and num[i] not in '-+':
                    num = num[:i]
                    break
            result = int(num)
            if result > 2147483647:
                return 2147483647
            elif result < -2147483648:
                return -2147483648
            return result
        except:
            return 0