import pandas as pd

train = pd.read_csv("./part2/ch6/creditcard_train.csv")
test = pd.read_csv("./part2/ch6/creditcard_test.csv")

print(train.head(1))
print(test.head(1))
print(train.info())

print(train.isnull().sum())  # OCCUPATION_TYPE        7976
print(test.isnull().sum())

# 결측치 데이터 삭제
print(train.shape)
train.dropna(subset=['OCCUPATION_TYPE'], inplace=True)
print(train.shape)

target = train.pop('STATUS')

# ID 제외
train = train.drop('ID',axis=1).copy()
test = test.drop('ID', axis=1).copy()

# 원-핫 인코딩
# train = pd.get_dummies(train)
# test = pd.get_dummies(test)  # -> f1 0.22018348623853212

# 레이블 인코딩
from sklearn.preprocessing import LabelEncoder
cols = train.select_dtypes(include='object').columns
for col in cols:
    le = LabelEncoder()
    train[col] = le.fit_transform(train[col])
    test[col] = le.fit_transform(test[col])

# 데이터 분할
from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

from sklearn.metrics import f1_score
f1 = f1_score(y_val, pred)
print('f1: ', f1)  # f1 0.22727272727272727

# 답 파일 생성
pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred})
submit.to_csv("result.csv", index=False)