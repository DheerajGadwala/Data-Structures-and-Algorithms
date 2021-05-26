import heapq


class minHeap:
    def __init__(self, arr):
        # heapq library functions generate minheaps by default
        self.arr = heapq.heapify(arr)

    def __init__(self):
        self.arr = []

    def add(self, val):
        heapq.heappush(self.arr, val)

    def remove(self):
        return heapq.heappop(self.arr)


class maxHeap:
    def __init__(self, arr):
        arr = [-i for i in arr]
        # negate the elements and heapify to get minHeap of negative values. If you don't consider the '-ve' sign, it is essentially a maxHeap.
        self.arr = heapq.heapify(arr)

    def __init__(self):
        self.arr = []

    def add(self, val):
        heapq.heappush(self.arr, -val)

    def remove(self):
        return -heapq.heappop(self.arr)


arr = [5, 3, 17, 10, 84, 19, 6, 22, 9]
heap1 = minHeap()
heap2 = maxHeap()

for i in arr:
    heap1.add(i)
    heap2.add(i)
print("minHeap: \n", heap1.arr)
print("on removing elements: ")
for i in arr:
    print(heap1.remove())
print("maxHeap: \n", heap2.arr)
print("on removing elements: ")
for i in arr:
    print(heap2.remove())
