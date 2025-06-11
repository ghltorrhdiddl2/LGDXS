import pandas as pd

train = pd.read_csv("./part2/ch6/diabetes_train.csv")
test = pd.read_csv("./part2/ch6/diabetes_test.csv")

print(train.head(1))
print(test.head(1))
target = train.pop("Outcome")

# 스케일링
from sklearn.preprocessing import MinMaxScaler
minmax = MinMaxScaler()
train = minmax.fit_transform(train)
test = minmax.transform(test)

# 검증 데이터 나누기
from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)
print(X_train.shape, X_val.shape, y_train.shape, y_val.shape)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(max_depth=5, n_estimators=500, random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict_proba(X_val)

from sklearn.metrics import roc_auc_score
roc = roc_auc_score(y_val, pred[:,1])
print('roc: ', roc)

# 답지 생성
pred = rf.predict_proba(test)
submit = pd.DataFrame({"pred":pred[:,1]})
submit.to_csv("result.csv", index=False)