def merge(arr, l1, r1, l2, r2):
    for i in range(l2, r2):
        val = arr[i]
        pos = i
        for j in range(i-1, l1-1, -1):
            if arr[j] > val:
                arr[j+1] = arr[j]
                pos = j
            else:
                break
        arr[pos] = val


def divide(arr, l, r):
    if(abs(l-r) != 1):
        l1 = l
        r1 = (l+r)//2
        l2 = (l+r)//2
        r2 = r
        divide(arr, l1, r1)
        divide(arr, l2, r2)
        merge(arr, l1, r1, l2, r2)


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
divide(arr, 0, len(arr))
print('after sorting: ', *arr)
