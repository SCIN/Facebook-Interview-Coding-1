152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/

int maxProduct(vector<int>& nums) {
    int max_product = nums[0];
    int min_product = nums[0];
    int ret = nums[0];
    for (int i = 1; i < nums.size(); i++) {
        if (nums[i] >= 0) {
            max_product = max(max_product * nums[i], nums[i]);
            min_product = min(min_product * nums[i], nums[i]);
        } else {
            int tmp = max_product;
            max_product = max(min_product * nums[i], nums[i]);
            min_product = min(tmp * nums[i], nums[i]);
        }
        ret = max(max_product, ret);
    }
    return ret;
}

public int maxProduct(int A[], int n) {
    if (n == 0) return 0;
    int maxProduct = A[0], minProduct = A[0], maxRes = A[0];
    for (int i = 1; i < n; i++) {
        if (A[i] >= 0) {
            maxProduct = max(maxProduct * A[i], A[i]);
            minProduct = min(minProduct * A[i], A[i]);
        } else {
            int temp = maxProduct;
            maxProduct = max(minProduct * A[i], A[i]);
            minProduct = min(temp * A[i], A[i]);
        }
        maxRes = max(maxRes, maxProduct);
    }
    return maxRes;
}
