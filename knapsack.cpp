#include <iostream>
using namespace std;

int main(int argc, char const *argv[])
{   
    // original problem
    // int p[] = {0, 10, 10, 12, 18};
    // int w[] = {0, 2, 4, 6, 9};

    // int n = 4, m = 15;

    int p[] = {0, 1, 2, 5, 6};
    int wt[] = {0, 2, 3, 4, 5};

    int n = 4, m = 8;

    int k[n+1][m+1];

    for (int i = 0; i <= n; i++)
    {
        for (int w = 0; w <= m; w++)
        {
            if (i == 0 || w == 0) {
                k[i][w] = 0;
            }

            else if (wt[i] <= w) {
                k[i][w] = max(k[i-1][w], k[i-1][w - wt[i]] + p[i]);
            }

            else {
                k[i][w] = k[i-1][w];
            }
        }
    }

    cout<<k[n][m]<<endl;

    int i = n, j = m;

    while (i > 0 || j > 0)
    {
        if (k[i][j] == k[i-1][j])
        {
            cout<<i<<" = 0"<<endl;
            i--;
        }

        else {
            cout<<i<<" = 1"<<endl;
            j = j - wt[i];
            i--;
        }
        
    }
    
    return 0;
}
