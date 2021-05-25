def merge(l, r):
    ret = []
    i, j = [0, 0]
    while(i+j < len(l)+len(r)):
        if i < len(l) and j < len(r):
            if l[i] < r[j]:
                ret.append(l[i])
                i += 1
            else:
                ret.append(r[j])
                j += 1
        elif i < len(l):
            ret.append(l[i])
            i += 1
        else:
            ret.append(r[j])
            j += 1
    return ret


def divide(arr):
    if len(arr) == 1:
        return arr
    l = divide(arr[:len(arr)//2])
    r = divide(arr[len(arr)//2:])
    return merge(l, r)


arr = [17, 3, 15, 7, 21, 14, 1, 6, 9, 11, 10, 1]
print('before sorting: ', *arr)
print('after sorting: ', *divide(arr))
