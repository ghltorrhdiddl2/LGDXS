import pandas as pd

train = pd.read_csv('./part2/ch8/car_train.csv')
test = pd.read_csv('./part2/ch8/car_test.csv')

print(train.head())  # [5 rows x 17 columns]  # target = Price
print(test.head(1))  # [1 rows x 16 columns]  
print(train.info())
print(train.isnull().sum()) # 결측치 없음
print(test.isnull().sum())

print(train.describe()) # minmax 써보자

# 삭제할 컬럼 Manufacturer, Model 
# object 타입 많음 -> 라벨인코딩
# pred, RMSLE 평가

train.drop(['Manufacturer', 'Model'], axis=1)
test.drop(['Manufacturer', 'Model'], axis=1)

target = train.pop('Price')

from sklearn.preprocessing import LabelEncoder
combined = pd.concat([train, test])
cols = train.select_dtypes(include='object').columns
for col in cols:
    le = LabelEncoder()
    combined[col] = le.fit_transform(combined[col])

train = combined[:len(train)]
test = combined[len(train):]

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)

from sklearn.ensemble import RandomForestRegressor
rf = RandomForestRegressor(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

from sklearn.metrics import mean_squared_error
rmsle = mean_squared_error(y_val, pred)**0.5
print('rmsle: ',rmsle)

pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred})
submit.to_csv("result.csv", index=False)

