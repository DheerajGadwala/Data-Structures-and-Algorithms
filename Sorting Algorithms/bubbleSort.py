def bubbleSort(arr):
    n = len(arr)
    for i in range(n, 1, -1):
        ind = True
        for j in range(1, i):
            if arr[j] < arr[j-1]:
                arr[j], arr[j-1] = arr[j-1], arr[j]
                ind = False
        if(ind):
            break


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
bubbleSort(arr)
print('after sorting: ', *arr)
