278. First Bad Version
// https://leetcode.com/problems/first-bad-version/
You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

    
public int firstBadVersion(int n) {
    int left = 1, right = n;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (isBadVersion(mid))  right = mid;
        else    left = mid + 1;
    }
    return left;
}

Run test case 
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=566963

n = 2: 
left = 1, right = 2, mid = 1;
if mid is bad, right = 1, return 1
if mid is not bad, left = 2, return 2;

