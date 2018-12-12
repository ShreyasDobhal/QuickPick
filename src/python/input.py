from numpy import *
import pandas as pd

def my_query(my_str,itemAffinity):
    recoList=itemAffinity[itemAffinity.item1==searchItem]\
        [["item2","score"]]\
        .sort_values("score", ascending=[0])
    return recoList
    

userItemData = pd.read_csv('ratings.csv')
userItemData.head()
itemList=list(set(userItemData["ItemId"].tolist()))
userCount=len(set(userItemData["ItemId"].tolist()))
itemAffinity= pd.DataFrame(columns=('item1', 'item2', 'score'))
rowCount=0
for ind1 in range(len(itemList)):
    item1Users = userItemData[userItemData.ItemId==itemList[ind1]]["userId"].tolist()
    for ind2 in range(ind1, len(itemList)):        
        if ( ind1 == ind2):
            continue       
        item2Users=userItemData[userItemData.ItemId==itemList[ind2]]["userId"].tolist()
        commonUsers= len(set(item1Users).intersection(set(item2Users)))
        score=commonUsers / userCount
        itemAffinity.loc[rowCount] = [itemList[ind1],itemList[ind2],score]
        rowCount +=1
        itemAffinity.loc[rowCount] = [itemList[ind2],itemList[ind1],score]
        rowCount +=1
itemAffinity.head()
searchItem=input()
recoList=my_query(searchItem,itemAffinity)
leng=3
i=0
set1=set()
for i in range(len(itemList)):
    set1.add(itemList[i])
    if len(set1)==3:
        break
i=0
if recoList.shape[0]==0:
    for i in range(leng):
        ele=set1.pop()
        print(ele)
else:
    for i in range(leng):
        col_vec=recoList.iloc[i]
        print(col_vec[0])