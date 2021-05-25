def selectionSort(arr):
    n = len(arr)
    for i in range(n):
        mn = arr[i]
        pos = i
        for j in range(i, n):
            if(mn > arr[j]):
                mn = arr[j]
                pos = j
        arr[i], arr[pos] = arr[pos], arr[i]


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
selectionSort(arr)
print('after sorting: ', *arr)
