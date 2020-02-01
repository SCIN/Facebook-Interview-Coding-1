33. Search in Rotated Sorted Array

Recursive:

int search(vector<int>& nums, int target) {
    return binarySearchHelper(nums, 0, nums.size() - 1, target);
}
int binarySearchHelper(vector<int>& nums, int left, int right, int target) {
    if (left > right) return -1;
    int mid = (left + right) / 2;
    if (nums[mid] == target) return mid;
    if (nums[mid] < nums[right]) {
        if (target > nums[mid] && target <= nums[right]) {
            return binarySearchHelper(nums, mid + 1, right, target);
        } else {
            return binarySearchHelper(nums, left, mid - 1, target);
        }
    } else {
        if (target >= nums[left] && target < nums[mid]) {
            return binarySearchHelper(nums, left, mid - 1, target);
        } else {
            return binarySearchHelper(nums, mid + 1, right, target);
        }
    }
}

Iteritive:

int search(vector<int>& nums, int target) {
    int left = 0;
    int right = nums.size() - 1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (target == nums[mid]) return mid;
        
        if (nums[mid] < nums[right]) {
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else {
            if (target < nums[mid] && target >= nums[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
    return -1;
}


public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) { // find min idx, when left == right return left
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[right])
            left = mid + 1;
        else    right = mid;
    }
    if (target == nums[left])   return left; // performance improved
    int rotate = left; 
    left = 0;
    right = nums.length - 1;
    while (left <= right) { // = return
        int mid = left + (right - left) / 2;
        int newMid = (mid + rotate) % nums.length;
        if (target < nums[newMid])  right = mid - 1;
        else if (target > nums[newMid])   left = mid + 1;
        else    return newMid;
    }
    return -1;
}

public int search(int[] nums, int target) {        
    int left = 0, right = nums.length - 1;
    while (left <= right) { // must find right idx within this loop : =
        int mid = (left + right) / 2;
        if (nums[mid] == target)   return mid;
        else if (nums[mid] < nums[right])
            if (nums[mid] < target && nums[right] >= target)
                left = mid + 1;
            else    right = mid - 1;
        else
            if (nums[left] <= target && target < nums[mid])
                right = mid - 1;
            else    left = mid + 1;
    }
    return -1;
}
