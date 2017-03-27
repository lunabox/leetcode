class Solution(object):
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