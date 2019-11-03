#[1] 125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
  `
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
  `
 
 #[2] 680. Valid Palindrome II
 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
   `
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
   `
