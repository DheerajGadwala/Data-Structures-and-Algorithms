def insertionSort(arr):
    n = len(arr)
    for i in range(1, n):
        temp = arr[i]
        pos = i
        for j in range(i-1, -1, -1):
            if(temp < arr[j]):
                arr[j+1] = arr[j]
                pos = j
            else:
                break
        arr[pos] = temp


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
insertionSort(arr)
print('after sorting: ', *arr)
