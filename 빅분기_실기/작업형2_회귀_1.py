import pandas as pd

train = pd.read_csv('./part2/ch8/flight_train.csv')
test = pd.read_csv('./part2/ch8/flight_test.csv')

print(train.head())
print(test.head(1))
print(train.info())
print(train.isnull().sum())

# flight 컬럼 제거 # object 타입 많음
print(train.describe())  # 스케일링 필요해보임
# RMSE 평가지표

train = train.drop('flight', axis=1)
test = test.drop('flight', axis=1)

target = train.pop('price')

# 스케일링
from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
cols = ['duration','days_left']
train[cols] = scaler.fit_transform(train[cols])
test[cols] = scaler.transform(test[cols])  # -> rmse:  4428.024873951547

# 인코딩
from sklearn.preprocessing import LabelEncoder
cols = train.select_dtypes(include='object').columns
for col in cols:
    le = LabelEncoder()
    train[col] = le.fit_transform(train[col])
    test[col] = le.transform(test[col])

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)

from sklearn.ensemble import RandomForestRegressor
rf = RandomForestRegressor(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

# RMSE
from sklearn.metrics import mean_squared_error
result = mean_squared_error(y_val, pred, squared=False)
print('rmse: ', result)   # 

pred = rf.predict(test)
submit = pd.DataFrame({'pred':pred})
submit.to_csv('result.csv',index=False)