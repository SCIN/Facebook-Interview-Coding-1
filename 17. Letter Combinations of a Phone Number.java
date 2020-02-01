17. Letter Combinations of a Phone Number

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Time: O(3 ^ n * 4 ^ m) 
n: number of digits in the input maps to 3 letters
m: number of digits in the input maps to 4 letters
Choose unordered_map for match: hashing takes time
Choose map for match: sorting takes time 
Mathmatically, hashmap is faster (O(1)). 
	
unordered_map<char, string> match;

vector<string> letterCombinations(string digits) {
	match['2'] = "abc";
	match['3'] = "def";
	match['4'] = "ghi";
	match['5'] = "jkl";
	match['6'] = "mno";
	match['7'] = "pqrs";
	match['8'] = "tuv";
	match['9'] = "wxyz";
	
	vector<string> ret;
	// edge 
	if (digits.size() == 0) return ret;
	dfs(ret, digits, 
	return ret;
}

void dfs(vector<string>& ret, string& digits, int index, string path) {
	if (indec == digits.size()) {
		ret.push_back(path);
		return;
	}
	string curr = match[digits[index]];
	for (int i = 0; i < curr.length(); i++) {
		dfs(ret, digits, index + 1, path + curr[i]);
	}
}

Java:
注意map取index时要 -‘0’		
Time: O(3 ^ n)

public List<String> letterCombinations(String digits) {
	String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	List<String> res = new ArrayList<>();
	if (digits.length() == 0)  return res; // important! "" -> [] Not [""]
	dfs(res, "", map, digits, 0);
	return res;
}
private void dfs(List<String> res, String tmp, String[] map, String digits, int start) {
	if (start == digits.length()) {
		res.add(tmp);
		return;
	}
	for (int i = 0; i < map[digits.charAt(start) - '0'].length(); i++) { // -'0'
		dfs(res, tmp + map[digits.charAt(start) - '0'].charAt(i), map, digits, start + 1);
	}
}
