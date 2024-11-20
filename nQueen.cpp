#include <iostream>
#include <vector>
using namespace std;

void addtoSolution(vector<vector<int>> &board, vector<vector<int>> &ans, int size) {
    vector<int> temp;

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            temp.push_back(board[i][j]);
            cout<<board[i][j]<<" ";
        }
        ans.push_back(temp);
        cout<<endl;
    }

    cout<<endl;
}

bool isSafe(int row, int col, vector<vector<int>> &board, int size) {
    int c = col;
    int r = row;

    // check for same row
    while (c >= 0) {
        if (board[r][c] == 1) {
            return false;
        }
        c--;
    }

    // check for diagonal
    r = row; c = col;
    while (r >= 0 && c >= 0) {
        if (board[r][c] == 1) {
            return false;
        }

        r--; c--;
    }

    // check for diagonal
    r = row; c = col;
    while (r < size && c >= 0) {
        if (board[r][c] == 1) {
            return false;
        }

        r++; c--;
    }

    return true;
}



void nQueen(int col, vector<vector<int>> &board, vector<vector<int>> &ans, int size) {
    // base case
    if (col == size) {
        addtoSolution(board, ans, size);
        return;
    }

    for (int row = 0; row < size; row++)
    {
        if (isSafe(row, col, board, size)) {

            board[row][col] = 1;
            nQueen(col+1, board, ans, size);

            // backtrack
            board[row][col] = 0;         
        }
    }
    

}

int main(int argc, char const *argv[])
{
    int size = 4;

    // int arr[size][size] = {{0}};

    vector<vector<int>> board;
    vector<vector<int>> ans;

    vector<int> vec;
    for (int i = 0; i < size; i++)
    {
        vec.push_back(0);
    }

    for (int i = 0; i < size; i++)
    {
        board.push_back(vec);
    }
    
    // for (int i = 0; i < size; i++)
    // {
    //     for (int j = 0; j < size; j++)
    //     {
    //         cout<<board[i][j]<<" ";
    //     }
        
    //     cout<<endl;
    // }

    nQueen(0, board, ans, size);
    
    return 0;
}
