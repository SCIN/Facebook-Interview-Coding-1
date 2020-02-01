76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
Time O(m + n) Space O(m + n)
m and n are the length of string s and t respectively

string minWindow(string s, string t) {
    // edge case: empty string
    // The code won't break even if we dont have this line
    // BUT it will show the applicant is considering edge cases!
    if (s.empty() || t.empty()) return "";
    
    unordered_char<char, int> dict;
    for (char c : t) {
        dict[c]++;
    }
    int slow = 0;
    int count = 0;
    int min_length = INT_MAX;
    int ret = "";
    for (int fast = 0; fast < s.length(); fast++) {
        if (m.find(s[fast]) == m.end()) continue;
        m[fast]--;
        if (m[fast] == 0) count++;
        // contain all char in t, start to move slow pointer
        while (count == dict.size()) {
            if (fast - slow + 1 < min_length) {
                min_length = fast - slow + 1;
                ret = s.substr(slow, fast - slow + 1);
            }
            if (m.find(s[slow]) == m.end()) {
                slow++;
                continue;
            }
            m[s[slow]]++;
            if (m[s[slow]] == 1) {
                count--;
            }
            slow++;
        }
    } 
    return (min_length == INT_MAX) ? "" : ret;
}

public String minWindow(String s, String t) {
    int[] count = new int[128];
    for (char c : t.toCharArray())
        count[c]++;
    String res = "";
    int start = 0, end = 0, len = t.length(), min = s.length();
    while (end < s.length()) {
        if (count[s.charAt(end++)]-- > 0)   len--; // valid
        while (len == 0) {
            if (end - start <= min) { // update min & res
                min = end - start;
                res = s.substring(start, end);
            }
            if (count[s.charAt(start++)]++ == 0)    len++; // make invalid
        }
    }
    return res;
}

扣76,   样的是T给的是set,  会有重复,  样瞬秒， 开始问我时间复杂度，我说O(n)他 开始 顿扯，我说T的size 并 影响，最后 他同意
最后要我写test case, 后来当T 是空的时候会有问题， 我 上改 bug就结束让我问问题 
