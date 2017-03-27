class Solution(object):
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = list()
        for i in range(1, n + 1):
            show = ''
            if i % 3 == 0:
                show += 'Fizz'
            if i % 5 == 0:
                show += 'Buzz'
            if show == '':
                show = str(i)
            result.append(show)
        return result