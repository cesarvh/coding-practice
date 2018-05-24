#############################################################################
### Hash Map that uses the linear probing method of collision handling ######
#############################################################################

class Map():
    currentSize = 0
    capacity = 0

    def __init__(self, initialSize, loadFactor=0.75):
        self.capacity = initialSize
        self.hashArray = [None for i in range(self.capacity)]
        self.loadFactor = loadFactor
    
    def __repr__(self):
        index = 0
        rep = []
        for item in self.hashArray:
            rep.append(str(index) + " -> " + str(item))
            index += 1
        return '\n'.join(rep)

    def put(self, key, value):
        if self.contains(key):
            self.remove(key)

        if self.currentSize >= self.capacity * self.loadFactor:
            self.rehash()
        
        bucketPlace = self.computeHash(key)

        if self.hashArray[bucketPlace] == None:
            self.hashArray[bucketPlace] = [key, value]
        else:
            # originalBucket = bucketPlace
            while self.hashArray[bucketPlace] != None:
                bucketPlace += 1
                if (bucketPlace >= self.capacity):
                    bucketPlace = 0
            self.hashArray[bucketPlace] = [key, value]
        
        self.currentSize += 1
    
    def contains(self, key, getter=False):
        return self.getIndexOf(key) != -1
    
    def get(self, key):
        return self.getIndexOf(key, getter=True)
    
    def remove(self, key):   
        index = self.getIndexOf(key)
        self.hashArray[index] = None

    def getIndexOf(self, key, getter=False):
        bucketPlace = self.computeHash(key)
        
        bucketContents = self.hashArray[bucketPlace]
        if (bucketContents is None):
            return -1 if not getter else Exception("No such item")
        
        if bucketContents[0] == key:
            return bucketPlace if not getter else bucketContents[1]
        else:
            originalBucket = bucketPlace
            bucketPlace += 1
            while (bucketPlace != originalBucket):
                if (bucketPlace >= self.capacity):
                    bucketPlace = 0
                if self.hashArray[bucketPlace] == None:
                    bucketPlace += 1
                else:
                    if self.hashArray[bucketPlace][0] == key:
                        return bucketPlace if not getter else self.hashArray[bucketPlace][1]
                    bucketPlace += 1
        return -1 if not getter else Exception("No such item")

    def shrink(self):
        self.currentSize <= self.capacity * 0.5
        newSize = int(self.capacity * 0.75)
        difference = self.capacity - newSize
        for _ in range(difference):
            self.hashArray.remove(None)

    def printMap(self):
        pass
    
    def rehash(self):
        self.capacity *= 2
        for _ in range(self.capacity // 2):
            self.hashArray.append(None)

    def computeHash(self, key):
        return hash(key) % self.capacity
