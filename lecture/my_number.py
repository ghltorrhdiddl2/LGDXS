# 모듈 : 관련된 기능의 함수들을 묶어주는 개념 -> 응집도를 높일 수 있다
def is_odd(n): # 홀수 여부를 알려주는 함수
    # docstring
    """ 입력된 numberrk 홀수라면 True를 반환
        짝수라면 False를 반환"""
    return n % 2 != 0

def is_even(n): # 짝수 여부를 알려주는 함수
    return n % 2 == 0