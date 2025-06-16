import pandas as pd

train = pd.read_csv("./part2/ch7/glass_train.csv")
test = pd.read_csv("./part2/ch7/glass_test.csv")

print(train.head())
print(test.head(1))
print(train.info())

print(train.isnull().sum())
print(test.isnull().sum())

# f1-weighted
print(train.describe())  # 이상치 없어보임
target = train.pop('Type')

# from sklearn.preprocessing import StandardScaler
# scaler = StandardScaler()  # 무난한거
# train = scaler.fit_transform(train)
# test = scaler.transform(test)   # f1:  0.6119801766860591

from sklearn.model_selection import train_test_split
X_train, X_val, y_train, y_val = train_test_split(train, target, test_size=0.2, random_state=0)
print(X_train.shape, X_val.shape, y_train.shape, y_val.shape)

from sklearn.ensemble import RandomForestClassifier
rf = RandomForestClassifier(random_state=0)
rf.fit(X_train, y_train)
pred = rf.predict(X_val)

from sklearn.metrics import f1_score
f1 = f1_score(y_val, pred, average='weighted')
print('f1: ',f1)  # f1:  0.6119801766860591 스케일링 빼도 변화 없으니 빼자
