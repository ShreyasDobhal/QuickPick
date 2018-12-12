from numpy import *
import pandas as pd


#print ("hi from python")

def my_query(my_str,itemAffinity):
    recoList=itemAffinity[itemAffinity.item1==searchItem]\
        [["item2","score"]]\
        .sort_values("score", ascending=[0])
    return recoList
    
#productSaleData
userItemData = pd.read_csv('C:\\Users\\Shreyas\\Documents\\NetBeansProjects\\QuickPick\\src\\python\\productSaleData.csv')
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
searchItem=raw_input()
recoList=my_query(searchItem,itemAffinity)
leng=3
for i in range(leng):
    col_vec=recoList.iloc[i]
    print(col_vec[0])




