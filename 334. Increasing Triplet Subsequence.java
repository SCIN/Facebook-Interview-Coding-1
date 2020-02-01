334. Increasing Triplet Subsequence
// https://leetcode.com/problems/increasing-triplet-subsequence/

Given [1, 2, 3, 4, 5],
return true.
Given [5, 4, 3, 2, 1],
return false.

问怎么test code，要求考虑corner cases。
bool increasingTriplet(vector<int>& nums) {
    int first_min = INT_MAX;
    int second_min = INT_MAX;
    for (int i = 0; i < nums.size(); i++) {
        if (nums[i] < first_min) {
            first_min = nums[i];
        } else if (nums[i] > first_min && nums[i] < second_min) {
            second_min = nums[i];
        } if (nums[i] > second_min){
            return true;
        }
    }
    return false;
}

public boolean increasingTriplet(int[] nums) {
    int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
    for(int num : nums){
        if(num <= min)      min = num; // important
        else if(num <= secondMin)    secondMin = num;// important
        else    return true;
    }
    return false;
}

