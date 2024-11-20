// PART A: Implement Quicksort using divide and conquer strategy and display time for sorting
// for 500 values.
// PART B: Use same data for Mergesort and compare the time required with Quicksort.

#include <iostream>
#include <chrono>
using namespace std;

// quicksort
int getPivotIndex(int *array, int start, int end) {
    int pivot = start;
    int i = start+1, j = end;

    while(i <= j)
    {
        while(array[i] < array[pivot] && i < end+1) {
            i++;
        }
        
        while (array[j] > array[pivot] && j > start)
        {
            j--;
        }
        
        if (i > j)
        {
            int temp = array[pivot];
            array[pivot] = array[j];
            array[j] = temp;

            return j;
        }
        
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    return -1;
}

void quicksort(int *array, int start, int end) {
    if (start < end) {

        // uncomment for randomised quicksort
        int r = start + (rand() % (end - start));
        int temp = array[start];
        array[start] = array[r];
        array[r] = temp;

        int pivot = getPivotIndex(array, start, end);
        quicksort(array, start, pivot-1);
        quicksort(array, pivot+1, end);
    }

    return;
}


// merge sort
void merge(int* arr, int l, int mid, int e) {
    int len1 = mid - l + 1;
    int len2 = e - mid;

    int a[len1];
    int b[len2];

    for (int i = 0; i < len1; i++)
    {
        a[i] = arr[l+i];
    }

    for (int i = 0; i < len2; i++)
    {
        b[i] = arr[mid+i+1];
    }

    int i = 0, j = 0, k = l;

    while (i < len1 && j < len2) {

        if (a[i] > b[j]) {
            arr[k] = b[j];
            j++;
            
        }

        else {
            arr[k] = a[i];
            i++; 
        }

        k++;
    }

    while (i < len1) {
        arr[k] = a[i];
        i++; k++;
    }

    while (j < len2) {
        arr[k] = b[j];
        j++; k++;
    }
}

void merge_sort(int* arr, int l, int e) {
    if (l < e)
    {
        int mid = (l+e)/2;
        merge_sort(arr, l, mid);
        merge_sort(arr, mid+1, e);

        merge(arr, l, mid, e);
    }
    
    return;
}

int main(int argc, char const *argv[])
{
    int size = 500;
    int arr[size];

    for (int i = 0; i < size; i++)
    {
        arr[i] = size - i;
    }
    

    // ****************************************************** Q1 PG - 1 ****************************
    auto start = chrono::high_resolution_clock().now();
    quicksort(arr, 0, size-1);
    auto end = chrono::high_resolution_clock().now();
    auto duration = chrono::duration_cast<chrono::microseconds>(end-start);

    for (int i = 0; i < size; i++)
    {
        cout<<arr[i]<<" ";
    }
    cout<<endl;

    cout<< "time taken = " << duration.count() << " microseconds" << endl;

    // auto start = chrono::high_resolution_clock().now();
    // merge_sort(arr, 0, size-1);
    // auto end = chrono::high_resolution_clock().now();

    // auto duration = chrono::duration_cast<chrono::microseconds>(end-start);

    // for (int i = 0; i < size; i++)
    // {
    //     cout<<arr[i]<<" ";
    // }
    // cout<<endl;

    // cout<< "time taken = " << duration.count() << " microseconds" << endl;
    return 0;
}
