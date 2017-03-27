class Solution(object):
    def findWords(self, words):
        """
        :type words: List[str]
        :rtype: List[str]
        """
        lines = ['qwertyuiop', 'asdfghjkl', 'zxcvbnm']
        def find(ch):
            for n in range(0, len(lines)):
                if lines[n].find(ch) > -1:
                    return n
            return -1
        
        def isSameLine(word):
            index = -1
            for ch in word:
                if index == -1:
                    index = find(ch)
                elif index != find(ch):
                    return False
            return True
        
        result = list()
        for word in words:
            if isSameLine(word.lower()):
                result.append(word)
        return result