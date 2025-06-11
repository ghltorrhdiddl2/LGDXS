import pandas as pd

train = pd.read_csv("./part2/ch7/score_train.csv")
test = pd.read_csv("./part2/ch7/score_test.csv")

print(train.head())
print(test.head(1))
print(train.info())

print(train.isnull().sum())
print(test.isnull().sum())

# Credit_Score
# 평가: f1-macro

target = train.pop('Credit_Score')

# 스케일링
from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
cols = train.select_dtypes(include=['int','float']).columns
train[cols] = scaler.fit_transform(train[cols])
test[cols] = scaler.transform(test[cols])

# 원핫 인코딩
train = pd.get_dummies(train)
test = pd.get_dummies(test)

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)
print(X_train.shape, X_val.shape, y_train.shape, y_val.shape)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

from sklearn.metrics import f1_score
f1 = f1_score(y_val, pred, average='macro')
print("f1-macro: ", f1)

pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred})
submit.to_csv('result.csv', index=False)