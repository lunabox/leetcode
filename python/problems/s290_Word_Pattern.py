#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''
class Solution(object):
    def wordPattern(self, pattern, str):
        """
        :type pattern: str
        :type str: str
        :rtype: bool
        """
        words = str.split(' ')
        if len(pattern) != len(words):
            return False
        ch_map = {}
        word_map = {}
        for i in range(len(pattern)):
            ch = pattern[i]
            if ch_map.has_key(ch): # 先正向查询是否匹配
                if ch_map[ch] != words[i]:
                    return False
            else:
                ch_map[ch] = words[i]
            if word_map.has_key(words[i]): #再反向查询是否匹配
                if ch != word_map[words[i]]:
                    return False
            else:
                word_map[words[i]] = ch
        return True