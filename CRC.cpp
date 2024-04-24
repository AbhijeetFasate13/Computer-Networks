#include <iostream>
#include <string>

using namespace std;

// XOR operation for two binary strings
string xor1(const string& a, const string& b) {
    string result;
    int n = b.size();
    result.reserve(n);
    for (int i = 0; i < n; i++)
        result.push_back(a[i] == b[i] ? '0' : '1');
    return result;
}

// Modulo-2 division
string mod2div(string dividend, const string& divisor) {
    int pick = divisor.size();
    string tmp = dividend.substr(0, pick);
    int n = dividend.size();

    while (pick < n) {
        if (tmp[0] == '1')
            tmp = xor1(divisor, tmp) + dividend[pick];
        else
            tmp = xor1(string(pick, '0'), tmp) + dividend[pick];
        pick++;
    }

    if (tmp[0] == '1')
        tmp = xor1(divisor, tmp);
    else
        tmp = xor1(string(pick, '0'), tmp);

    return tmp.substr(1); // remove the leading zero
}

// Function to encode data by appending the remainder of the modular division at the end of data
void encodeData(const string& data, const string& key) {
    string appended_data = data + string(key.size() - 1, '0');
    string remainder = mod2div(appended_data, key);
    string codeword = data + remainder;
    cout << "Remainder : " << remainder << "\n";
    cout << "Encoded Data (Data + Remainder) : " << codeword << "\n";
}

// Function to simulate the receiver checking the transmitted data
void receiver(const string& data, const string& key) {
    string remainder = mod2div(data, key);
    if (remainder.find('1') != string::npos)
        cout << "There is some error in the data" << endl;
    else
        cout << "Correct message received" << endl;
}

int main() {
    string data = "100100";
    string key = "1101";
    cout << "Sender side..." << endl;
    encodeData(data, key);

    cout << "\nReceiver side..." << endl;
    receiver(data + mod2div(data + string(key.size() - 1, '0'), key), key);

    return 0;
}

/*
### Explanation of the Code

The given C++ code is a simplified and more compact version of a CRC (Cyclic Redundancy Check) implementation for error detection in data transmission. Here's a breakdown of its major components:

1. **XOR Operation (`xor1` function)**:
   - **Input**: Two binary strings of equal length.
   - **Output**: A binary string representing the XOR result.
   - This function computes the XOR of corresponding bits of the two strings. If the bits are the same, the result is '0'; if different, it's '1'.

2. **Modulo-2 Division (`mod2div` function)**:
   - **Input**: A 'dividend' string and a 'divisor' string.
   - **Output**: The remainder after performing binary division.
   - The function simulates binary division (similar to long division) but using XOR instead of subtraction. The remainder from this division is used as the error-checking code (CRC).

3. **Encoding Data (`encodeData` function)**:
   - **Input**: Original data and a key (CRC polynomial).
   - **Purpose**: To append the CRC (remainder) to the original data, making a 'codeword'.
   - This function appends zeroes to the data (as many as the length of the key minus one), calculates the CRC using `mod2div`, and appends this CRC to the original data.

4. **Receiving and Verifying Data (`receiver` function)**:
   - **Input**: Codeword (data plus CRC) and the key.
   - **Purpose**: To check if the received codeword is correct by recalculating the CRC.
   - If the CRC of the received codeword is all zeros, the data is deemed correct. Otherwise, an error is detected.

### Steps to Compile and Run the Code in Linux

Assuming the code is saved in a file named `crc.cpp` within a folder `121B1B258` on the Desktop, follow these steps to compile and run the code:

1. **Open Terminal**:
   - You can open the terminal using the shortcut `Ctrl+Alt+T` or by searching for "terminal" in your applications menu.

2. **Navigate to the Directory Containing the Code**:
   - Change to the Desktop directory and then to `121B1B258` using:

     cd ~/Desktop/121B1B258


3. **Compile the Code**:
   - Use the g++ compiler to compile your code. If `g++` is not installed, you can install it using `sudo apt install g++`. Compile the code with:

     g++ crc.cpp -o crc

   - This command compiles `crc.cpp` and creates an executable named `crc`.

4. **Run the Compiled Program**:
   - Execute the compiled program by typing:

     ./crc

   - This will run the program, and you'll see the output directly in the terminal, showing the remainder, the encoded data, and the result of the receiver checking the transmitted data.

These steps will help you compile and run the C++ program on a Linux system, assuming all necessary tools are installed and accessible from your command line.

*/
