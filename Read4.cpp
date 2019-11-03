
158. Read N Characters Given Read4 II - Call multiple times
Given a file and assume that you can only read the file using a given method read4, 
implement a method read to read n characters. 
Your method read may be called multiple times.

// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        int externalIdx = 0;
        while (externalIdx < n) {
            if (currIndexInBuf >= readLeftByte) {
                readLeftByte = read4(buf4);
                currIndexInBuf = 0;
                if (readLeftByte == 0) break;
            }
            buf[externalIdx++] = buf4[currIndexInBuf++];
        }
        return externalIdx;
    }
    private:
    char buf4[4];
    int readLeftByte = 0;
    int currIndexInBuf = 0;
};
