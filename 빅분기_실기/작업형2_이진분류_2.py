import pandas as pd

train = pd.read_csv("./part2/ch6/hr_train.csv")
test = pd.read_csv("./part2/ch6/hr_test.csv")

print(train.head(1))
print(test.head(1))
target = train.pop('target')

# # roc 평가지표
print(train.info())  # object형 많음
print(train.isnull().sum())

train = train.fillna("X")
test = test.fillna("X")

# 레이블 인코딩
from sklearn.preprocessing import LabelEncoder
train_test = pd.concat([train, test])
cols = train.select_dtypes(include='object').columns
for col in cols:
    le = LabelEncoder()
    train_test[col] = le.fit_transform(train_test[col])
train = train_test[:len(train)].copy()
test = train_test[len(train):].copy()

# id 제거(성능 떨어짐)
train.drop('enrollee_id', axis=1, inplace=True)
test.drop('enrollee_id', axis=1, inplace=True)

# 스케일링
from sklearn.preprocessing import RobustScaler
scaler = RobustScaler()
train = scaler.fit_transform(train)
test = scaler.transform(test)    # -> 이거 빼면 roc:  0.7649556215788462

# 검증 데이터 분할
from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)
print(X_train.shape, X_val.shape, y_train.shape, y_val.shape)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict_proba(X_val)

from sklearn.metrics import roc_auc_score
roc = roc_auc_score(y_val, pred[:,1])
print('roc: ',roc)   # roc:  0.7651923806312951

# 답 파일
pred = rf.predict_proba(test)
submit = pd.DataFrame({'pred':pred[:,1]})
submit.to_csv("result.csv", index=False)