list1 = [1,2,3,4,5]
list2 = [6,7,8,9,10]
list3 = []
for i in list1 : 
    if(i % 2 != 0) :
        list3.append(i)
for i in list2 : 
    if(i % 2 == 0) :
        list3.append(i)
print("Numbers in list 3 are : ")
print(list3)
