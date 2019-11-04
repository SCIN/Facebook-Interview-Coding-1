1060. Missing Element in Sorted Array
Given a sorted array A of unique numbers, 
find the K-th missing number starting from the leftmost number of the array.

Time O(log(n)) base 2 n:length of the array
Space O(1)
Binary Search
```
int missingElement(vector<int>& nums, int k) {
    int left = 0;
    int right = nums.size() - 1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (missingCountFromBeginning(nums, mid) < k) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return nums[left - 1] - missingCountFromBeginning(nums, left - 1) + k ;
}

int missingCountFromBeginning(vector<int>& nums, int index) {
    return nums[index] - nums[0] + index;
}
```
