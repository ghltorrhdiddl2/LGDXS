import pandas as pd

train = pd.read_csv("./part2/ch6/diabetes_train.csv")
test = pd.read_csv("./part2/ch6/diabetes_test.csv")

print(train.head())
print(train.isnull().sum())
print(test.isnull().sum())

print(train.info())
print(test.head())

target = train.pop('Outcome')

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train,target, test_size=0.2, random_state=0)
print(X_train.shape, y_train.shape, X_val.shape, y_val.shape)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict_proba(X_val)
print('pred ',pred[:5])

from sklearn.metrics import roc_auc_score
roc = roc_auc_score(y_val, pred[:,1])
print('roc : ', roc)

pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred[:,1]})
submit.to_csv("result.csv", index=False)
print(pd.read_csv("result.csv").head())