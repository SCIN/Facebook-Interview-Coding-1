#[1] 125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
  ```
  bool isPalindrome(string s) {
    int i = 0; 
    int j = 0;
    while (i < j) {
      while (i < j && !isalnum(s[i])) i++;
      while (i < j && !isalnum(s[j])) j--;
      if (toupper(s[i]) != toupper(s[j])) return false;
      i++;
      j--;
    }
    return true;
  }
  ```
 
 #[2] 680. Valid Palindrome II
 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
   ```
   bool validPalindrome(string s) {
      int i = 0; 
      int j = 0;
      while (i < j) {
        if (s[i] != s[j]) {
          reuturn check(s, i + 1, j) || check(s, i, j - 1);
        }
        i++;
        j--;
      }
      return true;
   }
   bool check(string& s, int i, int j) {
      int left = i;
      int right = j;
      while (left < right) {
        if (s[left] != s[right]) return false;
        left++;
        right--;
      }
      return true;
   }
   ```
 #[3] 9. Palindrome Number
 Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
   Time: O(log(n)) base 10
   Space O(1)
   Edge cases: 
   ##[1] negative number. eg: -121 => 121- -> return false directly
   ##[2] numbers less than 10. eg: 1 => 1 -> return true direcly
   ##[3] numbers end with 0 eg: 1210 => 0121
   Method: flip the number from the end to the middle and compare with the front half
   bool isPalindrome(int x) {
      if (x < 0) return false;
      if (x < 10) return true;
      int fast = x;
      int slow = x;
      int rhalf = 0;
      // if (fast / 10 == 0) 
      // 1233210
      // 1233210 1233210 0
      // 12332 123321 0
      // 123 12332 1
      // 1 1233 12
      // 0 123 123
      // so we shift one bit more before go into the while loop to make sure this never happens
      while (fast / 10) {
          rhalf = rhalf * 10 + slow % 10;
          slow /= 10;
          fast /= 100;
      }
      return (fast == 0) ? (slow == rhalf) : (slow / 10 == rhalf);
   }
