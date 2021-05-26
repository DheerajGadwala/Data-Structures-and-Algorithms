def maxHeapify(arr, j):
    currPos = j
    while currPos >= 0:
        l = 2*currPos+1
        r = 2*currPos+2
        if r < j and arr[r] > arr[currPos]:
            arr[r], arr[currPos] = arr[currPos], arr[r]
        if l < j and arr[l] > arr[currPos]:
            arr[l], arr[currPos] = arr[currPos], arr[l]
        currPos -= 1


def heapSort(arr):
    n = len(arr)-1
    while n > 0:
        maxHeapify(arr, n)
        arr[0], arr[n] = arr[n], arr[0]
        n -= 1


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
heapSort(arr)
print('after sorting: ', *arr)
