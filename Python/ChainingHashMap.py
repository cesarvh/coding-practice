#######################################################################
### Hash Map that uses the Chaining method of collision handling ######
######################################################################
class Map():
    currentSize = 0
    capacity = 0

    def __init__(self, initialSize, loadFactor=0.75):
        self.capacity = initialSize
        self.hashArray = [[] for i in range(self.capacity)]
        self.loadFactor = loadFactor

    def __repr__(self):
        rep = []
        index = 0
        for bucket in self.hashArray:
            rep.append(str(index) + ' -> ' + str(bucket)[1: -1])
            index += 1
        return '\n'.join(rep)

    def put(self, key, value):
        if self.contains(key):
            self.remove(key)

        if self.currentSize >= self.capacity * self.loadFactor:
            self.rehash()
        
        bucketPlace = self.computeHash(key)
        self.hashArray[bucketPlace].append([key, value])
        self.currentSize += 1


    def contains(self, key, containsFlag=False):
        bucketPlace = self.computeHash(key)
        for item in self.hashArray[bucketPlace]:
            if item[0] == key:
                return True
        return False
    
    def get(self, key):
        bucketPlace = self.computeHash(key)
        for pair in self.hashArray[bucketPlace]:
            if pair[0] == key:
                return pair[1]
        return None
    
    def size(self):
        return self.currentSize
    
    def isEmpty(self):
        return self.currentSize == 0

    def rehash(self, shrink=False):

        if shrink:
            newCapacity = int(self.capacity // 2)
            i = self.capacity
            while i > newCapacity:
                self.hashArray.remove([])
                i -= 1
            self.capacity = newCapacity
        else:
            i = self.capacity
            while i < self.capacity * 2:
                self.hashArray.append([])
                i += 1
            self.capacity *= 2
        print(self.capacity)

        for bucket in self.hashArray:
            for item in bucket:
                newSpot = self.computeHash(item[0])
                self.hashArray[newSpot].append([item[0], item[1]])
                bucket.remove([item[0], item[1]])

    def shrink(self):
        if self.currentSize <= self.capacity * 0.5:
            self.rehash(True)

    def remove(self, key):
        print(key + ' === ') 
        self.shrink()
        print(key)
        if not self.contains(key):
            return 
        bucket = self.hashArray[self.computeHash(key)]
        for item in bucket:
            if item[0] == key:
                bucket.remove([item[0], item[1]])
        self.currentSize -= 1

    def computeHash(self, key):
        return hash(key) % self.capacity
    
    def printMap(self):
        index = 0
        for bucket in self.hashArray:
            print (str(index) + ' -> ' + str(bucket)[1: -1])
            index += 1
