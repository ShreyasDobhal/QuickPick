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
searchItem=input("prompt")
print("\n\n\n\n\n")
recoList=my_query(searchItem,itemAffinity)
leng=3
for i in range(leng):
    col_vec=recoList.iloc[i]
    print(col_vec[0])





YE KNN KA CODE HAI (FOLLOWING)

import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix
from sklearn.metrics import f1_score
from sklearn.metrics import accuracy_score
dataset=pd.read_csv('custom.csv')
int num_rows=dataset.shape[0]
print(num_rows)
X=dataset.iloc[:,1:14]
Y=dataset.iloc[:,14]
X_train,X_test,Y_train,Y_test=train_test_split(X,Y,random_state=0,test_size=0.4)
sc_X=StandardScaler()
import math
math.sqrt(len(Y_test))
classifier=KNeighborsClassifier(n_neighbors=3,p=2,metric='euclidean')
classifier.fit(X_train,Y_train)
y_pred=classifier.predict(X_test)
print(X_test)
print(y_pred)

print(accuracy_score(Y_test,y_pred))