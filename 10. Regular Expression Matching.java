10. Regular Expression Matching

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

    
This is a regular expression question involving NFA and DFA,
We have a DFA M on s (M_ori) already and we need to build a NFA machine on p (M_new).
         
Accepting State of p = Accepting State of s
Transition function of p 
Sigma_new(q, '*') = q;
Sigma_new(q, '.') = any state p in Q_ori which is reachable from q.
Sigma_new(q, char c) = Sigma_ori(q, c)

This implies when we meet a kleene star, we need to stay at the same state until we meet a different char then move forward to other state.
When we meet a '.', we can move to any state reachable from the current state. 
For the rest, just regular matching as the same with the original machine. 
   

Then We can get the recursive solution.

bool isMatch(string s, string p) {
    if (p.empty()) { return s.empty(); }
    if (s.empty()) { return p[1] == '*' && isMatch(s, p.substr(2)); }
    bool first_match = (s[0] == p[0] || p[0] == '.');
    if (p[1] == '*') {
        return isMatch(s, p.substr(2)) || (first_match && isMatch(s.substr(1), p));
    } else {
        return first_match && isMatch(s.substr(1), p.substr(1));
    }
}

DP O(m*n)
    
dp[i][j] to be true if s[0..i) matches p[0..j) and false otherwise.
1. dp[i][j] = dp[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
2. dp[i][j] = dp[i][j - 2], if p[j - 1] == '*' and the pattern repeats for 0 time;
3. dp[i][j] = dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*' and the pattern repeats for at least 1 time.
                     
bool isMatch(string s, string p) {
    int m = s.size(), n = p.size();
    vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));
    dp[0][0] = true;
    for (int i = 0; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (p[j - 1] == '*') {
                dp[i][j] = dp[i][j - 2] || (i && dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'));
            } else {
                dp[i][j] = i && dp[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
            }
        }
    }
    return dp[m][n];
}
我说DP，但他偏说喜欢递归。。。幸好时间 多 ，没让写代码，  从 没写过递归版本的。。。

Solution: DP 
[YouTube] // https://www.youtube.com/watch?v=l3hda49XcDE

dp[i][j] = dp[i - 1][j - 1]	, p(j - 1) != '*' && s(i - 1) = p(j - 1)
         = dp[i][j - 2]		, p(j - 1) == '*' && matches empty
         = dp[i - 1][j] 	, p(j - 1) == '*' && s(i - 1) = p(j - 2), 'x' repeats >= 1 times 

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    // p[0.., j - 3, j - 2, j - 1] matches empty iff p[j - 1] is '*' and p[0..j - 3] matches empty
    for (int j = 1; j < n; j += 2) 
        if (p.charAt(j) == '*')     dp[0][j + 1] = dp[0][j - 1];
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) 
            if (p.charAt(j - 1) != '*')
                dp[i][j] = dp[i - 1][j - 1] && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
            else
                dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
    return dp[m][n];
}
private boolean isCharMatch(char s, char p) {
    return p == '.' || s == p;
}


******************************Follow Up: 优化空间*******************************

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    for (int j = 1; j < n; j += 2) 
        if (p.charAt(j) == '*')     dp[j + 1] = dp[j - 1];
    for (int i = 1; i <= m; i++) {
        boolean pre = dp[0];
        dp[0] = false;
        for (int j = 1; j <= n; j++) {
            boolean tmp = dp[j];
            if (p.charAt(j - 1) != '*')
                dp[j] = pre && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
            else
                dp[j] = dp[j - 2] || dp[j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
            pre = tmp;
        }
    }
    return dp[n];
}




-----------------------

44. Wildcard Matching

We define the state P[i][j] to be whether s[0..i) matches p[0..j). The state equations are as follows:
P[i][j] = P[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '?'), if p[j - 1] != '*';
P[i][j] = P[i][j - 1] || P[i - 1][j], if p[j - 1] == '*'.

// Equation 1). means that if p[j-1] is not *, f(i,j) is determined by if s[0:i-2] matches p[0:j-2] and if (s[i-1]==p[j-1] or p[j-1]=='?').
// Equation 2). means that if p[j-1] is *, f(i,j) is true if either f(i,j-1) is true: s[0:i-1] matches p[0:j-2] and * is not used here; 
// or f(i-1,j) is true: s[0:i-2] matches p[0:j-1] and * is used to match s[i-1].

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for(int j = 1; j <= n && p.charAt(j-1) == '*'; j++) 
        dp[0][j] = true;
    for (int i = 1; i <= m; i++) 
        for (int j = 1; j <= n; j++) 
            if (p.charAt(j - 1) != '*') 
                dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
            else 
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
    return dp[m][n];
}










