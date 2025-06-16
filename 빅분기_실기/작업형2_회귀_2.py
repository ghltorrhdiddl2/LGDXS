import pandas as pd

train = pd.read_csv('./part2/ch8/laptop_train.csv')
test = pd.read_csv('./part2/ch8/laptop_test.csv')

print(train.head())
print(test.head(1))
print(train.info())
print(train.isnull().sum())
print(test.isnull().sum())
print(train.describe())

# Series 컬럼 삭제
#평가 R2
train = train.drop('Series', axis=1)
test = test.drop('Series', axis=1)

# 결측치 처리(범주형)
c_cols = ['Model', 'Processor', 'Processor_Gen', 'Hard_Disk_Capacity', 'OS']
train[c_cols] = train[c_cols].fillna("X")
test[c_cols] = test[c_cols].fillna("X")

# 결측치 처리(수치형)
n_cols = ['RAM']
train[n_cols] = train[n_cols].fillna(-1)
test[n_cols] = test[n_cols].fillna(-1)

target = train.pop('Price')

combined = pd.concat([train, test])
combined_dummies = pd.get_dummies(combined)
train = combined_dummies[:len(train)]
test = combined_dummies[len(train):]

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)

from sklearn.ensemble import RandomForestRegressor
rf = RandomForestRegressor(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

from sklearn.metrics import r2_score
r2 = r2_score(y_val, pred)
print('r2: ', r2)

pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred})
submit.to_csv("result.csv", index=False)