import sys
sys.setrecursionlimit(100)


def quick(arr, l, r):
    if r == l:
        return
    p = l
    i = l+1
    for j in range(i, r):
        if arr[j] <= arr[p]:
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
    arr[p], arr[i-1] = arr[i-1], arr[p]
    quick(arr, l, i-1)
    quick(arr, i, r)


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
quick(arr, 0, len(arr))
print('after sorting: ', *arr)
