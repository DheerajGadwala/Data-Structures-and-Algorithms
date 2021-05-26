class minHeap:
    def __init__(self):
        self.arr = []

    def add(self, val):
        self.arr.append(val)
        self._bubbleUp(len(self.arr)-1)

    def remove(self):
        self._swap(0, -1)
        ret = self.arr.pop()
        self._bubbleDown()
        return ret

    def _swap(self, pos1, pos2):
        self.arr[pos1], self.arr[pos2] = self.arr[pos2], self.arr[pos1]

    def _bubbleUp(self, pos):
        if pos == 0:
            return
        parent = (pos-1)//2
        if self.arr[pos] < self.arr[parent]:
            self._swap(pos, parent)
            self._bubbleUp(parent)

    def _bubbleDown(self, pos=0):
        left = 2*pos+1
        right = 2*pos+2
        if right < len(self.arr):
            if self.arr[pos] > self.arr[left] or self.arr[pos] > self.arr[right]:
                if self.arr[left] < self.arr[right]:
                    self._swap(left, pos)
                    self._bubbleDown(left)
                else:
                    self._swap(right, pos)
                    self._bubbleDown(right)
        elif left < len(self.arr):
            if self.arr[left] < self.arr[pos]:
                self._swap(pos, left)


arr = [5, 3, 17, 10, 84, 19, 6, 22, 9]
heap = minHeap()
for i in arr:
    heap.add(i)
print(heap.arr)
for i in arr:
    print(heap.remove())
